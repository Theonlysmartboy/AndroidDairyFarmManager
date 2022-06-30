/*
 * Copyright (c) 2022. This app and its source code is property of Otema technologies and is distributed for use by its clients on a use as is basis.
 * All rights are reserved by Otema technologies. It is therefore illegal to modify, copy or use any part of this system or its source code for any purpose different from that intended by the developer.
 * The dependencies used in the process of building this system and copyrights of their respective developers and are distributed with this system on the basis of each of those copyrights.
 */

package com.otemainc.mydairyfarmmanager.ui.welcome;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;

import com.otemainc.mydairyfarmmanager.R;
import com.otemainc.mydairyfarmmanager.utils.db.UserSession;

public class SplashActivity extends AppCompatActivity {
    private UserSession session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        session = new UserSession(getApplicationContext());
        setContentView(R.layout.activity_splash);
        splash();
    }
    private void splash() {
        new Handler().postDelayed(() -> session.checkLogin(),5000);
    }
}