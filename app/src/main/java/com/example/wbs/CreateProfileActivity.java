package com.example.wbs;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CreateProfileActivity extends AppCompatActivity {

    private UserProfileClass wbsProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);

        Log.i("BLT CPA [1]", "TEST");

        wbsProfile = (UserProfileClass) getIntent().getExtras().getSerializable("wbsProfile");

        Log.i("BLT CPA [2]", "TEST");

        if (wbsProfile != null) {
            final TextView TxtName = findViewById(R.id.CPA_edit_name);
            TxtName.setText("" + wbsProfile.getName());

            Log.i("BLT CPA [3]", "TEST");

            final TextView TxtAge = findViewById(R.id.CPA_edit_age);
            TxtAge.setText("" + wbsProfile.getAge());
        }


        Log.i("BLT CPA [4]", "TEST");

        final Button ButtonFemale = findViewById(R.id.CPA_button_female);
        final Button ButtonMale = findViewById(R.id.CPA_button_male);

        Log.i("BLT CPA [5]", "TEST");



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
        EditTextName.requestFocus();
        final EditText EditTextAge = findViewById(R.id.CPA_edit_age);

        /*
        EditTextName.addTextChangedListener(new TextWatcher() {
               public void afterTextChanged(Editable s) {

               }
            }

        );*/
    }
}
