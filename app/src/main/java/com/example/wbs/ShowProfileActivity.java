package com.example.wbs;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
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
        final TextView TxtName = findViewById(R.id.SPA_text_name);
        TxtName.setText(wbsProfile.getName());
    }

    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        myIntent.putExtra("wbsProfile", wbsProfile);
        startActivityForResult(myIntent, 0);
        finish();
        return true;
    }
}
