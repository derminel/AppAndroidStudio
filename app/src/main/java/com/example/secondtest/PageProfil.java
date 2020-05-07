package com.example.secondtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import android.os.Bundle;

public class PageProfil extends AppCompatActivity {
    private Profile profile;//
    private ProfileDAO profileDAO;//
    private String login;//



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_profil);
        this.login = getIntent().getStringExtra("LOGIN_PROFIL");
        this.profile = new Profile(login, this);

        TextView nameDB = findViewById(R.id.NameDB);
        nameDB.setText(profile.getName());

        TextView LastNameDB = findViewById(R.id.LastNameDB);
        LastNameDB.setText(profile.getLastname());


        TextView AddressDB = findViewById(R.id.AddressDB);
        AddressDB.setText(profile.getAddress());


        TextView PreferencesDB = findViewById(R.id.PreferencesDB);
        PreferencesDB.setText(profile.getPreferences());

        configureNextButtonChangeProfile();
        configureNextButtonBackToMenu();
    }
    private void configureNextButtonChangeProfile() {
        Button changeProfile = (Button) findViewById(R.id.buttonChangeProfile);
        changeProfile.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                Intent intent = new Intent(PageProfil.this, PageProfilUpdate.class);
                intent.putExtra("LOGIN_CHANGE", login);
                startActivity(intent);
            }
        });

    }

    private void configureNextButtonBackToMenu() {
        Button retourAcceuil = (Button) findViewById(R.id.buttonRetourAccueuil);
        retourAcceuil.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                Intent intent = new Intent(PageProfil.this, PageAccueil.class);
                intent.putExtra("LOGIN_ACCEUIL", login);
                startActivity(intent);
            }
        });
    }
}
