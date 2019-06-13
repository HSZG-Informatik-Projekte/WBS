package com.example.wbs;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class SplashScreen extends AppCompatActivity {

    private UserProfileClass wbsProfile;

    private static int SPLASH_TIME = 2000; //This is 2 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        wbsProfile = new UserProfileClass(this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do any action here. Now we are moving to next page
                Intent mySuperIntent;

                Log.i("BLT SC [getisProfile()]", "" + wbsProfile.getisProfile());

                if(wbsProfile.getisProfile() == true) {
                    mySuperIntent = new Intent(SplashScreen.this, MainActivity.class);
                } else {
                    mySuperIntent = new Intent(SplashScreen.this, CreateProfileActivity.class);
                }
                mySuperIntent.putExtra("wbsProfile", wbsProfile);
                startActivity(mySuperIntent);
                /* This 'finish()' is for exiting the app when back button pressed
                 *  from Home page which is ActivityHome
                 */
                finish();
            }
        }, SPLASH_TIME);
    }
}
