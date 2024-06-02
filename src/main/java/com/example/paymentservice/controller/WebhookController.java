package com.example.paymentservice.controller;

import com.stripe.Stripe;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/webhook")
public class WebhookController {
    @PostMapping("/stripe")
    private void receiveStripeWebhookEvents(@RequestBody String stripeEvent){
        System.out.println(stripeEvent);
    }
}
