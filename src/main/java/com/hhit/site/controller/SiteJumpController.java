package com.hhit.site.controller;

import com.hhit.common.service.FileService;
import com.hhit.common.utils.Query;
import com.hhit.site.service.MemberService;
import com.hhit.site.service.NewsService;
import com.hhit.site.service.ProjectService;
import com.hhit.site.service.PublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.Map;

@Controller
public class SiteJumpController {

    @Autowired
    NewsService newsService;

    @Autowired
    ProjectService projectService;

    @Autowired
    PublicationService publicationService;

    @Autowired
    MemberService memberService;

    @Autowired
    FileService fileService;

    @GetMapping({ "/jumpTo/site/{url}" })
    String jump(Model model,@PathVariable String url) {
        if ("index".equals(url)){
            Map<String,Object> map = new HashMap<>(16);
            map.put("type",5);
            model.addAttribute("lunbo",fileService.list(map));

            map.put("offset",0);
            map.put("limit",5);
            map.remove("type",5);
            model.addAttribute("news",newsService.list(map));

            map.clear();
            map.put("offset",0);
            map.put("limit",6);
            model.addAttribute("members",memberService.list(map));
        }else if ("projects".equals(url)){
            model.addAttribute("ds",projectService.list(new HashMap<>(16)));
        }else if("publications".equals(url)){
            model.addAttribute("ds",publicationService.list(new HashMap<>(16)));
        }
        return "site/site/"+url;
    }


}
