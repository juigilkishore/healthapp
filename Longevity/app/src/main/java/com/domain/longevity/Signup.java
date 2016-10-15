package com.domain.longevity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.util.Patterns;

public class Signup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
    }

    public void returning_user(View view){
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }

    public void signup_button(View view) {
        if (validateInputs()) {
            Intent intent = new Intent(this, Questionnaire.class);
            startActivity(intent);
        }
    }

    public boolean validateInputs()
    {
        EditText username = (EditText) findViewById(R.id.signupUser);
        EditText phone_number = (EditText) findViewById(R.id.signupPhone);
        EditText email = (EditText) findViewById(R.id.signupEmail);
        EditText password = (EditText) findViewById(R.id.signupPassword);
        EditText confirm_password = (EditText) findViewById(R.id.signupConfirmPassword);

        if (!validateUsername(username.getText().toString())){
            username.setError("Invalid Username !");
            username.requestFocus();
            return false;
        }
        else if (!validatePhoneNumber(phone_number.getText().toString())){
            phone_number.setError("Invalid Phone number !");
            phone_number.requestFocus();
            return false;
        }
        else if (!validateEmail(email.getText().toString())) {
            email.setError("Invalid Email ID !");
            email.requestFocus();
            return false;
        }
        else if (!validatePassword(password.getText().toString())){
            password.setError("Minimum 6 characters !");
            password.requestFocus();
            return false;
        }
        else if (!validateConfirmPassword(password.getText().toString(),
                confirm_password.getText().toString())){
            confirm_password.setError("Mentioned password do not match !");
            confirm_password.requestFocus();
            return false;
        }
        else {
            return true;
        }
    }

    public boolean validateUsername(String username){
        return username != null && username.length() >= 6 && username.length() <=15;
    }

    public boolean validatePhoneNumber(String number){
        return number != null && Patterns.PHONE.matcher(number).matches() && number.length() == 10;
    }

    public boolean validateEmail(String email){
        return email != null && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public boolean validatePassword(String password){
        return  password != null && password.length() >= 6;
    }

    public boolean validateConfirmPassword(String password, String confirm_password){
        return password.equals(confirm_password);
    }
}
