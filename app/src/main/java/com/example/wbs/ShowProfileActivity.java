package com.example.wbs;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONObject;

public class ShowProfileActivity extends AppCompatActivity {
    private UserProfileClass wbsProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //ActionBar actionBar = getActionBar();
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        wbsProfile = (UserProfileClass) getIntent().getExtras().getSerializable("wbsProfile");
        setContentView(R.layout.activity_show_profile);

        final TextView TxtName = findViewById(R.id.SPA_text_name2);
        TxtName.setText("" + wbsProfile.getName());
        final TextView TxtSex = findViewById(R.id.SPA_text_sex2);
        TxtSex.setText("" + wbsProfile.getGender());
        final TextView TxtAge = findViewById(R.id.SPA_text_age2);
        TxtAge.setText("" + wbsProfile.getAge());
        final TextView TxtColor = findViewById(R.id.SPA_text_color2);
        TxtColor.setText("" + wbsProfile.getColor());

        final ImageView ImageEdit = findViewById(R.id.SPA_image_edit);

        ImageEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mySuperIntent = new Intent(ShowProfileActivity.this, CreateProfileActivity.class);
                mySuperIntent.putExtra("wbsProfile", wbsProfile);
                mySuperIntent.putExtra("editProfile", true);
                startActivity(mySuperIntent);
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        myIntent.putExtra("wbsProfile", wbsProfile);
        startActivityForResult(myIntent, 0);
        finish();
        return true;
    }

}
