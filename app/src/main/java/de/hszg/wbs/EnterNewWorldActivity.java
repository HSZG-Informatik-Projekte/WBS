package de.hszg.wbs;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class EnterNewWorldActivity extends AppCompatActivity {

    private UserProfileClass wbsProfile;
    private static int SPLASH_TIME = 2000; //This is 2 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_new_world);

        wbsProfile = JsonUtil.readProfileFromJson(this);
        final int worldid = wbsProfile.getLocalMap();
        Log.i("worldid", "worldid " + worldid);
        final WorldClass worldClass = JsonUtil.readWorldFromJson(this).get(worldid);
        ImageView mapImageView = findViewById(R.id.ENWA_imageview_background);
        TextView mapText = findViewById(R.id.ENWA_text_head);
        mapText.setText(getResources().getString(R.string.ENWA_Header) + " " + getResources().getString(getResources().getIdentifier(worldClass.getName(), "string", getPackageName())));
        mapImageView.setImageResource(getResources().getIdentifier(worldClass.getMap(), "mipmap", this.getPackageName()));

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(myIntent);
                finish();
            }
        }, SPLASH_TIME);
    }
}
