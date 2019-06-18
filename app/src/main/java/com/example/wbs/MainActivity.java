package com.example.wbs;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private UserProfileClass wbsProfile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        wbsProfile = (UserProfileClass) getIntent().getExtras().getSerializable("wbsProfile");

        setContentView(R.layout.activity_main);

        ImageView fab = findViewById(R.id.MA_floatingActionButton);

        ImageView image = findViewById(R.id.MA_imageview_1);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mySuperIntent = new Intent(MainActivity.this, VideoScreenActivity.class);
                mySuperIntent.putExtra("wbsProfile", wbsProfile);
                mySuperIntent.putExtra("videoNumber", 1);
                startActivity(mySuperIntent);
            }
        });

        ImageView image2 = findViewById(R.id.MA_imageview_2);
        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mySuperIntent = new Intent(MainActivity.this, VideoScreenActivity.class);
                mySuperIntent.putExtra("wbsProfile", wbsProfile);
                mySuperIntent.putExtra("videoNumber", 2);
                startActivity(mySuperIntent);
            }
        });
    }
}
