package DatabaseAccess;

import javax.annotation.Resource;
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
    @Resource(lookup = "mail/mySession")
    private Session mailSession;

    public EmailSender(String emailAddress, String emailMessage, String subject) {
        this.emailAddress = emailAddress;
        this.emailMessage = emailMessage;
        this.subject = subject;
    }

    public void sendEmail() throws Exception {
        String sender = "doNotReply@recipeApp.com";
        String recipient = emailAddress;
        
        try {
            MimeMessage message = new MimeMessage(mailSession);
            message.setFrom(new InternetAddress(sender));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject(subject);
            message.setText(emailMessage);

            Transport.send(message);

        } 
        catch (Exception e) {
            throw e;
        }
    }
    
}
