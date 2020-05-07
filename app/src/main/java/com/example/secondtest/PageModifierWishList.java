package com.example.secondtest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class PageModifierWishList extends AppCompatActivity {
    private WishListDAO wishListDAO;//
    private ModifierDAO modifierDAO;//
    private String wishlistnb;//
    private String name;//
    private boolean publicList;//
    private ArrayList<String> admins;//
    private String[] Visibles;//
    private String creator;//
    private String recipient;//


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_modifier_wishlist);

        wishListDAO = new WishListDAO(this);
        modifierDAO = new ModifierDAO(this);
        this.wishlistnb = getIntent().getStringExtra("LISTNB_MODIFIER");
        this.creator = wishListDAO.getCreator(wishlistnb);
        this.recipient = wishListDAO.getRecipient(wishlistnb);

        TextView nameDB = findViewById(R.id.ListNameDB);
        nameDB.setText(wishListDAO.getName(wishlistnb));

        TextView publicDB = findViewById(R.id.PublicDB);
        if (wishListDAO.getAccess(wishlistnb)) {
            publicDB.setText("true");
        } else {
            publicDB.setText("false");
        }

        TextView creatorDB = findViewById(R.id.CreatorDB);
        creatorDB.setText(this.creator);

        TextView recipientDB = findViewById(R.id.RecipientDB);
        recipientDB.setText(this.recipient);

        configureChangeListButton();

    }

    private void configureChangeListButton() {
        Button nextButton = (Button) findViewById(R.id.buttonChangeList);
        nextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                Intent intent = new Intent(PageModifierWishList.this, PageModifierWishListUpdate.class);
                intent.putExtra("LISTNB_UPDATE", wishlistnb);
                startActivity(intent);
            }
        });

    }


}
