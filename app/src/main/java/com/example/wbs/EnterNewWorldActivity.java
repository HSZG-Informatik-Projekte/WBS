package com.example.wbs;

import android.annotation.SuppressLint;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;

public class EnterNewWorldActivity extends AppCompatActivity {

    private UserProfileClass wbsProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        wbsProfile = (UserProfileClass) getIntent().getExtras().getSerializable("wbsProfile");

        setContentView(R.layout.activity_enter_new_world);

    }
}
