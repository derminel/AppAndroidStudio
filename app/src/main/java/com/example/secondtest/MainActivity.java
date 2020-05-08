package com.example.secondtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private User user;
    EditText login;
    EditText password;

    //A supprimer si inutile
    //private DatabaseHelper myDb ;
    //private FriendsDAO friendsDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        this.login = findViewById(R.id.login);
        this.password = findViewById(R.id.password);

        //A supprimer si inutile
        //this.myDb = new DatabaseHelper(this);
        //this.friendsDAO = new FriendsDAO(this);

        configureButtonSignIn();
        configureButtonSignUp();
    }

    private void showToast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    private void configureUser(){
        this.user = new User(this.password.getText().toString(), this.login.getText().toString(), this);
    }

    private void configureButtonSignIn() {
        Button SignIn = findViewById(R.id.ConfirmConnection);
        SignIn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                configureUser();
                if((login.getText().toString().equals("")) || (login.getText().toString().equals(""))) {
                    showToast("You have to enter your login, and your password");
                }
                else if (!(user.exist(login.getText().toString()))){
                    showToast("You have got no account, create one");
                }
                else if (!(user.connection(login.getText().toString(), password.getText().toString()))){
                    showToast("You've made a mistake in your password");
                }
                else{
                    Intent gotoSignIn = new Intent(MainActivity.this, PageAccueil.class);
                    gotoSignIn.putExtra("Login", login.getText().toString());
                    startActivity(gotoSignIn);
                }
            }
        });
    }

    private void configureButtonSignUp() {
        Button SignUp = findViewById(R.id.CreateAccount);
        SignUp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                Intent gotoSignup= new Intent(MainActivity.this, PageCreationAccount.class);
                gotoSignup.putExtra("Login", login.getText().toString());
                startActivity(gotoSignup);
            }
        });
    }
}
