package com.hhit.site.controller;

import com.hhit.common.annotation.Log;
import com.hhit.common.controller.BaseController;
import com.hhit.common.domain.FileDO;
import com.hhit.common.utils.PageUtils;
import com.hhit.common.utils.Query;
import com.hhit.common.utils.R;
import com.hhit.downloadRes.domain.SoftwareDO;
import com.hhit.downloadRes.service.SoftwareService;
import com.hhit.site.domain.RecruitmentDO;
import com.hhit.site.service.RecruitmentService;
import com.hhit.system.domain.UserDO;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 *
 *
 * @Title: 软件/服务
 * @author liujun
 * @created 2018-11
 *
 */
@Controller
@Scope("prototype")
@RequestMapping("/site/software")
public class SoftwareController extends BaseController {

    @Autowired
    SoftwareService softwareService;

    Calendar now = Calendar.getInstance();
    
    @GetMapping()
    @RequiresPermissions("site:software:software")
    String software() {
        return "site/software/software";
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("site:software:software")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        List<SoftwareDO> softwareList = softwareService.list(query);
        int total = softwareService.count(query);
        PageUtils pageUtils = new PageUtils(softwareList, total);
        return pageUtils;
    }

    @ResponseBody
    @RequestMapping(value = "/oe/lists", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public R list(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        List<SoftwareDO> softwareList = softwareService.list(new HashMap<String, Object>(16));
        if(softwareList.size()>0) {
            R r = R.ok();
            r.put("softwareList", softwareList);

            return R.ok().put("softwareList", softwareList);
        }else
            return R.error("内容为空");
    }

    @GetMapping("/add")
    @RequiresPermissions("site:software:add")
    String add() {
        return "site/software/add";
    }

    @GetMapping("/edit/{id}")
    @RequiresPermissions("site:software:edit")
    String edit(@PathVariable("id") Integer id, Model model) {
        SoftwareDO softwareDO = softwareService.get(id);
        model.addAttribute("soft", softwareDO);
        return "site/software/edit";
    }

    /**
     * 保存
     */
    @ResponseBody
    @RequiresPermissions("site:software:add")
    @PostMapping("/save")
    public R save(SoftwareDO softwareDO) {
        softwareDO.setSoftCreateDate(new Date());
        softwareDO.setSoftCreateby(getUser().getUserId());
        int count;
        if (softwareDO.getSoftId() == null || "".equals(softwareDO.getSoftId())) {
            count = softwareService.save(softwareDO);
        } else {
            count = softwareService.update(softwareDO);
        }
        if (count > 0) {
            return R.ok().put("soft_id", softwareDO.getSoftId());
        }
        else{
            return R.error("您未作任何修改，请重新打开本窗口编辑更新。");
        }
    }

    /**
     * 修改
     */
    @Log("更新发布的软件包或服务")
    @RequiresPermissions("site:software:edit")
    @ResponseBody
    @RequestMapping("/update")
    public R update( SoftwareDO softwareDO) {
        Long userid ;
                if (null == softwareDO.getSoftCreateby()){
                    userid = softwareService.get(softwareDO.getSoftId()).getSoftCreateby();
                }else {
                    userid = softwareDO.getSoftCreateby();
                }
        UserDO userDO = getUser();
        if (!(userid.equals(userDO.getUserId())) && (userDO.getUserId() != 1)) {
            return R.error("您无权编辑其他用户上传的文件。");
        }
        softwareDO.setSoftCreateDate(new Date());
        softwareService.update(softwareDO);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequiresPermissions("site:software:remove")
    @PostMapping("/remove")
    @ResponseBody
    public R remove(Integer id) {
        SoftwareDO softwareDO = softwareService.get(id);
        Long userid = softwareDO.getSoftCreateby();
        UserDO userDO = getUser();
        if (!(userid.equals(userDO.getUserId())) && (userDO.getUserId() != 1)) {
            return R.error("您无权删除其他用户上传的文件。");
        }
        if (softwareService.remove(id) > 0) {
            return R.ok();
        }
        return R.error();
    }

//    /**
//     * 删除
//     */
//    @RequiresPermissions("site:software:batchRemove")
//    @PostMapping("/batchRemove")
//    @ResponseBody
//    public R remove(@RequestParam("ids[]") Integer[] nids) {
//        softwareService.batchRemove(nids);
//        return R.ok();
//    }
}
