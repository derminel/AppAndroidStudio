package com.example.secondtest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.LauncherActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;


public class PagesListesDeSouhaits extends AppCompatActivity {

    String[] items;
    ArrayList<String> listItems;
    ArrayAdapter<String> adapter;
    ListView listView;
    SearchView searchView;
    Button addButton;
    boolean canInit = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pages_listes_de_souhaits);

        listView=(ListView)findViewById(R.id.ListViewWishLists);
        searchView=(SearchView) findViewById(R.id.SearchbarWishlists);
        addButton=(Button) findViewById(R.id.addwishlistbutton);

        if(canInit){
            initList();
            canInit = false;
        }

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PagesListesDeSouhaits.this, PagePourAjouterUneListeDeSouhait.class) ;
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            listItems.add(data.getStringExtra("inputtext"));
            adapter=new ArrayAdapter<String>(this,
                    R.layout.row_wishlists, R.id.textView1, listItems);
            listView.setAdapter(adapter);
        }
    }

    public void initList(){
        items=new String[]{"Canada","China","Japan","USA"};
        listItems=new ArrayList<>(Arrays.asList(items));
        adapter=new ArrayAdapter<String>(this,
                R.layout.row_wishlists, R.id.textView1, listItems);
        listView.setAdapter(adapter);
    }
}
