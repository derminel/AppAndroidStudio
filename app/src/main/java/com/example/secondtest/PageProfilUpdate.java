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
    ProfileDAO profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_profil_update);

        this.myDb = new DatabaseHelper(this);
        this.profile = new ProfileDAO(this);

        this.name = findViewById(R.id.name_update);
        this.last_name = findViewById(R.id.last_name_update);
        this.address = findViewById(R.id.address_update);
        this.preferences = findViewById(R.id.preferences_update);
        configureButtonSaveProfile();

    }

    private void configureButtonSaveProfile() {
        profile.updateProfile("derminel", name.toString(), last_name.toString(), address.toString(), preferences.toString());
        Button buttonSaveProfile = (Button) findViewById(R.id.buttonSaveProfile);
        buttonSaveProfile.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                startActivity(new Intent(PageProfilUpdate.this, PageProfil.class));
            }
        });
    }



}
