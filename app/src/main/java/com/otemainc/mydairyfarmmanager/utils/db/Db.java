/*
 * Copyright (c) 2022. This app and its source code is property of Otema technologies and is distributed for use by its clients on a use as is basis.
 * All rights are reserved by Otema technologies. It is therefore illegal to modify, copy or use any part of this system or its source code for any purpose different from that intended by the developer.
 * The dependencies used in the process of building this system and copyrights of their respective developers and are distributed with this system on the basis of each of those copyrights.
 */

package com.otemainc.mydairyfarmmanager.utils.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Db extends SQLiteOpenHelper {
    // on database schema change, you must increment database version.
    private static final int DATABASE_VERSION = 5;
    private static final String DATABASE_NAME = "farm_manager.db";
    //create users table
    private static final String SQL_CREATE_USERS_TABLE =  "CREATE TABLE tbl_users (" +
            "id int(11) NOT NULL," +
            "name varchar(60) NOT NULL," +
            "email varchar(60) NOT NULL," +
            "phone varchar(12) NOT NULL," +
            "token varchar(255) NOT NULL," +
            "town varchar(100)," +
            "estate varchar(100)," +
            "longi varchar(100)," +
            "lati varchar(100));";

    //delete users table
    private static final String SQL_DELETE_USERS_TABLE = "DROP TABLE IF EXISTS tbl_users";

    public Db(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_USERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion <1){
            //we simply add new tables to existing ones
        }
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
    public boolean addUser( int id, String name,String email, String phone,
                            String token, String town, String estate){
        boolean added;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValue = new ContentValues();
        contentValue.put("id",id);
        contentValue.put("name",name);
        contentValue.put("email",email);
        contentValue.put("phone",phone);
        contentValue.put("token",token);
        contentValue.put("town",town);
        contentValue.put("estate", estate);
        long result = db.insert("tbl_users",null,contentValue);
        added= result != -1;
        return added;
    }
    public Cursor getUser(){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * from tbl_users",null);
    }

    public String getTownName() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor user = db.rawQuery("SELECT * from tbl_users",null);
        user.moveToFirst();
        user.close();
        return user.getString(5);
    }
    public String getToken() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor user = db.rawQuery("SELECT * from tbl_users",null);
        user.moveToFirst();
        user.close();
        return user.getString(4);
    }

    public void deleteUser(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from tbl_users");
    }
}
