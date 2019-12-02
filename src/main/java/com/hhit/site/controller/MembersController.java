package com.hhit.site.controller;


import com.hhit.common.annotation.Log;
import com.hhit.common.config.AppConfig;
import com.hhit.common.controller.BaseController;
import com.hhit.common.domain.DictDO;
import com.hhit.common.service.DictService;
import com.hhit.common.utils.PageUtils;
import com.hhit.common.utils.Query;
import com.hhit.common.utils.R;


import com.hhit.site.domain.MemberDO;
import com.hhit.site.service.MemberService;
import com.hhit.system.domain.UserDO;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * 
 * @Title: MembersController
 * @description:  
 * @author liujun
 * @create 2018-10-05
 * 
 */
@Controller
@Scope("prototype")
@RequestMapping("/site/members")
public class MembersController extends BaseController {
    @Autowired
    MemberService memberService;
    @Autowired
    private DictService dictService;
    @Autowired
    private AppConfig appConfig;

    @GetMapping()
    @RequiresPermissions("site:members:members")
    String members() {
        return "site/members/members";
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("site:members:members")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        List<MemberDO> memberDOList = memberService.list(query);
        int total = memberService.count(query);
        PageUtils pageUtils = new PageUtils(memberDOList, total);
        return pageUtils;
    }

    @ResponseBody
    @RequestMapping(value = "/oe/rd/{ph}", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public String re(@PathVariable("ph") String path) {

        return "site/site/"+path;
    }

    @ResponseBody
    @RequestMapping(value = "/oe/lists", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public R list(HttpServletRequest request, HttpServletResponse response) {
        // 授权跨域资源共享。跨域站点请求 jsonp 格式为：  `callback=?? `, `??` 为任意合法字符组合。
        response.setHeader("Access-Control-Allow-Origin", "*");
        List<MemberDO> memberList = memberService.findAll(new HashMap<String, Object>(256));
        if(memberList.size()>0) {
            return R.ok().put("memberList", memberList);
        }else{
            return R.error("成员信息为空");
        }
    }

    @GetMapping("/add")
    @RequiresPermissions("site:members:add")
    String add(Model model) {
        List<DictDO> dictDOS_i = dictService.listByType("site_member_introduce");
        List<DictDO> dictDOS_ie = dictService.listByType("site_member_introduceEng");
        List<DictDO> dictDOS_a = dictService.listByType("site_member_area");
        List<DictDO> dictDOS_ae = dictService.listByType("site_member_areaEng");
        if(dictDOS_i.size()>0 ) {
            model.addAttribute("introduce",dictDOS_i.get(0).getValue());
        }
        if(dictDOS_ie.size()>0 ) {
            model.addAttribute("introduceEng",dictDOS_ie.get(0).getValue());
        }
        if(dictDOS_a.size()>0 ) {
            model.addAttribute("area", dictDOS_a.get(0).getValue());
        }
        if(dictDOS_ae.size()>0 ) {
            model.addAttribute("area_eng", dictDOS_ae.get(0).getValue());
        }
        return "site/members/add";
    }

    @GetMapping("/edit/{mid}")
    @RequiresPermissions("site:members:edit")
    String edit(@PathVariable("mid") Long mid, Model model) {
        MemberDO member = memberService.get(mid);
        List<DictDO> dictDOS_job = dictService.listByType("site_member_job");
        String type_job = member.getMjob();
        for (DictDO dictDO:dictDOS_job){
            if(type_job.equals(dictDO.getValue())){
                dictDO.setRemarks("checked");
            }
        }
        List<DictDO> dictDOS_group= dictService.listByType("site_member_group");
        String type_group = member.getMjob();
        for (DictDO dictDO:dictDOS_group){
            if(type_group.equals(dictDO.getValue())){
                dictDO.setRemarks("checked");
            }
        }
        model.addAttribute("membergroup",dictDOS_group);
        model.addAttribute("memberjob",dictDOS_job);
        model.addAttribute("member", member);
        return "site/members/edit";
    }
    @GetMapping("/updatePic/{mid}")
    @RequiresPermissions("site:members:edit")
    String updatePic(@PathVariable("mid") Long mid, Model model) {
        MemberDO member = memberService.get(mid);
        model.addAttribute("member", member);
        return "site/members/updatePic";
    }

    /**
     * 保存
     */
    @ResponseBody
    @RequiresPermissions("site:members:add")
    @PostMapping("/save")
    public R save(MemberDO member) {
        member.setGtmCreate(new Date());
        member.setGtmModified(new Date());
        member.setCreated(getUser().getUserId());
        member.setModified(getUser().getUserId());
        int count;
        if (member.getMid() == null || "".equals(member.getMid())) {
            count = memberService.save(member);
        } else {
            count = memberService.update(member);
        }
        if (count > 0) {
            return R.ok().put("mid", member.getMid());
        }else{
            return R.error("您未作任何修改，请重新打开本窗口编辑更新。");
        }
    }

    @Log("更改照片")
    @ResponseBody
    @RequestMapping(value = "/pic/update", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    R edit(@RequestParam("avatar_file") MultipartFile file,Long mid, String avatar_data,HttpServletRequest request) {

        String fileName;
        fileName = file.getOriginalFilename();

        if(fileName==null||fileName==""){
            return R.error();
        }
        try {
            fileName = memberService.updateMemberImg(file, avatar_data, mid);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(0,"剪裁错误");
        }
        try {
            MemberDO memberDO = memberService.get(mid);
            memberDO.setPic(fileName);
            memberService.update(memberDO);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(0,"照片上传错误或该成员未存入数据库,其他异常请联系管理员");
        }

        return R.ok();
    }

    /**
     * 修改
     */
    @RequiresPermissions("site:members:edit")
    @ResponseBody
    @RequestMapping("/update")
    public R update(MemberDO member) {
        Long userid = member.getCreated();
        if(userid==null){
            member.setCreated((long) 1);
            userid= Long.valueOf(1);
        }
        UserDO userDO = getUser();
        if (!(userid.equals(userDO.getUserId())) && (userDO.getUserId() != 1)) {
            return R.error("您无权更新其他用户新增的成员信息。");
        }
        member.setModified(userDO.getUserId());
        member.setGtmModified(new Date());
        if(memberService.update(member)>0) {
            return R.ok();
        } else {
            return R.error("更新错误");
        }
    }

    /**
     * 删除
     */
    @RequiresPermissions("site:members:remove")
    @PostMapping("/remove")
    @ResponseBody
    public R remove(Long id) {
        Long userid = memberService.get(id).getCreated();
        if(null == userid || "".equals(userid)){
            userid = Long.valueOf(1);
        }
        UserDO userDO = getUser();
        if (!(userid.equals(userDO.getUserId())) && (userDO.getUserId() != 1)) {
            return R.error("您无权删除其他用户新增的成员信息。");
        }
        if (memberService.remove(id) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 删除
     */
    @RequiresPermissions("site:members:batchRemove")
    @PostMapping("/batchRemove")
    @ResponseBody
    public R remove(@RequestParam("ids[]") Long[] mids) {
        UserDO userDO = getUser();
        if (userDO.getUserId() != 1) {
            return R.error("您非管理员，无该权限。");
        }
        memberService.batchRemove(mids);
        return R.ok();
    }
}
