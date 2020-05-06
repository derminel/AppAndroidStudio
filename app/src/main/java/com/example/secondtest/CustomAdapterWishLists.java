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
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapterWishLists extends ArrayAdapter<WishList> {
    ArrayList<WishList> wishLists;

    //activity context
    Context context;

    //the layout resource file for the list items
    int resource;

    WishListDAO wishListDAO;

    //constructor initializing the values
    public CustomAdapterWishLists(Context context, int resource, ArrayList<WishList> wishLists) {
        super(context, resource, wishLists);
        this.context = context;
        this.resource = resource;
        this.wishLists = wishLists;
        this.wishListDAO = new WishListDAO(context);
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        //récupérer la "View"
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(resource, null, false);

        //ImageView imageView = view.findViewById(R.id.imageView);
        TextView wishListName = view.findViewById(R.id.wishListName);
        Button buttonDelete = view.findViewById(R.id.deletewishlistbutton);

        final WishList wishList = wishLists.get(position);

        //adding values to the list item
        //imageView.setImageDrawable(context.getResources().getDrawable(hero.getImage()));
        wishListName.setText(wishList.getName());

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeWishList(wishList);
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final WishList wishList = wishLists.get(position);
                Intent intent = new Intent(context, PageProduits.class);
                intent.putExtra("WISHLISTNAME1", wishList.getName());
                intent.putExtra("WISHLISTNUMBER1", wishList.getListNb()); //Recoivent tous le même Numero....
                context.startActivity(intent);
            }
        });

        return view;
    }

    //adaptation de la searchView
    @NonNull
    @Override
    public Filter getFilter() {
        return wishListsFilter;
    }

    private Filter wishListsFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            List<WishList> suggestions = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                suggestions.addAll(wishLists);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (WishList item : wishLists) {
                    if (item.getName().toLowerCase().contains(filterPattern)) {
                        suggestions.add(item);
                    }
                }
            }

            results.values = suggestions;
            results.count = suggestions.size();

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            clear();
            addAll((List) results.values);
            notifyDataSetChanged();
        }

        @Override
        public CharSequence convertResultToString(Object resultValue) {
            return ((WishList) resultValue).getName();
        }
    };

    //permet de supprimer une wishlist
    private void removeWishList(final WishList wishList) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Are you sure you want to delete this?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                wishListDAO.deleteList(wishList.getName());
                wishLists.remove(wishList);

                notifyDataSetChanged();
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
