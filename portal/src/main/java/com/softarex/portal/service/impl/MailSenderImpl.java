package com.softarex.portal.service.impl;

import com.softarex.portal.service.MailSender;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailSenderImpl implements MailSender {
    private final JavaMailSender mailSender;

    @Override
    public void sendMessage(String email, String message, String subject) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setSubject(subject);
        mailMessage.setText(message);
        mailMessage.setTo(email);
        mailSender.send(mailMessage);
    }
}