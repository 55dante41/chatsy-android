package com.limelitelabs.chatsy.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A Model class for holding a Group object
 */
public class Group {
    public String _id, name, createdBy;
    public boolean isPrivate, isVisible;
    public String description;
    public Date createdOn;
    public JSONArray tags;
    private String[] admins, authorizedUsers, unauthorizedUsers;

    public Group() {

    }

    public Group(String name, String description, String createdBy, Date createdOn, boolean isPrivate, boolean isVisible, JSONArray tags){
        this.name = name;
        this.createdBy = createdBy;
        this.description = description;
        this.createdOn = createdOn;
        this.isPrivate = isPrivate;
        this.isVisible = isVisible;
        this.tags = tags;
    }

    public String[] getAdmins() {
        if(admins!=null) {
            return this.admins;
        }
        return new String[0];
    }

    public void setAdmins(String[] admins) {
        this.admins = admins;
    }

    public String[] getAuthorizedUsers() {
        if(admins!=null) {
            return this.authorizedUsers;
        }
        return new String[0];
    }

    public void setAuthorizedUsers(String[] authorizedUsers) {
        this.authorizedUsers = authorizedUsers;
    }

    public String[] getUnauthorizedUsers() {
        if(admins!=null) {
            return this.unauthorizedUsers;
        }
        return new String[0];
    }

    public void setUnauthorizedUsers(String[] unauthorizedUsers) {
        this.unauthorizedUsers = unauthorizedUsers;
    }

    public void parseJSON(JSONObject jsonObject) throws JSONException, ParseException {
        this._id = jsonObject.getString("_id");
        this.name = jsonObject.getString("name");
        this.description = jsonObject.getString("description");
        this.createdBy = jsonObject.getString("createdBy");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        this.createdOn = sdf.parse(jsonObject.getString("createdOn"));
        this.isVisible = true;
        this.isPrivate = jsonObject.getBoolean("isPrivate");
        this.tags = jsonObject.getJSONArray("tags");
    }
}