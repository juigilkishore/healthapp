package com.domain.longevity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Questionnaire extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questionnaire);
    }

    public void submit_button(View view) {
        Toast.makeText(this, "Submitted", Toast.LENGTH_SHORT).show();
    }
}