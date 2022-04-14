package com.app.deafkeyboard.activities.admin;

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
import com.app.deafkeyboard.adapters.DashboardAdapter;
import com.app.deafkeyboard.model.Posters;
import com.app.deafkeyboard.utils.DashboardItem;
import com.app.deafkeyboard.utils.SharedData;

import java.util.ArrayList;

public class AdminDashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

//        (findViewById(R.id.posters))
//                .setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Intent intent = new Intent(AdminDashboardActivity.this, PostersActivity.class);
//                        startActivity(intent);
//                    }
//                });
//
//        (findViewById(R.id.logout))
//                .setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//
//                        SharedData.loggedUser = null;
//                        SharedData.chat = null;
//                        SharedData.mCurrentIndex = new MediatorLiveData<>();
//
//                        Intent intent = new Intent(AdminDashboardActivity.this, LoginActivity.class);
//                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
//                        startActivity(intent);
//                    }
//                });


        ArrayList<DashboardItem> dashboardItems = new ArrayList<>();
        ArrayList<Integer> englishSigns = new ArrayList<>();
        englishSigns.add(R.mipmap.p);
        englishSigns.add(R.mipmap.o);
        englishSigns.add(R.mipmap.s);
        englishSigns.add(R.mipmap.t);
        englishSigns.add(R.mipmap.e);
        englishSigns.add(R.mipmap.r);
        englishSigns.add(R.mipmap.s);


        ArrayList<Integer> arabicSigns = new ArrayList<>();
        arabicSigns.add(R.mipmap.y1);
        arabicSigns.add(R.mipmap.x1);
        arabicSigns.add(R.mipmap.n1);
        arabicSigns.add(R.mipmap.v1);
        arabicSigns.add(R.mipmap.a15555);
        arabicSigns.add(R.mipmap.c1);


        dashboardItems.add(new DashboardItem("Posters", "ملصقات", englishSigns, arabicSigns));

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