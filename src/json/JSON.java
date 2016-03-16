/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package json;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import org.json.*;

/**
 *
 * @author Matheus
 */
public class JSON {
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

        String url = "http://i3geo.saude.gov.br/i3geo/ogc.php?service=WFS&version=1.0.0&request=GetFeature&typeName=upa_funcionamento_cnes&outputFormat=JSON";
        String json = "{\"type\":\"FeatureCollection\",\"features\":[{\"type\":\"Feature\",\"properties\":[{\"gid\":\"7206445\"},{\"co_cep\":\"14150-000\"},{\"uf\":\"SP\"},{\"cidade\":\"Serrana\"},{\"no_fantasia\":\"UPA DR GERALDO C P REIS\"},{\"no_bairro\":\"JD ROMULO MONTANARI\"},{\"nu_endereco\":\"235\"},{\"no_logradouro\":\"GABRIEL JOSE DO VALE\"},{\"nu_telefone\":\"(16) 3987-1513\"},{\"ano_upa_func\":\"2016\"},{\"mes_upa_func\":\"1\"},{\"fonte_recurso\":\"MS\"},{\"porte\":\"1\"}],\"geometry\":{\"type\":\"Point\",\"coordinates\":[-47.602643966675,-21.208098872715]}}]}";
 
//        JSONObject objPrincipal;
//        JSONArray arrayFeatures;
//        JSONArray arrayProperties;
        try {
            JSONObject objPrincipal = new JSONObject(readUrl(url));
            JSONArray arrayFeatures =  objPrincipal.getJSONArray("features");
            for (int i = 0; i < arrayFeatures.length(); i++) 
            {
                JSONObject obj = arrayFeatures.getJSONObject(i);
                System.out.println("id: " + (i+1) + " type: " + obj.getString("type"));
                JSONArray arrayProperties = obj.getJSONArray("properties");
                
                for(int a = 0; a < arrayProperties.length(); a++)
                {
                   JSONObject objPro = arrayProperties.getJSONObject(a);
                   System.out.println("gid: " + objPro.getString("gid"));
                    
//                    JSONObject gid = objPro.getJSONObject("gid");
//                    System.out.println("cep: " + gid.getString("co_cep"));
//                    System.out.println("uf: " + gid.getString("uf"));
//                    System.out.println("cidade: " + gid.getString("cidade"));
//                    System.out.println("nome: " + gid.getString("no_fantasia"));
//                    System.out.println("bairro: " + gid.getString("no_bairro"));
//                    System.out.println("endereço: " + gid.getString("nu_endereco"));
//                    System.out.println("rua: " + gid.getString("no_logradouro"));
//                    System.out.println("telefone: " + gid.getString("nu_telefone"));
//                    System.out.println("ano: " + gid.getString("ano_upa_func"));
//                    System.out.println("mês: " + gid.getString("mes_upa_func"));
//                    System.out.println("recurso: " + gid.getString("fonte_recurso"));
//                    System.out.println("porte: " + gid.getString("porte"));
                }
            }
        } catch (JSONException e) 
        {
            System.out.println("Erro JSON: " + e.getMessage());
        } catch (Exception e) 
        {
            System.out.println("Erro geral: " + e.getMessage());
        }
    }

}