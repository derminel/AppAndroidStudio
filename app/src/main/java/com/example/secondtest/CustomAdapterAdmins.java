package com.example.secondtest;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CustomAdapterAdmins extends ArrayAdapter<String> {
    ModifierDAO modifierDAO;
    ArrayList<String> admins;
    String listnb;
    Context context;
    int resource;

    public CustomAdapterAdmins(@NonNull Context context, int resource, ArrayList<String> admins, String listnb) {
        super(context, resource, admins);
        this.context = context;
        this.listnb = listnb;
        this.admins= admins;
        this.resource = resource;
        this.modifierDAO = new ModifierDAO(context);


    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(resource, null, false);

        TextView wishListName = view.findViewById(R.id.wishListName);
        Button buttonDelete = view.findViewById(R.id.deletewishlistbutton);
        final String login = admins.get(position);

        wishListName.setText(login);

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeAdmin(login, listnb, context);
            }
        });

        return view;

    }
    private void showToast(String msg) {
        Toast.makeText(context.getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    private void removeAdmin(final String login, final String listnb, final Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Are you sure you want to delete this?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                int err = modifierDAO.deleteAdmin(listnb, login, context);
                if (err == -3) {
                    showToast("The creator of the list must remain an admin");
                    return;
                }
                notifyDataSetChanged();
                Intent intent = new Intent(context, PagePourModifierAdmins.class);
                intent.putExtra("WishlistNb", listnb);
                context.startActivity(intent);
            }
        });

        //if response is negative nothing is being done
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        //creating and displaying the alert dialog
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
