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

import com.ecommerce.order.dtos.response.ProductResponse;

@FeignClient(name = "product-service")
public interface ProductServiceClient {

    @GetMapping("/api/products/{id}")
    ProductResponse getProductDetails(@PathVariable("id") String id);
}
