package com.app.deafkeyboard.activities.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.app.deafkeyboard.R;
import com.app.deafkeyboard.utils.SharedData;

public class SelectLearningActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_learning);

        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.actionbar_gradient));
        getSupportActionBar().setHomeButtonEnabled(true); //for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//for back button
        getSupportActionBar().setTitle("Select Learning");

        (findViewById(R.id.learning))
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        SharedData.learn_type = 1;
                        Intent intent = new Intent(SelectLearningActivity.this,LearningActivity.class);
                        startActivity(intent);
                    }
                });

        (findViewById(R.id.exam))
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(SelectLearningActivity.this,
                                ExamActivity.class);
                        startActivity(intent);
                    }
                });


        (findViewById(R.id.chat))
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        SharedData.learn_type = 2;
                        Intent intent = new Intent(SelectLearningActivity.this,LearningActivity.class);
                        startActivity(intent);
                    }
                });

        (findViewById(R.id.posters))
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        SharedData.learn_type = 3;
                        Intent intent = new Intent(SelectLearningActivity.this,LearningActivity.class);
                        startActivity(intent);
                    }
                });

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