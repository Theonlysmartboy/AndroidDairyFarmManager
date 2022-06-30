/*
 * Copyright (c) 2022. This app and its source code is property of Otema technologies and is distributed for use by its clients on a use as is basis.
 * All rights are reserved by Otema technologies. It is therefore illegal to modify, copy or use any part of this system or its source code for any purpose different from that intended by the developer.
 * The dependencies used in the process of building this system and copyrights of their respective developers and are distributed with this system on the basis of each of those copyrights.
 */

package com.otemainc.mydairyfarmmanager.ui.welcome;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.otemainc.mydairyfarmmanager.R;
import com.otemainc.mydairyfarmmanager.ui.auth.LoginActivity;

public class OnboardingViewPagerAdapter extends PagerAdapter {
    Context ctx;

    public OnboardingViewPagerAdapter(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        LayoutInflater layoutInflater= (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.on_boarding_layout,container,false);

        ImageView logo=view.findViewById(R.id.logo);

        ImageView ind = view.findViewById(R.id.ind);
        ImageView ind1=view.findViewById(R.id.ind1);
        ImageView ind2=view.findViewById(R.id.ind2);
        ImageView ind3=view.findViewById(R.id.ind3);

        TextView title=view.findViewById(R.id.title);
        TextView desc=view.findViewById(R.id.desc);

        ImageView next=view.findViewById(R.id.next);
        ImageView back=view.findViewById(R.id.back);

        Button btnGetStarted=view.findViewById(R.id.btnGetStarted);
        btnGetStarted.setOnClickListener(v -> {
            Intent intent=new Intent(ctx, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_NEW_TASK);
            ctx.startActivity(intent);
        });
        next.setOnClickListener(v -> OnBoardingActivity.viewPager.setCurrentItem(position+1));

        back.setOnClickListener(v -> OnBoardingActivity.viewPager.setCurrentItem(position-1));

        switch (position)
        {
            case 0:
                logo.setImageResource(R.drawable.logo);
                ind.setImageResource(R.drawable.selected);
                ind1.setImageResource(R.drawable.unselected);
                ind2.setImageResource(R.drawable.unselected);
                ind3.setImageResource(R.drawable.unselected);

                title.setText(R.string.welcome_text);
                desc.setText(R.string.welcome_decsr);
                back.setVisibility(View.GONE);
                next.setVisibility(View.VISIBLE);
                break;
            case 1:
                logo.setImageResource(R.drawable.logo1);
                ind.setImageResource(R.drawable.unselected);
                ind1.setImageResource(R.drawable.selected);
                ind2.setImageResource(R.drawable.unselected);
                ind3.setImageResource(R.drawable.unselected);

                title.setText(R.string.asset_management_text);
                desc.setText(R.string.asset_management_descr);
                back.setVisibility(View.VISIBLE);
                next.setVisibility(View.VISIBLE);
                break;
            case 2:
                logo.setImageResource(R.drawable.logo2);
                ind.setImageResource(R.drawable.unselected);
                ind1.setImageResource(R.drawable.unselected);
                ind2.setImageResource(R.drawable.selected);
                ind3.setImageResource(R.drawable.unselected);

                title.setText(R.string.partner_title);
                desc.setText(R.string.partner_text);
                back.setVisibility(View.VISIBLE);
                next.setVisibility(View.VISIBLE);
                break;
            case 3:
                logo.setImageResource(R.drawable.logo3);
                ind.setImageResource(R.drawable.unselected);
                ind1.setImageResource(R.drawable.unselected);
                ind2.setImageResource(R.drawable.unselected);
                ind3.setImageResource(R.drawable.selected);

                title.setText(R.string.payment_title);
                desc.setText(R.string.payment_descr);
                back.setVisibility(View.VISIBLE);
                next.setVisibility(View.GONE);
                break;
        }
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
