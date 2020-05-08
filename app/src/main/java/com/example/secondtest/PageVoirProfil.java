package com.example.secondtest;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PageVoirProfil extends AppCompatActivity {
    private String login;

    //Creation de la page profil
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.login = getIntent().getStringExtra("Login");
        String friend = getIntent().getStringExtra("from");
        UserDAO userDAO = new UserDAO(this);
        Cursor userInfo = userDAO.getAllColumn(friend);
        userInfo.moveToFirst();

        setContentView(R.layout.activity_voir_un_ami);
        TextView title = findViewById(R.id.textNewProfile);
        title.setText(String.format("%s's profile",friend));
        TextView name = findViewById(R.id.NameDBMyProfil);
        TextView lastName = findViewById(R.id.LastNameDBMyProfil);
        TextView address = findViewById(R.id.AddressDBMyProfil);
        TextView preferences = findViewById(R.id.PreferencesDBMyProfil);
        name.setText(userInfo.getString(1));
        lastName.setText(userInfo.getString(2));
        address.setText(userInfo.getString(3));
        preferences.setText(userInfo.getString(5));

        configureButtonBack();
    }

    private void start(Class<?> cls){
        Intent page = new Intent(PageVoirProfil.this, cls);
        page.putExtra("Login", login);
        startActivity(page);
    }

    private void configureButtonBack() {
        Button retourAcceuil = findViewById(R.id.GoBackProfile);
        retourAcceuil.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){ start(PageAmis.class); }
        });
    }
}
