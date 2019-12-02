package com.hhit.saier.controller;

import com.hhit.common.annotation.Log;
import com.hhit.common.config.Constant;
import com.hhit.common.controller.BaseController;
import com.hhit.common.domain.DictDO;
import com.hhit.common.service.DictService;
import com.hhit.common.utils.PageUtils;
import com.hhit.common.utils.Query;
import com.hhit.common.utils.R;
import com.hhit.oa.domain.NotifyDO;
import com.hhit.oa.domain.NotifyRecordDO;
import com.hhit.oa.service.NotifyRecordService;
import com.hhit.oa.service.NotifyService;
import com.hhit.saier.domain.MedicineDO;
import com.hhit.saier.service.BoxService;
import com.hhit.saier.service.MedBoxMiniService;
import com.hhit.saier.service.MedicineService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping("/saier/medicine")
public class SaierMediaController extends BaseController {

    @Resource
    MedBoxMiniService miniService;


    @GetMapping()
    @RequiresPermissions("saier:medicine:medicine")
    String medicine() {
        return "saier/medicine/medicine";
    }

    String prefix = "saier/medicine";
    @Resource
    private MedicineService medicineService;
    @Resource
    private BoxService boxService;

    @RequiresPermissions("saier:medicine:medicine")
    @GetMapping("/list")
    @ResponseBody
    public PageUtils list(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        List<MedicineDO> medicines = medicineService.list(query);
        int total = medicineService.count(query);
        PageUtils pageUtils = new PageUtils(medicines, total);
        return pageUtils;
    }

    @GetMapping("/updatePic/{licensenumber}")
    @RequiresPermissions("saier:medicine:edit")
    String updatePic(@PathVariable("licensenumber") String licensenumber, Model model) {
        MedicineDO medicineDO = medicineService.get(licensenumber);
        model.addAttribute("medicine", medicineDO);
        return "saier/medicine/updatePic";
    }

    @GetMapping("/m/list")
    @ResponseBody
    R list() {
        List<MedicineDO> medicines = medicineService.list(new HashMap<>(16));
        return R.ok().put("medicine",medicines);
    }

    @GetMapping("/m/query/{license}")
    @ResponseBody
    R listAllInfo(@PathVariable("license")String license) {
        MedicineDO medicineDO = medicineService.get(license);

        return Objects.requireNonNull(R.ok().put("medicine", medicineService.get(license))).put("box",boxService.findByLicense(license));
    }

    @PostMapping("/m/querybar/{barcode}")
    @ResponseBody
    R listMedicine(@PathVariable("barcode")String barcode) {
        MedicineDO medicineDO = medicineService.getByBarCode(barcode);

        return Objects.requireNonNull(R.ok().put("medicine", medicineService.get(barcode))).put("box",boxService.findByLicense(medicineDO.getLicensenumber()));
    }

    @Log("添加药品")
    @RequiresPermissions("saier:medicine:add")
    @GetMapping("/add")
    String add() {
        return prefix + "/add";
    }


    @Log("编辑药品")
    @RequiresPermissions("saier:medicine:edit")
    @GetMapping("/edit/{id}")
    String edit(@PathVariable("id") String id, Model model) {
        MedicineDO MedicineDO = medicineService.get(id);
        model.addAttribute("medicine", MedicineDO);
        return prefix + "/edit";
    }

    @Log("保存药品")
    @RequiresPermissions("saier:medicine:add")
    @PostMapping("/save")
    @ResponseBody()
    R save(MedicineDO medicine) {
        if (medicineService.save(medicine) > 0) {
            return R.ok();
        } else {
            return R.error(1, "保存失败");
        }
    }

    @Log("更新药品")
    @RequiresPermissions("saier:medicine:edit")
    @PostMapping("/update")
    @ResponseBody()
    R update(MedicineDO medicine) {
        if (medicineService.update(medicine) > 0) {
            return R.ok();
        } else {
            return R.error(1, "保存失败");
        }
    }

    @Log("删除药品")
    @RequiresPermissions("saier:medicine:remove")
    @PostMapping("/remove")
    @ResponseBody()
    R remove(String licensenumber) {

        if (medicineService.remove(licensenumber) > 0) {
            return R.ok();
        } else {
            return R.error(1, "删除失败");
        }
    }

    @RequiresPermissions("saier:medicine:batchRemove")
    @Log("批量删除药品")
    @PostMapping("/batchRemove")
    @ResponseBody
    R batchRemove(@RequestParam("licensenumbers[]") String[] licensenumbers) {
        int r = medicineService.batchRemove(licensenumbers);
        if (r > 0) {
            return R.ok();
        }
        return R.error();
    }

    @Log("更改照片")
    @ResponseBody
    @RequestMapping(value = "/pic/update", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    R edit(@RequestParam("avatar_file") MultipartFile file, String licensenumber, String avatar_data, HttpServletRequest request) {

        String fileName;
        fileName = file.getOriginalFilename();

        if(fileName==null||fileName==""){
            return R.error();
        }
        try {
            fileName = medicineService.uploadPic(file, avatar_data, licensenumber);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(0,"剪裁错误");
        }

        return R.ok();
    }
}
