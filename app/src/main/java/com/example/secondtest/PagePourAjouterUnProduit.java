package com.example.secondtest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
//Juste copier-coller A modifier
public class PagePourAjouterUnProduit extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private ProductDAO productDAO;
    private ContentDAO contentDAO;
    private String wishListNb;
    private String wishListName;
    private String login;
    private EditText name;
    private String category;
    private EditText price;
    private EditText website;
    private EditText info;
    private String[] categories= new String[]{"Other", "Sport", "Clothes", "Games", "Technology", "Jewellery", "Decoration"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.wishListNb = getIntent().getStringExtra("WishlistNb");
        this.wishListName = getIntent().getStringExtra("WishlistName");
        this.login = getIntent().getStringExtra("Login");
        this.contentDAO = new ContentDAO(this);
        this.productDAO = new ProductDAO(this);

        setContentView(R.layout.activity_page_pour_ajouter_un_produit);
        this.category = "Other";
        this.name = findViewById(R.id.setNameCreate);
        this.price = findViewById(R.id.setPriceCreate);
        this.website = findViewById(R.id.setWebsiteCreate);
        this.info = findViewById(R.id.setInfoCreate);

        configureSpinner();
        configureCreate();
    }

    private int parseprice(){
        int prix=-1;
        if(!this.price.getText().toString().equals("")) { prix = Integer.parseInt(this.price.getText().toString()); }
        return prix;
    }
    private void start(Class<?> cls){
        Intent page = new Intent(PagePourAjouterUnProduit.this, cls);
        page.putExtra("Login", login);
        page.putExtra("WishlistName", wishListName);
        page.putExtra("WishlistNb", wishListNb);
        startActivity(page);
    }
    private void showToast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        this.category=this.categories[position];
    }
    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        showToast("you didn't choose a category" );
    }

    private void configureSpinner(){
        Spinner spin = findViewById(R.id.setCategorySpinner);
        spin.setOnItemSelectedListener(this);
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,categories);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);
    }
    private void configureCreate(){
        Button buttonConfirm = findViewById(R.id.buttonCreateProduct);
        final String productNb = "Product" + Integer.parseInt(productDAO.lineCounter())+1;
        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int prix= parseprice();
                    if(name.getText().toString().equals("")){
                        showToast("You have to enter a product name");
                    }else {
                        productDAO.addProduct(login, wishListNb, productNb, name.getText().toString(),
                                prix, info.getText().toString(), category, website.getText().toString());
                        contentDAO.addProduct(wishListNb, productNb);
                        start(PageProduits.class);
                    }
                }catch(Exception e) { showToast("Your price is not an integer");}
            }
        });
    }
}