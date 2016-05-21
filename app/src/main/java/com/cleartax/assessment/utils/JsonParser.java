package com.cleartax.assessment.utils;

import android.util.Log;

import com.cleartax.assessment.model.Tweet;
import com.cleartax.assessment.twitter.api.TwitterRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alenave on 21/05/16.
 */
public class JsonParser {

    Tweet currItem = null;

    public JSONObject parsing(String searchTerm) throws IOException, KeyStoreException, KeyManagementException {

        return new TwitterRequest().get(searchTerm);
    }

    public List<Tweet> tweetFeedList(JSONObject jo) throws IOException {

        List<Tweet> feedList = new ArrayList<>();
        if(jo!=null) {
            try {
                if (jo.has("statuses")) {
                    JSONArray ja = jo.getJSONArray("statuses");
                    for (int i = 0; i < ja.length(); i++) {
                        JSONObject jo2 = (JSONObject) ja.get(i);
                        Log.d("Clear Object", jo2.toString());
                        currItem = new Tweet();
                        currItem.setDateCreated(jo2.getString("created_at"));
                        currItem.setId(jo2.getString("id"));
                        currItem.setText(jo2.getString("text"));
                        if (jo2.has("user")) {
                            JSONObject jsonUserObject = (JSONObject) jo2.get("user");
                            currItem.setName(jsonUserObject.getString("name"));
                            currItem.setScreenName(jsonUserObject.getString("screen_name"));
                            currItem.setProfileImageUrl(jsonUserObject.getString("profile_image_url"));
                        }
                        feedList.add(currItem);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return feedList;
    }
}
