package com.hhit.common.interceptor;


import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * 问题：除了GET请求可以跨域访问外，POST和DELETE请求仍然不可以跨域访问
 * 错误提示：
 * XMLHttpRequest cannot load Response to preflight request doesn’t pass access control check: No ‘Access-Control-Allow-Origin’ header is present on the requested resource.
 * 解释：
 * a. 对于简单请求，如GET，只需要在HTTP Response后添加Access-Control-Allow-Origin。
 * b. 对于非简单请求，比如POST、PUT、DELETE等，浏览器会分两次应答。第一次preflight（method: OPTIONS），主要验证来源是否合法，并返回允许的Header等。第二次才是真正的HTTP应答。所以服务器必须处理OPTIONS应答。
 * 这对此问题的解决办法：
 *
 * @Title: CrossDomainIntercepter
 * @description:  
 * @author liujun
 * @create 2018-09-04
 * 
 */
public class CrossDomainIntercepter extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setHeader("Access-Control-Allow-Origin", "*"); //设置允许哪些url可以跨域请求到本域，*表示所有
        return true;
    }
}
