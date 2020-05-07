package com.example.secondtest;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PageDetailProduits extends AppCompatActivity {

    TextView titleProduct;
    TextView productName;
    TextView productCategory;
    TextView productPrice;
    TextView productWebsite;
    TextView productInfo;
    private Button likeButton;
    private Button dislikeButton;

    ProductDAO productDAO;
    Cursor aboutProduct;
    String productNb;
    private String login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_detail_produits);

        this.titleProduct = (TextView) findViewById(R.id.titleProduct);
        this.productName = (TextView) findViewById(R.id.setName);
        this.productCategory = (TextView) findViewById(R.id.setCategory);
        this.productPrice = (TextView) findViewById(R.id.setPrice);
        this.productWebsite = (TextView) findViewById(R.id.setWebsite);
        this.productInfo = (TextView) findViewById(R.id.setInfo);
        this.likeButton = (Button) findViewById(R.id.likeButton);
        this.dislikeButton = (Button) findViewById(R.id.dislikeButton);

        this.productDAO = new ProductDAO(this);
        this.productNb = productDAO.getProductNumber(getIntent().getStringExtra("PRODUCTNAME1"));
        this.aboutProduct = productDAO.getAllColumn(productNb);
        this.login = getIntent().getStringExtra("LOGIN_DETAILS_PRODUIT");

        this.aboutProduct.moveToFirst();

        this.titleProduct.setText(aboutProduct.getString(1));
        this.productName.setText(aboutProduct.getString(1));
        this.productPrice.setText(aboutProduct.getString(3));
        this.productInfo.setText(aboutProduct.getString(4));
        this.productCategory.setText(aboutProduct.getString(5));
        this.productWebsite.setText(aboutProduct.getString(6));

        configurelikeButton();
    }

    private void configurelikeButton() {

    }

    private void showToast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }
}
