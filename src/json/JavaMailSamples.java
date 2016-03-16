package javamailsamples;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class JavaMailSamples {

    public static void main(String[] args) {

        Properties login = new Properties();
        String properties = "javamailsamples/login.properties";
        try {
            InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream(properties);
            login.load(stream);
        } catch (IOException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
        
        Properties props = new Properties();
        
        // JangoSMTP
        /*props.put("mail.smtp.host", "relay.jangosmtp.net");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "25");
        props.put("mail.smtp.starttls.enable", "true");
        String username = login.getProperty("jango.username");
        String password = login.getProperty("jango.password");*/
        
        // Yahoo
        /*props.put("mail.smtp.host", "smtp.mail.yahoo.com.br");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "25");
        String username = login.getProperty("yahoo.username");
        String password = login.getProperty("yahoo.password");*/

        // Hotmail
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.host", "smtp.live.com");
        props.put("mail.smtp.socketFactory.port", "587");
        props.put("mail.smtp.socketFactory.fallback", "false");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "587");
        String username = login.getProperty("hotmail.username");
        String password = login.getProperty("hotmail.password");
      
        // GMail
        /*props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        //props.put("mail.smtp.socketFactory.port", "465");
        //props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        String username = login.getProperty("gmail.username");
        String password = login.getProperty("gmail.password");*/
        
        // Iniciando sessão
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        };
        Session session = Session.getDefaultInstance(props, auth);
        session.setDebug(true);

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("email@hotmail.com"));
            message.addRecipients(Message.RecipientType.TO, InternetAddress.parse("email1@gmail.com"));
            message.addRecipients(Message.RecipientType.TO, InternetAddress.parse("email2@gmail.com"));
            message.setSubject("Enviando email com JavaMail via JangoSMTP");
            String msg = "Enviei este <i>e-mail</i> utilizando <b>JavaMail</b>.";
            message.setContent(msg, "text/html");
            Transport.send(message);
            System.out.println("Mensagem enviada.");

        } catch (MessagingException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
    }

}
