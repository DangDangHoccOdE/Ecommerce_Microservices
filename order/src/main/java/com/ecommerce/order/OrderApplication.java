package com.ecommerce.order;

import java.util.TimeZone;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import com.ecommerce.order.env.EnvLoader;

@SpringBootApplication
public class OrderApplication {

	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
		new SpringApplicationBuilder(OrderApplication.class)
			.initializers(new EnvLoader())
			.run(args);
	}

}
