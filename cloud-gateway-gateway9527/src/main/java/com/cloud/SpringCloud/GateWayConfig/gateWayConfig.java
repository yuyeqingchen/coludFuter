package com.cloud.SpringCloud.GateWayConfig;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.lang.annotation.Annotation;

public class gateWayConfig implements GlobalFilter, Order {
    @Override
    public Class<? extends Annotation> annotationType() {

        return null;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        return null;
    }

    @Override
    public int value() {
        return 0;
    }
}
