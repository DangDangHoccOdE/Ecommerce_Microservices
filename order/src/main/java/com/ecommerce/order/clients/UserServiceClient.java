// package com.ecommerce.order.clients;

// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.service.annotation.GetExchange;
// import org.springframework.web.service.annotation.HttpExchange;

// import com.ecommerce.order.dtos.response.UserResponse;

// @HttpExchange
// public interface UserServiceClient {

//     @GetExchange("/api/users/{id}")
//     public UserResponse getUserDetails(@PathVariable String id);
// }

// package com.ecommerce.order.clients;

// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.service.annotation.GetExchange;
// import org.springframework.web.service.annotation.HttpExchange;

// import com.ecommerce.order.dtos.response.ProductResponse;

// @HttpExchange
// public interface ProductServiceClient {

//     @GetExchange("/api/products/{id}")
//     ProductResponse getProductDetails(@PathVariable String id);
// }

package com.ecommerce.order.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ecommerce.order.dtos.response.UserResponse;

@FeignClient(name = "user-service")
public interface UserServiceClient {

    @GetMapping("/api/users/{id}")
    UserResponse getUserDetails(@PathVariable("id") String id);
}
