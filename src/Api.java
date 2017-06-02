import com.google.gson.*;  //https://github.com/google/gson , version 2.8.0 was used at the time

import java.util.HashMap;
import java.util.Map;

import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Api {
    static Map<String,Integer> teams;
    static {
        teams = new HashMap<String, Integer>();
        teams.put("cavaliers",1610612739);
        teams.put("raptors",1610612761);
        teams.put("celtics",1610612738);
        teams.put("hawks",1610612737);
        teams.put("wizards",1610612764);
        teams.put("hornets",1610612766);
        teams.put("pacers",1610612754);
        teams.put("bulls",1610612741);
        teams.put("bucks",1610612749);
        teams.put("pistons",1610612765);
        teams.put("knicks",1610612752);
        teams.put("magic",1610612753);
        teams.put("76ers",1610612755);
        teams.put("heat",1610612748);
        teams.put("nets",1610612751);
        teams.put("warriors",1610612744);
        teams.put("spurs",1610612759);
        teams.put("rockets",1610612745);
        teams.put("clippers",1610612746);
        teams.put("jazz",1610612762);
        teams.put("thunder",1610612760);
        teams.put("grizzlies",1610612763);
        teams.put("nuggets",1610612743);
        teams.put("trail blazers",1610612757);
        teams.put("pelicans",1610612740);
        teams.put("kings",1610612758);
        teams.put("timberwolves",1610612750);
        teams.put("mavericks",1610612742);
        teams.put("lakers",1610612747);
        teams.put("suns",1610612756);
    }


    public String[] teaminfo(String name) throws Exception
    {
        String[] result = new String[4];
        System.out.println(name + ' ' + teams.get(name));
        String urls = "http://stats.nba.com/stats/teaminfocommon/?Season=2016-17&LeagueID=00&SeasonType=Regular%20Season&TeamID="+teams.get(name);
        URL url = new URL(urls);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String str = in.readLine();

        JsonObject jsonObject = new JsonParser().parse(str).getAsJsonObject();

        JsonArray ja = jsonObject.getAsJsonArray("resultSets");
        JsonObject j0 = ja.get(0).getAsJsonObject();
        JsonArray j1 = j0.getAsJsonArray("rowSet");
        JsonArray j2 = j1.get(0).getAsJsonArray();
        String city = j2.get(2).getAsString();
        String nme = j2.get(3).getAsString();
        String conf = j2.get(5).getAsString();
        Integer win = j2.get(8).getAsInt();
        Integer lose = j2.get(9).getAsInt();
        Integer rank = j2.get(11).getAsInt();

        con.disconnect();
        in.close();

        urls = "http://stats.nba.com/stats/teamgamelog/?Season=2016-17&SeasonType=Regular%20Season&TeamID="+teams.get(name);
        url = new URL(urls);
        con = (HttpURLConnection) url.openConnection();

        in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        str = in.readLine();

        jsonObject = new JsonParser().parse(str).getAsJsonObject();

        ja = jsonObject.getAsJsonArray("resultSets");
        j0 = ja.get(0).getAsJsonObject();
        j1 = j0.getAsJsonArray("rowSet");
        String l5g = "";
        for (int i=0; i<5; i++) {
            j2 = j1.get(i).getAsJsonArray();
            l5g = j2.get(4).getAsCharacter() + l5g;
        }

        result[0]=city + ' ' + nme + "  " + conf + "ern Conference";
        result[1]="Current record: " + win + '-' + lose;
        result[2]="Current conference rank: " + rank;
        result[3]=("Last 5 games: " + l5g);


        return result;
    }

}