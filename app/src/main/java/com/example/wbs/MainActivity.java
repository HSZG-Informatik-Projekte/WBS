package com.example.wbs;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private UserProfileClass wbsProfile;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private NavigationView NavView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        wbsProfile = JsonUtil.readProfileFromJson(this);

        setContentView(R.layout.activity_main);

        mDrawerLayout =(DrawerLayout) findViewById(R.id.menuLayout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout,R.string.open,R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        NavView = findViewById(R.id.NavMenu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        NavView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_profile:
                        Intent mySuperIntent = new Intent(MainActivity.this, ShowProfileActivity.class);
                        startActivity(mySuperIntent);
                        return true;
                    default:
                        return false;
                }
            }
        });


        ImageView image = findViewById(R.id.MA_imageview_1);
        //image.setImageResource(android.R.drawable.star_big_on);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mySuperIntent = new Intent(MainActivity.this, VideoScreenActivity.class);
                mySuperIntent.putExtra("videoNumber", 1);
                startActivity(mySuperIntent);
            }
        });

        ImageView image2 = findViewById(R.id.MA_imageview_2);
        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mySuperIntent = new Intent(MainActivity.this, VideoScreenActivity.class);
                mySuperIntent.putExtra("videoNumber", 2);
                startActivity(mySuperIntent);
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
