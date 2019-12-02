package com.hhit.saier.oldController;


import com.hhit.common.annotation.Log;
import com.hhit.common.controller.BaseController;
import com.hhit.common.utils.PageUtils;
import com.hhit.common.utils.Query;
import com.hhit.common.utils.R;
import com.hhit.saier.domain.*;
import com.hhit.saier.service.MedicineMiniService;
import com.hhit.saier.service.MedicinePlanRecordService;
import com.hhit.saier.service.MedicinePlanService;
import com.hhit.saier.service.MedicineService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

/** 
 *
 * 
 * @Title: MedicinePlanController
 * @description:  
 * @author liujun
 * @create 2018-10-29
 * 
 */
@Controller
//@Scope("prototype")
@RequestMapping("/saier/plan")
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes= Application.class)
public class MedicinePlanController extends BaseController {
    String prefix = "saier/plan";
    @Resource
    private MedicinePlanService planService;
    @Resource
    private MedicinePlanRecordService planRecordService;
    @Resource
    private MedicineService medicineService;
    @Resource
    private MedicineMiniService medicineMiniService;

    @RequiresPermissions("saier:plan:plan")
    @GetMapping()
    String plan() {
        System.out.println("--------------------------");
        return prefix + "/plan";
    }

    @RequiresPermissions("saier:plan:plan")
    @GetMapping("/list")
    @ResponseBody()
    PageUtils list(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        List<MedicinePlanDO> plans = planService.list(query);
        if (plans.contains(null)){
            plans.clear();
        }
        int total = planService.count(query);
        return new PageUtils(plans, total);
    }

    @GetMapping("/m/list/history/year")
    @ResponseBody

    public R listHistoryYear() {
        Map query  = new HashMap(1);
        List list = planRecordService.countYear(query);
        int count = planRecordService.count(query);
        Map map1 = new HashMap();
        for (int i = 0; i <=list.size()-1; i++) {
            map1.put(((Map<? extends String, ?>) list.get(i)).get("year"),
                    ((Map<? extends String, ?>) list.get(i)).get("count(1)"));
        }

        return R.ok().put("data",map1).put("total",count);
    }

    @GetMapping("/m/list/history/month")
    @ResponseBody
//    @Test
    public R listHistoryMonth() {
        Map query  = new HashMap(1);
        List list = planRecordService.countMonth(query);
        int count = planRecordService.count(query);
        Map map1 = new HashMap();
        for (int i = 0; i <=list.size()-1; i++) {
            Map<? extends String, ?> item = (Map<? extends String, ?>) list.get(i);
            map1.put(item.get("month"),
                    item.get("count(1)"));
        }
        System.out.println(map1);

        return R.ok().put("data",map1).put("total",count);
    }

    @GetMapping("/m/list/history/day")
    @ResponseBody

    public R listHistoryDay() {
        Map query  = new HashMap(1);
        List list = planRecordService.countDay(query);
        int count = planRecordService.count(query);
        Map map1 = new HashMap();
        for (int i = 0; i <=list.size()-1; i++) {
            map1.put(((Map<? extends String, ?>) list.get(i)).get("day"),
                    ((Map<? extends String, ?>) list.get(i)).get("count(1)"));
        }

        return R.ok().put("data",map1).put("total",count);
    }


