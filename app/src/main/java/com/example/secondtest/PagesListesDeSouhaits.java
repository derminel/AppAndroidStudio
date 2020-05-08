package com.example.secondtest;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;
import java.util.ArrayList;

public class PagesListesDeSouhaits extends AppCompatActivity {
    private CustomAdapterWishLists adapter;
    private ListView listView;
    private SearchView searchView;
    private WishListDAO wishListDAO;
    private String login;

    //Creation de la page ou l on peut consulter ses wishlists
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.wishListDAO = new WishListDAO(this);
        this.login = getIntent().getStringExtra("Login");

        setContentView(R.layout.activity_pages_listes_de_souhaits);
        listView = findViewById(R.id.ListViewWishLists);

        initList();
        configureSearchView();
        configureBackButton();
        configureAddButton();
    }

    private void start(Class<?> cls){
        Intent page = new Intent(PagesListesDeSouhaits.this, cls);
        page.putExtra("Login", login);
        startActivity(page);
    }

    private void initList(){
        ArrayList<WishList> wishLists = new ArrayList<>();
        ArrayList<String> wishlistsNb = new ArrayList<>();
        for (String listNb : this.wishListDAO.getWishLists(login)){
            wishLists.add(new WishList(wishListDAO.getName(listNb), true, this)); //mis a true juste pour la créationwishLists.add(newList);
            wishlistsNb.add(listNb);
        }
        adapter=new CustomAdapterWishLists(this, R.layout.row_wishlists, wishLists, wishlistsNb, login);
        listView.setAdapter(adapter);
    }

    private void configureSearchView(){
        this.searchView= findViewById(R.id.SearchbarWishlists);
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

    private void configureBackButton(){
        Button Back = findViewById(R.id.GoBackWishlists);
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { start(PageAccueil.class); }
        });
    }
    private void configureAddButton(){
        Button addButton = findViewById(R.id.addwishlistbutton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { start(PagePourAjouterUneListeDeSouhait.class); }
        });
    }
}
