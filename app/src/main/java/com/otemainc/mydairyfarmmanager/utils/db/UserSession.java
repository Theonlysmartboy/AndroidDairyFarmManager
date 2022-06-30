/*
 * Copyright (c) 2022. This app and its source code is property of Otema technologies and is distributed for use by its clients on a use as is basis.
 * All rights are reserved by Otema technologies. It is therefore illegal to modify, copy or use any part of this system or its source code for any purpose different from that intended by the developer.
 * The dependencies used in the process of building this system and copyrights of their respective developers and are distributed with this system on the basis of each of those copyrights.
 */

package com.otemainc.mydairyfarmmanager.utils.db;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.otemainc.mydairyfarmmanager.ui.MainActivity;
import com.otemainc.mydairyfarmmanager.ui.auth.LoginActivity;

import java.util.HashMap;

public class UserSession {
    // Shared Preferences
    SharedPreferences pref;
    // Editor for Shared preferences
    SharedPreferences.Editor editor;
    // Context
    Context context;
    // Shared pref mode
    int PRIVATE_MODE = 0;
    // Sharedpref file name
    private static final String PREF_NAME = "UserSessionPref";
    // First time logic Check
    public static final String FIRST_TIME = "firsttime";
    // All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";
    // User name (make variable public to access from outside)
    public static final String KEY_NAME = "name";
    // Email address (make variable public to access from outside)
    public static final String KEY_EMAIL = "email";
    // Mobile number (make variable public to access from outside)
    public static final String KEY_MOBiLE = "mobile";
    // user avatar (make variable public to access from outside)
    public static final String KEY_PHOTO = "photo";
    // number of items in our cart
    public static final String KEY_CART = "cartvalue";
    // number of items in our wishlist
    public static final String KEY_WISHLIST = "wishlistvalue";
    // check first time app launch
    public static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";
    // Constructor
    public UserSession(Context context){
        this.context = context;
        pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }
    /**
     * Create login session
     * */
    public void createLoginSession(String name, String email, String mobile, String photo){
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);
        // commit changes
        editor.commit();
    }
    /**
     * Check login method will check user login status
     * If false it will redirect user to login page
     * Else will redirect user to the main page */
    public void checkLogin(){
        // Check login status
        if(!this.isLoggedIn()){
            Intent i = new Intent(context, LoginActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        }else{
            Intent m = new Intent(context, MainActivity.class);
            m.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            m.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(m);
        }

    }

    /**
     * Get stored session data
     * */
    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<>();
        // user name
        user.put(KEY_NAME, pref.getString(KEY_NAME, null));

        // user email id
        user.put(KEY_EMAIL, pref.getString(KEY_EMAIL, null));

        // user phone number
        user.put(KEY_MOBiLE, pref.getString(KEY_MOBiLE, null));

        // user avatar
        user.put(KEY_PHOTO, pref.getString(KEY_PHOTO, null)) ;

        // return user
        return user;
    }

    /**
     * Clear session details
     * */
    public void logoutUser(){
        //clearing all user data from db
        new Db(context).deleteUser();
        // Clearing all data from Shared Preferences
        editor.putBoolean(IS_LOGIN,false);
        editor.commit();
        // After logout redirect user to Login Activity
        Intent i = new Intent(context, LoginActivity.class);
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        // Staring Login Activity
        context.startActivity(i);
    }

    /**
     * Quick check for login
     * **/
    // Get Login State
    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false);
    }

    public boolean  getFirstTime() {
        return pref.getBoolean(FIRST_TIME, true);
    }

    public void setFirstTime(boolean n){
        editor.putBoolean(FIRST_TIME,n);
        editor.commit();
    }

    public void setFirstTimeLaunch(boolean isFirstTime) {
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.commit();
    }

    public boolean isFirstTimeLaunch() {
        return pref.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }
}
