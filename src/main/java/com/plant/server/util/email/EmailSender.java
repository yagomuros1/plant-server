package com.plant.server.util.email;

import com.plant.server.business.entities.admin.Admin;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.Set;

@Component
@Slf4j
public class EmailSender {

    @Value("${spring.mail.from}")
    private String from;
    @Value("${spring.mail.fromname}")
    private String fromName;
    @Value("${spring.mail.username}")
    private String username;
    @Value("${spring.mail.password}")
    private String password;
    @Value("${spring.mail.host}")
    private String host;
    @Value("${spring.mail.port}")
    private int port;
    @Value("${mail.enabled}")
    private Boolean mailingEnabled;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private MessageSource messageSource;

    // USERS
    public void sendUserRegistered(String to, String nickname, Locale locale) throws IOException {
        final Context ctx = new Context(locale);
        ctx.setVariable("nickname", nickname);
        String subject = this.messageSource.getMessage("email.newuser.subject", null, locale);
        final String htmlContent = this.templateEngine.process("emails/newuser.html", ctx);
        this.sendTemplateEmail(to, null, null, subject, htmlContent);
    }

    public void sendUserActivated(String to, String nickname, Locale locale) throws IOException {
        final Context ctx = new Context(locale);
        ctx.setVariable("nickname", nickname);
        String subject = this.messageSource.getMessage("email.activateduser.subject", null, locale);
        final String htmlContent = this.templateEngine.process("emails/activateuser.html", ctx);
        this.sendTemplateEmail(to, null, null, subject, htmlContent);
    }

    public void sendChangePasswordEmail(String to, String link, Locale locale) throws IOException {
        final Context ctx = new Context(locale);
        ctx.setVariable("link", link);
        String subject = this.messageSource.getMessage("email.changepassword.subject", null, locale);
        final String htmlContent = this.templateEngine.process("emails/changepassword.html", ctx);
        this.sendTemplateEmail(to, null, null, subject, htmlContent);
    }

    public void sendPasswordChanged(String to, Locale locale) throws IOException {
        final Context ctx = new Context(locale);
        String subject = this.messageSource.getMessage("email.changedpassword.subject", null, locale);
        final String htmlContent = this.templateEngine.process("emails/changedpassword.html", ctx);
        this.sendTemplateEmail(to, null, null, subject, htmlContent);
    }

    // AUX
    public void sendEmail(String to, Set<String> ccs, Set<String> ccos, String subject, String content) throws IOException {
        this.sendEmailIfEnabled(to, ccs, ccos, subject, content);
    }

    private void sendTemplateEmail(String to, Set<String> ccs, Set<String> ccos, String subject, String content) throws IOException {
        this.sendEmailIfEnabled(to, ccs, ccos, subject, content);
    }

    private void sendEmailIfEnabled(String to, Set<String> ccs, Set<String> ccos, String subject, String content) throws IOException {
        if (!this.mailingEnabled) {
            logEmail(to, ccs, ccos, subject, content);
        } else {
            this.sendEmailAux(to, ccs, ccos, subject, content);
        }
    }

    private static void logEmail(String to, Set<String> ccs, Set<String> ccos, String subject, String content) {
        StringBuffer mailLogSB = new StringBuffer("Fake mail");
        mailLogSB.append("\n\t" + "subject" + ": '" + subject + "'");
        mailLogSB.append("\n\t" + "to" + ": '" + to + "'");
        mailLogSB.append("\n\t" + "ccs" + ": '" + ccs + "'");
        mailLogSB.append("\n\t" + "ccos" + ": '" + ccos + "'");
        mailLogSB.append("\n\t" + "content" + ": '" + content + "'");
        log.debug(mailLogSB.toString());
    }

    // based on https://docs.aws.amazon.com/ses/latest/DeveloperGuide/send-using-smtp-java.html
    private void sendEmailAux(String to, Set<String> ccs, Set<String> ccos, String subject, String content) throws IOException {
        Transport transport = null;
        try {
            // Create a Properties object to contain connection configuration information.
            Properties props = System.getProperties();
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtp.port", this.port);
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.auth", "true");

            // Create a Session object to represent a mail session with the specified properties.
            Session session = Session.getDefaultInstance(props);

            // Create a message with the specified information.
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(this.from, this.fromName));
            if (to != null) {
                message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            }
            if (ccs != null) {
                for (String cc : ccs) {
                    message.addRecipient(Message.RecipientType.CC, new InternetAddress(cc));
                }
            }
            if (ccos != null) {
                for (String cco : ccos) {
                    message.addRecipient(Message.RecipientType.BCC, new InternetAddress(cco));
                }
            }
            message.setSubject(subject);


            MimeMultipart multipart = new MimeMultipart("related");

            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(content, "text/html; charset=UTF-8");
            multipart.addBodyPart(messageBodyPart, 0);

            message.setContent(multipart, "text/html");

            // Create a transport.
            transport = session.getTransport();

            // Send the message.
            // Connect to Amazon SES using the SMTP username and password you specified above.
            transport.connect(this.host, this.username, this.password);

            // Send the email.
            transport.sendMessage(message, message.getAllRecipients());
        } catch (Exception e) {
            throw new IOException(e);
        } finally {
            // Close and terminate the connection.
            if (transport != null) {
                try {
                    transport.close();
                } catch (MessagingException e) {
                    throw new IOException(e);
                }
            }
        }
    }

}
