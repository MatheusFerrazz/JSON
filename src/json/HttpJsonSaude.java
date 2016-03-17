package httpjson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HttpJsonSaude {

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
        try {
            JSONObject features = new JSONObject(readUrl(url));
            JSONArray arrayFeatures = features.getJSONArray("features");
            for (int i = 0; i < arrayFeatures.length(); i++) {
                JSONObject objUpas = arrayFeatures.getJSONObject(i);
                JSONArray arrayUpas = objUpas.getJSONArray("properties");
                
                String nomeFantasia = arrayUpas.getJSONObject(4).getString("no_fantasia");
                String cidade = arrayUpas.getJSONObject(3).getString("cidade");
                String uf = arrayUpas.getJSONObject(2).getString("uf");
                String bairro = arrayUpas.getJSONObject(5).getString("no_bairro");
                String fone = arrayUpas.getJSONObject(8).getString("nu_telefone");
                
                System.out.printf("%s (%s - %s) \nBairro: %s - Telefone: %s \n\n", 
                    nomeFantasia, cidade, uf, bairro, fone);
            }            
        } catch (JSONException e) {
            System.out.println("Erro JSON: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro geral: " + e.getMessage());
        }
    }

}
