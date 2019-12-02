package com.hhit.site.controller;

import com.hhit.common.config.Constant;
import com.hhit.common.controller.BaseController;
import com.hhit.common.domain.DictDO;
import com.hhit.common.service.DictService;
import com.hhit.common.utils.PageUtils;
import com.hhit.common.utils.Query;
import com.hhit.common.utils.R;
import com.hhit.site.domain.ProjectDO;
import com.hhit.site.service.ProjectService;
import com.hhit.system.domain.UserDO;
import io.swagger.models.auth.In;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * 
 * @Title: ProjectController
 * @description:  
 * @author liujun
 * @create 2018-10-05
 * 
 */
@Controller
@Scope("prototype")
@RequestMapping("/site/project")
public class ProjectController extends BaseController {
    @Autowired
    ProjectService projectService;
    @Autowired
    DictService dictService;

    @GetMapping()
    @RequiresPermissions("site:project:project")
    String project() {
        return "site/project/project";
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("site:project:project")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        List<ProjectDO> projectList = projectService.list(query);
        int total = projectService.count(query);
        PageUtils pageUtils = new PageUtils(projectList, total);
        return pageUtils;
    }

    @ResponseBody
    @RequestMapping(value = "/oe/lists", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public R list(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        List<ProjectDO> projectDOList = projectService.findAll(new HashMap<String, Object>(256));
        if(projectDOList.size()>0) {
            return R.ok().put("projectList", projectDOList);
        }else
            return R.error("Project内容为空");
    }

    @GetMapping("/add")
    @RequiresPermissions("site:project:add")
    String add(Model model) {
        List<DictDO> dictDOS_ae = dictService.listByType("site_project_project");
        if(dictDOS_ae.size()>0 )
        model.addAttribute("introduce",dictDOS_ae.get(0).getValue());
        return "site/project/add";
    }

    @GetMapping("/edit/{pid}")
    @RequiresPermissions("site:project:edit")
    String edit(@PathVariable("pid") Long pid, Model model) {
        ProjectDO projectDO = projectService.get(pid);
        model.addAttribute("project", projectDO);
        return "site/project/edit";
    }

    /**
     * 保存
     */
    @ResponseBody
    @RequiresPermissions("site:project:add")
    @PostMapping("/save")
    public R save(ProjectDO projectDO) {

        if (projectDO.getCreated() == null) {
            projectDO.setCreated(getUserId());
        }
        if (projectDO.getModified() == null) {
            projectDO.setModified(getUserId());
        }
        projectDO.setCreated(getUserId());
        projectDO.setModified(getUserId());
        projectDO.setGtmCreate(new Date());
        projectDO.setGtmModified(new Date());
        int count;
        if (projectDO.getPid() == null || "".equals(projectDO.getPid())) {

            count = projectService.save(projectDO);
        } else {
            count = projectService.update(projectDO);
        }
        if (count > 0) {
            return R.ok().put("pid", projectDO.getPid());
        }
        else{
            return R.error("您未作任何修改，请重新打开本窗口编辑更新。");
        }
    }

    /**
     * 修改
     */
    @RequiresPermissions("site:project:edit")
    @ResponseBody
    @RequestMapping("/update")
    public R update( ProjectDO projectDO) {
        UserDO userDO = getUser();
        Long userid = projectDO.getCreated();
        if(userid==null){
            projectDO.setCreated((long) 1);
            userid= Long.valueOf(1);
        }
        if (!(userid.equals(userDO.getUserId())) && (userDO.getUserId() != 1)) {
            return R.error("您无权更新其他用户新增的项目信息。");
        }
        projectDO.setModified(getUserId());
        projectDO.setGtmModified(new Date());
        projectService.update(projectDO);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequiresPermissions("site:project:remove")
    @PostMapping("/remove")
    @ResponseBody
    public R remove(Long id) {
        UserDO userDO = getUser();
        Long userid = projectService.get(id).getCreated();
        if (!(userid.equals(userDO.getUserId())) && (userDO.getUserId() != 1)) {
            return R.error("您无权删除其他用户新增的项目信息。");
        }
        if (projectService.remove(id) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 删除
     */
    @RequiresPermissions("site:project:batchRemove")
    @PostMapping("/batchRemove")
    @ResponseBody
    public R remove(@RequestParam("ids[]") Long[] pids) {
        UserDO userDO = getUser();

        if (userDO.getUserId() != 1) {
            return R.error("您非管理员，无该权限");
        }
        projectService.batchRemove(pids);
        return R.ok();
    }
}
