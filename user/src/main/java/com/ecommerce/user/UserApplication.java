package com.ecommerce.user;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import com.ecommerce.user.env.EnvLoader;

@SpringBootApplication
public class UserApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(UserApplication.class)
			.initializers(new EnvLoader())
			.run(args);
	}

}