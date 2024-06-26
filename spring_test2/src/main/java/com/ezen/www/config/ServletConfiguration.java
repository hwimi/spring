package com.ezen.www.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@EnableWebMvc
@ComponentScan(basePackages = {"com.ezen.www.controller","com.ezen.www.service","com.ezen.www.handler"})
public class ServletConfiguration implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		//resources 경로 / 나주엥 파일 업로드 경로 설정 추가
		// ** resources 모든 경로
		registry.addResourceHandler("/re/**").addResourceLocations("/resources/");
		registry.addResourceHandler("up/**")
		.addResourceLocations("file:///D:\\_myProject\\_java\\_fileUpload\\");
		
	}

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		// view 경로 설정
		InternalResourceViewResolver viewResolver=new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		viewResolver.setViewClass(JstlView.class);
		
		registry.viewResolver(viewResolver);
	}
	//multipartResolver 설정
	//multipartResolver 이름 이거야함
	@Bean(name="multipartResolver")
	public MultipartResolver getmuMultipartResolver() {
		StandardServletMultipartResolver multipartResolver=new StandardServletMultipartResolver();
		return multipartResolver;
	}


	
}
