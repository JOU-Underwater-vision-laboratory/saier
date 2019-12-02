package com.hhit.common.config;

import com.hhit.common.interceptor.CrossDomainIntercepter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 *
 * https://blog.csdn.net/u010924834/article/details/50965714
 * 解决跨域ajax传输问题
 *
 * @date 2018/9/4 20:56
 */

@Component
class WebConfigurer extends WebMvcConfigurerAdapter {
	@Autowired
    AppConfig appConfig;
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/files/**").addResourceLocations("file:///"+ appConfig.getUploadPath());
	}

	@Override
    public void addInterceptors(InterceptorRegistry registry) {

        //添加拦截器
        registry.addInterceptor(new CrossDomainIntercepter())
                .addPathPatterns("/downPage/oe/**");
        super.addInterceptors(registry);
    }

}