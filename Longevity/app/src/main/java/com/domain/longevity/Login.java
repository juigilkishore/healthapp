package com.domain.longevity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.text.TextUtils;

public class Login extends AppCompatActivity {

    DatabaseHelper longevity_db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        longevity_db = new DatabaseHelper(this);
    }

    public void login_button(View view) {
        if (validateInputs()) {
            Intent intent = new Intent(this, Questionnaire.class);
            intent.putExtra("user_id", longevity_db.getUserID());
            intent.putExtra("question_id_list", longevity_db.getQuestionIDs());
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

    public boolean validateUserPasswordCombination(String userIdentifier, String password) {
        boolean isPhone = TextUtils.isDigitsOnly(userIdentifier);
        return longevity_db.validateReturningUser(userIdentifier, password, isPhone);
    }

}
