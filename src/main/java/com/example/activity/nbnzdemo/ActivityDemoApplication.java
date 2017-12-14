package com.example.activity.nbnzdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Scope;

import com.example.activity.nbnzdemo.model.NZBNAccessToken;


@EnableCaching
@SpringBootApplication
@ImportResource(locations = { "classpath*:ctx-iris.xml" })
public class ActivityDemoApplication {

	public static void main(String[] args) {

		SpringApplication.run(ActivityDemoApplication.class, args);
	}


}
