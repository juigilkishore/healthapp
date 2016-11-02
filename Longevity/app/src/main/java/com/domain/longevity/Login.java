package com.domain.longevity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    DatabaseHelper longevity_db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        longevity_db = new DatabaseHelper(this);
    }

    public void login_button(View view) {
        Intent intent = getIntent();
        String user_id = intent.getStringExtra("user_id");
        String [] qIDList = intent.getStringArrayExtra("question_id_list");

        if (validateInputs()) {
            intent = new Intent(this, Questionnaire.class);
            intent.putExtra("user_id", user_id);
            intent.putExtra("question_id_list", qIDList);
            startActivity(intent);
        }
    }

    public boolean validateInputs()
    {
        EditText user_identifier = (EditText) findViewById(R.id.loginUserIdentifier);
        EditText password = (EditText) findViewById(R.id.loginPassword);

        if (!validateUserPasswordCombination(
                user_identifier.getText().toString(), password.getText().toString())){
            Toast.makeText(this, "Provided credentials do not match !!", Toast.LENGTH_SHORT).show();
            return false;
        }
         else {
            return true;
        }
    }

    public boolean validateUserPasswordCombination(String user_identifier, String password){
        // TODO: Add logic to check in the DB for the existence of the user and the combination
        // TODO: with password return boolean accordingly
        // TODO: Add logic to check in the DB with the provided password
        return true;
    }

}
