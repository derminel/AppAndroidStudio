package com.example.secondtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class PageProduits extends AppCompatActivity {

    private ArrayList<Product> products;
    private CustomAdapterProducts adapter;
    private ListView listView;
    private TextView titleWishList;

    private WishListDAO wishListDAO;
    private String nameWishList;
    private String nameWishListReload;
    private String wishListNb ;
    private String wishListNbReload ;
    private String login;
    private String loginReload;

    //Creation de la page de tous les produits deja crees par les utilisateurs de l application
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.wishListDAO = new WishListDAO(this);
        this.login = getIntent().getStringExtra("Login");
        this.wishListNb = getIntent().getStringExtra("WishlistNb");
        this.nameWishList = wishListDAO.getName(wishListNb);

        setContentView(R.layout.activity_page_produits);
        this.listView = findViewById(R.id.ListViewProducts);
        this.titleWishList = findViewById(R.id.titleWishlist);
        this.titleWishList.setText(nameWishList);

        initList();
        configureAddButton();
        configureBack();
        configureSearchView();
        configureSettingButton();
    }

    private void showToast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    private void start(Class<?> cls){
        Intent page = new Intent(PageProduits.this, cls);
        page.putExtra("Login", login);
        page.putExtra("WishlistNb", wishListNb);
        page.putExtra("WishlistName", nameWishList);
        page.putExtra("Login", login);
        startActivity(page);
    }

    private void initList(){
        WishList wishList = new WishList(this.wishListDAO.getName(this.wishListNb),
                this.wishListDAO.getAccess(this.wishListNb),this);
        if(wishListNbReload == null){
            this.products = wishList.getProducts(wishListNb, this);
            adapter=new CustomAdapterProducts(this,
                    R.layout.row_products, products,wishListNb, login);
        }
        else {
            this.products = wishList.getProducts(wishListNbReload, this);
            adapter = new CustomAdapterProducts(this,
                    R.layout.row_products, products, wishListNbReload, loginReload);
        }
        listView.setAdapter(adapter);
    }
    private void configureBack(){
        Button back =  findViewById(R.id.GoBackWishlist);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { start(PagesListesDeSouhaits.class); }
        });
    }
    private void configureSearchView(){
        SearchView searchView = findViewById(R.id.SearchbarProducts);
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
    private void configureAddButton(){
        Button addButton = findViewById(R.id.addProductButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { start(PagePourAjouterUnProduit.class); }
        });
    }
    private void configureSettingButton() {
        Button settingButton = findViewById(R.id.editWishlist);
        settingButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){ start(PageModifierWishList.class);}
        });
    }
}
