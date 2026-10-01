package vn.iotstar.util;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.io.InputStream;
import java.util.Properties;

public class XMail_24110187 {

    private static final Properties MAIL_CONFIG = new Properties();

    static {
        try (InputStream is = XMail_24110187.class.getClassLoader()
                .getResourceAsStream("mail.properties")) {
            if (is != null) MAIL_CONFIG.load(is);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void send(String to, String subject, String htmlBody) {
        try {
            Properties props = new Properties();
            props.put("mail.smtp.host", MAIL_CONFIG.getProperty("mail.host"));
            props.put("mail.smtp.port", MAIL_CONFIG.getProperty("mail.port"));
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.ssl.trust", MAIL_CONFIG.getProperty("mail.host"));

            final String username = MAIL_CONFIG.getProperty("mail.username");
            final String password = MAIL_CONFIG.getProperty("mail.password");

            Session session = Session.getInstance(props, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(
                    MAIL_CONFIG.getProperty("mail.from"),
                    MAIL_CONFIG.getProperty("mail.fromName")));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setContent(htmlBody, "text/html; charset=UTF-8");

            Transport.send(message);
            System.out.println("✅ Đã gửi mail đến: " + to);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Gửi mail thất bại", e);
        }
    }
}