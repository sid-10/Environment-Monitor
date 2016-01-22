package com.example.siddharth.airpollution;

import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{

    Button bLogout;

    EditText etName, etAge, etUsername;
    UserLocalStore userLocalStore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.siddharth.airpollution.R.layout.activity_main);


        etName = (EditText) findViewById(com.example.siddharth.airpollution.R.id.etName);
        etAge= (EditText) findViewById(com.example.siddharth.airpollution.R.id.etAge);
        etUsername= (EditText) findViewById(com.example.siddharth.airpollution.R.id.etUsername);
        //etPassword= (EditText) findViewById(R.id.etPassword);
        bLogout = (Button) findViewById(com.example.siddharth.airpollution.R.id.bLogout);

        bLogout.setOnClickListener(this);

        userLocalStore = new UserLocalStore(this);
    }


    @Override
    protected void onStart()
    {
        super.onStart();

        if(authenticate()==true)
        {
            displayUserDetails();
        }else{
            startActivity(new Intent(MainActivity.this, Login.class));
        }
    }

    private boolean authenticate()
    {
        return userLocalStore.getUserLoggedIn();
    }

    private void displayUserDetails()
    {
        User user = userLocalStore.getLoggedInUser();

        etUsername.setText(user.username);
        etName.setText(user.name);
        etAge.setText(user.age+"");
    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case com.example.siddharth.airpollution.R.id.bLogout:

                userLocalStore.clearUserData();
                userLocalStore.setUserLoggedIn(false);
                startActivity(new Intent(this, Login.class));
                break;

        }
    }
}