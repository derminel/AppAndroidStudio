package com.example.secondtest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class PagePourModifierAdmins extends AppCompatActivity {
    String wishlistNb;
    ListView listViewad;
    SearchView searchView;
    Button addButton;
    private String login;
    ArrayList<String> admins;
    ArrayAdapter<String> adapter;
    ModifierDAO modifierDAO;


    //Creation de la page pour modifier les logins qui sont administrateurs de la wishlist
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_changer_admins);
        this.wishlistNb  = getIntent().getStringExtra("WishlistNb");
        modifierDAO = new ModifierDAO(this);

        listViewad=(ListView)findViewById(R.id.ListViewAdmins);
        searchView=(SearchView) findViewById(R.id.SearchbarAdmins);
        addButton=(Button) findViewById(R.id.addAdminbutton);
        this.login  = getIntent().getStringExtra("Login");

        initList();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });

        configureAddButton();
        configureBackButton();
    }
    private void start(Class<?> cls){
        Intent page = new Intent(PagePourModifierAdmins.this, cls);
        page.putExtra("Login", login);
        page.putExtra("WishlistNb", wishlistNb);
        startActivity(page);
    }
    private void initList(){
        this.admins = modifierDAO.getAdmin(wishlistNb);
        adapter=new CustomAdapterAdmins(this, R.layout.row_wishlists, admins, wishlistNb);
        listViewad.setAdapter(adapter);
    }

    //bouton pour ajouter des amis
    private void configureAddButton() {
        Button addFriend = findViewById(R.id.addAdminbutton);
        addFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int err = configureModifierDao();
                if (err == -1) {
                    showToast("This login is already an admin");
                }
                if (err == -2) {
                    showToast("This login does not exist");
                }
                showToast(searchView.getQuery().toString() + " has been added as an admin");
            }
        });
    }

    private int configureModifierDao(){
        return modifierDAO.updateAdmin(wishlistNb, searchView.getQuery().toString(),this);
    }

    private void configureBackButton(){
        Button BackButton = findViewById(R.id.buttonRetourAdmin);
        BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start(PageModifierWishList.class);
            }
        });
    }

    private void showToast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }
}
