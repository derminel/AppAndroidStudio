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

    ArrayList<Product> products;
    CustomAdapterProducts adapter;
    ListView listView;
    SearchView searchView;
    Button addButton;
    Button settingButton;
    Button likeButton;
    Button dislikeButton;
    TextView titleWishList;

    boolean canInit = true;
    ContentDAO contentDAO ;
    WishListDAO wishListDAO;
    String wishListNb ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_produits);

        this.listView=(ListView)findViewById(R.id.ListViewProducts);
        this.searchView=(SearchView) findViewById(R.id.SearchbarProducts);
        this.addButton=(Button) findViewById(R.id.addProduct);
        this.settingButton=(Button) findViewById(R.id.settings);
        this.likeButton=(Button) findViewById(R.id.likeButton);
        this.dislikeButton=(Button) findViewById(R.id.dislikeButton);
        this.titleWishList =(TextView) findViewById(R.id.titleWishlist);

        this.contentDAO = new ContentDAO(this);
        this.wishListDAO = new WishListDAO(this);
        this.wishListNb = getIntent().getStringExtra("WISHLISTNUMBER1");
        String nameWishList = getIntent().getStringExtra("WISHLISTNAME1");
        this.titleWishList.setText(nameWishList);


        showToast(this.wishListNb);

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
    }

    private void showToast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    private void initList(){
        WishList wishList = new WishList(this.wishListDAO.getName(this.wishListNb), this.wishListDAO.getAccess(this.wishListNb),this);
        this.products = wishList.getProducts();
        adapter=new CustomAdapterProducts(this,
                R.layout.row_products, products,wishListNb);
        listView.setAdapter(adapter);
    }

    private void configureAddButton(){
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PageProduits.this, PagePourAjouterUnProduit.class) ;
                startActivityForResult(intent, 1);
            }
        });
    }
}
