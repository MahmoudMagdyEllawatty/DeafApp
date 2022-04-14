package com.app.deafkeyboard.activities.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MediatorLiveData;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.app.deafkeyboard.LoginActivity;
import com.app.deafkeyboard.R;
import com.app.deafkeyboard.activities.admin.AdminDashboardActivity;
import com.app.deafkeyboard.adapters.DashboardAdapter;
import com.app.deafkeyboard.utils.DashboardItem;
import com.app.deafkeyboard.utils.SharedData;

import java.util.ArrayList;

public class UserDashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

//        (findViewById(R.id.learning))
//                .setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Intent intent = new Intent(UserDashboardActivity.this,SelectLearningActivity.class);
//                        startActivity(intent);
//                    }
//                });
//
//        (findViewById(R.id.chat))
//                .setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Intent intent = new Intent(UserDashboardActivity.this,UserChatsActivity.class);
//                        startActivity(intent);
//                    }
//                });
//
//        (findViewById(R.id.posters))
//                .setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Intent intent = new Intent(UserDashboardActivity.this,UserPostersActivity.class);
//                        startActivity(intent);
//                    }
//                });
//
//        (findViewById(R.id.logout))
//                .setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        SharedData.loggedUser = null;
//                        SharedData.chat = null;
//                        SharedData.mCurrentIndex = new MediatorLiveData<>();
//
//                        Intent intent = new Intent(UserDashboardActivity.this, LoginActivity.class);
//                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
//                        startActivity(intent);
//                    }
//                });



        ArrayList<DashboardItem> dashboardItems = new ArrayList<>();
        ArrayList<Integer> englishSigns = new ArrayList<>();
        englishSigns.add(R.mipmap.l);
        englishSigns.add(R.mipmap.e);
        englishSigns.add(R.mipmap.a);
        englishSigns.add(R.mipmap.r);
        englishSigns.add(R.mipmap.n);
        englishSigns.add(R.mipmap.i);
        englishSigns.add(R.mipmap.n);
        englishSigns.add(R.mipmap.g);


        ArrayList<Integer> arabicSigns = new ArrayList<>();
        arabicSigns.add(R.mipmap.c1);
        arabicSigns.add(R.mipmap.s1);
        arabicSigns.add(R.mipmap.x1);
        arabicSigns.add(R.mipmap.bb);
        arabicSigns.add(R.mipmap.y1);


        dashboardItems.add(new DashboardItem("Learning", "تعليم", englishSigns, arabicSigns));

        englishSigns = new ArrayList<>();
        englishSigns.add(R.mipmap.c);
        englishSigns.add(R.mipmap.h);
        englishSigns.add(R.mipmap.a);
        englishSigns.add(R.mipmap.t);


        arabicSigns = new ArrayList<>();
        arabicSigns.add(R.mipmap.y1);
        arabicSigns.add(R.mipmap.f1);
        arabicSigns.add(R.mipmap.a15555);
        arabicSigns.add(R.mipmap.h1);
        arabicSigns.add(R.mipmap.d1);
        arabicSigns.add(R.mipmap.ccc);


        dashboardItems.add(new DashboardItem("Chat", "المحادثة", englishSigns, arabicSigns));



        englishSigns = new ArrayList<>();
        englishSigns.add(R.mipmap.p);
        englishSigns.add(R.mipmap.o);
        englishSigns.add(R.mipmap.s);
        englishSigns.add(R.mipmap.t);
        englishSigns.add(R.mipmap.e);
        englishSigns.add(R.mipmap.r);
        englishSigns.add(R.mipmap.s);


        arabicSigns = new ArrayList<>();
        arabicSigns.add(R.mipmap.y1);
        arabicSigns.add(R.mipmap.x1);
        arabicSigns.add(R.mipmap.n1);
        arabicSigns.add(R.mipmap.v1);
        arabicSigns.add(R.mipmap.a15555);
        arabicSigns.add(R.mipmap.c1);


        dashboardItems.add(new DashboardItem("posters", "ملصقات", englishSigns, arabicSigns));

        englishSigns = new ArrayList<>();
        englishSigns.add(R.mipmap.l);
        englishSigns.add(R.mipmap.o);
        englishSigns.add(R.mipmap.g);
        englishSigns.add(R.mipmap.o);
        englishSigns.add(R.mipmap.u);
        englishSigns.add(R.mipmap.t);

        arabicSigns = new ArrayList<>();
        arabicSigns.add(R.mipmap.g1);
        arabicSigns.add(R.mipmap.j1);
        arabicSigns.add(R.mipmap.zzz);
        arabicSigns.add(R.mipmap.e1);

        dashboardItems.add(new DashboardItem("Logout", "خروج", englishSigns, arabicSigns));

        RecyclerView recyclerView = findViewById(R.id.list);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new DashboardAdapter(dashboardItems,this));


    }
}