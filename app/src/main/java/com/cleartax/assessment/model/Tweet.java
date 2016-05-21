package com.cleartax.assessment.model;

/**
 * Created by alenave on 21/05/16.
 */

import com.google.gson.annotations.SerializedName;

public class Tweet {

    @SerializedName("created_at")
    private String DateCreated;

    @SerializedName("id")
    private String Id;

    @SerializedName("text")
    private String Text;

    @SerializedName("screen_name")
    private String screenName;

    @SerializedName("name")
    private String name;

    @SerializedName("profile_image_url")
    private String profileImageUrl;

    public String getDateCreated() {
        return DateCreated;
    }

    public String getId() {
        return Id;
    }

    public String getText() {
        return Text;
    }

    public void setDateCreated(String dateCreated) {
        DateCreated = dateCreated;
    }

    public void setId(String id) {
        Id = id;
    }

    public void setText(String text) {
        Text = text;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String  toString(){
        return getText();
    }
}
