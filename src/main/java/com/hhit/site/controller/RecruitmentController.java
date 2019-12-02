package com.hhit.site.controller;

import com.hhit.common.annotation.Log;
import com.hhit.common.config.Constant;
import com.hhit.common.controller.BaseController;
import com.hhit.common.domain.DictDO;
import com.hhit.common.service.DictService;
import com.hhit.common.utils.PageUtils;
import com.hhit.common.utils.Query;
import com.hhit.common.utils.R;
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
 * @Title: 招聘
 * @description: 招聘
 * @author liujun
 * @create 2018-11
 *
 */
@Controller
@Scope("prototype")
@RequestMapping("/site/recruitment")
public class RecruitmentController  extends BaseController {

    @Autowired
    RecruitmentService recruitmentService;
    @Autowired
    DictService dictService;

    Calendar now = Calendar.getInstance();
    
    @GetMapping()
    @RequiresPermissions("site:recruitment:recruitment")
    String recruitment() {
        return "site/recruitment/recruitment";
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("site:recruitment:recruitment")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        List<RecruitmentDO> recruitmentList = recruitmentService.list(query);
        int total = recruitmentService.count(query);
        PageUtils pageUtils = new PageUtils(recruitmentList, total);
        return pageUtils;
    }

    @ResponseBody
    @RequestMapping(value = "/oe/lists", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public R list(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        List<RecruitmentDO> recruitmentList = recruitmentService.findAll(new HashMap<String, Object>(256));
        if(recruitmentList.size()>0) {
            R r = R.ok();
            r.put("recruitmentList", recruitmentList);

            return R.ok().put("recruitmentList", recruitmentList);
        }else
            return R.error("内容为空");
    }

    @GetMapping("/add")
    @RequiresPermissions("site:recruitment:add")
    String add() {
        return "site/recruitment/add";
    }

    @GetMapping("/edit/{rid}")
    @RequiresPermissions("site:recruitment:edit")
    String edit(@PathVariable("rid") Long rid, Model model) {
        RecruitmentDO recruitmentDO = recruitmentService.get(rid);
            model.addAttribute("recruitment", recruitmentDO);
        return "site/recruitment/edit";
    }

    /**
     * 保存
     */
    @ResponseBody
    @RequiresPermissions("site:recruitment:add")
    @PostMapping("/save")
    public R save(RecruitmentDO recruitmentDO) {
        recruitmentDO.setGtmCreate(new Date());
        recruitmentDO.setGtmModified(new Date());
        recruitmentDO.setCreated(getUser().getUserId());
        recruitmentDO.setModified(getUser().getUserId());
        int count;
        if (recruitmentDO.getRid() == null || "".equals(recruitmentDO.getRid())) {
            count = recruitmentService.save(recruitmentDO);
        } else {
            count = recruitmentService.update(recruitmentDO);
        }
        if (count > 0) {
            return R.ok().put("rid", recruitmentDO.getRid());
        }
        else{
            return R.error("您未作任何修改，请重新打开本窗口编辑更新。");
        }
    }

    /**
     * 修改
     */
    @Log("更新招聘公告")
    @RequiresPermissions("site:recruitment:edit")
    @ResponseBody
    @RequestMapping("/update")
    public R update( RecruitmentDO recruitmentDO) {
        UserDO userDO = getUser();
        Long userid = recruitmentDO.getCreated();
        if (!(userid.equals(userDO.getUserId())) && (userDO.getUserId() != 1)) {
            return R.error("您无权更新其他用户新增的招聘信息。");
        }
        recruitmentDO.setModified(userDO.getUserId());
        recruitmentDO.setGtmCreate(new Date());
        recruitmentDO.setGtmModified(new Date());
        recruitmentService.update(recruitmentDO);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequiresPermissions("site:recruitment:remove")
    @PostMapping("/remove")
    @ResponseBody
    public R remove(Long id) {
        UserDO userDO = getUser();
        Long userid = recruitmentService.get(id).getCreated();
        if (!(userid.equals(userDO.getUserId())) && (userDO.getUserId() != 1)) {
            return R.error("您无权删除其他用户新增的项目信息。");
        }
        if (recruitmentService.remove(id) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 删除
     */
    @RequiresPermissions("site:recruitment:batchRemove")
    @PostMapping("/batchRemove")
    @ResponseBody
    public R remove(@RequestParam("ids[]") Long[] nids) {
        UserDO userDO = getUser();

        if (userDO.getUserId() != 1) {
            return R.error("您非管理员，无该权限");
        }
        recruitmentService.batchRemove(nids);
        return R.ok();
    }
}
