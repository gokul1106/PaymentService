package com.example.paymentservice.paymentgateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentGatewayStrategyChooser {
    @Autowired
    private RazorPayPaymentGateway razorPayPaymentGateway;

    public PaymentGateway getBestPaymentGatewayStrategy(){
        return razorPayPaymentGateway;
    }
}
