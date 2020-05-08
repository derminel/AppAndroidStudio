package com.example.secondtest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class PageModifierWishList extends AppCompatActivity {
    private WishListDAO wishListDAO;//
    private ModifierDAO modifierDAO;//
    private String wishlistnb;//
    private String name;//
    private boolean publicList;//
    private ArrayAdapter<String> admins;//
    private String[] Visibles;//
    private String creator;//
    private String recipient;//
    private String login;


    //Creation de la page pour modifier une wishlist
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_modifier_wishlist);

        wishListDAO = new WishListDAO(this);
        modifierDAO = new ModifierDAO(this);
        this.wishlistnb = getIntent().getStringExtra("WishlistNb");
        this.creator = wishListDAO.getCreator(wishlistnb);
        this.recipient = wishListDAO.getRecipient(wishlistnb);
        this.login = getIntent().getStringExtra("Login");

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

        ListView adminsDB = findViewById(R.id.ListViewAdmins);
        ArrayList<String> admins = modifierDAO.getAdmin(wishlistnb);

        ListView visibleDB = findViewById(R.id.ListViewVisible);
        ArrayList<String> visibles = modifierDAO.getVisible(wishlistnb);


        ArrayAdapter<String> adapterAdmin =
                new ArrayAdapter<String>(this, R.layout.row_admins, admins);
        adminsDB.setAdapter(adapterAdmin);

        ArrayAdapter<String> adapterVisible =
                new ArrayAdapter<String>(this, R.layout.row_admins, visibles);
        visibleDB.setAdapter(adapterVisible);
        //this.admins = adapter;

        configureChangeListButton();
        configureChangeAdminsButton();
        configureChangeVisibleButton();
        configureRetourDesParametres();

    }

    private void configureChangeListButton() {
        Button nextButton = (Button) findViewById(R.id.buttonChangeList);
        nextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                Intent intent = new Intent(PageModifierWishList.this, PageModifierWishListUpdate.class);
                intent.putExtra("WishlistNb", wishlistnb);
                intent.putExtra("Login", login);
                startActivity(intent);
            }
        });

    }

    private void configureChangeAdminsButton() {
        Button nextButton = (Button) findViewById(R.id.editAdmins);
        nextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                Intent intent = new Intent(PageModifierWishList.this, PagePourModifierAdmins.class);
                intent.putExtra("WishlistNb", wishlistnb);
                intent.putExtra("Login", login);
                startActivity(intent);
            }
        });

    }

    private void configureChangeVisibleButton() {
        Button nextButton = (Button) findViewById(R.id.editVisible);
        nextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                Intent intent = new Intent(PageModifierWishList.this, PagePourModiferVisibles.class);
                intent.putExtra("WishlistNb", wishlistnb);
                intent.putExtra("Login", login);
                startActivity(intent);
            }
        });

    }

    private void configureRetourDesParametres() {
        Button nextButton = findViewById(R.id.buttonRetourdesParametres);
        nextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                Intent intent = new Intent(PageModifierWishList.this, PageProduits.class);
                intent.putExtra("WishlistNb", wishlistnb);
                intent.putExtra("Login", login);
                startActivity(intent);
            }
        });

    }
}
