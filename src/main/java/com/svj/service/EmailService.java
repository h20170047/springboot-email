package com.svj.service;

import com.svj.dto.EmailRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class EmailService {
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    @Autowired
    public void EmailService(JavaMailSender javaMailSender){
        this.javaMailSender= javaMailSender;
    }

    public String sendSimpleEmail(EmailRequest request){
        SimpleMailMessage mailMessage= new SimpleMailMessage();

        mailMessage.setFrom(sender);
        mailMessage.setTo(request.getToEmail());
        mailMessage.setSubject(request.getSubject());
        mailMessage.setText(request.getMessageBody());
        javaMailSender.send(mailMessage);
        return "Email sent successfully to: "+request.getToEmail();
    }

    public String sendEmailWithAttachemnt(EmailRequest request) throws MessagingException {
        MimeMessage mimeMessage= javaMailSender.createMimeMessage();
        MimeMessageHelper helper= new MimeMessageHelper(mimeMessage, true);

        helper.setFrom(sender);
        helper.setTo(request.getToEmails());
        helper.setSubject(request.getSubject());
        helper.setText(request.getMessageBody());
        FileSystemResource file= new FileSystemResource(new File(request.getAttachment()));
        helper.addAttachment(file.getFilename(), file);

        javaMailSender.send(mimeMessage);
        return "Mail sent successfully with attachment "+file.getFilename();
    }

}
