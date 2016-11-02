package com.domain.longevity;

import java.util.UUID;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Questionnaire extends AppCompatActivity {

    DatabaseHelper longevity_db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questionnaire);
        longevity_db = new DatabaseHelper(this);
    }

    public void addInfoData(){
        final UUID ID1 = UUID.randomUUID();
        final UUID ID2 = UUID.randomUUID();
        final UUID ID3 = UUID.randomUUID();
        final UUID ID4 = UUID.randomUUID();
        final UUID ID5 = UUID.randomUUID();
        final UUID ID6 = UUID.randomUUID();

        final EditText ans1 = (EditText) findViewById(R.id.answer1);
        final EditText ans2 = (EditText) findViewById(R.id.answer2);
        final EditText ans3 = (EditText) findViewById(R.id.answer3);
        final EditText ans4 = (EditText) findViewById(R.id.answer4);
        final EditText ans5 = (EditText) findViewById(R.id.answer5);
        final EditText ans6 = (EditText) findViewById(R.id.answer6);

        Intent intent = getIntent();
        String user_id = intent.getStringExtra("user_id");
        String [] qIDList = intent.getStringArrayExtra("question_id_list");

        boolean isInfoAdded1 = longevity_db.insertInfoData(ID1.toString(), user_id, qIDList[0],
                ans1.getText().toString(), null);
        boolean isInfoAdded2 = longevity_db.insertInfoData(ID2.toString(), user_id, qIDList[1],
                ans2.getText().toString(), null);
        boolean isInfoAdded3 = longevity_db.insertInfoData(ID3.toString(), user_id, qIDList[2],
                ans3.getText().toString(), null);
        boolean isInfoAdded4 = longevity_db.insertInfoData(ID4.toString(), user_id, qIDList[3],
                ans4.getText().toString(), null);
        boolean isInfoAdded5 = longevity_db.insertInfoData(ID5.toString(), user_id, qIDList[4]
                , ans5.getText().toString(), null);
        boolean isInfoAdded6 = longevity_db.insertInfoData(ID6.toString(), user_id, qIDList[5],
                ans6.getText().toString(), null);

        boolean isInfoAdded = isInfoAdded1 && isInfoAdded2 && isInfoAdded3 && isInfoAdded4 &&
                isInfoAdded5 && isInfoAdded6;
        if (isInfoAdded)
            Toast.makeText(this, "User information Submitted", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "Failed to Submit information !", Toast.LENGTH_SHORT).show();

    }
    public void submit_button(View view) {
        addInfoData();
    }
}
