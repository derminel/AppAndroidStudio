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
    private SearchView searchView;
    private Button addButton;
    private Button settingButton;
    private TextView titleWishList;

    private boolean canInit = true;
    private WishListDAO wishListDAO;
    private String nameWishList;
    private String nameWishListReload;
    private String wishListNb ;
    private String wishListNbReload ;
    private String login;
    private String loginReload;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_produits);

        this.listView = findViewById(R.id.ListViewProducts);
        this.searchView = findViewById(R.id.SearchbarProducts);
        this.addButton = findViewById(R.id.addProductButton);
        this.settingButton = findViewById(R.id.editWishlist);
        this.titleWishList = findViewById(R.id.titleWishlist);

        this.login = getIntent().getStringExtra("Login");
        this.wishListDAO = new WishListDAO(this);
        this.wishListNb = getIntent().getStringExtra("WishlistNb");
        this.nameWishList = getIntent().getStringExtra("WishlistName");
        this.titleWishList.setText(nameWishList);

        if(canInit){
            initList();
            canInit = false;
        }

        showToast(wishListNb);

        //Filter adapté pour les listes de Product
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
        configureSettingButton();
    }

    private void showToast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    private void initList(){
        WishList wishList = new WishList(this.wishListDAO.getName(this.wishListNb), this.wishListDAO.getAccess(this.wishListNb),this);
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

    private void configureAddButton(){
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PageProduits.this, PagePourAjouterUnProduit.class) ;
                intent.putExtra("WishlistNb", wishListNb);
                intent.putExtra("WishlistName", nameWishList);
                intent.putExtra("Login", login);
                startActivityForResult(intent, 1);
            }
        });
    }
    private void configureSettingButton() {
        settingButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                Intent intent = new Intent(PageProduits.this, PageModifierWishList.class);
                intent.putExtra("WishlistNb", wishListNb);
                startActivity(intent);
            }
        });
    }
}
