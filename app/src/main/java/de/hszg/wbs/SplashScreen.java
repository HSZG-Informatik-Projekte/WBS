package de.hszg.wbs;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {

    private UserProfileClass wbsProfile;

    private static int SPLASH_TIME = 500; //This is 2 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        wbsProfile = JsonUtil.readProfileFromJson(this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mySuperIntent;
                if(wbsProfile.getisProfile()) {
                    mySuperIntent = new Intent(getApplicationContext(), MainActivity.class);
                } else {
                    mySuperIntent = new Intent(getApplicationContext(), CreateProfileActivity.class);
                }
                startActivity(mySuperIntent);

                finish();
            }
        }, SPLASH_TIME);
    }
}
