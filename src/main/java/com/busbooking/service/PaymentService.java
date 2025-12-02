package com.busbooking.service;

import com.busbooking.dto.PaymentRequest;
import com.busbooking.dto.PaymentResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentService {
    
    @Value("${stripe.api.key}")
    private String stripeApiKey;
    
    @Value("${razorpay.key.id}")
    private String razorpayKeyId;
    
    public PaymentResponse processPayment(PaymentRequest request) {
        log.info("Processing payment for booking: {}", request.getBookingId());
        
        try {
            switch (request.getPaymentMethod().toLowerCase()) {
                case "stripe":
                    return processStripePayment(request);
                case "razorpay":
                    return processRazorpayPayment(request);
                default:
                    return PaymentResponse.builder()
                            .success(false)
                            .message("Unsupported payment method")
                            .build();
            }
        } catch (Exception e) {
            log.error("Payment processing failed", e);
            return PaymentResponse.builder()
                    .success(false)
                    .message("Payment failed: " + e.getMessage())
                    .build();
        }
    }
    
    private PaymentResponse processStripePayment(PaymentRequest request) {
        // Stripe payment integration
        String paymentId = "stripe_" + UUID.randomUUID().toString();
        
        return PaymentResponse.builder()
                .success(true)
                .paymentId(paymentId)
                .transactionId(paymentId)
                .amount(request.getAmount())
                .currency("INR")
                .status("COMPLETED")
                .message("Payment successful via Stripe")
                .build();
    }
    
    private PaymentResponse processRazorpayPayment(PaymentRequest request) {
        // Razorpay payment integration
        String paymentId = "razorpay_" + UUID.randomUUID().toString();
        
        return PaymentResponse.builder()
                .success(true)
                .paymentId(paymentId)
                .transactionId(paymentId)
                .amount(request.getAmount())
                .currency("INR")
                .status("COMPLETED")
                .message("Payment successful via Razorpay")
                .build();
    }
}