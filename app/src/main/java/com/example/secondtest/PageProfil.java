package com.example.secondtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import android.os.Bundle;

public class PageProfil extends AppCompatActivity {
    private String login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.login = getIntent().getStringExtra("Login");
        UserDAO userDAO = new UserDAO(this);
        Cursor userInfo = userDAO.getAllColumn(login);
        userInfo.moveToFirst();

        setContentView(R.layout.activity_page_profil);
        TextView name = findViewById(R.id.NameDBMyProfil);
        TextView lastName = findViewById(R.id.LastNameDBMyProfil);
        TextView address = findViewById(R.id.AddressDBMyProfil);
        TextView preferences = findViewById(R.id.PreferencesDBMyProfil);
        name.setText(userInfo.getString(1));
        lastName.setText(userInfo.getString(2));
        address.setText(userInfo.getString(3));
        preferences.setText(userInfo.getString(5));

        configureButtonChangeProfile();
        configureButtonBack();
    }

    private void start(Class<?> cls){
        Intent page = new Intent(PageProfil.this, cls);
        page.putExtra("Login", login);
        startActivity(page);
    }

    private void configureButtonChangeProfile() {
        Button changeProfile = findViewById(R.id.editProfile);
        changeProfile.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){ start(PageProfilUpdate.class); }
        });

    }

    private void configureButtonBack() {
        Button retourAcceuil = findViewById(R.id.GoBackProfile);
        retourAcceuil.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){ start(PageAccueil.class); }
        });
    }
}
