package com.example.wbs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class EnterNewWorldActivity extends AppCompatActivity {

    private UserProfileClass wbsProfile;
    ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        wbsProfile = (UserProfileClass) getIntent().getExtras().getSerializable("wbsProfile");

        setContentView(R.layout.activity_enter_new_world);

         image = findViewById(R.id.MA_imageview_1);
         image.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent mySuperIntent = new Intent(EnterNewWorldActivity.this, VideoScreenActivity.class);
                 mySuperIntent.putExtra("wbsProfile", wbsProfile);
                 startActivity(mySuperIntent);
             }
         });
    }
}
