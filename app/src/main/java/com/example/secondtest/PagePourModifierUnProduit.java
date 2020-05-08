package com.example.secondtest;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

//Juste copier-coller A modifier
public class PagePourModifierUnProduit extends AppCompatActivity {

    private EditText productName;
    private EditText productCategory;
    private EditText productPrice;
    private EditText productWebsite;
    private EditText productInfo;
    private ProductDAO productDAO;
    private Cursor infoProduct;
    private String wishListNb;
    private String login;
    private String productNb;

    //Creation de la page pour modifier un produit
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_pour_modifier_un_produit);

        this.productName = findViewById(R.id.setNameEdit);
        //this.productCategory =  findViewById(R.id.setCategorySpinner);
        this.productPrice = findViewById(R.id.setPriceEdit);
        this.productWebsite = findViewById(R.id.setWebsiteEdit);
        this.productInfo = findViewById(R.id.setInfoEdit);

        this.productNb = getIntent().getStringExtra("ProductNb");
        this.wishListNb = getIntent().getStringExtra("WishlistNb");
        this.login = getIntent().getStringExtra("Login");
        this.productDAO = new ProductDAO(this);
        this.infoProduct = productDAO.getAllColumn(productNb);
        infoProduct.moveToFirst();
        this.productName.setText(infoProduct.getString(1));
        this.productPrice.setText(infoProduct.getString(3));
        this.productInfo.setText(infoProduct.getString(4));
        this.productCategory.setText(infoProduct.getString(5));
        this.productWebsite.setText(infoProduct.getString(6));

        Button buttonSave = findViewById(R.id.saveProduct);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productDAO.updateProduct(productNb, productName.getText().toString(),
                        Integer.parseInt(productPrice.getText().toString()),productInfo.getText().toString(),
                        productCategory.getText().toString(),productWebsite.getText().toString());
                Intent intent = new Intent(PagePourModifierUnProduit.this, PageDetailProduits.class);
                intent.putExtra("ProductNb", productNb);
                intent.putExtra("WishlistNb", wishListNb);
                intent.putExtra("Login", login);
                startActivity(intent);
            }
        });
    }

    private void showToast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }
}