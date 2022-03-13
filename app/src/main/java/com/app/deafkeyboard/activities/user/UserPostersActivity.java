package com.app.deafkeyboard.activities.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.deafkeyboard.R;
import com.app.deafkeyboard.activities.admin.PostersActivity;
import com.app.deafkeyboard.adapters.PostersAdapter;
import com.app.deafkeyboard.callback.PostersCallback;
import com.app.deafkeyboard.controller.PostersController;
import com.app.deafkeyboard.model.Posters;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;

public class UserPostersActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    ImageView imgNoProduct;
    TextView no_data_fount;
    private ShimmerFrameLayout mShimmerViewContainer;
    SwipeRefreshLayout mSwipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_posters);
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.actionbar_gradient));
        getSupportActionBar().setHomeButtonEnabled(true); //for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//for back button
        getSupportActionBar().setTitle("Posters");

        no_data_fount = findViewById(R.id.no_data_fount);
        recyclerView = findViewById(R.id.cart_recyclerview);
        imgNoProduct = findViewById(R.id.image_no_product);
        mShimmerViewContainer = findViewById(R.id.shimmer_view_container);
        mSwipeRefreshLayout =findViewById(R.id.swipeToRefresh);
        //set color of swipe refresh
        mSwipeRefreshLayout.setColorSchemeResources(R.color.purple_700);

        // set a GridLayoutManager with default vertical orientation and 3 number of columns
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager); // set LayoutManager to RecyclerView
        recyclerView.setHasFixedSize(true);

        //swipe refresh listeners
        mSwipeRefreshLayout.setOnRefreshListener(() -> {

            loadData();
            //after shuffle id done then swife refresh is off
            mSwipeRefreshLayout.setRefreshing(false);
        });


        loadData();


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

    private void loadData(){
        new PostersController().getPosterss(new PostersCallback() {
            @Override
            public void onSuccess(ArrayList<Posters> complaints) {
                ArrayList<Posters> banks = new ArrayList<>();
                banks = complaints;
                if(banks.isEmpty()){
                    recyclerView.setVisibility(View.GONE);
                    mSwipeRefreshLayout.setVisibility(View.GONE);
                    no_data_fount.setVisibility(View.VISIBLE);
                    no_data_fount.setText(R.string.no_result_found);
                    imgNoProduct.setVisibility(View.VISIBLE);
                    imgNoProduct.setImageResource(R.drawable.not_found);
                    //Stopping Shimmer Effects
                    mShimmerViewContainer.stopShimmer();
                    mShimmerViewContainer.setVisibility(View.GONE);

                }else{
                    //Stopping Shimmer Effects
                    mShimmerViewContainer.stopShimmer();
                    mShimmerViewContainer.setVisibility(View.GONE);

                    mSwipeRefreshLayout.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.VISIBLE);
                    imgNoProduct.setVisibility(View.GONE);
                    no_data_fount.setVisibility(View.GONE);

                    recyclerView.setAdapter(new PostersAdapter(banks, UserPostersActivity.this));
                }
            }

            @Override
            public void onFail(String msg) {
                //Stopping Shimmer Effects
                mShimmerViewContainer.stopShimmer();
                mShimmerViewContainer.setVisibility(View.GONE);

                recyclerView.setVisibility(View.VISIBLE);
                imgNoProduct.setVisibility(View.GONE);
                Toast.makeText(UserPostersActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }



}