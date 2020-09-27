package ru.afanasev.diplom.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MvcConfig implements WebMvcConfigurer{
	
	
    private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
            "classpath:/META-INF/resources/", "classpath:/resources/",
            "classpath:/static/", "classpath:/templates/", "classpath:/css/",
            "classpath:/fonts/","classpath:/img/","classpath:/js/"};

    @Override
  	public void addResourceHandlers(ResourceHandlerRegistry registry) {
      registry.addResourceHandler("/**")
      .addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
  }
    

    
   
}