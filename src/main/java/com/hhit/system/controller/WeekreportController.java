package com.hhit.system.controller;

import com.hhit.common.annotation.Log;
import com.hhit.common.controller.BaseController;
import com.hhit.common.utils.PageUtils;
import com.hhit.common.utils.Query;
import com.hhit.common.utils.R;
import com.hhit.system.config.WeekreportTemplate;
import com.hhit.system.domain.RoleDO;
import com.hhit.system.domain.UserDO;
import com.hhit.system.domain.UserRoleDO;
import com.hhit.system.domain.WeekreportDO;
import com.hhit.system.service.RoleService;
import com.hhit.system.service.WeekreportService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 周报管理
 * @author  liujun
 */
@Controller
@Scope("prototype")
@RequestMapping("/week/report")
public class WeekreportController extends BaseController {
    @Autowired
    WeekreportService weekreportService;
    @Autowired
    RoleService roleService;

    @GetMapping()
    @RequiresPermissions("system:weekreport:weekreport")
    String weekreport() {
        return "site/weekreport/weekreport";
    }


    /**
     * 查询列表分发
     */
    @Log("查询周报")
    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("system:weekreport:group")
    public PageUtils list(@RequestParam Map<String, Object> params ) {
        Query query = new Query(params);
        Long userid = getUserId();
        UserDO userDO = getUser();
        UserRoleDO roleID = roleService.getUid(userDO.getUserId());
        List<WeekreportDO> weekreportList ;
        if(roleID.getRoleId()==61){
            weekreportList = weekreportService.list(query);
        }else {
            query.put("uid",userid);
//            query.put("created",getName());
            weekreportList = weekreportService.list(query);
        }
        int total = weekreportService.count(query);
        PageUtils pageUtils = new PageUtils(weekreportList, total);
        return pageUtils;
    }

//    /**
//     *  面向管理员等权限账号查看全部周内容
//     */
//    @ResponseBody
//    @GetMapping("/t/list")
//    @RequiresPermissions("system:weekreport:weekreport")
//    public PageUtils tlist(@RequestParam Map<String, Object> params) {
//        Query query = new Query(params);
//        List<WeekreportDO> weekreportList = weekreportService.findAll(query);
//        int total = weekreportService.count(query);
//        PageUtils pageUtils = new PageUtils(weekreportList, total);
//        return pageUtils;
//    }

    @Log("打开周报添加页")
    @GetMapping("/add")
    @RequiresPermissions("system:weekreport:add")
    String add(Model model) {
        model.addAttribute("temp", WeekreportTemplate.getTemp());
        return "site/weekreport/add";
    }

    @Log("打开周报编辑页")
    @GetMapping("/edit/{wid}")
    @RequiresPermissions("system:weekreport:edit")
    String edit(@PathVariable("wid") String wid, Model model) {
        WeekreportDO weekreportDO = weekreportService.get(Long.parseLong(wid));
        model.addAttribute("weekreport", weekreportDO);
        return "site/weekreport/edit";
    }
    @Log("打开周报回复页")
    @GetMapping("/reply/{wid}")
    @RequiresPermissions("system:weekreport:reply")
    String reply(@PathVariable("wid") Long wid, Model model) {
        WeekreportDO weekreportDO = weekreportService.get(wid);
        model.addAttribute("weekreport", weekreportDO);
        return "site/weekreport/reply";
    }

    /**
     * 批复周报
     */
    @Log("批复周报")
    @ResponseBody
    @PostMapping("/reply")
    @RequiresPermissions("system:weekreport:reply")
    public R reply(WeekreportDO notify) {
        if (null != notify.getCreateBy() && !"".equals(notify.getCreateBy())){
            notify.setCreateBy(notify.getCreateBy()+","+getUser().getName());
        }
        notify.setPushBy(getUserId());
        notify.setCreateBy(getUser().getName());
        if (weekreportService.reply(notify) > 0) {
            String wContent = notify.getwContent()+"<br/><br/>"+notify.getWreply();
            notify.setwContent(wContent);
            notify.setIsRead(1);
            weekreportService.update(notify);
            return R.ok();
        }
        return R.error();
    }

    /**
     * 保存
     */
    @Log("新建周报")
    @ResponseBody
    @RequiresPermissions("system:weekreport:add")
    @PostMapping("/save")
    public R save(WeekreportDO weekreportDO) {
        weekreportDO.setCreated(getUser().getName());
        weekreportDO.setUid(getUserId());
        weekreportDO.setDeptid(getUser().getDeptId());
        weekreportDO.setGtmCreate(new Date());
        weekreportDO.setGtmModified(new Date());
        int count;
        if (weekreportDO.getWid() == null || "".equals(weekreportDO.getWid())) {
            count = weekreportService.save(weekreportDO);
        } else {
            count = weekreportService.update(weekreportDO);
        }
        if (count > 0) {
            return R.ok().put("wid", weekreportDO.getWid());
        }
        return R.error();
    }

    /**
     * 修改
     */
    @Log("更新周报")
    @RequiresPermissions("system:weekreport:edit")
    @ResponseBody
    @RequestMapping("/update")
    public R update( WeekreportDO weekreportDO) {
        weekreportDO.setGtmModified(new Date());
        weekreportService.update(weekreportDO);
        return R.ok();
    }

    /**
     * 删除
     */
    @Log("删除周报")
    @RequiresPermissions("system:weekreport:remove")
    @PostMapping("/remove")
    @ResponseBody
    public R remove(Long id) {
        if (weekreportService.remove(id) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 删除
     */
    @Log("批量删除周报")
    @RequiresPermissions("system:weekreport:batchRemove")
    @PostMapping("/batchRemove")
    @ResponseBody
    public R remove(@RequestParam("ids[]") Long[] wids) {
        weekreportService.batchRemove(wids);
        return R.ok();
    }
}
