package com.example.paymentservice.service;

import com.example.paymentservice.paymentgateway.PaymentGateway;
import com.example.paymentservice.paymentgateway.PaymentGatewayStrategyChooser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    @Autowired
    private PaymentGatewayStrategyChooser paymentGatewayStrategyChooser;

    public String initiatePayment(String email, String phoneNumber, String orderId, String name, Long amount){
        PaymentGateway paymentGateway = paymentGatewayStrategyChooser.getBestPaymentGatewayStrategy();
        return paymentGateway.getPaymentLink(email,phoneNumber,orderId,name,amount);
    }
}
