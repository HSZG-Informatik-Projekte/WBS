package de.hszg.wbs;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

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
        //Europa
        final ArrayList<ArrayList<String>> waypoints = JsonUtil.readWorldFromJson(this).get(0).getWaypoints();
        //Log.i("waypoints", "android.R.drawable.ic_menu_help " + android.R.drawable.ic_menu_help);
        //Log.i("waypoints", "getResources " + getResources().getIdentifier("ic_menu_help","drawable", "" + getPackageName()));
        Log.i("waypoints", "waypoints.size() " + waypoints.size());
        for(int i = 0; i < waypoints.size(); i++) {
            int rescourcename = getResources().getIdentifier("MA_imageview_" + i, "id", this.getPackageName());
            ImageView image = findViewById(rescourcename);
            image.setVisibility(View.VISIBLE);
            final int videoNumber = Integer.parseInt(waypoints.get(i).get(1));
            Log.i("waypoints", "" + videoNumber);
            //über videoid die fragenid holen
            int questionId = 0;
            ArrayList videos = JsonUtil.readVideoFromJson(this);
            for(int j = 0; i < videos.size(); i++){
                VideoClass video = (VideoClass) videos.get(j);
                if(video.getId() == videoNumber){
                    questionId = video.getQuestionId();
                    break;
                }
            }

            if(wbsProfile.checkQuestionid(questionId)) {
                image.setImageResource(android.R.drawable.star_big_on);
            } else {
                image.setImageResource(android.R.drawable.ic_menu_help);
                //image.setImageResource(getResources().getIdentifier(waypoints.get(i).get(0),"android","" + getPackageName()));
            }
            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent mySuperIntent = new Intent(MainActivity.this, VideoScreenActivity.class);
                    mySuperIntent.putExtra("videoNumber", videoNumber);
                    startActivity(mySuperIntent);
                }
            });
        }


    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
