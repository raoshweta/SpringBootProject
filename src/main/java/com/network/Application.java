package com.network;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * This class is Spring boot class which starts application.
 */
@EntityScan
@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = { "com.network" })
@EnableJpaRepositories
public class Application {

	/**
	 * Main method
	 */

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}