/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package json;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Properties;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Matheus
 */
public class JsonUI extends javax.swing.JFrame {
String msgCompleta;
    /**
     * Creates new form JsonUI
     */
    public JsonUI() {
        initComponents();
    }
    private static String readUrl(String urlString) throws Exception {
        BufferedReader reader = null;
        try {
            URL url = new URL(urlString);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuilder buffer = new StringBuilder();
            int read;
            while ((read = reader.read()) != -1) {
                buffer.append((char)read);
            }
            return buffer.toString();
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }
    
    public void pegandoDadosJson()
    {
         String url = "http://54.171.219.170/live/json/f1/2015/41565/circuit.json";
         String url2 = "http://54.171.219.170/live/json/f1/2015/41565/header_41565.json";
         String url3 = "http://54.171.219.170/live/json/f1/2015/41565/42589.json";
         // INFO SOBRE GP DO BRASIL!
         try {
                JSONObject objPrincipal2 = new JSONObject(readUrl(url2));
                JSONObject objPrincipal3 = new JSONObject(readUrl(url3));
                JSONObject objPrincipal = new JSONObject(readUrl(url));
                msgCompleta="----========Dados do GP do Brasil de F1========----"+"\n";
                msgCompleta+="Nome: " + objPrincipal.getString("name")+"\n";
                msgCompleta+="Cidade: " + objPrincipal.getString("city")+"\n";
                msgCompleta+="País: " + objPrincipal.getString("country_id")+"\n";
                msgCompleta+="Curvas: " + objPrincipal.getString("bends")+"\n";
                msgCompleta+="Ano de Fundação: " + objPrincipal.getString("foundation_year")+"\n";
                msgCompleta+="Quantidade de Voltas: " + objPrincipal2.getString("race_laps")+"\n";
                msgCompleta+="Distância do Circuito: " + objPrincipal2.getString("race_distance")+"\n";
                System.out.println("");
 
             JSONArray recordes =  objPrincipal.getJSONArray("records");
            for (int i = 0; i < recordes.length(); i++) 
            {
                JSONObject obj = recordes.getJSONObject(i);
                msgCompleta += "Tipo do Recorde: " + obj.getString("type") + "\n";
                msgCompleta += "Ano: " + obj.getString("year") + "\n";
                msgCompleta += "Nome: " + obj.getString("label") + "\n";
                msgCompleta += "Equipe: " + obj.getString("team") + "\n";
                msgCompleta += "Nacionalidade: " + obj.getString("country_id") + "\n";
                msgCompleta += "Tempo: " + obj.getString("time") + "\n";
                msgCompleta += "Velocidade: " + obj.getString("average_speed") + " Km/h" + "\n";
                msgCompleta +=" "+"\n";
            }
                msgCompleta +="----=======Dados de Qualificação========----"+ "\n";
                msgCompleta +="Dia: " + objPrincipal3.getString("date_to_print")+"\n";
                msgCompleta +="Hora Local: " + objPrincipal3.getString("time_local")+ "\n";
                msgCompleta +="Tipo do Evento: " + objPrincipal3.getString("event_type")+ "\n";
                msgCompleta +="Nivel da Qualificação: " + objPrincipal3.getString("event_description")+ "\n";
                msgCompleta +=" "+"\n";
            
            JSONArray resultados =  objPrincipal3.getJSONArray("standing");
            for (int a = 0; a < resultados.length(); a++) 
            {
                JSONObject obj = resultados.getJSONObject(a);
                
                String posicao = obj.getString("position");
                String nome = obj.getString("driver_name");
                String sobrenome = obj.getString("driver_surname");
                String equipe = obj.getString("driver_team");
                String tempo = obj.getString("time");
                msgCompleta +=posicao + " - " + tempo + " - "+ nome + " " + sobrenome + " - " + equipe+ "\n";
                
            }
                
                msgCompleta +=" "+"\n";
                msgCompleta +="----=======Dados da Corrida========----"+ "\n";
                msgCompleta +="Vencedor: " + objPrincipal2.getString("winner_name") + " " + objPrincipal2.getString("winner_surname")+ "\n";
                msgCompleta +="Tempo de Corrida: " + objPrincipal2.getString("winner_time")+ "\n";
                msgCompleta +="Velocidade Média: " + objPrincipal2.getString("winner_average")+ "\n";
                msgCompleta +="Tempo Metereológico: " + objPrincipal2.getString("meteo_id")+ "\n";
                msgCompleta +="Volta mais Rápida da Corrida: " + objPrincipal2.getString("fastestlap_name") 
                        + " " + objPrincipal2.getString("fastestlap_surname") + " - " 
                        +  objPrincipal2.getString("fastestlap_time")+ "\n";
                msgCompleta +="Média de Velocidade da Volta mais Rápida: " + objPrincipal2.getString("fastestlap_average")+ "\n";
                msgCompleta +="Foi na volta: " + objPrincipal2.getString("fastestlap_number")+ "\n";
                System.out.println(msgCompleta);
    
        } catch (JSONException e) 
        {
            System.out.println("Erro JSON: " + e.getMessage());
        } catch (Exception e) 
        {
            System.out.println("Erro geral: " + e.getMessage());
        }
         jTextAreaMsgEmail.setText(msgCompleta);
         
    }

            
    
    public void enviandoEmail()
    {
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
            Email email = new SimpleEmail();
            email.setHostName("smtp.live.com");
            email.setSmtpPort(587);
            //email.setSSLOnConnect(true);
            email.setStartTLSRequired(true);
            email.setAuthenticator(new DefaultAuthenticator(username, password));
            email.setFrom(username);
            email.setSubject(assuntoEmail.getText());
            email.setMsg(jTextAreaMsgEmail.getText());
            email.addTo(emailDestino.getText());
            email.setDebug(true);
            email.send();
            //System.out.println("Mensagem enviada.");
        } catch (EmailException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        enviar = new javax.swing.JButton();
        emailDestino = new javax.swing.JTextField();
        jLabelEmail = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaMsgEmail = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        assuntoEmail = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        json = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        enviar.setText("ENVIAR");
        enviar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                enviarMouseClicked(evt);
            }
        });
        enviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enviarActionPerformed(evt);
            }
        });

        emailDestino.setText("Ex. fulano@hotmail.com");
        emailDestino.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                emailDestinoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                emailDestinoFocusLost(evt);
            }
        });

        jLabelEmail.setText("E-mail Destino:");

        jTextAreaMsgEmail.setEditable(false);
        jTextAreaMsgEmail.setColumns(20);
        jTextAreaMsgEmail.setRows(5);
        jScrollPane1.setViewportView(jTextAreaMsgEmail);

        jLabel1.setText("Assunto E-mail:");

        jLabel2.setText("Mensagem:");

        json.setText("JSON");
        json.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jsonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel2)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(jLabel1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabelEmail)
                                    .addGap(13, 13, 13)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(emailDestino, javax.swing.GroupLayout.DEFAULT_SIZE, 385, Short.MAX_VALUE)
                                .addComponent(assuntoEmail))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(enviar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(json, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addGap(21, 21, 21))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(assuntoEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(json))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelEmail)
                    .addComponent(emailDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(enviar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void emailDestinoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_emailDestinoFocusGained
        if (emailDestino.getText().
                equals("Ex. fulano@hotmail.com")) {
            emailDestino.setText("");
        }
    }//GEN-LAST:event_emailDestinoFocusGained

    private void emailDestinoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_emailDestinoFocusLost
        if (emailDestino.getText().equals("")) {
            emailDestino.setText("Ex. fulano@hotmail.com");
        }
    }//GEN-LAST:event_emailDestinoFocusLost

    private void enviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enviarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_enviarActionPerformed

    private void enviarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_enviarMouseClicked
        
        enviandoEmail();
        // TODO add your handling code here:
    }//GEN-LAST:event_enviarMouseClicked

    private void jsonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jsonMouseClicked
        pegandoDadosJson();
        // TODO add your handling code here:
    }//GEN-LAST:event_jsonMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JsonUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JsonUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JsonUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JsonUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JsonUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField assuntoEmail;
    private javax.swing.JTextField emailDestino;
    private javax.swing.JButton enviar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabelEmail;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextAreaMsgEmail;
    private javax.swing.JButton json;
    // End of variables declaration//GEN-END:variables
}
