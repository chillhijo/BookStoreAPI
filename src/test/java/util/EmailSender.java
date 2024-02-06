package util;

import org.testng.annotations.Test;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

public class EmailSender {

    @Test
    public void sendEmailReport() {
        // Sender's Gmail credentials
        String fromEmail = "pralicadusko93@gmail.com";
        String password = "hgno qmfr jefa dxku"; // Use App Password if 2FA is enabled

        // Recipient's email address
        String toEmail = "duskokona93@gmail.com";

        // Email properties
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Create a session with the Gmail server
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });

        try {
            // Create a MimeMessage object
            Message message = new MimeMessage(session);

            // Set the sender and recipient addresses
            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));

            // Set the email subject
            message.setSubject("Test Report");

            // Create a Multipart object to add the text and attachment parts
            Multipart multipart = new MimeMultipart();

            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setText("This is a test email sent from Java using SMTP and inside should be a test report attachment");
            multipart.addBodyPart(textPart);

            // Attachment part
            MimeBodyPart attachmentPart1 = new MimeBodyPart();
            String filePath1 = "test-output/emailable-report.html";
            attachmentPart1.attachFile(filePath1);
            multipart.addBodyPart(attachmentPart1);

            MimeBodyPart attachmentPart2 = new MimeBodyPart();
            String filePath2 = "test-output/testng-results.xml";
            attachmentPart2.attachFile(filePath2);
            multipart.addBodyPart(attachmentPart2);

            message.setContent(multipart);

            // Send the email
            Transport.send(message);

            System.out.println("Email sent successfully.");

        } catch (MessagingException | java.io.IOException e) {
            e.printStackTrace();
        }
    }
}
