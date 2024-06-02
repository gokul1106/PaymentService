package com.example.paymentservice.paymentgateway;

public interface PaymentGateway {
    String getPaymentLink(String email, String phoneNumber, String orderId, String name, Long amount);
}
