package com.invillia.acme;

import com.invillia.acme.log.MessageLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import java.io.File;
import java.security.Security;

@SpringBootApplication
@Slf4j
public class InvilliaApplication {

	private static final String PROPERTIES = "application.properties";

	public static void main(String[] args) {
		Security.setProperty("crypto.policy", "unlimited");
		SpringApplication.run(InvilliaApplication.class, args);
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		log.info(MessageLog.BC0001.text());
		Resource[] resources = new ClassPathResource[]
				{new ClassPathResource(PROPERTIES)};
		PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
		propertySourcesPlaceholderConfigurer.setLocations(resources);
		return propertySourcesPlaceholderConfigurer;
	}
}
