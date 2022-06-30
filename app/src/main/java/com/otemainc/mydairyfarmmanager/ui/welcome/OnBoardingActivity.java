/*
 * Copyright (c) 2022. This app and its source code is property of Otema technologies and is distributed for use by its clients on a use as is basis.
 * All rights are reserved by Otema technologies. It is therefore illegal to modify, copy or use any part of this system or its source code for any purpose different from that intended by the developer.
 * The dependencies used in the process of building this system and copyrights of their respective developers and are distributed with this system on the basis of each of those copyrights.
 */

package com.otemainc.mydairyfarmmanager.ui.welcome;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.otemainc.mydairyfarmmanager.R;

public class OnBoardingActivity extends AppCompatActivity {
    public static ViewPager viewPager;
    OnboardingViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding);
        viewPager=findViewById(R.id.viewpager);
        adapter=new OnboardingViewPagerAdapter(this);
        viewPager.setAdapter(adapter);
        if (isOpenAllRead())
        {
            Intent intent=new Intent(OnBoardingActivity.this,SplashActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        else
        {
            SharedPreferences.Editor editor=getSharedPreferences("slide",MODE_PRIVATE).edit();
            editor.putBoolean("slide",true);
            editor.apply();
        }

    }

    private boolean isOpenAllRead() {
        SharedPreferences sharedPreferences=getSharedPreferences("slide",MODE_PRIVATE);
        return sharedPreferences.getBoolean("slide",false);
    }
}