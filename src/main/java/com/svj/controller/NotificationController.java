package com.svj.controller;

import com.svj.dto.EmailRequest;
import com.svj.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

@RestController
@RequestMapping("/notifications")
public class NotificationController {
    private EmailService service;

    @Autowired
    public void NotificationController(EmailService service){
        this.service= service;
    }

    @PostMapping("/sendEmail")
    public String sendTextEmail(@RequestBody EmailRequest request){
        return service.sendSimpleEmail(request);
    }

    @PostMapping("/sendAttachment")
    public String sendTextEmailWithAttachement(@RequestBody EmailRequest request) throws MessagingException {
        return service.sendEmailWithAttachemnt(request);
    }
}
