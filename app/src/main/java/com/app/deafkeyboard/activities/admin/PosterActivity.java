package com.app.deafkeyboard.activities.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.app.deafkeyboard.R;

public class PosterActivity extends AppCompatActivity {


    // في البوستر يكون فيه امكانيه اضافة صورة -- done
    // نحول الكلمة لاشارة
    // الاشارة تكون عربي / انجليزي
    // في القوائم تكون الكلمة وتحتها معناها بلغة الاشارة -- done
    // ١_ الادمن لازم يعدل و يحذف مو بس يضيف  -- done
    //٢_ الكيبود حق لغة الاشاره لازم باللغة العربية والانجليزي نضيف العربي -- done
    //وكلمه space و remove نعدلهم لان واضحين
    //٣ _ التعديلات اللي قلناهم يبي يشوفهم مثلا خيار ان تتغير الكتابه للغة الاشاره عند المستقبل -- done
    //٤_ التدقيق الاملائي
    //٥_تحت كل كلمه لغة الاشاره حقتها مثلا learning و chat -- done

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poster);
    }
}