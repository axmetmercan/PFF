package com.example.pff;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class publishedAdvertismentsRecyclerView extends RecyclerView.Adapter<publishedAdvertismentsRecyclerView.AdvertisementHolder> {

    private ArrayList<PublishedAdvertisements> publishedAdvertisements;

    public publishedAdvertismentsRecyclerView(ArrayList<PublishedAdvertisements> publishedAdvertisements) {
        this.publishedAdvertisements = publishedAdvertisements;
    }


    public ArrayList<PublishedAdvertisements> getPublishedAdvertisements() {
        return publishedAdvertisements;
    }

    public void setPublishedAdvertisements(ArrayList<PublishedAdvertisements> publishedAdvertisements) {
        this.publishedAdvertisements = publishedAdvertisements;
    }

    @NonNull
    @Override
    public AdvertisementHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.published_advertisement_row_layout,parent,false);
        AdvertisementHolder holder = new AdvertisementHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdvertisementHolder holder, int position) {
        holder.petName.setText(publishedAdvertisements.get(position).getPetName());
        holder.petCategory.setText(publishedAdvertisements.get(position).getPetCategory());
        Picasso.get().load(publishedAdvertisements.get(position).getImgUrl()).into(holder.petImage);


    }

    @Override
    public int getItemCount() {
        return publishedAdvertisements.size();
    }


    public class AdvertisementHolder extends RecyclerView.ViewHolder {
        TextView petName, petCategory;
        ImageView petImage;

        public AdvertisementHolder(@NonNull View itemView) {
            super(itemView);

            petName = itemView.findViewById(R.id.txtPublisedPetName);
            petCategory = itemView.findViewById(R.id.txtPublishedPetCategory);
            petImage = itemView.findViewById(R.id.publishedPetImage);
        }
    }
}
