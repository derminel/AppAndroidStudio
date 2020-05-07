package com.example.secondtest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class PageAjouterAdmin extends AppCompatActivity {
    EditText admin;
    ModifierDAO modifierDAO;
    String wishlistnb;
    Context context;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_pour_ajouter_admins);
        wishlistnb = getIntent().getStringExtra("LISTNB_AJOUT_ADMIN");
        modifierDAO = new ModifierDAO(this);
        context = this;

        admin = findViewById(R.id.nameNewAdmin);
        configureConfirmAdminButton();
    }

    private void configureConfirmAdminButton() {
        Button nextButton = (Button) findViewById(R.id.ConfirmAddAdmin);
        nextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                modifierDAO.updateAdmin(wishlistnb, admin.getText().toString());
                Intent intent = new Intent(PageAjouterAdmin.this, PagePourModifierAdmins.class);
                intent.putExtra("LISTNB_ADMIN", wishlistnb);
                startActivity(intent);
            }
        });

    }
}
