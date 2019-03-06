package com.chemwater.week4day2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chemwater.week4day2.model.users.Result;
import com.chemwater.week4day2.model.users.UserResponse;

import java.util.ArrayList;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {
    private static final String IMAGE_URL = "";



    //List of user responses that will be populated into the recycler view
    ArrayList<UserResponse> userResponses ;

    //Constructor for the Adapter
    public MyRecyclerViewAdapter(ArrayList<UserResponse> userResponsesArrayList) {
        this.userResponses = userResponsesArrayList ;
    }

    //Inflate the xml into active memeory (renders)
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        //return new instance of the viewholder( We need to inflate(render) the view to passing
        //                                           by telling the context of where this is going
        //                                           to be rendered, the layout to inflate, the viewgroup
        //                                            to  assign it to, and if we need a root level attachment)
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.user_item, viewGroup, false));
    }

    // Once we have the viewholder, we then populated the data when we bind to the created viewholder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
        //Get the item's information which we wish to populate for that viewholder
        UserResponse currentUsersBeingPopulated = userResponses.get(position);
        //use the passed viewholder to access the items view and populate
        viewHolder.tvUsernameDisplay.setText(currentUsersBeingPopulated.getResults().get(position).getName().toString()) ;
        viewHolder.tvEmailDisplay.setText(currentUsersBeingPopulated.getResults().get(position).getEmail());
        viewHolder.tvPhoneNumberDisplay.setText(currentUsersBeingPopulated.getResults().get(position).getPhone()) ;
        //viewHolder.rtBevRating.setRating(currentBeverageBeingPopulated.getRating());

        //Using glide to associate the web resourced image to our imageView
        Glide
                .with(viewHolder.itemView.getContext())
                .load("https://images.freeimages.com/images/large-previews/25d/eagle-1523807.jpg")
                .into(viewHolder.imgPictureDisplay) ;


        Log.d("TAG", "onBindViewHolder: item being rendered = " + position);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(v.getContext(), beveragesArrayList.get(position).getName() + "clicked", Toast.LENGTH_SHORT).show();
                Bundle bundle = new Bundle();
                bundle.putParcelable("user", userResponses.get(position));
                Intent intentToStartDetails = new Intent(v.getContext(), DetailActivity.class);
                intentToStartDetails.putExtras(bundle);
                v.getContext().startActivity(intentToStartDetails);
            }
        });
    }

    //Add to list, notify the adapter that the info in the list has changed
    public void addUserToList(UserResponse userResponse) {
        userResponses.add(userResponse);
        notifyDataSetChanged() ;
    }

    @Override
    public int getItemCount() {
        return userResponses.size();
    }


    //Inner class view container.  This container holds the views that we will use for each item.
    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvUsernameDisplay;
        TextView tvEmailDisplay ;
        TextView tvPhoneNumberDisplay;
        ImageView imgPictureDisplay;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            tvUsernameDisplay = itemView.findViewById(R.id.tvUsernameDisplay);
            tvEmailDisplay = itemView.findViewById(R.id.tvEmailDisplay);
            tvPhoneNumberDisplay = itemView.findViewById(R.id.tvPhoneNumberDisplay);
            imgPictureDisplay = itemView.findViewById(R.id.imgPictureDisplay);
        }
    }
}
