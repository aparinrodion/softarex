package com.softarex.portal.service;

public interface MailSender {
    void sendMessage(String email, String message, String subject);
}
