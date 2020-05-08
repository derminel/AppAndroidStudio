package com.example.secondtest;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

//Juste copier-coller A modifier
public class PagePourModifierUnProduit extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private EditText productName;
    private EditText productPrice;
    private EditText productWebsite;
    private EditText productInfo;
    private ProductDAO productDAO;
    private String wishListNb;
    private String login;
    private String productNb;
    private String category;
    private Cursor infoProduct;
    private String[] categories= new String[]{"Other", "Sport", "Clothes", "Games", "Technology", "Jewellery", "Decoration"};

    //Creation de la page pour modifier un produit
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.productNb = getIntent().getStringExtra("ProductNb");
        this.wishListNb = getIntent().getStringExtra("WishlistNb");
        this.login = getIntent().getStringExtra("Login");
        this.productDAO = new ProductDAO(this);
        infoProduct = productDAO.getAllColumn(productNb);
        infoProduct.moveToFirst();

        setContentView(R.layout.activity_page_pour_modifier_un_produit);
        this.productName = findViewById(R.id.setNameEdit);
        this.productPrice = findViewById(R.id.setPriceEdit);
        this.productWebsite = findViewById(R.id.setWebsiteEdit);
        this.productInfo = findViewById(R.id.setInfoEdit);
        this.productName.setText(infoProduct.getString(1));
        this.productPrice.setText(infoProduct.getString(3));
        this.productInfo.setText(infoProduct.getString(4));
        this.productWebsite.setText(infoProduct.getString(6));

        configureSave();
        configureSpinner();
    }
    private void configureSave(){
        Button buttonSave = findViewById(R.id.saveProduct);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productDAO.updateProduct(productNb, productName.getText().toString(),
                        Integer.parseInt(productPrice.getText().toString()),productInfo.getText().toString(),
                        category,productWebsite.getText().toString());
                start(PageDetailProduits.class);
            }
        });
    }
    private void start(Class<?> cls){
        Intent page = new Intent(PagePourModifierUnProduit.this, cls);
        page.putExtra("Login", login);
        page.putExtra("ProductNb", productNb);
        page.putExtra("WishlistNb", wishListNb);
        startActivity(page);
    }
    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        this.category=this.categories[position];
    }
    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        showToast("you didn't choose a category" );
    }
    private int indexOf(String[] a, String target) {
        for (int i = 0; i < a.length; i++)
            if (a[i].equals(target)) { return i; }
        return -1;
    }
    private void configureSpinner(){
        Spinner spin = findViewById(R.id.setCategorySpinner);
        spin.setOnItemSelectedListener(this);
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,categories);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);
        spin.setSelection(indexOf(categories,infoProduct.getString(5)));
    }
    private void showToast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }
}