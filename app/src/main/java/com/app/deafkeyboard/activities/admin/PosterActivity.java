package com.app.deafkeyboard.activities.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.app.deafkeyboard.R;

public class PosterActivity extends AppCompatActivity {


    // في البوستر يكون فيه امكانيه اضافة صورة
    // نحول الكلمة لاشارة
    // الاشارة تكون عربي / انجليزي
    // في القوائم تكون الكلمة وتحتها معناها بلغة الاشارة
    //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poster);
    }
}