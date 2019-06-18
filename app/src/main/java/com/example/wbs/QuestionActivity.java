package com.example.wbs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class QuestionActivity extends AppCompatActivity {

    private List<CheckBox> checkboxes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        ArrayList<QuestionClass> questionClass = JsonUtil.readQuestionFromJson(this);
        int questionNr = 0; //DEBUG

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

            checkboxes.add(check);

            LinearLayout lin = (LinearLayout) findViewById(R.id.QA_LinearLayOut_answerLayout);
            lin.addView(check);
        }

    }
}
