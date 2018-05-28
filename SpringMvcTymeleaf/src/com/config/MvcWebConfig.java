package com.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan("com.controller")
public class MvcWebConfig  implements WebMvcConfigurer{
	
		@Autowired
		private ApplicationContext context;
	
		//Create ResourceTemplateResolver
	@Bean
	public SpringResourceTemplateResolver templateResolver(){

		SpringResourceTemplateResolver templateResolver=new SpringResourceTemplateResolver();
		templateResolver.setApplicationContext(context);
		templateResolver.setPrefix("/WEB-INF/view/");
		templateResolver.setSuffix(".html");
		return templateResolver;
	}
	
	//create template engine
	
	public SpringTemplateEngine templateEngine(){
		SpringTemplateEngine templateEngine=new SpringTemplateEngine();
		templateEngine.setTemplateResolver(templateResolver());
		templateEngine.setEnableSpringELCompiler(true);
		return templateEngine;
	}
	
	//registry tymeleafresolver
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		
		ThymeleafViewResolver resolver=new ThymeleafViewResolver();
		resolver.setTemplateEngine(templateEngine());
		registry.viewResolver(resolver);
	}
	
}
