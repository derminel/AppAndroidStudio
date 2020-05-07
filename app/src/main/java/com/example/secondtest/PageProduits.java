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
    private ContentDAO contentDAO ;
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

        this.listView=(ListView)findViewById(R.id.ListViewProducts);
        this.searchView=(SearchView) findViewById(R.id.SearchbarProducts);
        this.addButton=(Button) findViewById(R.id.addProduct);
        this.settingButton=(Button) findViewById(R.id.settings);
        this.titleWishList =(TextView) findViewById(R.id.titleWishlist);

        this.login = getIntent().getStringExtra("LOGIN_PRODUITS");
        this.loginReload = getIntent().getStringExtra("LOGIN_PRODUITS_RELOAD");
        this.contentDAO = new ContentDAO(this);
        this.wishListDAO = new WishListDAO(this);
        this.wishListNb = getIntent().getStringExtra("WISHLISTNUMBER1");
        this.wishListNbReload = getIntent().getStringExtra("WISHLISTNUMBER3");
        this.nameWishList = getIntent().getStringExtra("WISHLISTNAME1");
        this.nameWishListReload = getIntent().getStringExtra("WISHLISTNAME3");
        if (nameWishListReload == null){
            this.titleWishList.setText(nameWishList);
        }
        else{
            this.titleWishList.setText(nameWishListReload);
        }

        showToast(wishListNb);

        if(canInit){
            initList();
            canInit = false;
        }

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
                    R.layout.row_products, products, wishListNb, loginReload);
        }
        listView.setAdapter(adapter);
    }

    private void configureAddButton(){
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PageProduits.this, PagePourAjouterUnProduit.class) ;
                if (wishListNbReload == null){
                    intent.putExtra("WISHLISTNUMBER2", wishListNb);
                    intent.putExtra("WISHLISTNAME2", nameWishList);
                    intent.putExtra("LOGIN_AJOUT_PRODUIT", login);
                }
                else {
                    intent.putExtra("WISHLISTNUMBER2", wishListNbReload);
                    intent.putExtra("WISHLISTNAME2", nameWishListReload);
                    intent.putExtra("LOGIN_AJOUT_PRODUIT", loginReload);
                }

                startActivityForResult(intent, 1);
            }
        });
    }
    private void configureSettingButton() {
        Button buttonSettings = (Button)  findViewById(R.id.settings);
        buttonSettings.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                Intent intent = new Intent(PageProduits.this, PageModifierWishList.class);
                intent.putExtra("LISTNB_MODIFIER", wishListNb);
                startActivity(intent);
            }
        });
    }
}
