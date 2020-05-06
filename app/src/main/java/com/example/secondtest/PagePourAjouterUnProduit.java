package com.example.secondtest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

//Juste copier-coller A modifier
public class PagePourAjouterUnProduit extends AppCompatActivity {

    private UserDAO userDAO ;
    private FriendsDAO friendsDAO;
    private boolean publicAccess;
    private EditText inputText;
    private String login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_pour_ajouter_un_produit);

        this.login = getIntent().getStringExtra("LOGIN3");

        this.userDAO = new UserDAO(this);
        this.friendsDAO = new FriendsDAO(this);
        this.publicAccess = false ;
        this.inputText = findViewById(R.id.nameNewFriend);

        showToast(login);

        Button buttonConfirm = findViewById(R.id.ConfirmAddFriend);

        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = configureUser();
                if (user.getLogin().equals(inputText.getText().toString())){
                    showToast("You can't add yourself as friend");
                }
                else if (!(userDAO.getUserLoginDb().contains(inputText.getText().toString()))){
                    showToast("This login doesn't exist");
                }
                else if (friendsDAO.areFriends(user.getLogin(), inputText.getText().toString())){
                    showToast("You're already friends");
                }
                else {
                    boolean isGood = user.friendRequest(inputText.getText().toString());
                    if (isGood){
                        showToast("Invitation has been sent");
                    }
                    else{
                        showToast("Problem with your invitation, try again");
                    }
                    startActivity(new Intent(PagePourAjouterUnProduit.this, PageProduits.class));
                }
            }
        });
    }

    private User configureUser(){
        return new User(null,this.login,this);
    }

    private void showToast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }
}