/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mail;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 *
 * @author khanhhoang
 */
public class Email {
    private static final String from = "lehoangkhanh209@gmail.com";
    private static final String password = "bnoudxqggsdznjrk";
    
    public boolean sendEmail(String to, String content) {
        boolean check = false;
        
        //Properties: Create properties
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP HOST
        props.put("mail.smtp.port", "587"); //TLS 587 SSL 465
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        
        //Create Authenticator
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
            
        };
        
        //Create session to send email
        Session session = Session.getInstance(props, auth);
        
        //Send email
        MimeMessage msg = new MimeMessage(session);
        try {
            //From 
            msg.setFrom(from);
 
            //To
            
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
            
            //Header email
            msg.setSubject("Your Password Was Sent To You");
            
            //Content
            msg.setText(content, "UTF-8");
            
            Transport.send(msg);
            
            check = true;
            
        } catch (Exception e) {
            e.printStackTrace();
            check = false;
        }
        
        return check;
    }
}
