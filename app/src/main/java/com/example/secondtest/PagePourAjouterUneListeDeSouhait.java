package com.example.secondtest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.LauncherActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class PagePourAjouterUneListeDeSouhait extends AppCompatActivity {

    private WishListDAO wishListDAO ;//
    private boolean publicAccess;//
    private EditText name;//
    private EditText recipient;
    private EditText description;
    private Button Back;
    private String login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_pour_ajouter_une_liste_de_souhait);

        this.wishListDAO = new WishListDAO(this);
        this.publicAccess = false ;
        this.name = findViewById(R.id.setNameNewWishlist);
        this.recipient = findViewById(R.id.setRecipientNewWishlist);
        this.description = findViewById(R.id.setDescriptionNewWishlist);
        this.login = getIntent().getStringExtra("LOGIN_AJOUT_LISTE_DE_SOUHAITS");

        Switch switchButton = findViewById(R.id.switchAccess);
        Button buttonConfirm = findViewById(R.id.ConfirmAddWishlist);
        Back = (Button)findViewById(R.id.GoBackAddWishlist);
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityretour();
            }
        });

        switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    publicAccess = true;
                }
                else {
                    publicAccess = false;
                }
            }
        });

        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.getText().toString().equals("") || recipient.getText().toString().equals("")){
                    showToast("Please fill in the 2 first fields");
                }
                else{
                    WishList newList = configureWishList();
                    wishListDAO.addList(newList.getName(), publicAccess, newList.getListNb(), description.getText().toString(), recipient.getText().toString(), login);
                    showToast("WishList has been created");
                    Intent intent = new Intent(PagePourAjouterUneListeDeSouhait.this, PagesListesDeSouhaits.class);
                    intent.putExtra("LOGIN_LISTE_DE_SOUHAITS_RELOAD", login);
                    startActivity(intent);
                }
            }
        });
    }
    public void activityretour(){
        Intent intent = new Intent(this, PagesListesDeSouhaits.class);
                startActivity(intent);
    }


    private WishList configureWishList(){
        return new WishList(this.name.getText().toString(), this.publicAccess,this);
    }

    private void showToast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }
}