    @GetMapping("/m/list/history")
    @ResponseBody
    public R listHistory() {

        List<MedicinePlanDO> plans = planService.list(new HashMap<>(1));
        if (plans.contains(null)){
            plans.clear();
        }
        List<ChildItemPlan> childItemPlans = new ArrayList<>();
        for (MedicinePlanDO plan:plans) {
            HashMap<String, Object> map1 = new HashMap<>();
            map1.put("pzwh",plan.getMedlicense());
            List<MedicineMini> medicineMini = medicineMiniService.list(map1);
            plan.setMedlicense(medicineMini.get(0).getName());
            childItemPlans.add(new ChildItemPlan().build(plan));
        }
        List<GroupItemPlan> groupItemPlans = new ArrayList<>();
        int bol = 0;
        for (ChildItemPlan citem: childItemPlans) {
            bol=0;
            if (groupItemPlans.isEmpty()){
                GroupItemPlan groupItemPlan = new GroupItemPlan(citem.getGroup());
                groupItemPlan.build(citem);
                groupItemPlans.add(groupItemPlan);
            }
            for (GroupItemPlan plan:groupItemPlans) {
                if (plan.getName().equals(citem.getGroup())){
                    plan.build(citem);
                    bol=1;
                }
            }
            if (bol==0){
                GroupItemPlan groupItemPlan = new GroupItemPlan(citem.getGroup());
                groupItemPlan.build(citem);
                groupItemPlans.add(groupItemPlan);
            }
        }
        return R.ok().put("items",groupItemPlans);
    }


    @GetMapping("/m/list/plan")
    @ResponseBody()
    R list() {
        int count=0;
        HashMap map = new HashMap<>(16);
        List<MedicinePlanDO> plans = planService.list(map);
        List<MedicinePlanVO1> planVOS = new ArrayList<>();
        for (MedicinePlanDO planDO : plans) {
            String[] times = planDO.getMedicinetime().split(",");
            List medTimeList = new ArrayList<MedTime>();
            String[] spliteTime;
            for (String time: times) {
                spliteTime = time.split(":");
                medTimeList.add(new MedTime(spliteTime[0],spliteTime[1]));
                count++;
            }
            MedicineDO medicineDO = medicineService.get(planDO.getMedlicense());
            HashMap<String, Object> map1 = new HashMap<>();
            map1.put("pzwh",planDO.getMedlicense());
            List<MedicineMini> medicineMini = medicineMiniService.list(map1);
            if (medicineDO==null){
                medicineDO = new MedicineDO();
            }
            medicineDO.build(medicineMini.get(0));
            planVOS.add(new MedicinePlanVO1(planDO.getId(), planDO.getMedlicense(),
                    planDO.getMedbox(), planDO.getTimes(), medTimeList, planDO.getNum(),medicineDO,planDO.getCtime()));
            count=0;
        }
        return R.ok().put("plan",planVOS);
    }

    @GetMapping("/m/list/plan2/{num}")
    @ResponseBody()
    R list2(@PathVariable("num") Integer num) {
        int count=0;
        HashMap map = new HashMap<>(16);
        List<MedicinePlanDO> plans = planService.list(map);
        List<MedicinePlanVO> planVOS = new ArrayList<>();
        for (MedicinePlanDO planDO : plans) {
            String[] times = planDO.getMedicinetime().split(",");
            HashMap<String ,MedTime> medTimeList = new HashMap();
            String[] spliteTime;
            for (String time: times) {
                /*
                -----------  11/30 20:53
                    此处时间中的冒号格式不确定，会导致错误
                 */
                spliteTime = time.split(":");
                if(spliteTime.length<2){
                    spliteTime = time.split("：");
                }
                medTimeList.put("time"+count,new MedTime(spliteTime[0],spliteTime[1]));
                count++;
            }
            MedicineDO medicineDO = medicineService.get(planDO.getMedlicense());
            HashMap<String, Object> map1 = new HashMap<>();
            map1.put("pzwh",planDO.getMedlicense());
            List<MedicineMini> medicineMini = medicineMiniService.list(map1);
            if (medicineDO==null){
                medicineDO = new MedicineDO();
            }
            medicineDO.build(medicineMini.get(0));
            medicineDO.buildEmpty(medicineDO);
            planVOS.add(new MedicinePlanVO(planDO.getId(),planDO.getMedlicense(),
                    planDO.getMedbox(), planDO.getTimes(), medTimeList, planDO.getNum(),medicineDO,planDO.getCtime(),planDO.getCreated(),planDO.getName()));
            count=0;
        }
        if (planVOS.size()>=num){
            return R.ok().put("plan",planVOS.get(num-1));
        }else {
            return R.ok();
        }
    }

