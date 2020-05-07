package com.example.secondtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import android.os.Bundle;

public class PageProfil extends AppCompatActivity {
    private String login;//
    private String loginReload;
    private Cursor userInfo;
    private TextView name;//
    private TextView lastName;//
    private TextView address; //
    private TextView preferences;//
    private UserDAO userDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_profil);
        this.login = getIntent().getStringExtra("LOGIN_PROFIL");
        this.loginReload = getIntent().getStringExtra("LOGIN_PROFIL_RELOAD");
        if(loginReload != null){
            login = loginReload;
        }

        this.name = (TextView) findViewById(R.id.NameDBMyProfil);
        this.lastName = (TextView) findViewById(R.id.LastNameDBMyProfil);
        this.address = (TextView) findViewById(R.id.AddressDBMyProfil);
        this.preferences = (TextView) findViewById(R.id.PreferencesDBMyProfil);

        this.userDAO = new UserDAO(this);
        this.userInfo = userDAO.getAllColumn(login);
        userInfo.moveToFirst();

        name.setText(userInfo.getString(1));
        lastName.setText(userInfo.getString(2));
        address.setText(userInfo.getString(3));
        preferences.setText(userInfo.getString(5));

        configureNextButtonChangeProfile();
        configureNextButtonBackToMenu();
    }
    private void configureNextButtonChangeProfile() {
        Button changeProfile = (Button) findViewById(R.id.editProfile);
        changeProfile.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                Intent intent = new Intent(PageProfil.this, PageProfilUpdate.class);
                intent.putExtra("LOGIN_CHANGE", login);
                startActivity(intent);
            }
        });

    }

    private void configureNextButtonBackToMenu() {
        Button retourAcceuil = (Button) findViewById(R.id.GoBackProfile);
        retourAcceuil.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                Intent intent = new Intent(PageProfil.this, PageAccueil.class);
                intent.putExtra("LOGIN_ACCEUIL_APRES_VISITE_PROFIL", login);
                startActivity(intent);
            }
        });
    }
}
