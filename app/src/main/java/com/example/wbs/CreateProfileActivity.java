package com.example.wbs;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateProfileActivity extends AppCompatActivity {

    private UserProfileClass wbsProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        wbsProfile = (UserProfileClass) getIntent().getExtras().getSerializable("wbsProfile");

        setContentView(R.layout.activity_create_profile);

        Button nextActivity = findViewById(R.id.CPA_button_next);

        nextActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mySuperIntent = new Intent(CreateProfileActivity.this, FollowerChoiceActivity.class);
                mySuperIntent.putExtra("wbsProfile", wbsProfile);
                startActivity(mySuperIntent);
                finish();
            }
        });

        final Button ButtonFemale = findViewById(R.id.CPA_button_female);
        final Button ButtonMale = findViewById(R.id.CPA_button_male);

        ButtonFemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ButtonFemale.setBackground(Drawable.createFromPath("/drawable/mybuttonborderpink.xml"));
                ButtonFemale.setBackgroundColor(Color.rgb(0xff, 0x69, 0xb4));//0x3F51B5);
                ButtonMale.setBackgroundResource(0);
                wbsProfile.setGender(UserProfileClass.Gender.FEMALE);
            }
        });

        ButtonMale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ButtonMale.setBackgroundColor(Color.rgb(0x3f, 0x51, 0xb5));
                ButtonFemale.setBackgroundResource(0);
                //ButtonMale.setBackground(Drawable.createFromPath("/drawable/mybuttonborderblue.xml"));
                wbsProfile.setGender(UserProfileClass.Gender.MALE);
            }
        });

        final EditText EditTextName = findViewById(R.id.CPA_edit_name);
        final EditText EditTextAge = findViewById(R.id.CPA_edit_age);

        /*
        EditTextName.addTextChangedListener(new TextWatcher() {
               public void afterTextChanged(Editable s) {

               }
            }

        );*/
    }
}
