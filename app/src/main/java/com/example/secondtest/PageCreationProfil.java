package com.example.secondtest;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PageCreationProfil extends AppCompatActivity {
    private DatabaseHelper myDb ;
    private EditText name;//
    private EditText last_name;//
    private EditText address; //
    private EditText preferences;//
    private ProfileDAO profile;//
    private UserDAO userDAO;
    private String login;//
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.myDb = new DatabaseHelper(this);
        this.profile = new ProfileDAO(this);
        this.userDAO = new UserDAO(this);
        this.login = getIntent().getStringExtra("Login");
        this.password = getIntent().getStringExtra("Password");

        setContentView(R.layout.activity_page_creation_profil);
        this.name = findViewById(R.id.NameDB);
        this.last_name = findViewById(R.id.LastNameDB);
        this.address = findViewById(R.id.AddressDB);
        this.preferences = findViewById(R.id.PreferencesDB);

        configureButtonCreateProfile();
        configureGoBackProfile();
    }

    private void showToast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }
    private void start(Class<?> cls){
        Intent page = new Intent(PageCreationProfil.this, cls);
        page.putExtra("Login", login);
        startActivity(page);
    }
    private User configureUser(){
        return new User(null, null, this);
    }
    private void configureGoBackProfile(){
        Button goBackButton = findViewById(R.id.GoBackProfileCreate);
        goBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { start(PageCreationAccount.class);
            }
        });
    }

    private void configureButtonCreateProfile() {
        Button buttonSaveProfile = findViewById(R.id.buttonCreateProfile);
        buttonSaveProfile.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                User user = configureUser();
                if (name.getText().toString().equals("") ||
                        last_name.getText().toString().equals("")){
                    showToast("Please fill in the 2 first fields");
                }
                else{
                    user.signIn(login, password);
                    user.setProfile(name.getText().toString(), last_name.getText().toString(),
                            null, address.getText().toString(), preferences.getText().toString());
                    userDAO.addUser(name.getText().toString(), last_name.getText().toString(), login, password, null,
                            address.getText().toString(), preferences.getText().toString());
                    start(PageAccueil.class);
                }

            }
        });
    }

}
