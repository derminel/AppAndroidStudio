package com.example.secondtest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PageAjouterVisible extends AppCompatActivity {
    EditText visible;
    ModifierDAO modifierDAO;
    String wishlistnb;
    Context context;

    //Creation de la page pour ajouter des logins a la liste, en tant que personne pouvant voir la wishlist
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_pour_ajouter_visibles);
        wishlistnb = getIntent().getStringExtra("WishlistNb");
        modifierDAO = new ModifierDAO(this);
        context = this;

        visible = findViewById(R.id.nameNewVisible);
        configureConfirmAdminButton();
    }
    private void showToast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }


    private void configureConfirmAdminButton() {
        Button nextButton = (Button) findViewById(R.id.ConfirmAddVisible);
        nextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                int err = modifierDAO.updateVisible(wishlistnb, visible.getText().toString(), context);
                if (err == -2) {
                    showToast("This login does not exist");
                }
                if (err == -1) {
                    showToast("This login is already an admin and can edit your list");
                }
                if (err == -3) {
                    showToast("You can not ");
                }
                Intent intent = new Intent(PageAjouterVisible.this, PagePourModiferVisibles.class);
                intent.putExtra("WishlistNb", wishlistnb);
                startActivity(intent);
            }
        });

    }
}
