package com.example.covid19;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<ListItem> listItems;
    private Context context;
    private HashMap<String, String> hMap;
    private HashMap<String, String> stateMap;

    MyAdapter(List<ListItem> listItems, Context context, HashMap<String, String> aMap, HashMap<String, String> aMap2) {
        this.listItems = listItems;
        this.context = context;
        this.hMap = aMap;
        this.stateMap = aMap2;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.list_item, parent, false );
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final ListItem listItem = listItems.get(position);

        holder.textViewCountryName.setText(listItem.getCountryName());
        holder.textViewTotalCases.setText(listItem.getTotalCases());
        holder.textViewTotalDeath.setText(listItem.getTotalDeath());
        holder.textViewTotalRecovered.setText(listItem.getTotalRecovered());
        holder.textViewTotalActive.setText(listItem.getTotalActive());
        holder.textViewNewCases.setText(listItem.getNewCases());
        holder.textViewNewDeaths.setText(listItem.getNewDeaths());

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Check if world data and then only launch second activity (< 40 is India data)
                Intent intent;
                if(listItems.size() > 40) {
                    // World Data Case
                    intent = new Intent(context, CountryActivity.class);
                } else {
                    // India State Data
                    intent = new Intent(context, IndiaActivity.class);
                    intent.putExtra("totalNewCases", listItem.getNewCases());
                    intent.putExtra("totalNewDeaths", listItem.getNewDeaths());
                }
                intent.putExtra("countryName", listItem.getCountryName());
                intent.putExtra("totalCases", listItem.getTotalCases());
                intent.putExtra("totalDeath", listItem.getTotalDeath());
                intent.putExtra("totalRecovered", listItem.getTotalRecovered());
                intent.putExtra("totalActive", listItem.getTotalActive());
                intent.putExtra("map", hMap);
                intent.putExtra("mapState", stateMap);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
//
            }
        });
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder{

        LinearLayout parentLayout;
        TextView textViewCountryName;
        TextView textViewTotalCases;
        TextView textViewTotalDeath;
        TextView textViewTotalRecovered;
        TextView textViewTotalActive;
        TextView textViewNewCases;
        TextView textViewNewDeaths;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            // Get all the view from the xml file and assign them to the variables
            this.parentLayout = itemView.findViewById(R.id.parent_layout_list_item);
            this.textViewCountryName = itemView.findViewById(R.id.country_state_name);
            this.textViewTotalCases = itemView.findViewById(R.id.total_cases_item_left);
            this.textViewTotalDeath = itemView.findViewById(R.id.total_deaths_item_left);
            this.textViewTotalRecovered = itemView.findViewById(R.id.total_recovered_item_left);
            this.textViewTotalActive = itemView.findViewById(R.id.total_active_item_left);
            this.textViewNewCases = itemView.findViewById(R.id.new_cases_item_left);
            this.textViewNewDeaths = itemView.findViewById(R.id.new_deaths_item_left);
        }
    }
}
