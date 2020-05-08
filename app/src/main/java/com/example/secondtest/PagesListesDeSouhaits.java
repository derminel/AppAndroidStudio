package com.example.secondtest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.LauncherActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;


public class PagesListesDeSouhaits extends AppCompatActivity {

    private ArrayList<WishList> wishLists; //
    private ArrayList<String> wishlistsNb;
    private CustomAdapterWishLists adapter;//
    private ListView listView;//
    private SearchView searchView;//
    private Button addButton;//

    private boolean canInit = true;//
    private WishListDAO wishListDAO;//
    private String login;//
    private String loginReload;
    private Button Back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pages_listes_de_souhaits);

        listView=(ListView)findViewById(R.id.ListViewWishLists);
        searchView=(SearchView) findViewById(R.id.SearchbarWishlists);
        addButton=(Button) findViewById(R.id.addwishlistbutton);

        this.wishListDAO = new WishListDAO(this);
        this.login = getIntent().getStringExtra("Login");
        Back = (Button)findViewById(R.id.GoBackWishlists);
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityretour1();
            }
        });

        if(canInit){
            initList();
            canInit = false;
        }

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
    public void activityretour1(){
        Intent intent = new Intent(this, PageAccueil.class);
        intent.putExtra("Login", login);
        startActivity(intent);
    }

    private void showToast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    private void initList(){
        this.wishLists = new ArrayList<WishList>();
        this.wishlistsNb = new ArrayList<String>();
        for (String listNb : this.wishListDAO.getWishLists(login)){
            WishList newList = new WishList(wishListDAO.getName(listNb), true, this); //mis a true juste pour la création
            this.wishLists.add(newList);
            this.wishlistsNb.add(listNb);
        }
        adapter=new CustomAdapterWishLists(this,
                R.layout.row_wishlists, wishLists, wishlistsNb, login);
        listView.setAdapter(adapter);
    }

    private void configureAddButton(){
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PagesListesDeSouhaits.this, PagePourAjouterUneListeDeSouhait.class);
                intent.putExtra("Login", login);
                startActivityForResult(intent, 1);
            }
        });
    }
}
