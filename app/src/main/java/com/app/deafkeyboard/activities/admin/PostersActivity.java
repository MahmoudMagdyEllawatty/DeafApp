package com.app.deafkeyboard.activities.admin;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
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
import com.app.deafkeyboard.callback.FileUploadCallback;
import com.app.deafkeyboard.callback.PostersCallback;
import com.app.deafkeyboard.controller.ImageController;
import com.app.deafkeyboard.controller.PostersController;
import com.app.deafkeyboard.model.Posters;
import com.app.deafkeyboard.utils.ImagePicker;
import com.app.deafkeyboard.utils.LoadingHelper;
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

    private static final int RESULT_LOAD_IMAGES = 25;
    String imageURL = "";
    LoadingHelper loadingHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posters);
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.actionbar_gradient));
        getSupportActionBar().setHomeButtonEnabled(true); //for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//for back button
        getSupportActionBar().setTitle("Posters");

        loadingHelper = new LoadingHelper(this);
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
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private boolean checkReadPermission(){
        int permissionWriteExternal = ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE);
        if (permissionWriteExternal != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,new String[]{ Manifest.permission.READ_EXTERNAL_STORAGE},2);
            return false;
        }else{
            return true;
        }
    }



    private void pickDoc(){
        Intent chooseImageIntent = ImagePicker.getPickImageIntent(this);
        startActivityForResult(chooseImageIntent, RESULT_LOAD_IMAGES);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGES && resultCode == Activity.RESULT_OK) {


            loadingHelper.showLoading("Uploading Image");
            Uri uri = ImagePicker.getUriFromResult(this, resultCode, data);;
            if(uri == null){
                Toast.makeText(this, "Cannot load image", Toast.LENGTH_SHORT).show();
            }else{
                new ImageController().uploadImage(uri, new FileUploadCallback() {
                    @Override
                    public void onSuccess(String url) {
                        imageURL = url;
                        loadingHelper.dismissLoading();
                    }

                    @Override
                    public void onFail(String msg) {
                        loadingHelper.dismissLoading();
                    }
                });
            }

        }
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
        final ImageButton selectImage = dialogView.findViewById(R.id.select_image);


        title.setText("Add Poster");

        selectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkReadPermission()){
                    pickDoc();
                }
            }
        });


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
            posters.setImage(imageURL);
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