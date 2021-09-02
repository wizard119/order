package com.example.order.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name ="payment", url="${api.url.payment}")
public interface PaymentService {
    
    @RequestMapping(method = RequestMethod.POST, value = "/payments", consumes = "application/json")
    public void startPayment(Payment payment);

}
