package com.busbooking.dto;

import lombok.Data;

@Data
public class PaymentRequest {
    private String bookingId;
    private Double amount;
    private String currency = "INR";
    private String paymentMethod; // stripe, razorpay
    private String cardNumber;
    private String cardExpiry;
    private String cardCvv;
    private String email;
}