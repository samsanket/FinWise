/*
 * Copyright (c) 2023.
 * Sanket Deshpande . All rights reserved
 */

package com.exp.FinWise.Resume.Service;

import com.exp.FinWise.Resume.dto.RequirementDTO;
import com.exp.FinWise.Resume.dto.SaveResponseRequest;
import com.exp.FinWise.Resume.model.RequirementModel;
import com.exp.FinWise.Resume.repository.RequirementRepository;
import com.vidya.leap.common.Model.Mail;
import com.exp.FinWise.usersOnBoarding.model.User;
import jakarta.mail.AuthenticationFailedException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;


@Service
public class MailService {

    private final static Logger logger = LoggerFactory.getLogger(MailService.class);


    private final JavaMailSender mailSender;

    private final Configuration templateConfiguration;
    @Autowired
    ResumeService resumeService;
    @Value("${app.velocity.templates.location}")
    private String basePackagePath;
    @Value("${spring.mail.username}")
    private String mailFrom;
    @Value("${app.token.password.reset.duration}")
    private Long expiration;

    @Autowired
    RequirementRepository requirementRepository;

    @Autowired
    public MailService(JavaMailSender mailSender, Configuration templateConfiguration) {
        this.mailSender = mailSender;
        this.templateConfiguration = templateConfiguration;
    }

    public void sendrequirmentSubmittedSuccess(RequirementDTO requirementDTO, UserDetails userDetails) throws IOException, TemplateException, MessagingException {
        User user = resumeService.getUser(userDetails);
        Mail mail = new Mail();
        mail.setSubject("Requirement Search process started ");
        mail.setTo(user.getEmail());       //
        mail.setFrom(mailFrom);
        mail.getModel().put("keywords", requirementDTO.getKeywords());
        mail.getModel().put("skills", requirementDTO.getSkills());
        mail.getModel().put("experienceMin", requirementDTO.getExperienceMin().toString());
        mail.getModel().put("experienceMax", requirementDTO.getExperienceMax().toString());
        mail.getModel().put("location", requirementDTO.getLocation());
        mail.getModel().put("min_salary", requirementDTO.getMin_salary().toString());
        mail.getModel().put("max_salary", requirementDTO.getMax_salary().toString());
        mail.getModel().put("numberOfResumes", requirementDTO.getNumberOfResumes().toString());
        mail.getModel().put("job_description", requirementDTO.getJob_description());


        templateConfiguration.setClassForTemplateLoading(getClass(), basePackagePath);
        Template template = templateConfiguration.getTemplate("requriment-submit-sucess.ftl");
        String mailContent = FreeMarkerTemplateUtils.processTemplateIntoString(template, mail.getModel());
        mail.setContent(mailContent);
        try {
            send(mail);
        } catch (AuthenticationFailedException e) {
            logger.info("Exception occured while sending mail :{} ",  e.getMessage());
        }
    }

    public void send(Mail mail) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_RELATED, StandardCharsets.UTF_8.name());
        helper.setTo(mail.getTo());
        helper.setText(mail.getContent(), true);
        helper.setSubject(mail.getSubject());
        helper.setFrom(mail.getFrom());
        try {
            mailSender.send(message);
        } catch (Exception e) {
            logger.info("error occured during mail send {}" , e.getMessage());
        }
    }

    public void sendFinalResultMail(SaveResponseRequest saveResponseRequest) throws IOException, TemplateException, MessagingException {
        Long requirmnetId= saveResponseRequest.getRequirnmentId();
        Optional<RequirementModel> optionalRequirementModel= requirementRepository.findById(requirmnetId);
        RequirementModel requirementModel= new RequirementModel();
        if (optionalRequirementModel.isPresent()){
            requirementModel=optionalRequirementModel.get();
        }
        User user= requirementModel.getUser();
        Mail mail = new Mail();
        mail.setSubject("Requirement Search process started ");
        mail.setTo(user.getEmail());
        mail.setFrom(mailFrom);
        mail.getModel().put("username", user.getUsername());

        templateConfiguration.setClassForTemplateLoading(getClass(), basePackagePath);
        Template template = templateConfiguration.getTemplate("response-success.ftl");
        String mailContent = FreeMarkerTemplateUtils.processTemplateIntoString(template, mail.getModel());
        mail.setContent(mailContent);
        try {
            send(mail);
        } catch (AuthenticationFailedException e) {
            logger.info("Exception occured while sending mail :{} ",  e.getMessage());
        }
    }
}
