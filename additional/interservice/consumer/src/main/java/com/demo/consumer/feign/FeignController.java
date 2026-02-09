package com.demo.consumer.feign;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/feign")
@RequiredArgsConstructor
public class FeignController {

    private final ProviderFeignClient providerFeignClient;

    @RequestMapping("/instance")
    public String getInstance() {
        return providerFeignClient.getInstanceDetail();
    }
}
