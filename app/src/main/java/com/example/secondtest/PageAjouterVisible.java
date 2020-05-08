package com.example.secondtest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class PageAjouterVisible extends AppCompatActivity {
    EditText visible;
    ModifierDAO modifierDAO;
    String wishlistnb;
    Context context;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_pour_ajouter_visibles);
        wishlistnb = getIntent().getStringExtra("WishlistNb");
        modifierDAO = new ModifierDAO(this);
        context = this;

        visible = findViewById(R.id.nameNewVisible);
        configureConfirmAdminButton();
    }

    private void configureConfirmAdminButton() {
        Button nextButton = (Button) findViewById(R.id.ConfirmAddVisible);
        nextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                modifierDAO.updateVisible(wishlistnb, visible.getText().toString());
                Intent intent = new Intent(PageAjouterVisible.this, PagePourModiferVisibles.class);
                intent.putExtra("WishlistNb", wishlistnb);
                startActivity(intent);
            }
        });

    }
}