    @GetMapping("/m/list/{medlicense}")
    @ResponseBody()
    R list(@PathVariable("medlicense") String param) {
        int count=0;
        HashMap map = new HashMap<>(16);
        medicineService.findByName(param);
        map.put("medlicense", param);
        List<MedicinePlanDO> plans = planService.list(map);
        List<MedicinePlanVO> planVOS = new ArrayList<>();
        for (MedicinePlanDO planDO : plans) {
            String[] times = planDO.getMedicinetime().split(",");
            HashMap<String ,MedTime> medTimeList = new HashMap();
            String[] spliteTime;
            for (String time: times) {
                spliteTime = time.split(":");
                medTimeList.put("time"+count,new MedTime(spliteTime[0],spliteTime[1]));
                count++;
            }

            planVOS.add(new MedicinePlanVO(planDO.getId(), planDO.getMedlicense(), planDO.getMedbox(), planDO.getTimes(), medTimeList, planDO.getNum()));
            count=0;
        }

        return R.ok().put("plan",planVOS);
    }

    @GetMapping("/m/checkPlan/{boxid}/{medlicense}")
    @ResponseBody()
    R checkPlan(@PathVariable("boxid")Integer boxid ,@PathVariable("medlicense") String param) {
        HashMap map = new HashMap<>(16);
        map.put("medlicense", param);
        map.put("boxid",boxid);
        List plans = planService.list(map);
        if (plans.size()>0){
            return R.ok().put("plan",plans.get(0));
        }else {
            return R.error(1,"未匹配到信息");
        }
    }

    @GetMapping("/m/checkPlan/{boxid}")
    @ResponseBody()
    R listPlan(@PathVariable("boxid")Integer boxid ) {
        HashMap map = new HashMap<>(16);
        map.put("boxid",boxid);
        List plans = planService.list(map);
        if (plans.size()>0){
            return R.ok().put("plan",plans.get(0));
        }else {
            return R.error(1,"未匹配到信息");
        }
    }

    @GetMapping("/m/plan/{boxid}")
    @ResponseBody()
    R listPlan2(@PathVariable("boxid")Integer boxid ) {
        int count=0;
        HashMap map = new HashMap<>(16);
        map.put("boxid",boxid);
        List<MedicinePlanDO> plans = planService.list(map);
        List<MedicinePlanVO> planVOS = new ArrayList<>();
        for (MedicinePlanDO planDO : plans) {
            String[] times = planDO.getMedicinetime().split(",");
            HashMap<String ,MedTime> medTimeList = new HashMap();
            String[] spliteTime;
            for (String time: times) {
                spliteTime = time.split(":");
                medTimeList.put("time"+count,new MedTime(spliteTime[0],spliteTime[1]));
                count++;
            }
            MedicineDO medicineDO = medicineService.get(planDO.getMedlicense());
            HashMap<String, Object> map1 = new HashMap<>();
            map1.put("pzwh",planDO.getMedlicense());
            List<MedicineMini> medicineMini = medicineMiniService.list(map1);
            if (medicineDO==null){
                medicineDO = new MedicineDO();
            }
            medicineDO.build(medicineMini.get(0));
            planVOS.add(new MedicinePlanVO(planDO.getId(), planDO.getMedlicense(),
                    planDO.getMedbox(), planDO.getTimes(), medTimeList, planDO.getNum(),medicineDO,planDO.getCtime()));
            count=0;
        }
        return R.ok().put("plan",planVOS);
    }

    @Log("添加")
    @RequiresPermissions("saier:plan:add")
    @GetMapping("/add")
    String add() {
        return prefix + "/add";
    }

    @Log("编辑")
    @RequiresPermissions("saier:plan:edit")
    @GetMapping("/edit/{id}")
    String edit(@PathVariable("id") Long id, Model model) {
        MedicinePlanDO MedicinePlanDO = planService.get(id);
        model.addAttribute("plan", MedicinePlanDO);
        return prefix + "/edit";
    }

