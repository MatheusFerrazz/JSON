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
         // INFO SOBRE GP DO BRASIL!
         try {
                JSONObject objPrincipal2 = new JSONObject(readUrl(url2));
                JSONObject objPrincipal3 = new JSONObject(readUrl(url3));
                JSONObject objPrincipal = new JSONObject(readUrl(url));
                System.out.println("----========Dados do GP do Brasil de F1========----");
                System.out.println("Nome: " + objPrincipal.getString("name"));
                System.out.println("Cidade: " + objPrincipal.getString("city"));
                System.out.println("País: " + objPrincipal.getString("country_id"));
                System.out.println("Curvas: " + objPrincipal.getString("bends"));
                System.out.println("Ano de Fundação: " + objPrincipal.getString("foundation_year"));
                System.out.println("Quantidade de Voltas: " + objPrincipal2.getString("race_laps"));
                System.out.println("Distância do Circuito: " + objPrincipal2.getString("race_distance"));
                System.out.println("");
             
             
             JSONArray recordes =  objPrincipal.getJSONArray("records");
            for (int i = 0; i < recordes.length(); i++) 
            {
                JSONObject obj = recordes.getJSONObject(i);
                System.out.println("Tipo do Recorde: " + obj.getString("type"));
                System.out.println("Ano: " + obj.getString("year"));
                System.out.println("Nome: " + obj.getString("label"));
                System.out.println("Equipe: " + obj.getString("team"));
                System.out.println("Nacionalidade: " + obj.getString("country_id"));
                System.out.println("Tempo: " + obj.getString("time"));
                System.out.println("Velocidade: " + obj.getString("average_speed") + " Km/h");
                System.out.println("");
            }
                System.out.println("----=======Dados de Qualificação========----");
                System.out.println("Dia: " + objPrincipal3.getString("date_to_print"));
                System.out.println("Hora Local: " + objPrincipal3.getString("time_local"));
                System.out.println("Tipo do Evento: " + objPrincipal3.getString("event_type"));
                System.out.println("Nivel da Qualificação: " + objPrincipal3.getString("event_description"));
                System.out.println("");
            
            JSONArray resultados =  objPrincipal3.getJSONArray("standing");
            for (int a = 0; a < resultados.length(); a++) 
            {
                JSONObject obj = resultados.getJSONObject(a);
                
                String posicao = obj.getString("position");
                String nome = obj.getString("driver_name");
                String sobrenome = obj.getString("driver_surname");
                String equipe = obj.getString("driver_team");
                String tempo = obj.getString("time");
                System.out.println(posicao + " - " + tempo + " - "+ nome + " " + sobrenome + " - " + equipe);
            }
                System.out.println("");
                System.out.println("----=======Dados da Corrida========----");
                System.out.println("Vencedor: " + objPrincipal2.getString("winner_name") + " " + objPrincipal2.getString("winner_surname") );
                System.out.println("Tempo de Corrida: " + objPrincipal2.getString("winner_time"));
                System.out.println("Velocidade Média: " + objPrincipal2.getString("winner_average"));
                System.out.println("Tempo Metereológico: " + objPrincipal2.getString("meteo_id"));
                System.out.println("Volta mais Rápida da Corrida: " + objPrincipal2.getString("fastestlap_name") 
                        + " " + objPrincipal2.getString("fastestlap_surname") + " - " 
                        +  objPrincipal2.getString("fastestlap_time"));
                System.out.println("Média de Velocidade da Volta mais Rápida: " + objPrincipal2.getString("fastestlap_average"));
                System.out.println("Foi na volta: " + objPrincipal2.getString("fastestlap_number"));
    
        } catch (JSONException e) 
        {
            System.out.println("Erro JSON: " + e.getMessage());
        } catch (Exception e) 
        {
            System.out.println("Erro geral: " + e.getMessage());
        }
    }

}

