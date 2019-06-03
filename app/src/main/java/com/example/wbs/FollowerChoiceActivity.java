package com.example.wbs;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.provider.MediaStore;
import android.renderscript.Allocation;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.ArrayList;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class FollowerChoiceActivity extends AppCompatActivity {

    private static Resources userFollowerChoice;
    private static final String active_follower = "#FFFFFF";
    private static final String non_active_follower = "#000000";

    ArrayList<ImageButton> imageButtonsList;
    private static final int[] FOLLOWER_IDS = {
            R.id.FCA_ImageButton_follower_1,
            R.id.FCA_ImageButton_follower_2,
            R.id.FCA_ImageButton_follower_3,
            R.id.FCA_ImageButton_follower_4,
            R.id.FCA_ImageButton_follower_5,
            R.id.FCA_ImageButton_follower_6
    };

    private void setAllFollowerNone() {
        for(ImageButton ib : imageButtonsList) {
            ib.setBackgroundColor(Color.parseColor(non_active_follower));
        }
    }

    public Resources getUserFollowerChoice() {
        return userFollowerChoice;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_follower_choice);

        //NEXT BUTTON
        final Button ButtonNext = findViewById(R.id.FCA_button_next);
        ButtonNext.setEnabled(false);
        ButtonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoadNewActivity = new Intent(FollowerChoiceActivity.this, MainActivity.class);
                startActivity(intentLoadNewActivity);
            }
        });

        //BACK BUTTON
        final Button ButtonBack = findViewById(R.id.FCA_button_back);
        ButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoadNewActivity = new Intent(FollowerChoiceActivity.this, CreateProfileActivity.class);
                startActivity(intentLoadNewActivity);
            }
        });

        imageButtonsList = new ArrayList<ImageButton>(FOLLOWER_IDS.length);

        for(int id : FOLLOWER_IDS) {
            final ImageButton ib = (ImageButton)findViewById(id);
            ib.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    userFollowerChoice = ib.getResources();
                    setAllFollowerNone();
                    ib.setBackgroundColor(Color.parseColor(active_follower));
                    ButtonNext.setEnabled(true);
                }
            });
            imageButtonsList.add(ib);
        }
    }
}
