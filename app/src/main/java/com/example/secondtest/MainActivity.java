package com.example.secondtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configureNextButtonConnect();
        configureNextButtonCreateAccount();
    }

    private void configureNextButtonConnect() {
        Button nextButton = (Button)  findViewById(R.id.ConfirmConnection);
        nextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                startActivity(new Intent(MainActivity.this, PageAccueil.class));
            }
        });
    }

    private void configureNextButtonCreateAccount() {
        Button nextButton = (Button)  findViewById(R.id.CreateAccount);
        nextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                startActivity(new Intent(MainActivity.this, PageCreationAccount.class));
            }
        });
    }
}
