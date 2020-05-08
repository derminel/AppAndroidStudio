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
    private DatabaseHelper myDb ;
    private EditText name;//
    private EditText lastName;//
    private EditText address; //
    private EditText preferences;//
    private UserDAO userDAO;//
    private String login;//
    private Cursor userInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_profil_update);

        this.login = getIntent().getStringExtra("LOGIN_CHANGE");

        this.myDb = new DatabaseHelper(this);
        this.userDAO = new UserDAO(this);

        this.name = (EditText) findViewById(R.id.name_update);
        this.lastName = (EditText) findViewById(R.id.last_name_update);
        this.address = (EditText) findViewById(R.id.address_update);
        this.preferences = (EditText) findViewById(R.id.preferences_update);

        this.userInfo = userDAO.getAllColumn(login);
        userInfo.moveToFirst();

        name.setText(userInfo.getString(1));
        lastName.setText(userInfo.getString(2));
        address.setText(userInfo.getString(3));
        preferences.setText(userInfo.getString(5));

        configureButtonSaveProfile();
        configureNextButtonBackToProfile();
    }

    private void configureNextButtonBackToProfile() {
        Button retourAcceuil = (Button) findViewById(R.id.GoBackProfileUpdate);
        retourAcceuil.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                Intent intent = new Intent(PageProfilUpdate.this, PageProfil.class);
                intent.putExtra("LOGIN_PROFIL_RELOAD", login);
                startActivity(intent);
            }
        });
    }

    private void configureButtonSaveProfile() {
        Button buttonSaveProfile = (Button) findViewById(R.id.buttonSaveProfile);
        buttonSaveProfile.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                if (name.getText().toString().equals("") || lastName.getText().toString().equals("") || address.getText().toString().equals("")){
                    showToast("Please fill in the 3 first fields");
                }
                else{
                    userDAO.updateProfile(login, name.getText().toString(), lastName.getText().toString(),
                            address.getText().toString(), preferences.getText().toString());
                    Intent intent = new Intent(PageProfilUpdate.this, PageProfil.class);
                    intent.putExtra("LOGIN_PROFIL_RELOAD", login);
                    startActivity(intent);
                }

            }
        });
    }

    private void showToast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }
}
