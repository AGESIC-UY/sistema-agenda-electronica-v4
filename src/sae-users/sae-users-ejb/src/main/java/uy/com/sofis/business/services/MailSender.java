/*
 * Sofis Solutions
 */
package uy.com.sofis.business.services;

import java.util.Properties;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import javax.mail.util.ByteArrayDataSource;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.annotation.Resource;
import javax.ejb.LocalBean;
import uy.com.sofis.tipos.SgMail;
import uy.com.sofis.tipos.SgMailFile;

/**
 *
 * @author Sofis Solutions
 */
@Stateless
@LocalBean
public class MailSender {
    
    private static final Logger LOGGER = Logger.getLogger(MailSender.class.getName());
    
    @Resource(name = "java:/sae/mail")
    private Session session;

    public void sendMail(SgMail mail) throws Exception {
        //TODO: agregar config para hab y desh envío
        if (mail.getRecipients() != null && mail.getRecipients().length > 0) {
            Properties props = session.getProperties();
            if (session.getProperty("mail.smtp.ssl.trust") != null) {
                props.put("mail.smtp.ssl.trust", session.getProperty("mail.smtp.ssl.trust"));
            }
            session.getInstance(props);

            Message msg = new MimeMessage(session);
            msg.setSubject(MimeUtility.encodeText(mail.getSubject(), "utf8", "B"));


            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(mail.getMessage(), "text/html; charset=\"" + "utf8" + "\"");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);

            if (mail.getFiles() != null) {
                for (SgMailFile mf : mail.getFiles()) {
                    if (mf.getFileContent() != null && mf.getFileContent().length > 0 && mf.getFileName() != null) {
                        messageBodyPart = new MimeBodyPart();
                        DataSource source = new ByteArrayDataSource(mf.getFileContent(), "application/octet-stream");
                        messageBodyPart.setDataHandler(new DataHandler(source));
                        messageBodyPart.setFileName(mf.getFileName());
                        multipart.addBodyPart(messageBodyPart);
                    }
                }
            }
            msg.setContent(multipart);

            InternetAddress addressFrom = new InternetAddress(session.getProperty("mail.from"));
            addressFrom.setPersonal(session.getProperty("mail.from"));
            msg.setFrom(addressFrom);
            InternetAddress[] addressTo = new InternetAddress[mail.getRecipients().length];
            for (int i = 0; i < mail.getRecipients().length; i++) {
                addressTo[i] = new InternetAddress(mail.getRecipients()[i]);
            }
            msg.setRecipients(Message.RecipientType.TO, addressTo);
            msg.saveChanges();
            Transport.send(msg);

        }
    }
}
