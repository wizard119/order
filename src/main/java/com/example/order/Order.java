package com.example.order;

import javax.persistence.*;

import com.example.order.external.Payment;
import com.example.order.external.PaymentService;

import org.springframework.beans.BeanUtils;
import java.util.List;
import java.util.Date;

@Entity
@Table(name="order_table")
public class Order {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long customerId;
    private Long productId;
    private String address;
    private String status;
    private int amt;

    // 1. pub/sub start
    @PostPersist
    public void onPostPersist(){
        OrderPlaced orderPlaced = new OrderPlaced();
        BeanUtils.copyProperties(this, orderPlaced);
        orderPlaced.publishAfterCommit();
    }
    //1. pub/sub end

    // 2. req/res start
    //@PostPersist
    //public void callPaymentStart(){
    //    Payment payment = new Payment();
    //    payment.setOrderId(this.getId());
    //    payment.setProductId(this.getProductId());        
    //    payment.setStatus("Req/Res PAYMENT COMPLETED");
    //    payment.setAmt(this.getAmt());
        
       // start payment
    //    PaymentService paymentService = OrderApplication.applicationContext.getBean(PaymentService.class);
    //    paymentService.startPayment(payment);
    //}
    // 2. req/res end

    @PreRemove
    public void onPreRemove(){
        OrderCanceled orderCanceled = new OrderCanceled();
        BeanUtils.copyProperties(this, orderCanceled);
        orderCanceled.publishAfterCommit();


    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return this.customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getProductId() {
        return this.productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getAmt() {
        return this.amt;
    }

    public void setAmt(int amt) {
        this.amt = amt;
    }

}
