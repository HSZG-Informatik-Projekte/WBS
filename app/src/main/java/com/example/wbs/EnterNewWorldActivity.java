package com.example.wbs;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class EnterNewWorldActivity extends AppCompatActivity {

    private UserProfileClass wbsProfile;
    private static int SPLASH_TIME = 2000; //This is 2 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_new_world);

        wbsProfile = (UserProfileClass) getIntent().getExtras().getSerializable("wbsProfile");

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mySuperIntent;
                mySuperIntent = new Intent(EnterNewWorldActivity.this, MainActivity.class);
                mySuperIntent.putExtra("wbsProfile", wbsProfile);
                startActivity(mySuperIntent);
                finish();
            }
        }, SPLASH_TIME);
    }
}
