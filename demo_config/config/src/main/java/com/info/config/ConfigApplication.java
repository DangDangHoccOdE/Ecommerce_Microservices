package com.info.config;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class ConfigApplication {

	public static void main(String[] args) {

		new SpringApplicationBuilder(ConfigApplication.class)
				.initializers(new EnvLoader())
				.run(args);
	}

}
