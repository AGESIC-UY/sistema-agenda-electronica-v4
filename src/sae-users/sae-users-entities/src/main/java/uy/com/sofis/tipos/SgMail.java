/*
 * Sofis Solutions
 */
package uy.com.sofis.tipos;

import java.util.List;

/**
 *
 * @author USUARIO
 */
public class SgMail {
    
    private String subject;
    private String[] recipients; 
    private String message; 
    private List<SgMailFile> files;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String[] getRecipients() {
        return recipients;
    }

    public void setRecipients(String[] recipients) {
        this.recipients = recipients;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<SgMailFile> getFiles() {
        return files;
    }

    public void setFiles(List<SgMailFile> files) {
        this.files = files;
    }
    
    
    
    
}
