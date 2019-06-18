package com.example.wbs;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateProfileActivity extends AppCompatActivity {

    private UserProfileClass wbsProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);

        wbsProfile = (UserProfileClass) getIntent().getExtras().getSerializable("wbsProfile");

        final EditText TxtName = findViewById(R.id.CPA_edit_name);
        final EditText TxtAge = findViewById(R.id.CPA_edit_age);
        final Button ButtonFemale = findViewById(R.id.CPA_button_female);
        final Button ButtonMale = findViewById(R.id.CPA_button_male);
        final Button nextActivity = findViewById(R.id.CPA_button_next);
        final Button changeActivityProfile = findViewById(R.id.CPA_button_next);

        //nextActivity.setEnabled(false);
        TxtName.setText("");
        TxtAge.setText("");

        if (wbsProfile.getisProfile()) {
            TxtName.setText("" + wbsProfile.getName());
            TxtAge.setText("" + wbsProfile.getAge());
            if (wbsProfile.getGender() == UserProfileClass.Gender.MALE) {
                ButtonMale.setBackgroundColor(Color.rgb(0x3f, 0x51, 0xb5));
                ButtonMale.setBackgroundResource(0);

            } else {
                ButtonFemale.setBackgroundColor(Color.rgb(0xff, 0x69, 0xb4));
                ButtonMale.setBackgroundResource(0);
            }
        }

        nextActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wbsProfile.setisProfile(true);
                JsonUtil.WBSProfileToJson(CreateProfileActivity.this, wbsProfile);
                Intent mySuperIntent = new Intent(CreateProfileActivity.this, FollowerChoiceActivity.class);
                mySuperIntent.putExtra("wbsProfile", wbsProfile);
                startActivity(mySuperIntent);
                finish();
            }
        });

        TxtName.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                wbsProfile.setName(s.toString());
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });

        TxtAge.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                try {
                    wbsProfile.setAge(Integer.parseInt(s.toString()));
                } catch (NumberFormatException e) {
                }
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });

        ButtonFemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ButtonFemale.setBackground(Drawable.createFromPath("/drawable/mybuttonborderpink"));
                ButtonFemale.setBackgroundColor(Color.rgb(0xff, 0x69, 0xb4));//0x3F51B5);
                ButtonMale.setBackgroundResource(0);
                wbsProfile.setGender(UserProfileClass.Gender.FEMALE);
            }
        });

        ButtonMale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ButtonMale.setBackgroundColor(Color.rgb(0x3f, 0x51, 0xb5));
                //ButtonMale.setBackground(Drawable.createFromPath("/drawable/mybuttonborderblue"));
                //ButtonMale.setBackground(Drawable.createFromPath("android.resource://" + getPackageName() + "/" + getResources().getIdentifier("mybuttonborderblue","drawable",""+getPackageName())));

                ButtonFemale.setBackgroundResource(0);
                wbsProfile.setGender(UserProfileClass.Gender.MALE);
            }
        });
    }
}
