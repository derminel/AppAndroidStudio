package com.example.secondtest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class PagePourModiferVisibles extends AppCompatActivity {
    String wishlistNb;
    ListView listViewVis;
    SearchView searchView;
    Button addButton;
    private boolean canInit = true;
    ArrayList<String> visibles;
    ArrayAdapter<String> adapter;
    ModifierDAO modifierDAO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_changer_visibles);
        this.wishlistNb  = getIntent().getStringExtra("WishlistNb");
        modifierDAO = new ModifierDAO(this);

        listViewVis=(ListView)findViewById(R.id.ListViewVisible);
        searchView=(SearchView) findViewById(R.id.SearchbarVisibles);
        addButton=(Button) findViewById(R.id.addVisblebutton);

        initList();
        /*if(canInit){
            initList();
            canInit = false;
        }*/
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

    private void initList(){
        this.visibles = modifierDAO.getVisible(wishlistNb);
        adapter=new CustomAdapterVisibles(this, R.layout.row_wishlists, visibles, wishlistNb);
        listViewVis.setAdapter(adapter);
    }
    private void configureAddButton(){
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PagePourModiferVisibles.this, PageAjouterVisible.class) ;
                intent.putExtra("WishlistNb", wishlistNb);
                startActivity(intent);
            }
        });
    }
    private void configureBackButton(){
        Button BackButton = findViewById(R.id.buttonRetourVisible);
        BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PagePourModiferVisibles.this, PageModifierWishList.class) ;
                intent.putExtra("WishlistNb", wishlistNb);
                startActivity(intent);
            }
        });
    }
}
