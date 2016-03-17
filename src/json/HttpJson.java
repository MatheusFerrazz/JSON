package httpjson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HttpJson {

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

        String url = "http://worldcup.sfg.io/matches";
        JSONArray matches;
        try {
            matches = new JSONArray(readUrl(url));
            for (int i = 0; i < matches.length(); i++) {
                JSONObject match = matches.getJSONObject(i);                
                JSONObject home = match.getJSONObject("home_team");
                JSONObject away = match.getJSONObject("away_team");
                
                String location = match.getString("location");
                String homeTeam = home.getString("country");
                int homeTeamGoals = home.getInt("goals");                
                String awayTeam = away.getString("country");
                int awayTeamGoals = away.getInt("goals");

                System.out.printf("Jogo %d: %s %d x %d %s [%s] \n", 
                    i+1, homeTeam, homeTeamGoals, awayTeamGoals, awayTeam, location);
            }
        } catch (JSONException e) {
            System.out.println("Erro JSON: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro geral: " + e.getMessage());
        }
    }

}
