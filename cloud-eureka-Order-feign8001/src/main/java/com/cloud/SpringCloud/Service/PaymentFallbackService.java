package com.cloud.SpringCloud.Service;

import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentService{
    @Override
    public String paymentOk(Integer Id) {
        return "-----PaymentFallbackService fall back-paymentInfo_OK ,o(╥﹏╥)o";
    }

    @Override
    public String payment_Timeout(Integer Id) {
        return "-----PaymentFallbackService fall back-paymentInfo_TimeOut ,o(╥﹏╥)o";
    }
}
