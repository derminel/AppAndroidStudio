package com.example.secondtest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PageAjouterAdmin extends AppCompatActivity {
    EditText admin;
    ModifierDAO modifierDAO;
    String wishlistnb;
    Context context;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_pour_ajouter_admins);
        wishlistnb = getIntent().getStringExtra("WishlistNb");
        modifierDAO = new ModifierDAO(this);
        context = this;

        admin = findViewById(R.id.nameNewAdmin);
        configureConfirmAdminButton();
    }

    private void showToast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }
    private void configureConfirmAdminButton() {
        Button nextButton = (Button) findViewById(R.id.ConfirmAddAdmin);
        nextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                int err = modifierDAO.updateAdmin(wishlistnb, admin.getText().toString(), context);
                if (err == -1) {
                    showToast("This login is already an admin");
                }
                if (err == -2) {
                    showToast("This login does not exist");
                }
                Intent intent = new Intent(PageAjouterAdmin.this, PagePourModifierAdmins.class);
                intent.putExtra("WishlistNb", wishlistnb);
                startActivity(intent);
            }
        });

    }
}
