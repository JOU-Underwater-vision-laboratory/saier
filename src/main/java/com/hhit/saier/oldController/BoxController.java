package com.hhit.saier.oldController;


import com.hhit.common.annotation.Log;
import com.hhit.common.controller.BaseController;
import com.hhit.common.utils.PageUtils;
import com.hhit.common.utils.Query;
import com.hhit.common.utils.R;
import com.hhit.saier.domain.*;
import com.hhit.saier.service.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

/** 
 *
 * 
 * @Title: MedBoxController
 * @description:  
 * @author liujun
 * @create 2018-10-29
 * 
 */
@Controller
@Scope("prototype")
@RequestMapping("/saier/box")
public class BoxController extends BaseController {
    String prefix = "saier/box";
    @Autowired
    BoxService boxService;
    @Resource
    MedicineService medicineService;
    @Resource
    MedicinePlanService medicinePlanService;
    @Resource
    MedicinePlanRecordService medicinePlanRecordService;
    @Resource
    MedicineMiniService medicineMiniService;


    @RequiresPermissions("saier:box:box")
    @GetMapping()
    String box() {
        return prefix + "/box";
    }

    @RequiresPermissions("saier:box:box")
    @GetMapping("/list")
    @ResponseBody()
    PageUtils list(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        List<BoxDO> box = boxService.list(query);
        int total = boxService.count(query);
        PageUtils pageUtils = new PageUtils(box, total);
        return pageUtils;
    }

    @GetMapping("/m/list")
    @ResponseBody()
    R list() {
        List<BoxDO> box = boxService.list(new HashMap<>(16));

        return R.ok().put("box",box);
    }

    @GetMapping("/m/list/{boxid}")
    @ResponseBody()
    R listboxid(@PathVariable("boxid")String boxid) {
        HashMap<String, Object> objectObjectHashMap = new HashMap<>(16);
        objectObjectHashMap.put("boxid",boxid);
        List<BoxDO> box = boxService.list(objectObjectHashMap);

        return R.ok().put("box",box);
    }


    // TODO  返回药物信息列表
    @GetMapping("/m/query/{gid}/{bid}")
    @ResponseBody()
    R getByGridAndBoxID(@PathVariable("gid")Integer gridid, @PathVariable("bid")Integer bid) {
        List<BoxDO> box = boxService.findByGB1(gridid,bid);
        Iterator<BoxDO> iterator = box.iterator();
        List<BoxMedicineDO> list = new ArrayList<>(16);
        while (iterator.hasNext()){
            BoxDO next = iterator.next();
            MedicineDO medicineDO = medicineService.get(next.getMboxid());

            if (medicineDO==null){
                medicineDO = new MedicineDO();
                Map map = new HashMap();
                map.put("pzwh",next.getMboxid());
                List<MedicineMini> medicineMiniList = medicineMiniService.list(map);
                if (medicineMiniList.size()>0) {
                    medicineDO.setLicensenumber(medicineMiniList.get(0).getPzwh());
                    medicineDO.setMedicinename(medicineMiniList.get(0).getName());
                    medicineDO.setManufacturer(medicineMiniList.get(0).getManufacturer());
                }
//                medicineDO.setBarCode(medicineMiniList.get(0).getBarcode());
                list.add(new BoxMedicineDO(next,medicineDO));
            }else {
                list.add(new BoxMedicineDO(next,medicineDO));
            }
        }
        return R.ok().put("result",list);
    }

    @Log("添加药盒")
    @RequiresPermissions("saier:box:add")
    @GetMapping("/add")
    String add() {
        return prefix + "/add";
    }

    @Log("编辑药盒")
    @RequiresPermissions("saier:box:edit")
    @GetMapping("/edit/{id}")
    String edit(@PathVariable("id") Long id,
//            @PathVariable("boxid")Integer boxid,
//                @PathVariable("mboxid")Integer mboxid,
                Model model) {
        List<BoxDO> boxDO = boxService.get(id);
        model.addAttribute("box", boxDO.get(0));
        return prefix + "/edit";
    }

    @Log("保存药盒")
    @RequiresPermissions("saier:box:add")
    @PostMapping("/save")
    @ResponseBody()
    R save(BoxDO box) {
        box.setCreated(Math.toIntExact(getUserId()));
        box.setName(getUsername());
        if (boxService.save(box) > 0) {
            return R.ok();
        } else {
            return R.error(1, "保存失败");
        }
    }

