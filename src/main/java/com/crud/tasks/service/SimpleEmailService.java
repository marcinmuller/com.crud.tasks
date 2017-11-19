package com.crud.tasks.service;

import com.crud.tasks.domain.Mail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SimpleEmailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleMailMessage.class);

    @Autowired
    private JavaMailSender javaMailSender;

    //thyme
    @Autowired
    private MailCreatorService mailCreatorService;
    //<-thyme

    public void send(final Mail mail){
        try {
//            SimpleMailMessage mailMessage = createMailMessage(mail);
//<-thyme->
//            javaMailSender.send(mailMessage);
            javaMailSender.send(createMimeMessage(mail));

            LOGGER.info("email has been sent");
        }catch (MailException e){
            LOGGER.error("failed to process email sending", e.getMessage(), e);
        }
    }

    //thyme
    private MimeMessagePreparator createMimeMessage(final Mail mail){
        return mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setTo(mail.getMailTo());
            messageHelper.setSubject(mail.getSubject());
            messageHelper.setText(mailCreatorService.buildTrelloCardEmail(mail.getMessage()),true);
        };
    }
    //<-thyme


    private SimpleMailMessage createMailMessage(final Mail mail){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail.getMailTo());
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mail.getMessage());
        if(mail.getToCc()!=null) mailMessage.setCc(mail.getToCc());
        return mailMessage;
    }
}
