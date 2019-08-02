package DatabaseAccess;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author dapfe
 */
public class EmailSender {
    
    private final String emailAddress;
    private final String emailMessage;
    private final String subject;


    public EmailSender(String emailAddress, String emailMessage, String subject) {
        this.emailAddress = emailAddress;
        this.emailMessage = emailMessage;
        this.subject = subject;
    }

    public void sendEmail() throws Exception {
        String sender = "recipeappmailserver@gmail.com";
        String senderPassword = "recipeApp1234";
        String recipient = emailAddress;
        
        try {
            Properties mailServerProperties = System.getProperties();
            mailServerProperties.put("mail.smtp.port", "587");
	    mailServerProperties.put("mail.smtp.auth", "true");
	    mailServerProperties.put("mail.smtp.starttls.enable", "true");
            mailServerProperties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
 
	    Session session = Session.getDefaultInstance(mailServerProperties, null);
	    MimeMessage message = new MimeMessage(session);
	    message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
	    message.setSubject(subject);
	    String emailBody = emailMessage;
	    message.setContent(emailBody, "text/plain");

            Transport transport = session.getTransport("smtp");
            transport.connect("smtp.gmail.com",sender,senderPassword);
            transport.sendMessage(message,message.getAllRecipients());

        } 
        catch (Exception e) {
            throw e;
        }
    }
    
}
