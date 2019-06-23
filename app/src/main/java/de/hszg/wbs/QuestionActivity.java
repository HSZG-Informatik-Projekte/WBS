package de.hszg.wbs;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class QuestionActivity extends AppCompatActivity {

    private UserProfileClass wbsProfile;
    private int choseCheckBox;
    private ArrayList<CheckBox> checkboxes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        wbsProfile = JsonUtil.readProfileFromJson(this);

        final Button ButtonNext = findViewById(R.id.QA_button_next);
        final Button ButtonBack = findViewById(R.id.QA_button_back);

        final ArrayList<QuestionClass> questionClass = JsonUtil.readQuestionFromJson(this);
        final int questionNr = (int) getIntent().getExtras().getSerializable("VQId");
        final TextView TxtHeader = findViewById(R.id.QA_textView_header);
        TxtHeader.setText(questionClass.get(questionNr).getQuestion());

        for (int i = 0; i < questionClass.get(questionNr).getAnwers().size(); i++) {
            CheckBox check = new CheckBox(this);
            check.setText(questionClass.get(questionNr).getAnwers().get(i));
            check.setPadding(10, 10, 10, 10);
            check.setTextSize(22);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(5, 5, 5, 5);
            params.gravity = Gravity.NO_GRAVITY;
            check.setLayoutParams(params);
            check.setGravity(Gravity.CENTER);
            final int ii = i;
            check.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    setCheckGroup((CheckBox)v);
                    ButtonNext.setEnabled(true);
                    choseCheckBox = ii;
                }

            });
            checkboxes.add(check);

            LinearLayout lin = (LinearLayout) findViewById(R.id.QA_LinearLayOut_answerLayout);
            lin.addView(check);
        }

        //NEXT BUTTON
        ButtonNext.setEnabled(false);
        ButtonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(QuestionActivity.this);
                alert.setTitle("WBS");

                //Richtige Antwort
                if(choseCheckBox == questionClass.get(questionNr).getRight()) {
                    alert.setMessage("Glückwunsch :) das war richtig!");
                    alert.setPositiveButton("weiter ...", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            wbsProfile.addQuestionsid(questionNr);
                            JsonUtil.WBSProfileToJson(QuestionActivity.this, wbsProfile);

                            Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(myIntent);
                            finish();
                        }
                    });
                } else { //Falsche Antwort
                    alert.setMessage("Leider Falsch :( Versuche es nocheinmal!");
                    alert.setNegativeButton("Neuer Versuch", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    alert.setPositiveButton("Später noch einmal", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(myIntent);
                            finish();
                        }
                    });
                }
                alert.create();
                alert.show();
            }
        });

        //BACK BUTTON
        ButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    public void onBackPressed(){
        Intent myIntent = new Intent(getApplicationContext(), VideoScreenActivity.class);
        myIntent.putExtra("videoNumber", (int) getIntent().getExtras().getSerializable("videoNumber"));

        startActivity(myIntent);
        finish();
    }

    private void setCheckGroup(CheckBox cks) {
        for (int i = 0; i < checkboxes.size(); i++) {
            checkboxes.get(i).setChecked(false);
        }
        cks.setChecked(true);
    }

}
