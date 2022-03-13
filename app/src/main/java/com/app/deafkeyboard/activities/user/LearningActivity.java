package com.app.deafkeyboard.activities.user;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Paint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.app.deafkeyboard.R;
import com.app.deafkeyboard.utils.AnimalHelper;
import com.app.deafkeyboard.utils.LettersHelper;
import com.app.deafkeyboard.utils.NumbersHelper;
import com.app.deafkeyboard.utils.SharedData;

import java.util.ArrayList;

public class LearningActivity extends AppCompatActivity {

    ImageView image;
    Button next,previous;
    int index = 0;

    ArrayList<AnimalHelper.Letters> animals;
    ArrayList<LettersHelper.Letters> letters;
    ArrayList<NumbersHelper.Letters> numbers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning);


        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.actionbar_gradient));
        getSupportActionBar().setHomeButtonEnabled(true); //for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//for back button
        if(SharedData.learn_type == 1)
            getSupportActionBar().setTitle("Learn Letters");
        else if(SharedData.learn_type == 2)
            getSupportActionBar().setTitle("Learn Numbers");
        else if(SharedData.learn_type == 3)
            getSupportActionBar().setTitle("Learn Animals");

        image = findViewById(R.id.image);
        next = findViewById(R.id.next);
        previous = findViewById(R.id.previous);


        if(SharedData.learn_type == 1){
            letters = new LettersHelper().getLetters();
        }else if(SharedData.learn_type == 2){
            numbers = new NumbersHelper().getLetters();
        }else if(SharedData.learn_type == 3){
            animals = new AnimalHelper().getLetters();
        }

        index = 0;
        setData();


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int size = SharedData.learn_type == 1 ? letters.size() :  SharedData.learn_type == 2 ? numbers.size() : animals.size();

                if(index < (size-1))
                    index++;
                setData();
            }
        });


        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(index > 0)
                    index--;
                setData();
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

    private void setData(){
        int size = SharedData.learn_type == 1 ? letters.size() :  SharedData.learn_type == 2 ? numbers.size() : animals.size();

        image.setImageResource(SharedData.learn_type == 1 ? letters.get(index).getImage() :
                SharedData.learn_type == 2 ? numbers.get(index).getImage() :
                        animals.get(index).getImage());
        if(index == 0){
            previous.setVisibility(View.GONE);
        }else{
            previous.setVisibility(View.VISIBLE);
        }

        if(index == size -1){
            next.setVisibility(View.GONE);
        }else{
            next.setVisibility(View.VISIBLE);
        }

    }
}