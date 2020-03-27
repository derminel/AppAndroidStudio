package com.example.secondtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PageCreationAccount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_creation_account);

        configureNextButton();
    }

    private void configureNextButton() {
        Button nextButton = (Button)  findViewById(R.id.Create);
        nextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                startActivity(new Intent(PageCreationAccount.this, PageProfil.class));
            }
        });
    }
}
