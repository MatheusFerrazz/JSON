package json;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class JavaMailApache {
    public static void main(String[] args) {

        Properties login = new Properties();
        String properties = "javamailsamples/login.properties";
        try {
            InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream(properties);
            login.load(stream);
        } catch (IOException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
        String username = login.getProperty("hotmail.username");
        String password = login.getProperty("hotmail.password");

        try {
            System.out.println("Enviando e-mail...");
            Email email = new SimpleEmail();
            email.setHostName("smtp.live.com");
            email.setSmtpPort(587);
            //email.setSSLOnConnect(true);
            email.setStartTLSRequired(true);
            email.setAuthenticator(new DefaultAuthenticator(username, password));
            email.setFrom(username);
            email.setSubject("Enviando e-mail com Apache Commons-email");
            email.setMsg("E-mail enviado a partir do Apache Commons-email.");
            email.addTo("email@gmail.com");
            email.setDebug(true);
            email.send();
            System.out.println("Mensagem enviada.");
        } catch (EmailException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
    }
}
