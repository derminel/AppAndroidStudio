package com.example.secondtest;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PageProfilUpdate extends AppCompatActivity {
    private DatabaseHelper myDb ;
    EditText name;
    EditText last_name;
    EditText address;
    EditText preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_profil_update);

        this.myDb = new DatabaseHelper(this);

        this.name = findViewById(R.id.name_update);
        this.last_name = findViewById(R.id.last_name_update);
        this.address = findViewById(R.id.address_update);
        this.preferences = findViewById(R.id.preferences_update);


    }


}
