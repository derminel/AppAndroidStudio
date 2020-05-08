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

public class PageModifierWishListUpdate extends AppCompatActivity {
    private WishListDAO wishListDAO;//
    private String wishlistNb;//
    private EditText name;//
    private EditText recipient;//
    private Button goBack;
    private boolean publicAccess;//
    UserDAO userDAO;

    //Creation de la page pour determiner le nom, les recipients et si la liste est publique ou non
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_modifier_wishlist_update);
        this.wishListDAO = new WishListDAO(this);
        this.wishlistNb = getIntent().getStringExtra("WishlistNb");
        this.userDAO = new UserDAO(this);

        this.name = findViewById(R.id.list_nameDB);
        this.recipient = findViewById(R.id.recipient);
        this.publicAccess = wishListDAO.getAccess(wishlistNb) ;
        this.goBack = findViewById(R.id.GoBackWishlistUpdate);

        Switch switchButton = findViewById(R.id.switchpublic);

        name.setText(wishListDAO.getName(wishlistNb));
        recipient.setText(wishListDAO.getRecipient(wishlistNb));
        switchButton.setChecked(publicAccess);


        switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    publicAccess = true;
                }
                else{
                    publicAccess = false;
                }
            }
        });


        configureButtonSaveWishlist();
        configureGoBack();

    }
    private void showToast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    private void configureButtonSaveWishlist() {
        Button nextButton = (Button)  findViewById(R.id.buttonSaveList);
        nextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                if (!recipient.getText().toString().equals("") && !userDAO.exist(recipient.getText().toString())) {
                    showToast("The login of the recipient does not exist");
                } else {
                    wishListDAO.updateWishlist(wishlistNb, name.getText().toString(), recipient.getText().toString(), publicAccess);
                    Intent intent = new Intent(PageModifierWishListUpdate.this, PageModifierWishList.class);
                    intent.putExtra("WishlistNb", wishlistNb);
                    startActivity(intent);
                }
            }
        });
    }

    private void configureGoBack() {
        goBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                Intent intent = new Intent(PageModifierWishListUpdate.this, PageModifierWishList.class);
                intent.putExtra("WishlistNb", wishlistNb);
                startActivity(intent);
            }
        });
    }
}
