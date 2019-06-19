package com.example.wbs;

import android.content.Intent;
import android.os.Bundle;
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

    private ArrayList<CheckBox> checkboxes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        final Button ButtonNext = findViewById(R.id.QA_button_next);
        final Button ButtonBack = findViewById(R.id.QA_button_back);

        ArrayList<QuestionClass> questionClass = JsonUtil.readQuestionFromJson(this);
        int questionNr = (int) getIntent().getExtras().getSerializable("VQId");

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
            check.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    setCheckGroup((CheckBox)v);
                    ButtonNext.setEnabled(true);
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
                Intent mySuperIntent;
                mySuperIntent = new Intent(QuestionActivity.this, MainActivity.class);
                startActivity(mySuperIntent);
                finish();
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

    private void setCheckGroup(CheckBox cks) {
        for (int i = 0; i < checkboxes.size(); i++) {
            checkboxes.get(i).setChecked(false);
        }
        cks.setChecked(true);
    }


}
