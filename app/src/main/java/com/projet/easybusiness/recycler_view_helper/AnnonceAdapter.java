package com.projet.easybusiness.recycler_view_helper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.projet.easybusiness.Annonce;
import com.projet.easybusiness.R;

import java.util.ArrayList;

public class AnnonceAdapter extends RecyclerView.Adapter<AnnonceViewHolder> {

    private ArrayList<Annonce> annonces;

    public AnnonceAdapter(ArrayList<Annonce> ads){
        this.annonces=ads;
    }

    @NonNull
    @Override
    public AnnonceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.ad_view,parent,false);
        return new AnnonceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AnnonceViewHolder holder, int position) {
        Annonce ad= annonces.get(position);
        holder.bind(ad);
    }

    @Override
    public int getItemCount() {
        return annonces.size();
    }
}
