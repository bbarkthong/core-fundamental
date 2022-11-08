package com.basah.spring.corefundamental.V0;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class OrderControllerV0 {

    private final OrderServiceV0 orderServiceV0;

    @GetMapping("/v0/request")
    private String request(String itemId) {
        orderServiceV0.orderItem(itemId);
        return "ok";
    }
}
