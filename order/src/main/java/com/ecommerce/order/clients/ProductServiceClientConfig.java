// package com.ecommerce.order.clients;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.web.client.RestClient;
// import org.springframework.web.client.support.RestClientAdapter;
// import org.springframework.web.service.invoker.HttpServiceProxyFactory;

// @Configuration
// public class ProductServiceClientConfig {

//     @Bean
//     public ProductServiceClient productServiceInterface(
//             RestClient.Builder restClientBuilder) {

//         RestClient restClient = restClientBuilder
//                 .baseUrl("http://product-service")
//                 .build();

//         return HttpServiceProxyFactory
//                 .builderFor(RestClientAdapter.create(restClient))
//                 .build()
//                 .createClient(ProductServiceClient.class);
//     }
// }

