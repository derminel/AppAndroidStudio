package com.example.secondtest;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PageProfilUpdate extends AppCompatActivity {
    private EditText name;
    private EditText lastName;
    private EditText address;
    private EditText preferences;
    private UserDAO userDAO;
    private String login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.login = getIntent().getStringExtra("Login");
        this.userDAO = new UserDAO(this);
        Cursor userInfo = userDAO.getAllColumn(login);
        userInfo.moveToFirst();

        setContentView(R.layout.activity_page_profil_update);
        this.name = findViewById(R.id.name_update);
        this.lastName = findViewById(R.id.last_name_update);
        this.address = findViewById(R.id.address_update);
        this.preferences = findViewById(R.id.preferences_update);
        name.setText(userInfo.getString(1));
        lastName.setText(userInfo.getString(2));
        address.setText(userInfo.getString(3));
        preferences.setText(userInfo.getString(5));

        configureButtonSaveProfile();
        configureBack();
    }
    private void showToast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }
    private void start(Class<?> cls){
        Intent page = new Intent(PageProfilUpdate.this, cls);
        page.putExtra("Login", login);
        startActivity(page);
    }

    private void configureBack() {
        Button retourAcceuil = findViewById(R.id.GoBackProfileUpdate);
        retourAcceuil.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){ start(PageProfil.class); }
        });
    }

    private void configureButtonSaveProfile() {
        Button buttonSaveProfile = findViewById(R.id.buttonSaveProfile);
        buttonSaveProfile.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                if (name.getText().toString().equals("") || lastName.getText().toString().equals("")){
                    showToast("Please fill in the 2 first fields");
                }
                else{
                    userDAO.updateProfile(login, name.getText().toString(), lastName.getText().toString(),
                            address.getText().toString(), preferences.getText().toString());
                    start(PageProfil.class);
                }
            }
        });
    }
}