    @Log("编辑")
    @GetMapping("/m/edit/{id}")
    String editM(@PathVariable("id") Long id, Model model) {
        MedicinePlanDO MedicinePlanDO = planService.get(id);
        model.addAttribute("plan", MedicinePlanDO);
        return prefix + "/edit";
    }

    @Log("保存")
    @RequiresPermissions("saier:plan:add")
    @PostMapping("/save")
    @ResponseBody()
    R save(MedicinePlanDO plan) {

        plan.setCreated(Math.toIntExact(getUserId()));
        plan.setName(getUsername());
        if (planService.save(plan) > 0) {
            return R.ok();
        } else {
            return R.error(1, "保存失败");
        }
    }
    @Log("保存")
    @GetMapping("/m/save/{term}/{medlicense}/{medbox}/{times}/{medicinetime}/{num}")
    @ResponseBody()
    R saveM(
            @PathVariable("term") String term,
            @PathVariable("medlicense") String medlicense,
            @PathVariable("medbox")Integer medbox,
            @PathVariable("times")String times,
            @PathVariable("medicinetime")String medicinetime,
            @PathVariable("num")String num) {
        MedicinePlanDO plan = new MedicinePlanDO(medlicense,medbox,times,medicinetime,num);
        plan.setTerm(term);
        plan.setCtime(new Date());
        if (plan.getCreated()==null){
            if (getUser()!=null){
                plan.setCreated(Math.toIntExact(getUserId()));
            }else {
                plan.setCreated(1);
            }
        }
        if (plan.getName()==null){
            if (getUser()!=null){
                plan.setName(getUsername());
            }else {
                plan.setName("admin");
            }
        }
        if (planService.save(plan) > 0) {
            return R.ok();
        } else {
            return R.error(1, "保存失败");
        }
    }

    @Log("更新")
      @RequiresPermissions("saier:plan:edit")
    @PostMapping("/update")
    @ResponseBody()
    R update(MedicinePlanDO plan) {
        if (planService.update(plan) > 0) {
            return R.ok();
        } else {
            return R.error(1, "保存失败");
        }
    }


    @Log("更新")
    @GetMapping("/m/update/{medlicense}/{medbox}/{times}/{medicinetime}/{num}")
    @ResponseBody()
    R updateM(
              @PathVariable("medlicense") String medlicense,
              @PathVariable("medbox")Integer medbox,
              @PathVariable("times")String times,
              @PathVariable("medicinetime")String medicinetime,
              @PathVariable("num")String num) {
        MedicinePlanDO plan = new MedicinePlanDO("国药准字"+medlicense,medbox,times,medicinetime,num);

        if (planService.update(plan) > 0) {
            return R.ok();
        } else {
            return R.error(1, "保存失败");
        }
    }

    @Log("删除")
      @RequiresPermissions("saier:plan:remove")
    @PostMapping("/remove")
    @ResponseBody()
    R save(Long id) {
        
        if (planService.remove(id) > 0) {
            return R.ok();
        } else {
            return R.error(1, "删除失败");
        }
    }

    @Log("删除")
    @GetMapping("/m/remove/{medlicense}/{boxid}")
    @ResponseBody()
    R removeM(@PathVariable("medlicense") String medlicense,@PathVariable("boxid") Integer boxid) {

        if (planService.remove(medlicense,boxid) > 0) {
            return R.ok();
        } else {
            return R.error(1, "删除失败");
        }
    }

      @RequiresPermissions("saier:plan:batchRemove")
    @Log("批量删除药品")
    @PostMapping("/batchRemove")
    @ResponseBody
    R batchRemove(@RequestParam("ids[]") Long[] ids) {
        int r = planService.batchRemove(ids);
        if (r > 0) {
            return R.ok();
        }
        return R.error();
    }

    @Log("批量删除药品")
    @GetMapping("/m/batchRemove")
    @ResponseBody
    R batchRemoveM(@RequestParam("ids[]") Long[] ids) {
        int r = planService.batchRemove(ids);
        if (r > 0) {
            return R.ok();
        }
        return R.error();
    }
}
