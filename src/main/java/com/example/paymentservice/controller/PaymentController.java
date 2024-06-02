package com.example.paymentservice.controller;

import com.example.paymentservice.dto.InitiatePaymentRequestDto;
import com.example.paymentservice.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @PostMapping
    public String initiatePayment(@RequestBody InitiatePaymentRequestDto initiatePaymentRequestDto){
        return paymentService.initiatePayment(initiatePaymentRequestDto.getEmail(),initiatePaymentRequestDto.getPhoneNumber(),initiatePaymentRequestDto.getOrderId(),initiatePaymentRequestDto.getName(),initiatePaymentRequestDto.getAmount());
    }
}
