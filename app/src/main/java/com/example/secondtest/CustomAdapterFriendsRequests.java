package com.example.secondtest;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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

public class CustomAdapterFriendsRequests extends ArrayAdapter<User> {
    //the list values in the List of type hero
    ArrayList<User> friendsRequests;

    //activity context
    Context context;

    //the layout resource file for the list items
    int resource;

    FriendsDAO friendsDAO;
    String login;

    //constructor initializing the values
    public CustomAdapterFriendsRequests(Context context, int resource, ArrayList<User> friendsRequests, String login) {
        super(context, resource, friendsRequests);
        this.context = context;
        this.resource = resource;
        this.friendsRequests = friendsRequests;
        this.login = login;
        this.friendsDAO = new FriendsDAO(context);
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        //récupérer la "View"
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(resource, null, false);

        //ImageView imageView = view.findViewById(R.id.imageView);
        TextView friendRequestName = view.findViewById(R.id.friendRequestName);
        Button refuseButton = view.findViewById(R.id.refuseButton);
        Button acceptButton = view.findViewById(R.id.acceptButton);

        final User friendsRequest = friendsRequests.get(position);

        //adding values to the list item
        //imageView.setImageDrawable(context.getResources().getDrawable(hero.getImage()));
        friendRequestName.setText(friendsRequest.getLogin());

        refuseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                refuseFriend(friendsRequest);
            }
        });

        acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                acceptFriend(friendsRequest);
            }
        });

        return view;
    }

    //adaptation de la searchView
    @NonNull
    @Override
    public Filter getFilter() {
        return friendsRequestsFilter;
    }

    private Filter friendsRequestsFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            List<User> suggestions = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                suggestions.addAll(friendsRequests);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (User friendsRequest : friendsRequests) {
                    if (friendsRequest.getLogin().toLowerCase().contains(filterPattern)) {
                        suggestions.add(friendsRequest);
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
            return ((User) resultValue).getLogin();
        }
    };

    //permet de supprimer une wishlist
    private void refuseFriend(final User friendsRequest) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Are you sure you want to refuse this person?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                friendsDAO.refuseFriend(login,friendsRequest.getLogin());
                friendsRequests.remove(friendsRequest);

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

    private void acceptFriend(final User friendsRequest) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Are you sure you want to accept this person?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                friendsDAO.acceptFriend(login, friendsRequest.getLogin());
                friendsRequests.remove(friendsRequest);

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
