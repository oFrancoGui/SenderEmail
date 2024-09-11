package com.ace4.senderemail.senderemail;


import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/send-email")
    public ResponseEntity<String> sendEmail(@RequestBody EmailRequestDTO emailRequest) {
        try {
            emailService.sendHtmlEmail(emailRequest.getTo(), emailRequest.getSubject(), emailRequest.getHtmlBody());
            return new ResponseEntity<>("Email sent successfully!", HttpStatus.OK);
        } catch (MessagingException e) {
            return new ResponseEntity<>("Failed to send email.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}