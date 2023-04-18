package com.example.demo.component;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;

@Component
@SessionScope
public class UserSession {
    int userID;
    List<Integer> listingIDs;


    public void addListingID(Integer id) {

        listingIDs.add(id);
    }

    public List<Integer> getListingIDs() {
        return listingIDs;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public UserSession(int userID) {
        this.userID = userID;
    }

    public UserSession() {

        this.listingIDs =  new ArrayList<>();
    }
}
