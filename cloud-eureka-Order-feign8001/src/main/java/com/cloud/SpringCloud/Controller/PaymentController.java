package com.cloud.SpringCloud.Controller;

import com.cloud.SpringCloud.Service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")
public class PaymentController {
    @Autowired
    private DiscoveryClient DiscoveryClient;

    @Autowired
    private RestTemplate restTemplate;

    @Resource
    private PaymentService PaymentService;



    @GetMapping("/consumer/payment/hystrix/ok/{Id}")
    public String paymentOk(@PathVariable("Id") Integer Id)
    {
        String result = PaymentService.paymentOk(Id);
        log.info(result);
        return result;
    }

    @HystrixCommand(fallbackMethod = "paymentTimeOutFallbackMethod",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "5000")
    })
    @GetMapping("/consumer/payment/hystrix/timeout/{Id}")
    public String payment_Timeout(@PathVariable("Id") Integer Id)
    {
//        int age = 10/0;
        String result = PaymentService.payment_Timeout(Id);
        return result;
    }

    public String paymentTimeOutFallbackMethod(@PathVariable("Id") Integer Id)
    {
        return "我是消费者80,对方支付系统繁忙请10秒钟后再试或者自己运行出错请检查自己,o(╥﹏╥)o";
    }

    public String payment_Global_FallbackMethod(){
        return "Global异常处理信息，请稍后再试，/(ㄒoㄒ)/~~";
    }
}
