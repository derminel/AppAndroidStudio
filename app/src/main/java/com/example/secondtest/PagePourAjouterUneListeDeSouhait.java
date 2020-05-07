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
    private EditText inputText;//
    private Button Back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_pour_ajouter_une_liste_de_souhait);

        this.wishListDAO = new WishListDAO(this);
        this.publicAccess = false ;
        this.inputText = findViewById(R.id.nameNewList);

        Switch switchButton = findViewById(R.id.switchAccess);
        Button buttonConfirm = findViewById(R.id.ConfirmAddWishlist);
        Back = (Button)findViewById(R.id.notification_background);
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
            }
        });

        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WishList newList = configureWishList();
                wishListDAO.addList(newList.getName(), publicAccess, newList.getListNb(), null, null, null);
                showToast("WishList has been created");
                startActivity(new Intent(PagePourAjouterUneListeDeSouhait.this, PagesListesDeSouhaits.class));
            }
        });
    }
    public void activityretour(){
        Intent intent = new Intent(this, PagesListesDeSouhaits.class);
                startActivity(intent);
    }


    private WishList configureWishList(){
        return new WishList(this.inputText.getText().toString(), this.publicAccess,this);
    }

    private void showToast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }
}