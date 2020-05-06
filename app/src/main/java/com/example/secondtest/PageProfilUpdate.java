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
    String login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_profil_update);
        this.login = getIntent().getStringExtra("LOGIN_CHANGE");

        this.myDb = new DatabaseHelper(this);
        this.profile = new ProfileDAO(this);

        this.name = (EditText) findViewById(R.id.name_update);
        this.last_name = findViewById(R.id.last_name_update);
        this.address = findViewById(R.id.address_update);
        this.preferences = findViewById(R.id.preferences_update);

        configureButtonSaveProfile();

    }

    private void configureButtonSaveProfile() {

        Button buttonSaveProfile = (Button) findViewById(R.id.buttonSaveProfile);
        buttonSaveProfile.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                profile.updateProfile(login, name.getText().toString(), last_name.getText().toString(), address.getText().toString(), preferences.getText().toString());
                Intent intent = new Intent(PageProfilUpdate.this, PageProfil.class);
                intent.putExtra("LOGIN_PROFIL", login);
                startActivity(intent);
            }
        });
    }



}
