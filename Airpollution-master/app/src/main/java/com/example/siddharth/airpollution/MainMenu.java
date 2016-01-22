package com.example.siddharth.airpollution;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }


    public void openMainActivity(View view) {
// Do something in response to button
        startActivity(new Intent(getApplicationContext(), MainActivity.class));

    }
    public void openActivity1(View view) {
// Do something in response to button
        startActivity(new Intent(getApplicationContext(), Activity1.class));

    }
    public void openActivity2(View view) {
// Do something in response to button
        startActivity(new Intent(getApplicationContext(), Activity2.class));

    }
    public void openActivity3(View view) {
// Do something in response to button
        startActivity(new Intent(getApplicationContext(), Activity3.class));

    }
    public void openActivity4(View view) {
// Do something in response to button
        startActivity(new Intent(getApplicationContext(), Activity4.class));

    }



}
