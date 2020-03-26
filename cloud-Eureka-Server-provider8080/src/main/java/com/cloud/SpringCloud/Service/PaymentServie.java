package com.cloud.SpringCloud.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class PaymentServie {

    public String paymentOk(Integer Id){
        return "线程池:  "+Thread.currentThread().getName()+"  paymentInfo_OK,id:  "+Id+"\t"+"O(∩_∩)O哈哈~";
    }

    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler")
    public String payment_Timeout(Integer Id){
        return "线程池:  "+Thread.currentThread().getName()+" id:  "+Id+"\t"+"O(∩_∩)O哈哈~"+"  耗时(秒): ";
    }


    public String paymentInfo_TimeOutHandler(Integer Id){
        return "线程池:  "+Thread.currentThread().getName()+"  8001系统繁忙或者运行报错，请稍后再试,id:  "+Id+"\t"+"o(╥﹏╥)o";
    }

    //=====服务熔断

    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),// 是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),// 请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"), // 时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"),// 失败率达到多少后跳闸
    })
    public String paymentCircuitBreaker(@PathVariable("Id") Integer id) {
        if(id < 0)
        {
            throw new RuntimeException("******id 不能负数");
        }
        String serialNumber = String.valueOf(UUID.randomUUID());
        return Thread.currentThread().getName()+"\t"+"调用成功，流水号: " + serialNumber;
    }
    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id)
    {
        return "id 不能负数，请稍后再试，/(ㄒoㄒ)/~~   id: " +id;
    }
}
