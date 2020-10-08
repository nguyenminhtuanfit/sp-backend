package com.sp.app.configuration;



import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfiguration {
	
	@Value("${origin.url}")
	private String originUrl;
    @Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("*").allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH")
				.allowedHeaders("*")
	            .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials")
	            .allowCredentials(true).maxAge(36000);
			}
		};
	}
}