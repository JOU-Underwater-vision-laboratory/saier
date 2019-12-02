package com.hhit.saier.oldController;


import com.hhit.common.annotation.Log;
import com.hhit.common.controller.BaseController;
import com.hhit.common.utils.PageUtils;
import com.hhit.common.utils.Query;
import com.hhit.common.utils.R;
import com.hhit.saier.domain.MedBoxMiniDO;
import com.hhit.saier.service.MedBoxMiniService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** 
 *
 * 
 * @Title: MedBoxMiniController
 * @description:  
 * @author liujun
 * @create 2018-10-29
 * 
 */
@Controller
@Scope("prototype")
@RequestMapping("/saier/mini")
public class MedBoxMiniController extends BaseController {
    String prefix = "saier/mini";
    @Resource
    private  MedBoxMiniService miniService;

    @RequiresPermissions("saier:mini:mini")
    @GetMapping()
    String mini() {
        return prefix + "/mini";
    }

    @RequiresPermissions("saier:mini:mini")
    @GetMapping("/list")
    @ResponseBody()
    PageUtils list(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        List<MedBoxMiniDO> mini = miniService.list(query);
        int total = miniService.count(query);
        return new PageUtils(mini, total);
    }

    @GetMapping("/m/list")
    @ResponseBody()
    R list() {

        List<MedBoxMiniDO> mini = miniService.list(new HashMap<>(16));
        return R.ok().put("mini",mini);
    }

    @Log("添加药品")
    @RequiresPermissions("saier:mini:add")
    @GetMapping("/add")
    String add() {
        return prefix + "/add";
    }

    @Log("编辑药品")
    @RequiresPermissions("saier:mini:edit")
    @GetMapping("/edit/{id}")
    String edit(@PathVariable("id") Long id, Model model) {
        MedBoxMiniDO MedBoxMiniDO = miniService.get(id);
        model.addAttribute("mini", MedBoxMiniDO);
        return prefix + "/edit";
    }

    @Log("保存药品")
    @RequiresPermissions("saier:mini:add")
    @PostMapping("/save")
    @ResponseBody()
    R save(MedBoxMiniDO mini) {
        if (miniService.save(mini) > 0) {
            return R.ok();
        } else {
            return R.error(1, "保存失败");
        }
    }

    @Log("更新药品")
    @RequiresPermissions("saier:mini:edit")
    @PostMapping("/update")
    @ResponseBody()
    R update(MedBoxMiniDO mini) {
        if (miniService.update(mini) > 0) {
            return R.ok();
        } else {
            return R.error(1, "保存失败");
        }
    }

    @Log("删除药品")
    @RequiresPermissions("saier:mini:remove")
    @PostMapping("/remove")
    @ResponseBody()
    R remove(Long id) {

        if (miniService.remove(id) > 0) {
            return R.ok();
        } else {
            return R.error(1, "删除失败");
        }
    }

    @RequiresPermissions("saier:mini:batchRemove")
    @Log("批量删除药品")
    @PostMapping("/batchRemove")
    @ResponseBody
    R batchRemove(@RequestParam("ids[]") Long[] ids) {
        int r = miniService.batchRemove(ids);
        if (r > 0) {
            return R.ok();
        }
        return R.error();
    }

    @Log("保存药品")
    @GetMapping("/m/save/{mid}/{first}/{second}/{third}/{forth}/{fifth}")
    @ResponseBody()
    R saveM(@PathVariable("mid")Integer mid,
            @PathVariable("first")String first,
            @PathVariable("second")String second,
            @PathVariable("third")String third,
            @PathVariable("forth")String forth,
            @PathVariable("fifth")String fifth) {
        MedBoxMiniDO mini = new MedBoxMiniDO(mid,first,second,third,forth,fifth);
        if (miniService.save(mini) > 0) {
            return R.ok();
        } else {
            return R.error(1, "保存失败");
        }
    }

    @Log("更新药品")
    @GetMapping("/m/update/{mid}/{first}/{second}/{third}/{forth}/{fifth}")
    @ResponseBody()
    R updateM(@PathVariable("mid")Integer mid,
              @PathVariable("first")String first,
              @PathVariable("second")String second,
              @PathVariable("third")String third,
              @PathVariable("forth")String forth,
              @PathVariable("fifth")String fifth) {
        MedBoxMiniDO mini = new MedBoxMiniDO(mid,first,second,third,forth,fifth);
        if (miniService.update(mini) > 0) {
            return R.ok();
        } else {
            return R.error(1, "保存失败");
        }
    }

    @Log("删除药品")
    @GetMapping("/m/remove/{id}")
    @ResponseBody()
    R removeM(@PathVariable("id") Long id) {

        if (miniService.remove(id) > 0) {
            return R.ok();
        } else {
            return R.error(1, "删除失败");
        }
    }


    @Log("批量删除药品")
    @GetMapping("/m/batchRemove")
    @ResponseBody
    R batchRemoveM(@RequestParam("ids[]") Long[] ids) {
        int r = miniService.batchRemove(ids);
        if (r > 0) {
            return R.ok();
        }
        return R.error();
    }

}

