package com.epicodus.airdd.services;

import android.util.Log;

import com.epicodus.airdd.Constants;
import com.epicodus.airdd.models.Game;
import com.epicodus.airdd.models.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MeetupService {

    public static void findGames(String location, Callback callback) {
//        OkHttpOAuthConsumer consumer = new OkHttpOAuthConsumer(Constants.API_CONSUMER_KEY, Constants.API_CONSUMER_SECRET);
//        consumer.setTokenWithSecret(Constants.API_TOKEN, Constants.API_TOKEN_SECRET);

        OkHttpClient client = new OkHttpClient.Builder()
//                .addInterceptor(new SigningInterceptor(consumer))
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.API_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.API_KEY_QUERY_PARAMETER, "2b40a421d52474851557b20da7076");
        urlBuilder.addQueryParameter(Constants.API_ZIP_QUERY_PARAMETER, "97215");
        urlBuilder.addQueryParameter(Constants.API_GROUP_URLNAME_QUERY_PARAMETER, "dndaloregon");
        urlBuilder.addQueryParameter("sign", "true");
        String url = urlBuilder.build().toString();


//        Log.d("MeetupService", url);


        Request request= new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }



    public ArrayList<Game> processResults(Response response) {
        ArrayList<Game> games = new ArrayList<>();
        try {
            String jsonData = response.body().string();




            jsonData = "{\n" +
                    "\t\"results\": [{\n" +
                    "\t\t\"utc_offset\": -28800000,\n" +
                    "\t\t\"venue\": {\n" +
                    "\t\t\t\"zip\": \"97030\",\n" +
                    "\t\t\t\"country\": \"us\",\n" +
                    "\t\t\t\"localized_country_name\": \"USA\",\n" +
                    "\t\t\t\"city\": \"Gresham\",\n" +
                    "\t\t\t\"address_1\": \"475 NE Burnside\",\n" +
                    "\t\t\t\"lon\": -122.42598,\n" +
                    "\t\t\t\"phone\": \"503-492-8492\",\n" +
                    "\t\t\t\"name\": \"Nexus Games\",\n" +
                    "\t\t\t\"id\": 466605,\n" +
                    "\t\t\t\"state\": \"OR\",\n" +
                    "\t\t\t\"lat\": 45.508695,\n" +
                    "\t\t\t\"repinned\": false\n" +
                    "\t\t},\n" +
                    "\t\t\"rsvp_limit\": 8,\n" +
                    "\t\t\"headcount\": 0,\n" +
                    "\t\t\"visibility\": \"public\",\n" +
                    "\t\t\"waitlist_count\": 0,\n" +
                    "\t\t\"created\": 1480412450000,\n" +
                    "\t\t\"maybe_rsvp_count\": 0,\n" +
                    "\t\t\"description\": \"<p>D&amp;D Adventurer's League Encounters is a public D&amp;D campaign. Each season storyline visits a different area of the Moonsea, affecting some change there, and all the factions are involved. As you defeat enemies, solve puzzles, finish quests, and perform heroic deeds, you’ll earn experience and renown that you can take with you to other D&amp;D Adventurer's League Events!<\\/p> <p>Bring your D&amp;D Adventurer's League character and Adventurers League Log sheet. If you have not played any Adventurers League Events, such as D&amp;D Expeditions or Encounters, you can bring a new level 1 character, or a pre-generated character can be provided for you. Visit dnd.wizards.com for rules and the Adventurers League Players Guide.<\\/p> <p>---------------<\\/p> <p>DM and module per session will be posted in the comments section.<\\/p> <p>RSVP guarantees your seat until the posted start time.<\\/p> <p>Late arrivals are not guaranteed to be seated.<\\/p> <p><i>We encourage ALL Adventurer's League (Encounters &amp; Expedition) players to <\\/i><b><i>make a purchase to support the store.<\\/i><\\/b><\\/p> <p>---------------<\\/p> <p>John Hescock is the DM For this Campaign. We are currently running a tier 1 campaign and recommend bringing level 1 characters.<\\/p> <p>\\n\\n\\nEveryone is expected to be there by 4:45pm or get in touch with the GM. Game promptly starts at 5pm.<\\/p> <p><br\\/>Follow League news, get updates about local conventions, participate in Adventures League surveys that will actually affect the League and the games you play in! Be part of this on our <a href=\\\"https:\\/\\/www.facebook.com\\/groups\\/dndaloregon\\/\\\">Adventurers League Oregon Facebook group!<\\/a><\\/p> <p>------------------------------------<\\/p> <p>Current Season: Storm Kings Thunder<\\/p> <p><img src=\\\"https:\\/\\/a248.e.akamai.net\\/secure.meetupstatic.com\\/photos\\/event\\/6\\/d\\/c\\/c\\/600_453628108.jpeg\\\" \\/> Hill giants are stealing all the grain and livestock they can while stone giants have been scouring settlements that have been around forever. Fire giants are press-ganging the smallfolk into the desert, while frost giant longships have been pillaging along the Sword Coast. Even the elusive cloud giants have been witnessed, their wondrous floating cities appearing above Waterdeep and Baldur’s Gate. Where is the storm giant King Hekaton, who is tasked with keeping order among the giants? <br\\/>The humans, dwarves, elves, and other small folk of the Sword Coast will be crushed underfoot from the onslaught of these giant foes. The only chance at survival is for the small folk to work together to investigate this invasion and harness the power of rune magic, the giants’ weapon against their ancient enemy the dragons. The only way the people of Faerun can restore order is to use the giants’ own power against them. <\\/p>\",\n" +
                    "\t\t\"how_to_find_us\": \"475 NE Burnside, Gresham, OR\",\n" +
                    "\t\t\"event_url\": \"https:\\/\\/www.meetup.com\\/dndaloregon\\/events\\/235983952\\/\",\n" +
                    "\t\t\"yes_rsvp_count\": 8,\n" +
                    "\t\t\"duration\": 15300000,\n" +
                    "\t\t\"name\": \"Gresham:\u00AD\u00AD Storm King's Thunder - Nexus Games\",\n" +
                    "\t\t\"id\": \"hdqtgmyvqblb\",\n" +
                    "\t\t\"time\": 1481244300000,\n" +
                    "\t\t\"updated\": 1481226038000,\n" +
                    "\t\t\"group\": {\n" +
                    "\t\t\t\"join_mode\": \"open\",\n" +
                    "\t\t\t\"created\": 1410970027000,\n" +
                    "\t\t\t\"name\": \"D&D Adventurers League - Oregon\",\n" +
                    "\t\t\t\"group_lon\": -122.5999984741211,\n" +
                    "\t\t\t\"id\": 17050692,\n" +
                    "\t\t\t\"urlname\": \"dndaloregon\",\n" +
                    "\t\t\t\"group_lat\": 45.4900016784668,\n" +
                    "\t\t\t\"who\": \"Adventurers\"\n" +
                    "\t\t},\n" +
                    "\t\t\"status\": \"upcoming\"\n" +
                    "\t}]\n" +
                    "}";




            Log.d("MeetupService", jsonData);
            if (response.isSuccessful()) {
                JSONObject meetupJSON = new JSONObject(jsonData);
                JSONArray gamesJSON = meetupJSON.getJSONArray("results");
                for (int i = 0; i < gamesJSON.length(); i++) {
                    JSONObject gameJSON = gamesJSON.getJSONObject(i);

                    String hostUsername = "HOST";
                    String hostPassword = "HOST USER PASSWORD";
                    String address_1 = gameJSON.getJSONObject("venue").getString("address_1");
                    String city = gameJSON.getJSONObject("venue").getString("city");
                    String state = gameJSON.getJSONObject("venue").getString("state");
                    String zip = gameJSON.getJSONObject("venue").getString("zip");

                    User host = new User(hostUsername, hostPassword);
                    Boolean ownerDM = true;
                    String title = gameJSON.getString("name");
                    String description = gameJSON.getString("description");
                    String location = address_1 + " " + city + ", " + state + " " + zip;
                    int dateTime = gameJSON.getInt("time");

//                    Log.d("MeetupService", address_1);
//                    Log.d("MeetupService", city);
//                    Log.d("MeetupService", state);
//                    Log.d("MeetupService", zip);
//                    Log.d("MeetupService", title);
//                    Log.d("MeetupService", description);
//                    Log.d("MeetupService", dateTime+"");






                    Game game = new Game(host, ownerDM, title, description, location, dateTime+"");

                    games.add(game);

//                    Log.v("MeetupService", game.getTitle());
                }
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
//            Log.v("MeetupService", "Yup, this is the catch!");
        }
        return games;
    }


}
