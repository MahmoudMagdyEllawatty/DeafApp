package com.app.deafkeyboard.activities.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.app.deafkeyboard.R;
import com.app.deafkeyboard.adapters.DashboardAdapter;
import com.app.deafkeyboard.utils.DashboardItem;
import com.app.deafkeyboard.utils.SharedData;

import java.util.ArrayList;

public class SelectLearningActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.actionbar_gradient));
        getSupportActionBar().setHomeButtonEnabled(true); //for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//for back button
        getSupportActionBar().setTitle("Select Learning");

//        (findViewById(R.id.learning))
//                .setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        SharedData.learn_type = 1;
//                        Intent intent = new Intent(SelectLearningActivity.this,LearningActivity.class);
//                        startActivity(intent);
//                    }
//                });
//
//        (findViewById(R.id.exam))
//                .setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Intent intent = new Intent(SelectLearningActivity.this,
//                                ExamActivity.class);
//                        startActivity(intent);
//                    }
//                });
//
//
//        (findViewById(R.id.chat))
//                .setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        SharedData.learn_type = 2;
//                        Intent intent = new Intent(SelectLearningActivity.this,LearningActivity.class);
//                        startActivity(intent);
//                    }
//                });
//
//        (findViewById(R.id.posters))
//                .setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        SharedData.learn_type = 3;
//                        Intent intent = new Intent(SelectLearningActivity.this,LearningActivity.class);
//                        startActivity(intent);
//                    }
//                });
//
//
//

        ArrayList<DashboardItem> dashboardItems = new ArrayList<>();
        ArrayList<Integer> englishSigns = new ArrayList<>();
        englishSigns.add(R.mipmap.l);
        englishSigns.add(R.mipmap.e);
        englishSigns.add(R.mipmap.e);
        englishSigns.add(R.mipmap.t);
        englishSigns.add(R.mipmap.t);
        englishSigns.add(R.mipmap.e);
        englishSigns.add(R.mipmap.r);
        englishSigns.add(R.mipmap.s);


        ArrayList<Integer> arabicSigns = new ArrayList<>();
        arabicSigns.add(R.mipmap.f1);
        arabicSigns.add(R.mipmap.j1);
        arabicSigns.add(R.mipmap.zzz);
        arabicSigns.add(R.mipmap.u1);


        dashboardItems.add(new DashboardItem("Letters", "حروف", englishSigns, arabicSigns));

        englishSigns = new ArrayList<>();
        englishSigns.add(R.mipmap.n);
        englishSigns.add(R.mipmap.u);
        englishSigns.add(R.mipmap.m);
        englishSigns.add(R.mipmap.b);
        englishSigns.add(R.mipmap.e);
        englishSigns.add(R.mipmap.r);
        englishSigns.add(R.mipmap.s);

        arabicSigns = new ArrayList<>();
        arabicSigns.add(R.mipmap.fff);
        arabicSigns.add(R.mipmap.j1);
        arabicSigns.add(R.mipmap.v1);
        arabicSigns.add(R.mipmap.a15555);
        arabicSigns.add(R.mipmap.y1);


        dashboardItems.add(new DashboardItem("Numbers", "أرقام", englishSigns, arabicSigns));



        englishSigns = new ArrayList<>();
        englishSigns.add(R.mipmap.a);
        englishSigns.add(R.mipmap.n);
        englishSigns.add(R.mipmap.i);
        englishSigns.add(R.mipmap.m);
        englishSigns.add(R.mipmap.a);
        englishSigns.add(R.mipmap.l);
        englishSigns.add(R.mipmap.s);


        arabicSigns = new ArrayList<>();
        arabicSigns.add(R.mipmap.f1);
        arabicSigns.add(R.mipmap.bb);
        arabicSigns.add(R.mipmap.zzz);
        arabicSigns.add(R.mipmap.a15555);
        arabicSigns.add(R.mipmap.z1);
        arabicSigns.add(R.mipmap.a15555);
        arabicSigns.add(R.mipmap.c1);


        dashboardItems.add(new DashboardItem("Animals", "حيوانات", englishSigns, arabicSigns));

        englishSigns = new ArrayList<>();
        englishSigns.add(R.mipmap.e);
        englishSigns.add(R.mipmap.x);
        englishSigns.add(R.mipmap.a);
        englishSigns.add(R.mipmap.m);

        arabicSigns = new ArrayList<>();
        arabicSigns.add(R.mipmap.a15555);
        arabicSigns.add(R.mipmap.g1);
        arabicSigns.add(R.mipmap.c1);
        arabicSigns.add(R.mipmap.b1);
        arabicSigns.add(R.mipmap.a15555);
        arabicSigns.add(R.mipmap.j1);

        dashboardItems.add(new DashboardItem("Exam", "اختبار", englishSigns, arabicSigns));

        RecyclerView recyclerView = findViewById(R.id.list);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new DashboardAdapter(dashboardItems,this));



    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            this.finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}