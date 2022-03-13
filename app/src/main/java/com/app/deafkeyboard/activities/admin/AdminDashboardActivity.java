package com.app.deafkeyboard.activities.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MediatorLiveData;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.app.deafkeyboard.LoginActivity;
import com.app.deafkeyboard.R;
import com.app.deafkeyboard.model.Posters;
import com.app.deafkeyboard.utils.SharedData;

public class AdminDashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        (findViewById(R.id.posters))
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(AdminDashboardActivity.this, PostersActivity.class);
                        startActivity(intent);
                    }
                });

        (findViewById(R.id.logout))
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        SharedData.loggedUser = null;
                        SharedData.chat = null;
                        SharedData.mCurrentIndex = new MediatorLiveData<>();

                        Intent intent = new Intent(AdminDashboardActivity.this, LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                });
    }
}