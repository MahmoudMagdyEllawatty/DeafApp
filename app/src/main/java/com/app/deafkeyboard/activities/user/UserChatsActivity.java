package com.app.deafkeyboard.activities.user;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.deafkeyboard.R;
import com.app.deafkeyboard.activities.admin.PostersActivity;
import com.app.deafkeyboard.adapters.ChatsAdapter;
import com.app.deafkeyboard.adapters.PostersAdapter;
import com.app.deafkeyboard.callback.ChatCallback;
import com.app.deafkeyboard.callback.UserCallback;
import com.app.deafkeyboard.controller.ChatController;
import com.app.deafkeyboard.controller.UserController;
import com.app.deafkeyboard.model.Chat;
import com.app.deafkeyboard.model.User;
import com.app.deafkeyboard.utils.SharedData;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class UserChatsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<User> users;
    ArrayList<Chat> chats;
    FloatingActionButton add;
    ImageView imgNoProduct;
    TextView no_data_fount;
    private ShimmerFrameLayout mShimmerViewContainer;
    SwipeRefreshLayout mSwipeRefreshLayout;

    User selectedUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_chats);

        recyclerView = findViewById(R.id.cart_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        no_data_fount = findViewById(R.id.no_data_fount);
        imgNoProduct = findViewById(R.id.image_no_product);
        mShimmerViewContainer = findViewById(R.id.shimmer_view_container);
        mSwipeRefreshLayout =findViewById(R.id.swipeToRefresh);
        //set color of swipe refresh
        mSwipeRefreshLayout.setColorSchemeResources(R.color.purple_700);
        add = findViewById(R.id.fab_add);


        loadUsers();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSelectUserDialog();
            }
        });



    }

    private void showSelectUserDialog(){
        String[] names = new String[users.size()];
        for (int i =0;i <users.size();i++){
            names[i] =  users.get(i).getName();
        }

        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setTitle("Select User");

        builder.setSingleChoiceItems(names, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                selectedUser = users.get(i);
            }
        }).setPositiveButton("Chat", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(selectedUser == null){
                    Toast.makeText(UserChatsActivity.this, "Please,Select User First", Toast.LENGTH_SHORT).show();
                }else{
                    Chat chat = new Chat();
                    chat.setChatDetails(new ArrayList<>());
                    chat.setFromUser(SharedData.loggedUser.getKey());
                    chat.setFromUserName(SharedData.loggedUser.getName());
                    chat.setKey("");
                    chat.setToUser(selectedUser.getKey());
                    chat.setToUserName(selectedUser.getName());


                    SharedData.chat = chat;
                    Intent intent = new Intent(UserChatsActivity.this,UserChatMessagesActivity.class);
                    startActivity(intent);

                }
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        }).show();



    }

    private void loadUsers(){
        new UserController()
                .getUsers(new UserCallback() {
                    @Override
                    public void onSuccess(ArrayList<User> chats) {
                        users = new ArrayList<>();
                        for (User user : chats){
                            if(!user.getKey().equals(SharedData.loggedUser.getKey())){
                                users.add(user);
                            }
                        }
                    }

                    @Override
                    public void onFail(String msg) {

                    }
                });


        new ChatController().getChats(new ChatCallback() {
            @Override
            public void onSuccess(ArrayList<Chat> chats1) {
                chats = chats1;
                if(chats.isEmpty()){
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

                    recyclerView.setAdapter(new ChatsAdapter(chats, UserChatsActivity.this));
                }
            }

            @Override
            public void onFail(String msg) {

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