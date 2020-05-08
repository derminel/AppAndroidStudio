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
        setContentView(R.layout.activity_page_creation_profil);
        this.login = getIntent().getStringExtra("LOGIN_PROFIL");
        this.password = getIntent().getStringExtra("PASSWORD_PROFIL");

        this.myDb = new DatabaseHelper(this);
        this.profile = new ProfileDAO(this);
        this.userDAO = new UserDAO(this);

        this.name = (EditText) findViewById(R.id.NameDB);
        this.last_name = (EditText) findViewById(R.id.LastNameDB);
        this.address = (EditText) findViewById(R.id.AddressDB);
        this.preferences = (EditText) findViewById(R.id.PreferencesDB);

        configureButtonCreateProfile();
        configureGoBackProfile();
    }

    private void configureGoBackProfile(){
        final Button goBackButton = (Button) findViewById(R.id.GoBackProfileCreate);
        goBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PageCreationProfil.this, PageCreationAccount.class));
            }
        });
    }

    private void configureButtonCreateProfile() {

        Button buttonSaveProfile = (Button) findViewById(R.id.buttonCreateProfile);
        buttonSaveProfile.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                User user =configureUser();
                if (name.getText().toString().equals("") || last_name.getText().toString().equals("") || address.getText().toString().equals("")){
                    showToast("Please fill in the 3 first fields");
                }
                else{
                    user.signIn(login, password);
                    user.setProfile(name.getText().toString(), last_name.getText().toString(),
                            null, address.getText().toString(), preferences.getText().toString());
                    userDAO.addUser(user.getProfile().getName(),user.getProfile().getLastname(),user.getLogin(),user.getPassword(),
                            null, user.getProfile().getAddress(), user.getProfile().getPreferences());
                    Intent intent = new Intent(PageCreationProfil.this, PageAccueil.class);
                    intent.putExtra("LOGIN_ACCUEIL_APRES_CREATION", login);
                    startActivity(intent);
                }

            }
        });
    }

    private User configureUser(){
        return new User(null, null, this);
    }

    private void showToast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

}
