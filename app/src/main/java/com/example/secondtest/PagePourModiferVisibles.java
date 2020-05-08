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

public class PagePourModiferVisibles extends AppCompatActivity {
    String wishlistNb;
    ListView listViewVis;
    SearchView searchView;
    Button addButton;
    private String login;
    ArrayList<String> visibles;
    ArrayAdapter<String> adapter;
    ModifierDAO modifierDAO;

    //Creation de la page pour modifier les logins qui peuvent voir la wishlist ou non
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_changer_visibles);
        this.wishlistNb  = getIntent().getStringExtra("WishlistNb");
        this.login  = getIntent().getStringExtra("Login");
        modifierDAO = new ModifierDAO(this);
        listViewVis = findViewById(R.id.ListViewVisible);
        initList();
        configureAddButton();
        configureBackButton();
        configureSearch();
    }
    private void start(Class<?> cls){
        Intent page = new Intent(PagePourModiferVisibles.this, cls);
        page.putExtra("Login", login);
        page.putExtra("WishlistNb", wishlistNb);
        startActivity(page);
    }
    private void initList(){
        this.visibles = modifierDAO.getVisible(wishlistNb);
        adapter=new CustomAdapterVisibles(this, R.layout.row_wishlists, visibles, wishlistNb);
        listViewVis.setAdapter(adapter);
    }
    private void configureSearch(){
        searchView = findViewById(R.id.SearchbarVisibles);
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
    }
    //bouton pour ajouter des amis
    private void configureAddButton() {
        Button addFriend = findViewById(R.id.addVisblebutton);
        addFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int err = configureModifierDao();
                if (err == -2) {
                    showToast("This login does not exist");
                }
                if (err == -1) {
                    showToast("This login is already an admin and can edit your list");
                }
                if (err == -3) {
                    showToast("You can not ");
                }
                showToast(searchView.getQuery().toString() + " has been added as a reader");
            }
        });
    }

    private void configureBackButton(){
        Button BackButton = findViewById(R.id.buttonRetourVisible);
        BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start(PageModifierWishList.class);
            }
        });
    }

    private int configureModifierDao(){
        return modifierDAO.updateVisible(wishlistNb, searchView.getQuery().toString(),this);
    }


    private void showToast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }
}
