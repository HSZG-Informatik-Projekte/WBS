package de.hszg.wbs;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class EnterNewWorldActivity extends AppCompatActivity {

    private UserProfileClass wbsProfile;
    private static int SPLASH_TIME = 2000; //This is 2 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_new_world);

        wbsProfile = JsonUtil.readProfileFromJson(this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mySuperIntent;
                mySuperIntent = new Intent(EnterNewWorldActivity.this, MainActivity.class);
                startActivity(mySuperIntent);
                finish();
            }
        }, SPLASH_TIME);
    }
}
