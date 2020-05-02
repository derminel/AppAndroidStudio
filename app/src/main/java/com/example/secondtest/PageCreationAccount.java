package com.example.secondtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PageCreationAccount extends AppCompatActivity {

    private DatabaseHelper myDb ;
    private User user;

    private EditText login;
    private EditText password1;
    private EditText password2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_creation_account);

        this.myDb = new DatabaseHelper(this);

        this.login = findViewById(R.id.loginCreateAccount);
        this.password1 = findViewById(R.id.password1CreateAccount);
        this.password2 = findViewById(R.id.password2CreateAccount);

        configureNextButtonCreate();
    }

    private void showToast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    private void configureNextButtonCreate() {
        Button nextButton = (Button) findViewById(R.id.ConfirmConnection);
        nextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (user.exist(login.getText().toString())){
                    showToast("This login is already taken");
                }
                else if (password1 != password2){
                    showToast("You've written 2 differents password");
                }
                else{
                    Intent resultIntentCreateAccount = new Intent();
                    resultIntentCreateAccount.putExtra("inputTextCreateAccountLogin", login.getText().toString());
                    resultIntentCreateAccount.putExtra("inputTextCreateAccountPassword", password1.getText().toString());

                    setResult(RESULT_OK, resultIntentCreateAccount);

                    startActivity(new Intent(PageCreationAccount.this, PageProfil.class));
                }
            }
        });
    }

    /*public void AddData() {
        boolean isInserted = myDb.insertData();
        if(isInserted){
            Toast.makeText(MainActivity.this,"Data Inserted",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(MainActivity.this,"Data not Inserted",Toast.LENGTH_LONG).show();
        }
    }*/
}
