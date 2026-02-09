package com.ecommerce.configserver;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.config.server.EnableConfigServer;

import com.ecommerce.configserver.env.EnvLoader;

@SpringBootApplication
@EnableConfigServer
public class ConfigserverApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(ConfigserverApplication.class)
			.initializers(new EnvLoader())
			.run(args);
	}

}
