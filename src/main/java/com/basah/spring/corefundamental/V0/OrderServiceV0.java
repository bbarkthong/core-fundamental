package com.basah.spring.corefundamental.V0;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceV0 {

    private final OrderRepositoryV0 orderRepositoryV0;

    public void orderItem(String ItemId) {
        orderRepositoryV0.save(ItemId);
    }
}
