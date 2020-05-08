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

public class PagePourModifierAdmins extends AppCompatActivity {
    String wishlistNb;
    ListView listViewad;
    SearchView searchView;
    Button addButton;
    private boolean canInit = true;
    ArrayList<String> admins;
    ArrayAdapter<String> adapter;
    ModifierDAO modifierDAO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_changer_admins);
        this.wishlistNb  = getIntent().getStringExtra("WishlistNb");
        modifierDAO = new ModifierDAO(this);

        listViewad=(ListView)findViewById(R.id.ListViewAdmins);
        searchView=(SearchView) findViewById(R.id.SearchbarAdmins);
        addButton=(Button) findViewById(R.id.addAdminbutton);

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
    }

    private void initList(){
        this.admins = modifierDAO.getAdmin(wishlistNb);
        adapter=new CustomAdapterAdmins(this, R.layout.row_wishlists, admins, wishlistNb);
        listViewad.setAdapter(adapter);
    }
    private void configureAddButton(){
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PagePourModifierAdmins.this, PageAjouterAdmin.class) ;
                intent.putExtra("WishlistNb", wishlistNb);
                startActivity(intent);
            }
        });
    }
}
