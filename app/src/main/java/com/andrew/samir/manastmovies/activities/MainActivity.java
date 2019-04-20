package com.andrew.samir.manastmovies.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.andrew.samir.manastmovies.R;
import com.andrew.samir.manastmovies.activities.peopleActivity.PeopleView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startActivity(new Intent(this, PeopleView.class));
        finish();
    }
}
