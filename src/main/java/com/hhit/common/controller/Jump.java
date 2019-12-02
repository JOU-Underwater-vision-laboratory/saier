package com.hhit.common.controller;

import com.hhit.common.annotation.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class Jump {

    @Log("请求跳转")
    @GetMapping("/jump/{toUrl}" )
    String jumpTo(Model model, @PathVariable String toUrl) {

        return "redirect:/jumpTo"+toUrl;
    }
    @Log("门户网站跳转")
    @GetMapping("/jump/0/{toUrl}" )
    String jumpToSite(Model model, @PathVariable String toUrl) {

        return "redirect:/jumpTo/site/"+toUrl;
    }
}
