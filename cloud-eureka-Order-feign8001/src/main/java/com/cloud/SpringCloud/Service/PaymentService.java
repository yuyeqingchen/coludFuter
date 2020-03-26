package com.cloud.SpringCloud.Service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value="cloud-provider-hystrix-payment",fallback = PaymentFallbackService.class)
public interface PaymentService {

    @GetMapping("/payment/hystrix/ok/{id}")
    public String paymentOk(@PathVariable("id") Integer id);

    @GetMapping("/payment/hystrix/timeout/{id}")
    public String payment_Timeout(@PathVariable("id") Integer id);
}
