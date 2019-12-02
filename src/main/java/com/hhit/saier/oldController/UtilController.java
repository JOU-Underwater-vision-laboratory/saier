package com.hhit.saier.oldController;

import com.hhit.common.annotation.Log;
import com.hhit.common.controller.BaseController;
import com.hhit.common.utils.R;
import com.hhit.saier.domain.MedicinePlanDO;
import com.hhit.saier.service.MedicineMiniService;
import com.hhit.saier.service.MedicinePlanService;
import org.apache.commons.collections.map.ListOrderedMap;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.*;

@Controller
@RequestMapping("/util")
public class UtilController extends BaseController {
    @Resource
    private MedicinePlanService planService;
    @Resource
    private MedicineMiniService medicineMiniService;

    @GetMapping("/now")
    @ResponseBody
    public R time(){
        Calendar now = Calendar.getInstance();
        Map map = new ListOrderedMap();
            map.put("year",now.get(Calendar.YEAR));
            map.put("month", now.get(Calendar.MONTH) + 1);
            map.put("day",now.get(Calendar.DAY_OF_MONTH));
            map.put("hour",now.get(Calendar.HOUR_OF_DAY));
            map.put("minute",now.get(Calendar.MINUTE));
            map.put("second", now.get(Calendar.SECOND));
            map.put("now",now.getTime());
            map.put("timeZone",now.getTimeZone());

            return R.ok().put("result",map);
    }

    @Log("查看图表")
    @RequiresPermissions("saier:graph:graph")
    @GetMapping("/graph")
    String jumpGraph() {
        return   "saier/graph";
    }

    @Log("查看图表")
    @RequiresPermissions("saier:graph:show")
    @PostMapping("/show/graph")
    @ResponseBody
    R showGraph() {
        Map<String,Object> map = new HashMap<>(16);
        if (getUser()!=null){
            map.put("created",getUserId());
            map.put(" name",getUsername());
        }else {
            map.put("created",1);
            map.put(" name","admin");
        }

        List<MedicinePlanDO> list = planService.list(map);

        // 横轴 获取适用的药物名称的list
        Set<String> mednameSet = new LinkedHashSet<>();
        Map<String,Object> medLicenseMap = new HashMap<>();
        List<GraphDO> grapthList = new ArrayList<>();
        ListIterator<GraphDO> graphDOListIterator = grapthList.listIterator();
        for (MedicinePlanDO planDO : list) {
            Map<String,Object> query = new HashMap();
            query.put("pzwh",planDO.getMedlicense());
            String name = medicineMiniService.list(query).get(0).getName();
            mednameSet.add(name);
            medLicenseMap.put(name,planDO);
        }
        for (String name:mednameSet) {
            int count = Collections.frequency(list, medLicenseMap.get(name));
            grapthList.add(new GraphDO(name,count));

        }


        return Objects.requireNonNull(R.ok().put("legend", mednameSet)).put("gvalue",grapthList);
    }
}

class GraphDO {
    private String name;
    private Integer value;

    GraphDO(){
    }
    GraphDO(String name,Integer value){
        this.name = name;
        this.value = value;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
