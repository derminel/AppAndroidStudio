package com.example.secondtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.secondtest.DatabaseContract.COLUMN_LISTS_CREATOR;
import static com.example.secondtest.DatabaseContract.COLUMN_LISTS_DESCRIPTION;
import static com.example.secondtest.DatabaseContract.COLUMN_LISTS_LISTNB;
import static com.example.secondtest.DatabaseContract.COLUMN_LISTS_NAME;
import static com.example.secondtest.DatabaseContract.COLUMN_LISTS_PUBLIC;
import static com.example.secondtest.DatabaseContract.COLUMN_LISTS_RECIPIENT;
import static com.example.secondtest.DatabaseContract.COLUMN_USERS_LOGIN;
import static com.example.secondtest.DatabaseContract.COLUMN_USERS_NAME;
import static com.example.secondtest.DatabaseContract.COLUMN_USERS_PASSWORD;
import static com.example.secondtest.DatabaseContract.TABLE_LISTS;
import static com.example.secondtest.DatabaseContract.TABLE_USERS;

public class PageCreationAccount extends AppCompatActivity {
    //
    private DatabaseHelper myDb ;
    private User user;
    private UserDAO userDAO;

    private EditText login;
    private EditText password1;
    private EditText password2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_creation_account);

        this.myDb = new DatabaseHelper(this);
        this.userDAO = new UserDAO(this);

        this.login = findViewById(R.id.loginCreateAccount);
        this.password1 = findViewById(R.id.password1CreateAccount);
        this.password2 = findViewById(R.id.password2CreateAccount);

        configureNextButtonCreate();
        configureGoBackAccount();
    }

    private void showToast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    private void configureGoBackAccount(){
        final Button goBackButton = (Button) findViewById(R.id.GoBackAccount);
        goBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PageCreationAccount.this, MainActivity.class));
            }
        });
    }

    private void configureNextButtonCreate() {
        final Button nextButton = (Button) findViewById(R.id.CreateAccount);
        nextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                configureUser();
                if (login.getText().toString().equals("") || password1.getText().toString().equals("") || password2.getText().toString().equals("")){
                    showToast("Please fill in all fields");
                }
                else if (user.exist(login.getText().toString())){
                    showToast("This login is already taken");
                }
                else if (!(password1.getText().toString().equals(password2.getText().toString()))){
                    showToast("You've written 2 differents password");
                }
                else{
                    Intent intent = new Intent(PageCreationAccount.this, PageCreationProfil.class);
                    intent.putExtra("LOGIN_PROFIL", login.getText().toString());
                    intent.putExtra("PASSWORD_PROFIL", password1.getText().toString());
                    startActivity(intent);
                }
            }
        });
    }

    private void configureUser(){
        this.user = new User(this.password1.getText().toString(), this.login.getText().toString(), this);
    }
}
