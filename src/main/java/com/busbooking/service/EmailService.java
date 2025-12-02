package com.busbooking.service;

import com.busbooking.model.Booking;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {
    
    private final JavaMailSender mailSender;
    
    @Async
    public void sendBookingConfirmation(Booking booking) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            
            helper.setTo(booking.getPassengerDetails().getEmail());
            helper.setSubject("Booking Confirmed - " + booking.getBookingNumber());
            
            String emailContent = buildBookingConfirmationEmail(booking);
            helper.setText(emailContent, true);
            
            mailSender.send(message);
            log.info("Booking confirmation email sent to: {}", booking.getPassengerDetails().getEmail());
        } catch (MessagingException e) {
            log.error("Failed to send booking confirmation email", e);
        }
    }
    
    @Async
    public void sendBookingCancellation(Booking booking) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            
            helper.setTo(booking.getPassengerDetails().getEmail());
            helper.setSubject("Booking Cancelled - " + booking.getBookingNumber());
            
            String emailContent = buildBookingCancellationEmail(booking);
            helper.setText(emailContent, true);
            
            mailSender.send(message);
            log.info("Booking cancellation email sent to: {}", booking.getPassengerDetails().getEmail());
        } catch (MessagingException e) {
            log.error("Failed to send booking cancellation email", e);
        }
    }
    
    private String buildBookingConfirmationEmail(Booking booking) {
        return "<html><body style='font-family: Arial, sans-serif;'>" +
                "<div style='max-width: 600px; margin: 0 auto; padding: 20px; border: 1px solid #ddd;'>" +
                "<h2 style='color: #4CAF50;'>🚌 Booking Confirmed!</h2>" +
                "<p>Dear " + booking.getPassengerDetails().getName() + ",</p>" +
                "<p>Your bus booking has been confirmed successfully.</p>" +
                "<div style='background-color: #f8f9fa; padding: 15px; border-radius: 5px; margin: 20px 0;'>" +
                "<h3>Booking Details</h3>" +
                "<p><strong>Booking Number:</strong> " + booking.getBookingNumber() + "</p>" +
                "<p><strong>Journey Date:</strong> " + booking.getJourneyDate() + "</p>" +
                "<p><strong>From:</strong> " + booking.getFromCity() + "</p>" +
                "<p><strong>To:</strong> " + booking.getToCity() + "</p>" +
                "<p><strong>Seats:</strong> " + String.join(", ", booking.getSeatNumbers()) + "</p>" +
                "<p><strong>Total Fare:</strong> ₹" + booking.getTotalFare() + "</p>" +
                "</div>" +
                "<p>Please arrive at the boarding point 15 minutes before departure.</p>" +
                "<p>Have a safe journey!</p>" +
                "<p>Best regards,<br>Bus Booking Team</p>" +
                "</div></body></html>";
    }
    
    private String buildBookingCancellationEmail(Booking booking) {
        return "<html><body style='font-family: Arial, sans-serif;'>" +
                "<div style='max-width: 600px; margin: 0 auto; padding: 20px; border: 1px solid #ddd;'>" +
                "<h2 style='color: #f44336;'>Booking Cancelled</h2>" +
                "<p>Dear " + booking.getPassengerDetails().getName() + ",</p>" +
                "<p>Your booking has been cancelled successfully.</p>" +
                "<div style='background-color: #f8f9fa; padding: 15px; border-radius: 5px; margin: 20px 0;'>" +
                "<p><strong>Booking Number:</strong> " + booking.getBookingNumber() + "</p>" +
                "<p><strong>Refund Amount:</strong> ₹" + booking.getTotalFare() + "</p>" +
                "<p><strong>Refund Status:</strong> Processing (3-5 business days)</p>" +
                "</div>" +
                "<p>If you have any questions, please contact our support team.</p>" +
                "<p>Best regards,<br>Bus Booking Team</p>" +
                "</div></body></html>";
    }
}