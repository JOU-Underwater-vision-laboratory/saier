package com.hhit.site.controller;


import com.hhit.common.config.Constant;
import com.hhit.common.controller.BaseController;
import com.hhit.common.controller.DictController;
import com.hhit.common.domain.DictDO;
import com.hhit.common.service.DictService;
import com.hhit.common.utils.PageUtils;
import com.hhit.common.utils.Query;
import com.hhit.common.utils.R;
import com.hhit.site.domain.NewsDO;
import com.hhit.site.service.NewsService;
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
 * @Title: NewsController
 * @description:  
 * @author liujun
 * @create 2018-10-05
 * 
 */
@Controller
@Scope("prototype")
@RequestMapping("/site/news")
public class NewsController extends BaseController {
    @Autowired
    NewsService newsService;
    @Autowired
    DictService dictService;
    Calendar now = Calendar.getInstance();

    @GetMapping()
    @RequiresPermissions("site:news:news")
    String news() {
        return "site/news/news";
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("site:news:news")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        List<NewsDO> newsList = newsService.list(query);
        int total = newsService.count(query);
        PageUtils pageUtils = new PageUtils(newsList, total);
        return pageUtils;
    }

    @ResponseBody
    @RequestMapping(value = "/oe/lists", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public R list(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        List<NewsDO> newsList = newsService.findAll(new HashMap<String, Object>(256));
        if(newsList.size()>0) {
            R r = R.ok();
            r.put("newsList", newsList);

            return R.ok().put("newsList", newsList);
        }else{
            return R.error("动态内容为空");
        }
    }

    @GetMapping("/add")
    @RequiresPermissions("site:news:add")
    String add(Model model) {

        List<DictDO> dictDOS_ae = dictService.listByType("site_news_news");
        if(dictDOS_ae.size()>0 ){
             model.addAttribute("introduce",dictDOS_ae.get(0).getValue());
        }
        return "site/news/add";
    }

    @GetMapping("/edit/{nid}")
    @RequiresPermissions("site:news:edit")
    String edit(@PathVariable("nid") Long nid, Model model) {
        NewsDO newsDO = newsService.get(nid);
        model.addAttribute("news", newsDO);
        return "site/news/edit";
    }

    /**
     * 保存
     */
    @ResponseBody
    @RequiresPermissions("site:news:add")
    @PostMapping("/save")
    public R save(NewsDO newsDO) {

        Integer year = now.get(Calendar.YEAR);
        Integer month = now.get(Calendar.MONTH)+1;
        newsDO.setNmonth(month);
        newsDO.setNyear(year);
        newsDO.setCreated(getUserId());
        newsDO.setModified(getUserId());
        newsDO.setGtmCreate(new Date());
        newsDO.setGtmModified(new Date());
        int count;
        if (newsDO.getNid() == null || "".equals(newsDO.getNid())) {
            count = newsService.save(newsDO);
        } else {
            count = newsService.update(newsDO);
        }
        if (count > 0) {
            return R.ok().put("nid", newsDO.getNid());
        }
        else{
            return R.error("您未作任何修改，请重新打开本窗口编辑更新。");
        }
    }

    /**
     * 修改
     */
    @RequiresPermissions("site:news:edit")
    @ResponseBody
    @RequestMapping("/update")
    public R update( NewsDO newsDO) {
        Long userid = newsDO.getCreated();
        if(userid==null){
            newsDO.setCreated((long) 1);
            userid= Long.valueOf(1);
        }
        UserDO userDO = getUser();
        if (!(userid.equals(userDO.getUserId())) && (userDO.getUserId() != 1)) {
            return R.error("您无权更新其他用户上传的动态。");
        }
        newsDO.setModified(userDO.getUserId());
        newsDO.setGtmModified(new Date());
        newsService.update(newsDO);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequiresPermissions("site:news:remove")
    @PostMapping("/remove")
    @ResponseBody
    public R remove(Long id) {
        Long userid = newsService.get(id).getCreated();
        UserDO userDO = getUser();
        if (!(userid.equals(userDO.getUserId())) && (userDO.getUserId() != 1)) {
            return R.error("您无权删除其他用户上传的动态。");
        }
        if (newsService.remove(id) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 删除
     */
    @RequiresPermissions("site:news:batchRemove")
    @PostMapping("/batchRemove")
    @ResponseBody
    public R remove(@RequestParam("ids[]") Long[] nids) {
        UserDO userDO = getUser();
        if (userDO.getUserId() != 1) {
            return R.error("您非超级管理员，无该权限。");
        }
        newsService.batchRemove(nids);
        return R.ok();
    }
}
