package com.hhit.site.controller;


import com.hhit.common.config.Constant;
import com.hhit.common.controller.BaseController;
import com.hhit.common.domain.DictDO;
import com.hhit.common.service.DictService;
import com.hhit.common.utils.PageUtils;
import com.hhit.common.utils.Query;
import com.hhit.common.utils.R;
import com.hhit.site.domain.PublicationDO;
import com.hhit.site.service.PublicationService;
import com.hhit.system.domain.UserDO;
import com.sun.org.apache.xpath.internal.operations.Mod;
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
 * @Title: PublicationController
 * @description:  
 * @author liujun
 * @create 2018-10-05
 * 
 */
@Controller
@Scope("prototype")
@RequestMapping("/site/publication")
public class PublicationController extends BaseController {
    @Autowired
    PublicationService publicationService;
    @Autowired
    DictService dictService;

    @GetMapping()
    @RequiresPermissions("site:publication:publication")
    String publication() {
        return "site/publication/publication";
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("site:publication:publication")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        List<PublicationDO> publicationList = publicationService.list(query);
        int total = publicationService.count(query);
        PageUtils pageUtils = new PageUtils(publicationList, total);
        return pageUtils;
    }

    @ResponseBody
    @RequestMapping(value = "/oe/lists", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public R list(HttpServletRequest request , HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        List<PublicationDO> publicationDOList = publicationService.findAll(new HashMap<String, Object>(256));
        if(publicationDOList.size()>0) {
            return R.ok().put("publicationList", publicationDOList);
        }else
            return R.error("publication内容为空");
    }

    @GetMapping("/add")
    @RequiresPermissions("site:publication:add")
    String add(Model model) {
        List<DictDO> dictDOS_ae = dictService.listByType("site_publication_publication");
        if(dictDOS_ae.size()>0 ){
            model.addAttribute("introduce",dictDOS_ae.get(0).getValue());
        }
        return "site/publication/add";
    }

    @GetMapping("/edit/{pid}")
    @RequiresPermissions("site:publication:edit")
    String edit(@PathVariable("pid") Long pid, Model model) {
        PublicationDO publicationDO = publicationService.get(pid);
        model.addAttribute("publication", publicationDO);
        return "site/publication/edit";
    }

    /**
     * 保存
     */
    @ResponseBody
    @RequiresPermissions("site:publication:add")
    @PostMapping("/save")
    public R save(PublicationDO publicationDO) {
        if(null==publicationDO.getPcategories()) {
            System.out.println("--------Publication-----save : : 184");
        }
        publicationDO.setGtmCreate(new Date());
        publicationDO.setGtmModified(new Date());
        publicationDO.setCreated(getUserId());
        publicationDO.setModified(getUserId());
        int count;
        if (publicationDO.getPid() == null || "".equals(publicationDO.getPid())) {

            count = publicationService.save(publicationDO);
        } else {

            count = publicationService.update(publicationDO);
        }
        if (count > 0) {
            return R.ok().put("pid", publicationDO.getPid());
        }
        else{
            return R.error("您未作任何修改，请重新打开本窗口编辑更新。");
        }
    }

    /**
     * 修改
     */
    @RequiresPermissions("site:publication:edit")
    @ResponseBody
    @RequestMapping("/update")
    public R update( PublicationDO publicationDO) {

        UserDO userDO = getUser();
        Long userid = publicationDO.getCreated();
        if(userid==null){
            publicationDO.setCreated((long) 1);
            userid= Long.valueOf(1);
        }
        if (!(userid.equals(userDO.getUserId())) && (userDO.getUserId() != 1)) {
            return R.error("您无权更新其他用户新增的信息。");
        }
        publicationDO.setModified(getUserId());
        publicationDO.setGtmModified(new Date());
        publicationService.update(publicationDO);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequiresPermissions("site:publication:remove")
    @PostMapping("/remove")
    @ResponseBody
    public R remove(Long id) {

        if (publicationService.remove(id) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 删除
     */
    @RequiresPermissions("site:publication:batchRemove")
    @PostMapping("/batchRemove")
    @ResponseBody
    public R remove(@RequestParam("ids[]") Long[] pids) {

        publicationService.batchRemove(pids);
        return R.ok();
    }
}
