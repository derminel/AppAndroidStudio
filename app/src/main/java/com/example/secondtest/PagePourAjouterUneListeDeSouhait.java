package com.example.secondtest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.LauncherActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class PagePourAjouterUneListeDeSouhait extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_pour_ajouter_une_liste_de_souhait);

        EditText editText = findViewById(R.id.nameNewList);
        final String inputText = editText.getText().toString();

        Button buttonConfirm = findViewById(R.id.ConfirmAddWishlist);

        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("inputtext", inputText);

                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }
}
