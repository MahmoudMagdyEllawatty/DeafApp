package com.app.deafkeyboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.app.deafkeyboard.activities.admin.AdminDashboardActivity;
import com.app.deafkeyboard.activities.user.UserDashboardActivity;
import com.app.deafkeyboard.callback.UserCallback;
import com.app.deafkeyboard.controller.UserController;
import com.app.deafkeyboard.model.User;
import com.app.deafkeyboard.utils.SharedData;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    EditText email,password;
    Button login;
    TextView register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        register = findViewById(R.id.resgiter);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(email.getText() == null){
                    email.setError("Required");
                    return;
                }else if(email.getText().toString().equals("")){
                    email.setError("Required");
                    return;
                }

                if(password.getText() == null){
                    password.setError("Required");
                    return;
                }else if(password.getText().toString().equals("")){
                    password.setError("Required");
                    return;
                }



                if(email.getText().toString().equals("admin@deaf.com") && password.getText().toString().equals("123456")){
                    Intent intent = new Intent(LoginActivity.this, AdminDashboardActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }else{
                    new UserController().checkLogin(email.getText().toString(), password.getText().toString(), new UserCallback() {
                        @Override
                        public void onSuccess(ArrayList<User> chats) {
                            if(chats.size() > 0){
                                SharedData.loggedUser = chats.get(0);
                                Intent intent = new Intent(LoginActivity.this, UserDashboardActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            }else{
                                Toast.makeText(LoginActivity.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFail(String msg) {
                            Toast.makeText(LoginActivity.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                        }
                    });
                }


            }
        });


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}