/*
 */
package ch.unibe.iwiqa.web.mail;

import java.util.Date;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;

/**
 *
 * @author Marc Jost
 */
@RequestScoped
public class MailSenderBean {

    private Properties mailProperties;

    @PostConstruct
    private void init() {
        mailProperties = MailPropertiesReader.readProperties();
    }

    public void send(String subject, String to, String mailBody) {
        try {

            Properties props = System.getProperties();
            props.put("mail.smtp.host", mailProperties.getProperty("mail.host"));

            Session session = Session.getInstance(props, null);

            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(mailProperties.getProperty("mail.from")));
            
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
            
            msg.setSubject(subject);
            
            msg.setDataHandler(new DataHandler(new ByteArrayDataSource(mailBody, "text/html")));
            
            msg.setHeader("X-Mailer", "msgsend");
            msg.setSentDate(new Date());
            
            Transport.send(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