    @Log("更新药盒")
    @PostMapping("/update")
    @ResponseBody()
    R update(BoxDO box) {
        if (boxService.update(box) > 0) {
            return R.ok();
        } else {
            return R.error(1, "保存失败");
        }
    }

    @Log("删除药盒")
    @RequiresPermissions("saier:box:remove")
    @PostMapping("/remove")
    @ResponseBody()
    R save(Long id) {

        if (boxService.remove(id) > 0) {
            return R.ok();
        } else {
            return R.error(1, "删除失败");
        }
    }

//    @RequiresPermissions("saier:box:batchRemove")
    @Log("批量删除药盒")
    @PostMapping("/batchRemove")
    @ResponseBody
    R batchRemove(@RequestParam("ids[]") Long[] ids) {
        int r = boxService.batchRemove(ids);
        if (r > 0) {
            return R.ok();
        }
        return R.error();
    }

    @Log("保存药盒")
    @GetMapping("/m/save/{grid}/{boxid}/{mboxid}/{rest}")
    @ResponseBody()
    R saveM(@PathVariable("grid") Integer grid,
            @PathVariable("boxid") Integer boxid,
            @PathVariable("mboxid") String mboxid,
            @PathVariable("rest") Integer rest ) {
        BoxDO box = new BoxDO(grid,boxid,mboxid,rest);
        if (box.getCreated()==null){
            if (getUser()!=null){
                box.setCreated(Math.toIntExact(getUserId()));
            }else {
                box.setCreated(1);
            }
        }
        if (box.getName()==null){
            if (getUser()!=null){
                box.setName(getUsername());
            }else {
                box.setName("admin");
            }
        }
        if (boxService.save(box) > 0) {
            return R.ok();
        } else {
            return R.error(1, "保存失败");
        }
    }


    /**
     *
     * @param grid
     * @param boxid
     * @param mboxid
     * @param bol  1 是吃，0 没吃
     * @return
     */
    @Log("更新药盒")
    @GetMapping("/m/update/{grid}/{boxid}/{mboxid}/{bol}")
    @ResponseBody()
    R updateM(@PathVariable("grid") Integer grid,
              @PathVariable("boxid") Integer boxid,
              @PathVariable("mboxid") String mboxid,
              @PathVariable("bol") Integer bol) {
        ;
        BoxDO gb = boxService.findByGB2(grid, boxid, mboxid);
        // 获取计划中的每次服用的剂量
        Map<String,Object> query = new HashMap();

        query.put("medlicense",mboxid);
        query.put("medbox",boxid);
        List<MedicinePlanDO> planDOS = medicinePlanService.list(query);
        int n = 0;
        if (planDOS.size()>0){
            String num = planDOS.get(0).getNum();
            try {
                n = Integer.parseInt(num);
            }catch (Exception e){
                return R.error(1,"剂量包含非数字，无法判定");
            }
        }
        gb.setRest(gb.getRest()-n);
        if (boxService.updateM(gb) > 0) {
//             随机判断是否是按时吃药
            medicinePlanRecordService.save(new MedicinePlanRecordDO(new Date(),String.valueOf(Math.random()>0.5?1:0),planDOS.get(0).getCreated(),planDOS.get(0).getName()));
            return R.ok();
        } else {
            return R.error(1, "保存失败");
        }
    }

    // TODO  删除一行， gridid, boxid, mboxid
    @Log("删除药盒")
    @GetMapping("/m/removemed/{boxId}/{mboxid}")
    @ResponseBody()
    R saveM(@PathVariable("boxId") Integer boxId,@PathVariable("mboxid") String mboxid) {

        if (boxService.removeM(boxId,mboxid) > 0) {
            return R.ok();
        } else {
            return R.error(1, "删除失败");
        }
    }

    @Log("批量删除药盒")
    @GetMapping("/m/batchRemove")
    @ResponseBody
    R batchRemoveM(@RequestParam("ids[]") Long[] ids) {
        int r = boxService.batchRemove(ids);
        if (r > 0) {
            return R.ok();
        }
        return R.error();
    }
}
