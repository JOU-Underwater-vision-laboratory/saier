package com.hhit.anali.controller;

import com.alibaba.fastjson.JSON;
import com.hhit.Application;
import com.hhit.anali.pojo.Bld;
import com.hhit.anali.pojo.HealthSaveDO;
import com.hhit.anali.util.QueryUtil;
import com.hhit.common.redis.shiro.RedisManager;
import com.hhit.common.utils.R;
import com.hhit.saier.domain.MedicinePlanDO;
import com.hhit.saier.service.BoxService;
import com.hhit.saier.service.MedicinePlanService;
import com.hhit.saier.service.MedicineService;
import com.hhit.saier.service.impl.MedicinePanServiceImpl;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.map.ListOrderedMap;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.*;

@Controller
@RequestMapping("/test/health")
public class HealthController {

    private final String INDEX_DRUG="health_drug";
    private final String INDEX_DISEASE="health_disease";
    private final String TYPE_DRUG = "drug";
    private final String TYPE_DISEASE = "disease";

    List<Float> temSet = new ArrayList<>();
    List<Integer> spo2Set = new ArrayList<>();
    List<Integer> breathSet = new ArrayList<>();



    @Resource private QueryUtil queryUtil;
    @Resource private MedicinePlanService planService;
    @Resource private MedicineService medicineService;
    @Resource  private RedisManager redisManager;

    @RequestMapping("/analyze/{name}")
    @ResponseBody
    public Map testPrefixQuery(@PathVariable("name") String name) {
//        boxService.get()
        // 根据用户信息，获得药物list。
        Map<String,Object> query  = new HashMap<>();
        query.put("name",name);
        List<String> dos = planService.listName(query);
        Set<String> cnNames = new HashSet<>();
        for (String plan:dos){
            String medName=plan;
            if (plan.indexOf("(")>0){
                medName = plan.substring(0,plan.indexOf("("));
            }
            cnNames.add(medName);
        }


        StringBuilder indcation = new StringBuilder();
        for (String cnName: cnNames
             ) {
            indcation.append(medicineService.findDrug(cnName.trim()).get(0));
        }

        // 根据药物信息查询drug表，获得适应症
        String analyze = queryUtil.analy(indcation.toString());
        QueryBuilder queryBuilder = QueryBuilders.matchQuery("treatment_brief", analyze);
        SearchResponse response = queryUtil.searchFunction(queryBuilder,INDEX_DISEASE,TYPE_DISEASE);
        SearchHits searchHits = queryUtil.testResponse(response);
        Map map = new TreeMap();
        int count=1;
        for (SearchHit searchHit : searchHits) {
            Map<String, Object> source = searchHit.getSource();
            float score = searchHit.getScore();
            map.put("result" + count, new HitsComparable(source, score));
            count++;
            if (count>=20){
                break;
            }
        }
        ExceptionHealth exceptionHealth = catchByHealthData(name);
        map.put("exceptionHealth",exceptionHealth);
        return map;
    }

    private ExceptionHealth catchByHealthData(String name) {
        List<?> list = redisManager.getList("saier_health_date_" + name);
        int count = list.size();
        Random random = new Random();
        int terms = count/2;

        for (int i=0;i<=terms;i++){
            LinkedHashMap map = (LinkedHashMap) list.get(random.nextInt(count));
            temSet.add((Float) ((LinkedHashMap)map.get("tem")).get("tem"));
            spo2Set.add((Integer) ((LinkedHashMap)map.get("oxy")).get("oxy"));
            breathSet.add((Integer)  ((LinkedHashMap)map.get("breath")).get("breath"));
        }

        int tem = caculateTem(temSet);
        int spo = caculateSpoSet(spo2Set);
        int breath = caculateBreath(breathSet);
        ExceptionHealth exceptionHealth = new ExceptionHealth();
        if (tem!=0){
            exceptionHealth.setA("您体温有发烧现象请留意");
        }
        if (spo!=0){
            exceptionHealth.setB("您血氧有过低现象，请留意低血氧症");
        }
        if (breath!=1){
            if (breath==0){
                exceptionHealth.setC("呼吸频率过低！！");
            }else if (breath==1){
                exceptionHealth.setC("呼吸频率过低,请注意调节呼吸，若一直如此请到正规医院查看！");
            }else {
                exceptionHealth.setC("呼吸频率异常");
            }
        }
        return exceptionHealth;
    }

    /**
     *  呼吸异常
     *  0,1,2
     * @param breathSet
     */
    private int caculateBreath(List<Integer> breathSet) {
        int normal = 0;
        int tooLarge = 0;
        int tooMini = 0;
        for (Integer breath : breathSet) {
            if(breath==null){
                continue;
            }
            if (breath<=20&&breath>=14){
                normal++;
            }else if (breath<14){
                tooMini++;
            }else if (breath>20){
                tooLarge++;
            }else {

            }
        }
        if (normal>2*(tooLarge+tooMini)){
            if (tooLarge>tooMini){
                return 2;
            }else {
                return 0;
            }
        }else {
            return 1;
        }

    }

    /**
     *  查看血氧是否有异常
     *   0,1 正，异
     * @param spo2Set
     */
    private Integer caculateSpoSet(List<Integer> spo2Set) {
        int normal =0;
        for (Integer spo2:spo2Set
             ) {
            if(spo2==null){
                continue;
            }
            if (spo2>=95){
                normal++;
            }
        }
        int size = spo2Set.size();
        if (normal<size/3){
//            不正常
            return 1;
        }
        return 0;
    }

    /**
     *  查看是否体温有异常
     *  0，1
     * @param temSet
     */
    private int caculateTem(List<Float> temSet) {
        int lowFever = 0 ;
        int middleFever = 0;
        int highFever = 0;
        for (Float tem: temSet
             ) {
            if(tem==null){
                continue;
            }
            if (tem<=38&&tem>37.5){
                lowFever++;
            }else if (tem<=38.5&&tem>38){
                middleFever++;
            }else if (tem>38.5){
                highFever++;
            }
        }
        int size = temSet.size();
        if (size/5 > (lowFever+middleFever+highFever) ){
            return 1;
        }else {
            return 0;
        }
    }


    @RequestMapping("/health/save/{name}")
    @ResponseBody
    public R  growthData(@RequestBody Object healthSave, @PathVariable("name") String name){
        ArrayList arrayList = (ArrayList) ( (ArrayList) healthSave).get(0);
        try {
            redisManager.setList("saier_health_date_"+name, arrayList);
        }catch (Exception e){
            e.printStackTrace();
            return R.error(1,"store error");
        }

        return R.ok();
    }
}

class HitsComparable implements Comparable<HitsComparable>{
    private Map<String, Object> source;
    private float score;

    public HitsComparable() {
    }

    public HitsComparable(Map<String, Object> source, float score) {
        this.source = source;
        this.score = score;
    }

    public Map<String, Object> getSource() {
        return source;
    }

    public void setSource(Map<String, Object> source) {
        this.source = source;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    @Override
    public int compareTo(HitsComparable o) {
        return Float.compare(this.getScore(), o.getScore());
    }
}

class  ExceptionHealth{
    private  String a ;
    private String b;
    private String c;

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }
}
