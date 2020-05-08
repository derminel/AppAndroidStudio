package com.example.secondtest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

public class PageModifierWishListUpdate extends AppCompatActivity {
    private WishListDAO wishListDAO;//
    private String wishlistNb;//
    private EditText name;//
    private EditText recipient;//
    private boolean publicAccess;//

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_modifier_wishlist_update);
        this.wishListDAO = new WishListDAO(this);
        this.wishlistNb = getIntent().getStringExtra("WishlistNb");

        this.name = findViewById(R.id.list_nameDB);
        this.recipient = findViewById(R.id.recipient);
        this.publicAccess = false ;

        Switch switchButton = findViewById(R.id.switchpublic);

        switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    publicAccess = true;
                }
            }
        });


        configureButtonSaveWishlist();

    }

    private void configureButtonSaveWishlist() {
        Button nextButton = (Button)  findViewById(R.id.buttonSaveList);
        nextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){

                wishListDAO.updateWishlist(wishlistNb, name.getText().toString(), recipient.getText().toString(), publicAccess);
                Intent intent = new Intent(PageModifierWishListUpdate.this, PageModifierWishList.class);
                intent.putExtra("WishlistNb", wishlistNb);
                startActivity(intent);
            }
        });
    }
}
