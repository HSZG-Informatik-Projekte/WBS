package de.hszg.wbs;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {

    private UserProfileClass wbsProfile;

    private static int SPLASH_TIME = 2000; //2 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        wbsProfile = JsonUtil.readProfileFromJson(this);
        wbsProfile.setLocalMap(MainActivity.EUROPE);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent myIntent;
                if(wbsProfile.getisProfile()) {
                    myIntent = new Intent(getApplicationContext(), MainActivity.class);
                } else {
                    myIntent = new Intent(getApplicationContext(), CreateProfileActivity.class);
                }
                startActivity(myIntent);

                finish();
            }
        }, SPLASH_TIME);
    }
}
