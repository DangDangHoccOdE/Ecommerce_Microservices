package com.ecommerce.product;

import java.util.TimeZone;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import com.ecommerce.product.env.EnvLoader;

@SpringBootApplication
public class ProductApplication {

	public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
		new SpringApplicationBuilder(ProductApplication.class)
			.initializers(new EnvLoader())
			.run(args);
	}

}
