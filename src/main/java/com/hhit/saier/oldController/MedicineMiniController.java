package com.hhit.saier.oldController;


import com.hhit.common.controller.BaseController;
import com.hhit.common.utils.PageUtils;
import com.hhit.common.utils.Query;
import com.hhit.common.utils.R;
import com.hhit.saier.domain.MedicineMini;
import com.hhit.saier.service.BoxService;
import com.hhit.saier.service.MedBoxMiniService;
import com.hhit.saier.service.MedicineMiniService;
import com.hhit.saier.service.MedicineService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/** 
 *
 * 
 * @Title: MedicineController
 * @description:  
 * @author liujun
 * @create 2018-10-29
 * 
 */
@Controller
@Scope("prototype")
@RequestMapping("/saier/medicinemini")
public class MedicineMiniController extends BaseController {
    String prefix = "saier/medicinemini";
    @Resource
    private MedicineMiniService medicineminiService;
    @Resource
    private MedicineService medicineService;
    @Resource
    private BoxService boxService;
    @Resource
    MedBoxMiniService miniService;
    @RequiresPermissions("saier:medicinemini:medicinemini")
    @GetMapping()
    String medicinemini() {
        return prefix + "/medicinemini";
    }

//    @RequiresPermissions("saier:medicinemini:medicinemini")
    @GetMapping("/list")
    @ResponseBody
    public PageUtils list(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        List<MedicineMini> medicineminis = medicineminiService.list(query);
        int total = medicineminiService.count(query);
        PageUtils pageUtils = new PageUtils(medicineminis, total);
        return pageUtils;
    }

    /**
     *  查1000
     * @return
     */
    @GetMapping("/m/list/thousand")
    @ResponseBody
    R list() {
        Map map = new HashMap<>(16);
        map.put("offset",0);
        map.put("limit",1000);
        List<MedicineMini> medicineminis = medicineminiService.list(map);
        return R.ok().put("medicineminimini",medicineminis);
    }

    /**
     *  分页查询
     * @param offset
     * @param limit
     * @return
     */
    @GetMapping("/m/list/{offset}/{limit}")
    @ResponseBody
    R listWithOffset(@PathVariable("offset") Integer offset,@PathVariable("limit") Integer limit) {
        Map map = new HashMap<>(16);
        map.put("offset",offset);
        map.put("limit",limit);
        List<MedicineMini> medicineminis = medicineminiService.list(map);
        return R.ok().put("medicineminimini",medicineminis);
    }

    @GetMapping("/m/query/{barcode}")
    @ResponseBody
    R listAllInfo(@PathVariable("barcode")String barcode) {
        MedicineMini medicineminiDO = medicineminiService.getByBarCode(barcode);
        Map map = new HashMap(16);
        map.put("mboxid",medicineminiDO.getPzwh());
        return Objects.requireNonNull(
                Objects.requireNonNull(Objects.requireNonNull(R.ok()
                        .put("medicinemini", medicineminiDO))
                        .put("medicine", medicineService.get(medicineminiDO.getPzwh())))
                        .put("box",boxService.list(map))
                );
    }

    @RequiresPermissions("saier:medicinemini:edit")
    @GetMapping("/edit/{id}")
    String edit(@PathVariable("id") String id, Model model) {
        MedicineMini MedicineMini = medicineminiService.get(id);
        model.addAttribute("medicinemini", MedicineMini);
        return prefix + "/edit";
    }


    @RequiresPermissions("saier:medicinemini:add")
    @PostMapping("/save")
    @ResponseBody()
    R save(MedicineMini medicinemini) {
        if (medicineminiService.save(medicinemini) > 0) {
            return R.ok();
        } else {
            return R.error(1, "保存失败");
        }
    }


    @RequiresPermissions("saier:medicinemini:edit")
    @PostMapping("/update")
    @ResponseBody()
    R update(MedicineMini medicinemini) {
        if (medicineminiService.update(medicinemini) > 0) {
            return R.ok();
        } else {
            return R.error(1, "保存失败");
        }
    }


    @RequiresPermissions("saier:medicinemini:remove")
    @PostMapping("/remove/{license}")
    @ResponseBody()
    R remove(@PathVariable("license") String licensenumber) {

        if (medicineminiService.remove(licensenumber) > 0) {
            return R.ok();
        } else {
            return R.error(1, "删除失败");
        }
    }

    @RequiresPermissions("saier:medicinemini:batchRemove")
    @PostMapping("/batchRemove")
    @ResponseBody
    R batchRemove(@RequestParam("licensenumbers[]") String[] licensenumbers) {
        int r = medicineminiService.batchRemove(licensenumbers);
        if (r > 0) {
            return R.ok();
        }
        return R.error();
    }


    @ResponseBody
    @RequestMapping(value = "/pic/update", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    R edit(@RequestParam("avatar_file") MultipartFile file,String licensenumber, String avatar_data,HttpServletRequest request) {

        String fileName;
        fileName = file.getOriginalFilename();

        if(fileName==null||fileName==""){
            return R.error();
        }
        try {
            fileName = medicineminiService.uploadPic(file, avatar_data, licensenumber);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(0,"剪裁错误");
        }

        return R.ok();
    }
}
