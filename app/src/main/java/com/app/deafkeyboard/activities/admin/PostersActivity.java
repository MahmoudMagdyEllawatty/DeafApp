package com.app.deafkeyboard.activities.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.AlertDialog;
import android.content.Intent;
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
import com.app.deafkeyboard.adapters.PostersAdapter;
import com.app.deafkeyboard.callback.PostersCallback;
import com.app.deafkeyboard.controller.PostersController;
import com.app.deafkeyboard.model.Posters;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class PostersActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    ImageView imgNoProduct;
    TextView no_data_fount;
    private ShimmerFrameLayout mShimmerViewContainer;
    SwipeRefreshLayout mSwipeRefreshLayout;
    FloatingActionButton add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posters);
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.actionbar_gradient));
        getSupportActionBar().setHomeButtonEnabled(true); //for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//for back button
        getSupportActionBar().setTitle("Posters");

        add = findViewById(R.id.fab_add);
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


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddDialog();
            }
        });
    }

    private void showAddDialog(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(PostersActivity.this);
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_station, null);
        dialog.setView(dialogView);
        dialog.setCancelable(false);

        final Button dialogBtnSubmit = dialogView.findViewById(R.id.btn_submit);
        final ImageButton dialogBtnClose = dialogView.findViewById(R.id.btn_close);
        final EditText etxtTitle = dialogView.findViewById(R.id.etxt_title);
        final EditText etxt_description = dialogView.findViewById(R.id.etxt_description);
        final TextView title = dialogView.findViewById(R.id.title);


        title.setText("Add Poster");


        final AlertDialog alertDialog = dialog.create();
        alertDialog.show();

        dialogBtnSubmit.setOnClickListener(v -> {

            if(etxtTitle.getText() == null){
                etxtTitle.setError(getString(R.string.required));
                return;
            }else if(etxtTitle.getText().toString().equals("")){
                etxtTitle.setError(getString(R.string.required));
                return;
            }
            if(etxt_description.getText() == null){
                etxt_description.setError(getString(R.string.required));
                return;
            }else if(etxt_description.getText().toString().equals("")){
                etxt_description.setError(getString(R.string.required));
                return;
            }

            Posters posters = new Posters();
            posters.setDescription(etxt_description.getText().toString());
            posters.setImage("");
            posters.setKey("");
            posters.setTitle(etxtTitle.getText().toString());


            new PostersController()
                    .Save(posters, new PostersCallback() {
                        @Override
                        public void onSuccess(ArrayList<Posters> policies) {
                            alertDialog.dismiss();
                            Toast.makeText(PostersActivity.this, "Saved", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFail(String msg) {
                            alertDialog.dismiss();
                            Toast.makeText(PostersActivity.this, msg, Toast.LENGTH_SHORT).show();
                        }
                    });


        });


        dialogBtnClose.setOnClickListener(v -> alertDialog.dismiss());
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

                    recyclerView.setAdapter(new PostersAdapter(banks, PostersActivity.this));
                }
            }

            @Override
            public void onFail(String msg) {
                //Stopping Shimmer Effects
                mShimmerViewContainer.stopShimmer();
                mShimmerViewContainer.setVisibility(View.GONE);

                recyclerView.setVisibility(View.VISIBLE);
                imgNoProduct.setVisibility(View.GONE);
                Toast.makeText(PostersActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

}