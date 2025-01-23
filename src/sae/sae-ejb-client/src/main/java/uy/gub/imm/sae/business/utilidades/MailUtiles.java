package uy.gub.imm.sae.business.utilidades;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.InitialContext;

public class MailUtiles {

    private static final Logger LOGGER = Logger.getLogger(MailUtiles.class.getName());
    
    public static final String CONTENT_TYPE_PLAIN = "text/plain; charset=UTF-8";
    public static final String CONTENT_TYPE_HTML = "text/html; charset=UTF-8";

    public static void enviarMail(final String to, final String subject, final String content, final String type) throws MessagingException {
        (new Thread() {
            @SuppressWarnings("UseSpecificCatch")
            @Override
            public void run() {
                try {

                    InitialContext ic = new InitialContext();
                    Session mailSession = (Session) ic.lookup("java:/sae/mail");
                    ic.close();

                    String sFrom = mailSession.getProperty("mail.smtp.user");

                    MimeMessage m = new MimeMessage(mailSession);
                    Address from = new InternetAddress(sFrom);
                    Address[] toList = new InternetAddress[]{new InternetAddress(to)};

                    m.setFrom(from);
                    m.setRecipients(Message.RecipientType.TO, toList);
                    m.setSubject(subject);
                    m.setSentDate(new java.util.Date());
                    m.setContent(content, type);

                    Transport.send(m);
                } catch (Exception ex) {
                    LOGGER.log(Level.SEVERE, "No se pudo enviar el mail", ex);
                }
            }
        }).start();
    }

}
