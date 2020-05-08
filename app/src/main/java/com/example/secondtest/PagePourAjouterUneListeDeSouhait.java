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

    private WishListDAO wishListDAO ;
    private boolean publicAccess;
    private EditText name;
    private EditText recipient;
    private EditText description;
    private String login;

    //creation de la page pour ajouter une liste de souhait
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.login = getIntent().getStringExtra("Login");
        this.wishListDAO = new WishListDAO(this);
        this.publicAccess = false ;

        setContentView(R.layout.activity_page_pour_ajouter_une_liste_de_souhait);
        this.name = findViewById(R.id.setNameNewWishlist);
        this.recipient = findViewById(R.id.setRecipientNewWishlist);
        this.description = findViewById(R.id.setDescriptionNewWishlist);

        configureconfirm();
        configureback();
        configureswitch();
    }
    private void start(Class<?> cls){
        Intent page = new Intent(PagePourAjouterUneListeDeSouhait.this, cls);
        page.putExtra("Login", login);
        startActivity(page);
    }
    private void configureswitch(){
        Switch switchButton = findViewById(R.id.switchAccess);
        switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) { publicAccess = isChecked; }
        });
    }
    private void configureconfirm(){
        Button buttonConfirm = findViewById(R.id.ConfirmAddWishlist);
        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.getText().toString().equals("")){
                    showToast("Please fill the name in");
                } else{
                    WishList newList = configureWishList();
                    wishListDAO.addList(newList.getName(), publicAccess, newList.getListNb(), description.getText().toString(), recipient.getText().toString(), login);
                    showToast("WishList has been created");
                    start(PagesListesDeSouhaits.class);
                }
            }
        });
    }
    private void configureback(){
        Button Back = findViewById(R.id.GoBackAddWishlist);
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start(PagesListesDeSouhaits.class);
            }
        });
    }

    private WishList configureWishList(){
        return new WishList(this.name.getText().toString(), this.publicAccess,this);
    }

    private void showToast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }
}