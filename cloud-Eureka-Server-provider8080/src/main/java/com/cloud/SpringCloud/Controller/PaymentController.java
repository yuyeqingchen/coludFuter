package com.cloud.SpringCloud.Controller;

import com.cloud.SpringCloud.Service.PaymentServie;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentServie PaymentServie;

    @Value("${server.port}")
    private Integer port;

    @GetMapping("/payment/hystrix/ok/{Id}")
    public String paymentOk(@PathVariable("Id") Integer Id){
        String result = PaymentServie.paymentOk(Id);
        log.info("*****result: "+result);
        return result;
    }

    @GetMapping("/payment/hystrix/timeout/{Id}")
    public String payment_Timeout(@PathVariable("Id") Integer Id){
        String result = PaymentServie.payment_Timeout(Id);
        log.info("****result: "+result);
        return result;
    }

    //====服务熔断
    @GetMapping("/payment/circuit/{Id}")
    public String paymentCircuitBreaker(@PathVariable("Id") Integer Id){
        String result = PaymentServie.paymentCircuitBreaker(Id);
        log.info("****result: "+result);
        return result;
    }
}
