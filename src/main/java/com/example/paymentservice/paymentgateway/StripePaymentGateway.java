package com.example.paymentservice.paymentgateway;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentLink;
import com.stripe.model.Price;
import com.stripe.param.PaymentLinkCreateParams;
import com.stripe.param.PriceCreateParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class StripePaymentGateway implements PaymentGateway {
    @Value("${stripe.secret}")
    private String stripeApiKey;

    @Override
    public String getPaymentLink(String email, String phoneNumber, String orderId, String name, Long amount) {
        try {
            Stripe.apiKey = stripeApiKey;
            PaymentLinkCreateParams params =
                    PaymentLinkCreateParams.builder()
                            .addLineItem(
                                    PaymentLinkCreateParams.LineItem.builder()
                                            .setPrice(getPrice(amount).getId())
                                            .setQuantity(1L)
                                            .build()
                            )
                            .setAfterCompletion(PaymentLinkCreateParams.AfterCompletion.builder()
                                    .setType(PaymentLinkCreateParams.AfterCompletion.Type.REDIRECT)
                                    .setRedirect(PaymentLinkCreateParams.AfterCompletion.Redirect.builder()
                                            .setUrl("https://www.google.com")
                                            .build())
                                    .build())
                            .build();

            PaymentLink paymentLink = PaymentLink.create(params);
            return paymentLink.getUrl();
        }
        catch (StripeException ex){
            throw new RuntimeException(ex);
        }
    }

    private Price getPrice(Long amount){
        try {
            PriceCreateParams priceCreateParams = PriceCreateParams.builder()
                    .setCurrency("usd")
                    .setUnitAmount(amount)
                    .setProductData(PriceCreateParams.ProductData.builder()
                            .setName("Test product")
                            .build())
                    .build();
            Price price = Price.create(priceCreateParams);
            return price;
        }
        catch (StripeException e) {
            throw new RuntimeException(e);
        }
    }
}
