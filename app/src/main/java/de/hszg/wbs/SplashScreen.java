package de.hszg.wbs;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class SplashScreen extends AppCompatActivity {

    private UserProfileClass wbsProfile;

    private static int SPLASH_TIME = 1000; //This is 2 seconds

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
                    mySuperIntent = new Intent(SplashScreen.this, MainActivity.class);
                } else {
                    mySuperIntent = new Intent(SplashScreen.this, CreateProfileActivity.class);
                }
                startActivity(mySuperIntent);
                /* This 'finish()' is for exiting the app when back button pressed
                 *  from Home page which is ActivityHome
                 */
                finish();
            }
        }, SPLASH_TIME);
    }
}
