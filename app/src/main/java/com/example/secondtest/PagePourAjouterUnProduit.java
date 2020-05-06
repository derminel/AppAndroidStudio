package com.example.secondtest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

//Juste copier-coller A modifier
public class PagePourAjouterUnProduit extends AppCompatActivity {

    private ProductDAO productDAO;
    private ContentDAO contentDAO;
    private boolean publicAccess;
    private String wishListNb;
    private String wishListName;
    private EditText name;
    private EditText category;
    private EditText price;
    private EditText website;
    private EditText info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_pour_ajouter_un_produit);

        this.publicAccess = false ;
        this.name = findViewById(R.id.setNameCreate);
        this.category = findViewById(R.id.setCategoryCreate);
        this.price = findViewById(R.id.setPriceCreate);
        this.website = findViewById(R.id.setWebsiteCreate);
        this.info = findViewById(R.id.setInfoCreate);
        this.wishListNb = getIntent().getStringExtra("WISHLISTNUMBER2");
        this.wishListName = getIntent().getStringExtra("WISHLISTNAME2");
        this.contentDAO = new ContentDAO(this);
        this.productDAO = new ProductDAO(this);

        Button buttonConfirm = findViewById(R.id.ConfirmAddProduct);

        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Product product = configureProduct();
                String productNb = product.getProductNb();
                if (productNb == null){
                    productNb = "Product" + String.valueOf(Integer.parseInt(productDAO.lineCounter())+1);
                    productDAO.addProduct(productNb,name.getText().toString(),Integer.parseInt(price.getText().toString()),
                            info.getText().toString(),category.getText().toString(),website.getText().toString());
                    contentDAO.addProduct(wishListNb, productNb);
                    Intent intent = new Intent(PagePourAjouterUnProduit.this, PageProduits.class);
                    intent.putExtra("WISHLISTNAME3", wishListName);
                    intent.putExtra("WISHLISTNUMBER3", wishListNb);
                    startActivity(intent);
                }
                else if (contentDAO.alreadyInList(wishListNb,product.getProductNb())){
                    showToast("This product is already in the list");
                }
                else {
                    contentDAO.addProduct(wishListNb, productNb);
                    Intent intent = new Intent(PagePourAjouterUnProduit.this, PageProduits.class);
                    intent.putExtra("WISHLISTNUMBER3", wishListNb);
                    startActivity(intent);
                }
            }
        });
    }

    private Product configureProduct(){
        int price = -1;
        if (!(this.price.getText().toString().equals(""))){
            price = Integer.parseInt(this.price.getText().toString());
        }
        return new Product(this.name.getText().toString(), price,this.info.getText().toString(),
                this.category.getText().toString(),this.website.getText().toString(),this);
    }

    private void showToast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }
}