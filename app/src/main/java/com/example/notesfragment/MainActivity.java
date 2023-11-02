package com.example.notesfragment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragmentlayout);

        TitleFragment fragment = new TitleFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame,fragment).commit();

    }
}