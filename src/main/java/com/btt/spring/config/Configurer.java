package com.btt.spring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class Configurer extends WebMvcConfigurerAdapter {

	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**");
	}
}
