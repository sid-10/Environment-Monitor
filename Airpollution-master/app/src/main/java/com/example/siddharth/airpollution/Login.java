package com.example.siddharth.airpollution;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
public class Login extends AppCompatActivity implements View.OnClickListener {

    Button bLogin;
    EditText etUsername, etPassword;
    TextView tvRegisterLink;

    UserLocalStore userLocalStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.siddharth.airpollution.R.layout.activity_login);


        etUsername = (EditText) findViewById(com.example.siddharth.airpollution.R.id.etUsername);
        etPassword = (EditText) findViewById(com.example.siddharth.airpollution.R.id.etPassword);
        bLogin = (Button) findViewById(com.example.siddharth.airpollution.R.id.bLogin);
        tvRegisterLink = (TextView) findViewById(com.example.siddharth.airpollution.R.id.tvRegisterLink);

        bLogin.setOnClickListener(this);
        tvRegisterLink.setOnClickListener(this);

        userLocalStore = new UserLocalStore(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case com.example.siddharth.airpollution.R.id.bLogin:

                String username=etUsername.getText().toString();
                String password=etPassword.getText().toString();
                User user = new User(username, password);
                authenticate(user);


                break;

            case com.example.siddharth.airpollution.R.id.tvRegisterLink:
                startActivity(new Intent(this, Register.class));
                break;
        }
    }

    private void authenticate(User user){
        ServerRequests serverRequests=new ServerRequests(this);
        serverRequests.fetchUserDataInBackground(user, new GetUserCallback() {
            @Override
            public void done(User returnedUser) {
                if(returnedUser==null){
                    showErrorMessage();
                }else{
                    logUserIn(returnedUser);
                }
            }
        });
    }
    private void showErrorMessage(){
        AlertDialog.Builder dialogBuilder= new AlertDialog.Builder(Login.this);
        dialogBuilder.setMessage("Unregistered User!!!");
        dialogBuilder.setPositiveButton("ok", null);
        dialogBuilder.show();
    }
    private  void logUserIn(User returnedUser){
        userLocalStore.storeUserData(returnedUser);
        userLocalStore.setUserLoggedIn(true);
        startActivity(new Intent(this, MainActivity.class));

    }
}
