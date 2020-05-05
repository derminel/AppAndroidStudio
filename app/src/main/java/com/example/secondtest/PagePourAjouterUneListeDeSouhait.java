package com.example.secondtest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.LauncherActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

public class PagePourAjouterUneListeDeSouhait extends AppCompatActivity {
    private DatabaseHelper myDb ;
    EditText namee;
    Button confirm;
    Switch publicc;
    Boolean publicc1;
    WishListDAO WW;

    //WishlistDAO nom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_pour_ajouter_une_liste_de_souhait);
        //this.profile = new WhislistDAO(this);
        namee = (EditText) findViewById(R.id.nameNewList);
        confirm= (Button) findViewById(R.id.ConfirmAddWishlist);
        publicc= (Switch) findViewById(R.id.switch1);
        publicc1= (Boolean)  publicc.isChecked();
        this.WW = new WishListDAO(this);

        final String inputText = namee.getText().toString();
        configureButtonSaveList();

        //Button buttonConfirm = findViewById(R.id.ConfirmAddWishlist);


    }

    private void configureButtonSaveList() {

        this.WW = new WishListDAO(this);
        this.confirm.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                WW.updateWishlist(namee.getText().toString(),publicc1);
            }
        });
    }
}
