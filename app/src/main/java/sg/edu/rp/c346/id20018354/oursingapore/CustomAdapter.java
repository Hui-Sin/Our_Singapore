package sg.edu.rp.c346.id20018354.oursingapore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

import static java.lang.String.valueOf;

public class CustomAdapter extends ArrayAdapter {
    Context parent_context;
    int layout_id;
    ArrayList<Island> islands;

    public CustomAdapter(Context context, int resource, ArrayList<Island> objects) {
        super(context, resource, objects);
        this.parent_context = context;
        this.layout_id = resource;
        this.islands = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obtain the LayoutInflater object
        LayoutInflater inflater = (LayoutInflater)
                parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // "Inflate" the View for each row
        View rowView = inflater.inflate(layout_id, parent, false);

        // Obtain the UI components and do the necessary binding
        TextView tvName = rowView.findViewById(R.id.tvName);
        TextView tvDescription = rowView.findViewById(R.id.tvDescription);
        //TextView tvStars = rowView.findViewById(R.id.tvStars);
        TextView tvAreas = rowView.findViewById(R.id.tvDescription);

        //ImageView imageView = rowView.findViewById(R.id.imageView);

        RatingBar ratingBar = rowView.findViewById(R.id.ratingBar);

        // Obtain the Android Version information based on the position
        Island currentIsland = islands.get(position);

        // Set values to the TextView to display the corresponding information
        tvName.setText(currentIsland.getName());
        tvDescription.setText(currentIsland.getDescription());
        tvAreas.setText(valueOf(currentIsland.getArea()));
        String stars = "";
        for(int i = 0; i < currentIsland.getStars(); i++){
            stars += " * ";
        }
        //tvStars.setText(stars);

        ratingBar.setRating(currentIsland.getStars());

        tvAreas.setText(currentIsland.getArea() + "");

//        if(currentSong.getYearReleased() >= 2019){
//            imageView.setImageResource(R.drawable.newsong);
//        }
//        else {
//            imageView.setVisibility(View.GONE);
//        }

        return rowView;
    }

}
