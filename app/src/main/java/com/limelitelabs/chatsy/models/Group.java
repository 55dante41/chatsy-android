package com.limelitelabs.chatsy.models;

import java.util.Date;

/**
 * A Model class for holding a Group object
 */
public class Group {
    public String name, createdBy;
    public boolean isPrivate, isVisible;
    public String description;
    public Date createdOn;
    public String[] tags;
    private String[] admins, authorizedUsers, unauthorizedUsers;

    public Group(String name, String createdBy, String description, Date createdOn, boolean isPrivate, boolean isVisible, String[] tags){
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
}