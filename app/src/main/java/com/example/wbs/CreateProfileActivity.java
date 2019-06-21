package com.example.wbs;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
    String gender = "";
    String color = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);

        wbsProfile = JsonUtil.readProfileFromJson(this);

        final EditText TxtName = findViewById(R.id.CPA_edit_name);
        final EditText TxtAge = findViewById(R.id.CPA_edit_age);
        final Button ButtonFemale = findViewById(R.id.CPA_button_female);
        final Button ButtonMale = findViewById(R.id.CPA_button_male);
        final Button nextActivity = findViewById(R.id.CPA_button_next);
        final Button ButtonColor_Yellow = findViewById(R.id.CPA_button_color_yellow);
        final Button ButtonColor_Red = findViewById(R.id.CPA_button_color_red);
        final Button ButtonColor_Blue = findViewById(R.id.CPA_button_color_blue);
        final Button ButtonColor_Green = findViewById(R.id.CPA_button_color_green);
        final Button changeActivityProfile = findViewById(R.id.CPA_button_next);


        //nextActivity.setEnabled(false);
        TxtName.setText("");
        TxtAge.setText("");

        if (wbsProfile.getisProfile()) {
            TxtName.setText("" + wbsProfile.getName());
            TxtAge.setText("" + wbsProfile.getAge());
            if (wbsProfile.getGender().getBezeichner().equals(UserProfileClass.Gender.MALE.getBezeichner())) {
                ButtonMale.setBackgroundColor(Color.rgb(0x3f, 0x51, 0xb5));
                ButtonFemale.setBackgroundResource(0);
                setGenderUsed("FEMALE");
            } else {
                ButtonFemale.setBackgroundColor(Color.rgb(0xff, 0x69, 0xb4));
                ButtonMale.setBackgroundResource(0);
                setGenderUsed("MALE");
            }
            switch (wbsProfile.getColor()) {
                case "#FFFF00": buttonSelected(ButtonColor_Yellow, "#FFFF00");
                break;
                case "#FF0000": buttonSelected(ButtonColor_Red, "#FF0000");
                break;
                case "#0000FF": buttonSelected(ButtonColor_Blue, "#0000FF");
                break;
                case "#00FF00": buttonSelected(ButtonColor_Green, "#00FF00");
                break;
            }
        }

        nextActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TxtName.getText().equals("") && !TxtAge.getText().equals("") && !gender.equals("") && !color.equals("")) {
                    wbsProfile.setisProfile(true);
                    JsonUtil.WBSProfileToJson(CreateProfileActivity.this, wbsProfile);
                    Intent mySuperIntent = new Intent(CreateProfileActivity.this, FollowerChoiceActivity.class);
                    startActivity(mySuperIntent);
                    finish();
                }
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
                setGenderUsed("FEMALE");
                ButtonFemale.setBackground(Drawable.createFromPath("/drawable/mybuttonborderpink"));
                ButtonFemale.setBackgroundColor(Color.rgb(0xff, 0x69, 0xb4));//0x3F51B5);
                ButtonMale.setBackgroundResource(0);
                wbsProfile.setGender(UserProfileClass.Gender.FEMALE);
            }
        });

        ButtonMale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setGenderUsed("MALE");
                ButtonMale.setBackgroundColor(Color.rgb(0x3f, 0x51, 0xb5));
                //ButtonMale.setBackground(Drawable.createFromPath("/drawable/mybuttonborderblue"));
                //ButtonMale.setBackground(Drawable.createFromPath("android.resource://" + getPackageName() + "/" + getResources().getIdentifier("mybuttonborderblue","drawable",""+getPackageName())));
                ButtonFemale.setBackgroundResource(0);
                wbsProfile.setGender(UserProfileClass.Gender.MALE);
            }
        });

        ButtonColor_Yellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { ;
                buttonSelected(ButtonColor_Yellow, "#FFFF00");
            }
        });
        ButtonColor_Red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonSelected(ButtonColor_Red, "#FF0000");
            }
        });
        ButtonColor_Blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonSelected(ButtonColor_Blue, "#0000FF");
            }
        });
        ButtonColor_Green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonSelected(ButtonColor_Green, "#00FF00");
            }
        });
    }

    private void setGenderUsed(String gender) {
        this.gender = gender;
    }

    private void buttonSelected(Button b1, String color) {
        this.color = color;
        wbsProfile.setColor(color);
        Button ButtonColor_Yellow = findViewById(R.id.CPA_button_color_yellow);
        ButtonColor_Yellow.setText("");
        Button ButtonColor_Red = findViewById(R.id.CPA_button_color_red);
        ButtonColor_Red.setText("");
        Button ButtonColor_Blue = findViewById(R.id.CPA_button_color_blue);
        ButtonColor_Blue.setText("");
        Button ButtonColor_Green = findViewById(R.id.CPA_button_color_green);
        ButtonColor_Green.setText("");
        b1.setText("o");
    }
}
