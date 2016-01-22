package com.example.siddharth.airpollution;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Register extends AppCompatActivity implements View.OnClickListener{

    Button bRegister;

    EditText etName, etAge, etUsername, etPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.siddharth.airpollution.R.layout.activity_register);


        etName = (EditText) findViewById(com.example.siddharth.airpollution.R.id.etName);
        etAge= (EditText) findViewById(com.example.siddharth.airpollution.R.id.etAge);
        etUsername= (EditText) findViewById(com.example.siddharth.airpollution.R.id.etUsername);
        etPassword= (EditText) findViewById(com.example.siddharth.airpollution.R.id.etPassword);
        bRegister = (Button) findViewById(com.example.siddharth.airpollution.R.id.bRegister);

        bRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case com.example.siddharth.airpollution.R.id.bRegister:
                String name = etName.getText().toString();
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                int age = 11;

                User user = new User(name, age, username, password);

                registerUser(user);
                break;
        }
    }
    private  void registerUser(User user){
        System.out.println("hello");
        ServerRequests serverRequests=new ServerRequests(this);
        serverRequests.storeUserDataInBackground(user, new GetUserCallback() {
            @Override
            public void done(User returnedUser) {
                startActivity(new Intent(Register.this,Login.class));
            }
        });
    }
}