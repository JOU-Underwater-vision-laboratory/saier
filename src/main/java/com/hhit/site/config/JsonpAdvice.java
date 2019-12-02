package com.hhit.site.config;


import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.AbstractJsonpResponseBodyAdvice;

/**
 *
 * 
 * @Title: JsonpAdvice
 * @description:  
 * @author liujun
 * @create 2018-11-01
 * 
 */
@ControllerAdvice(basePackages = "com.hhit.site.controller")
public class JsonpAdvice extends AbstractJsonpResponseBodyAdvice {
    public JsonpAdvice(){
        super("callback");
    }}
