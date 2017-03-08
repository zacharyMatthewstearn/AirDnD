package com.epicodus.airdd.services;

public class MeetupService {
//
//    public static void findGames(Callback callback) {
//
//        OkHttpClient client = new OkHttpClient.Builder()
//                .build();
//
//        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.API_BASE_URL).newBuilder();
//        urlBuilder.addQueryParameter(Constants.API_KEY_QUERY_PARAMETER, Constants.API_KEY);
//        urlBuilder.addQueryParameter(Constants.API_GROUP_URLNAME_QUERY_PARAMETER, "dndaloregon");
//        urlBuilder.addQueryParameter(Constants.API_SIGN_QUERY_PARAMETER, "true");
//        urlBuilder.addQueryParameter(Constants.API_PAGE_QUERY_PARAMETER, "10");
//        String url = urlBuilder.build().toString();
//
//        Request request= new Request.Builder()
//                .url(url)
//                .build();
//
//        Call call = client.newCall(request);
//        call.enqueue(callback);
//    }
//
//    public ArrayList<Game> processResults(Response response) {
//        ArrayList<Game> games = new ArrayList<>();
//        try {
//            String jsonData = response.body().string();
//
//            if (response.isSuccessful()) {
//                JSONObject meetupJSON = new JSONObject(jsonData);
//                JSONArray gamesJSON = meetupJSON.getJSONArray("results");
//
//                for (int i = 0; i < gamesJSON.length(); i++) {
//                    JSONObject gameJSON = gamesJSON.getJSONObject(i);
//
//                    String address_1 = "";
//                    String city = "";
//                    String state = "";
//                    String zip = "";
//                    Boolean ownerDM = true;
//                    String title = "";
//                    String description = "";
//                    int dateTime = 0;
//
//                    try {
//                        JSONObject venueTest = gameJSON.getJSONObject("venue");
//                        try {
//                            address_1 = gameJSON.getJSONObject("venue").getString("address_1");
//                        }
//                        catch(JSONException e) {
//                            e.printStackTrace();
//                        }
//                        try {
//                            city = gameJSON.getJSONObject("venue").getString("city");
//                        }
//                        catch(JSONException e) {
//                            e.printStackTrace();
//                        }
//                        try {
//                            state = gameJSON.getJSONObject("venue").getString("state");
//                        }
//                        catch(JSONException e) {
//                            e.printStackTrace();
//                        }
//                        try {
//                            zip = gameJSON.getJSONObject("venue").getString("zip");
//                        }
//                        catch(JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                    catch(JSONException e) {
//                        e.printStackTrace();
//                    }
//                    try {
//                        title = gameJSON.getString("name");
//                    }
//                    catch(JSONException e) {
//                        e.printStackTrace();
//                    }
//                    try {
//                        description = gameJSON.getString("description");
//                    }
//                    catch(JSONException e) {
//                        e.printStackTrace();
//                    }
//                    try {
//                        dateTime = gameJSON.getInt("time");
//                    }
//                    catch(JSONException e) {
//                        e.printStackTrace();
//                    }
//
//                    String location = address_1 + " " + city + ", " + state + " " + zip;
//
//                    Game game = new Game(title, description, location, dateTime+"", ownerDM);
//                    games.add(game);
//                }
//            }
//        } catch (IOException | JSONException e) {
//            e.printStackTrace();
//        }
//        return games;
//    }
}
