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
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapterProducts extends ArrayAdapter<Product> {
    private ArrayList<Product> products;//

    //activity context
    private Context context;//

    //the layout resource file for the list items
    private int resource;//

    private WishListDAO wishListDAO;//
    private ContentDAO contentDAO;//
    private String listNb;//
    private String login;

    //constructor initializing the values
    public CustomAdapterProducts(Context context, int resource, ArrayList<Product> products, String listNb, String login) {
        super(context, resource, products);
        this.context = context;
        this.resource = resource;
        this.products = products;
        this.listNb = listNb;
        this.login = login;
        this.contentDAO = new ContentDAO(context);
        this.wishListDAO = new WishListDAO(context);
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        //récupérer la "View"
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(resource, null, false);

        //ImageView imageView = view.findViewById(R.id.imageView);
        TextView produtName = view.findViewById(R.id.productName);
        Button deleteButton = view.findViewById(R.id.deleteProduct);

        final Product product = products.get(position);
        final ProductDAO productDAO = new ProductDAO(context);

        //adding values to the list item
        //imageView.setImageDrawable(context.getResources().getDrawable(hero.getImage()));
        produtName.setText(product.getName());

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeProduct(product);
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PageDetailProduits.class);
                intent.putExtra("ProductNb", productDAO.getProductNumber(product.getName()));
                intent.putExtra("Login", login);
                intent.putExtra("WishlistNb", listNb);
                context.startActivity(intent);
            }
        });
        return view;
    }

    //adaptation de la searchView
    @NonNull
    @Override
    public Filter getFilter() {
        return productsFilter;
    }

    private Filter productsFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            List<Product> suggestions = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                suggestions.addAll(products);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Product item : products) {
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
            return ((Product) resultValue).getName();
        }
    };

    //permet de supprimer une wishlist
    private void removeProduct(final Product product) {
        AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context,R.style.AlertDialog);
        builder.setTitle("Are you sure you want to delete this?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                contentDAO.removeProduct(listNb, product.getProductNb());
                products.remove(product);

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
