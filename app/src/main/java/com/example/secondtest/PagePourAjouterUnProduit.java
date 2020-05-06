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

    private WishListDAO wishListDAO;
    private ProductDAO productDAO;
    private ContentDAO contentDAO;
    private boolean publicAccess;
    private EditText inputText;
    private String login;
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

        Button buttonConfirm = findViewById(R.id.ConfirmAddProduct);

        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Product product = configureProduct();
                //if (contentDAO.alreadyInList()){
                    showToast("You can't add yourself as friend");
                //}
                /*else if (!(userDAO.getUserLoginDb().contains(inputText.getText().toString()))){
                    showToast("This login doesn't exist");
                }
                else if (friendsDAO.areFriends(user.getLogin(), inputText.getText().toString())){
                    showToast("You're already friends");
                }
                else {
                    boolean isGood = user.friendRequest(inputText.getText().toString());
                    if (isGood){
                        showToast("Invitation has been sent");
                    }
                    else{
                        showToast("Problem with your invitation, try again");
                    }
                    startActivity(new Intent(PagePourAjouterUnProduit.this, PageProduits.class));
                }*/
            }
        });
    }

    private Product configureProduct(){
        Double price = null;
        if (!(this.price.getText().toString().equals(""))){
            price = Double.parseDouble(this.price.getText().toString());
        }
        return new Product(this.name.getText().toString(), price,this.info.getText().toString(),
                this.category.getText().toString(),this.website.getText().toString(),this);
    }

    private void showToast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }
}