package de.hszg.wbs;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ShowProfileActivity extends AppCompatActivity {
    private UserProfileClass wbsProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_profile);

        wbsProfile = JsonUtil.readProfileFromJson(this);

        if (wbsProfile.getAction().equals("editProfileOK")) {
            Toast toast = Toast.makeText(this,"Profil erfolgreich geändert!", Toast.LENGTH_LONG);
            toast.show();
            wbsProfile.setAction("");
            JsonUtil.WBSProfileToJson(ShowProfileActivity.this, wbsProfile);
            wbsProfile = JsonUtil.readProfileFromJson(this);
        }

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final ImageView ImageEdit = findViewById(R.id.SPA_image_edit);
        ImageEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wbsProfile.setAction("editProfile");
                JsonUtil.WBSProfileToJson(getApplicationContext(), wbsProfile);
                startActivity(new Intent(getApplicationContext(), CreateProfileActivity.class));
                finish();
            }
        });

        final ImageView ImageDelete = findViewById(R.id.SPA_image_delete);
        ImageDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(ShowProfileActivity.this);
                alert.setTitle("WBS - Profile");
                alert.setMessage("Möchtest du dein Profil wirklich löschen?");
                alert.setPositiveButton("Ja", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        JsonUtil.DeleteProfile(ShowProfileActivity.this);
                        startActivity(new Intent(getApplicationContext(), CreateProfileActivity.class));
                        finish();
                    }
                });
                alert.setNegativeButton("Nein", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                alert.create();
                alert.show();
            }
        });

        final TextView TxtName = findViewById(R.id.SPA_text_name2);
        TxtName.setText("" + wbsProfile.getName());
        final TextView TxtSex = findViewById(R.id.SPA_text_sex2);
        TxtSex.setText("" + wbsProfile.getGender().getBezeichner());
        final TextView TxtAge = findViewById(R.id.SPA_text_age2);
        TxtAge.setText("" + wbsProfile.getAge());
        final TextView TxtColor = findViewById(R.id.SPA_text_color2);
        TxtColor.setText("" + wbsProfile.getColor());
        TxtColor.setTextColor(Color.parseColor(wbsProfile.getColor()));
        TxtColor.setBackgroundColor(Color.parseColor(wbsProfile.getColor()));

        final TextView TxtStars = findViewById(R.id.SPA_text_starcount);
        TxtStars.setText(wbsProfile.getStars() + "x");

        final ImageView ImageFollower = findViewById(R.id.SPA_image_follower);
        ImageFollower.setImageResource(wbsProfile.getFollower());
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }

    public void onBackPressed(){
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(myIntent);
        finish();
    }

}
