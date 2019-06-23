package de.hszg.wbs;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private UserProfileClass wbsProfile;
    private ArrayList<VideoClass> videos;
    private int worldId;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private NavigationView NavView;
    public static final int EUROPE = 0;
    public static final int AMERICA = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wbsProfile = JsonUtil.readProfileFromJson(this);
        videos = JsonUtil.readVideoFromJson(this);
        worldId = wbsProfile.getLocalMap();

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
                    case R.id.NAV_Item_profile:
                        startActivity(new Intent(getApplicationContext(), ShowProfileActivity.class));
                        return true;
                    case R.id.NAV_Item_follower_shop:
                    case R.id.NAV_Item_to_do_liste:
                    case R.id.NAV_Item_settings:
                        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                        alert.setTitle("WBS");
                        alert.setMessage("Dieses Feature ist noch nicht verfügbar!");
                        alert.setIcon(getResources().getDrawable(R.drawable.ic_menu_info));
                        alert.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        alert.create();
                        alert.show();
                        return true;
                    default:
                        return false;
                }
            }
        });
        final ArrayList<ArrayList<String>> waypoints = JsonUtil.readWorldFromJson(this).get(worldId).getWaypoints();
        for(int i = 0; i < waypoints.size(); i++) {
            int rescourcename = getResources().getIdentifier("MA_imageview_" + i, "id", this.getPackageName());
            ImageView image = findViewById(rescourcename);
            final int videoid = Integer.parseInt(waypoints.get(i).get(1));
            if(wbsProfile.checkQuestionid(videos.get(videoid).getQuestionId())) {
                image.setImageResource(android.R.drawable.star_big_on);
            }
            image.setVisibility(View.VISIBLE);
            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent myIntent = new Intent(getApplicationContext(), VideoScreenActivity.class);
                    myIntent.putExtra("videoNumber", videoid);
                    startActivity(myIntent);
                    finish();
                }
            });
        }
        ImageView continentButton = findViewById(R.id.MA_button_next);
        continentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean allesErfüllt = true;
                if(allesErfüllt) {
                    Intent myIntent = new Intent(getApplicationContext(), EnterNewWorldActivity.class);
                    wbsProfile.setLocalMap(AMERICA);
                    JsonUtil.WBSProfileToJson(getApplicationContext(), wbsProfile);
                    Log.i("worldid", "getLocalMap " + wbsProfile.getLocalMap());
                    startActivity(myIntent);
                    finish();
                }
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
