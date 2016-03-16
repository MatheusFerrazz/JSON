/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package json;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author CH
 */
public class F1json {
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
    
    public static void main(String[] args) {
        
         String url = "http://54.171.219.170/live/json/f1/2015/41565/circuit.json";
         String url2 = "http://54.171.219.170/live/json/f1/2015/41565/header_41565.json";
         String url3 = "http://54.171.219.170/live/json/f1/2015/41565/42589.json";
         String msgCompleta;
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
                        
//                System.out.println("----========Dados do GP do Brasil de F1========----");
//                System.out.println("Nome: " + objPrincipal.getString("name"));
//                System.out.println("Cidade: " + objPrincipal.getString("city"));
//                System.out.println("País: " + objPrincipal.getString("country_id"));
//                System.out.println("Curvas: " + objPrincipal.getString("bends"));
//                System.out.println("Ano de Fundação: " + objPrincipal.getString("foundation_year"));
//                System.out.println("Quantidade de Voltas: " + objPrincipal2.getString("race_laps"));
//                System.out.println("Distância do Circuito: " + objPrincipal2.getString("race_distance"));
//                System.out.println("");
             //System.out.println(msgCompleta);
             
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
//                System.out.println("Tipo do Recorde: " + obj.getString("type"));
//                System.out.println("Ano: " + obj.getString("year"));
//                System.out.println("Nome: " + obj.getString("label"));
//                System.out.println("Equipe: " + obj.getString("team"));
//                System.out.println("Nacionalidade: " + obj.getString("country_id"));
//                System.out.println("Tempo: " + obj.getString("time"));
//                System.out.println("Velocidade: " + obj.getString("average_speed") + " Km/h");
//                System.out.println("");
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
    }

}

