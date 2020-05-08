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
    private boolean publicAccess;
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
        setContentView(R.layout.activity_page_pour_ajouter_un_produit);

        this.publicAccess = false ;
        this.name = findViewById(R.id.setNameCreate);
        this.category = "Other";
        this.price = findViewById(R.id.setPriceCreate);
        this.website = findViewById(R.id.setWebsiteCreate);
        this.info = findViewById(R.id.setInfoCreate);
        this.wishListNb = getIntent().getStringExtra("WISHLISTNUMBER2");
        this.wishListName = getIntent().getStringExtra("WISHLISTNAME2");
        this.login = getIntent().getStringExtra("LOGIN_AJOUT_PRODUIT");
        this.contentDAO = new ContentDAO(this);
        this.productDAO = new ProductDAO(this);

        Button buttonConfirm = findViewById(R.id.buttonCreateProduct);

        Spinner spin = findViewById(R.id.setCategorySpinner);
        spin.setOnItemSelectedListener(this);
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,categories);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);

        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Product product = configureProduct();
                String productNb = product.getProductNb();
                if (productNb == null){
                    productNb = "Product" + Integer.parseInt(productDAO.lineCounter())+1;
                    productDAO.addProduct(login, wishListNb, productNb,name.getText().toString(),Integer.parseInt(price.getText().toString()),
                            info.getText().toString(),category,website.getText().toString());
                    contentDAO.addProduct(wishListNb, productNb);
                    Intent intent = new Intent(PagePourAjouterUnProduit.this, PageProduits.class);
                    intent.putExtra("WISHLISTNAME3", wishListName);
                    intent.putExtra("WISHLISTNUMBER3", wishListNb);
                    intent.putExtra("LOGIN_PRODUIT_RELOAD", login);
                    startActivity(intent);
                }
                else if (contentDAO.alreadyInList(wishListNb,product.getProductNb())){
                    showToast("This product is already in the list");
                }
                else {
                    contentDAO.addProduct(wishListNb, productNb);
                    Intent intent = new Intent(PagePourAjouterUnProduit.this, PageProduits.class);
                    intent.putExtra("WISHLISTNAME3", wishListName);
                    intent.putExtra("WISHLISTNUMBER3", wishListNb);
                    intent.putExtra("LOGIN_PRODUIT_RELOAD", login);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        this.category=this.categories[position];
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        Toast.makeText(getApplicationContext(),"you didn't choose a category" , Toast.LENGTH_LONG).show();
    }

    private Product configureProduct(){
        int price = -1;
        if (!(this.price.getText().toString().equals(""))){
            price = Integer.parseInt(this.price.getText().toString());
        }
        return new Product(this.name.getText().toString(), price,this.info.getText().toString(),
                this.category,this.website.getText().toString(),this);
    }

    private void showToast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }
}