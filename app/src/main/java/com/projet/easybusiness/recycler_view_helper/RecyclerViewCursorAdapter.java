package com.projet.easybusiness.recycler_view_helper;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.projet.easybusiness.R;

public class RecyclerViewCursorAdapter extends RecyclerView.Adapter<AnnonceViewHolder>{

    private Context mcontext; // represente l'activité dans laquelle on se trouve
    private Cursor mcursor; // represente le pointeur vers l'élément que l'on rechercher

    public RecyclerViewCursorAdapter(Context mcontext, Cursor mcursor) {
        this.mcontext = mcontext;
        this.mcursor = mcursor;
    }

    @NonNull
    @Override
    public AnnonceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(mcontext);
        View view = inflater.inflate(R.layout.ad_view,parent,false);
        return new AnnonceViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull AnnonceViewHolder holder, int position) {
        //  si on n'a plus rien dans le curseur alors on arrête la méthode
        if(!mcursor.moveToPosition(position)){
            return;
        }
        holder.bind(mcursor);
    }

    @Override
    public int getItemCount() {
        return mcursor.getCount();
    }

    /**
     * Permet de prendre le prochain curseur
     * Prend en paramétre le nouveau curseur
     */
    public void swapCursor(Cursor newCursor){
        if(mcursor != null ){
            mcursor.close();
        }

        if(newCursor !=null){
            notifyDataSetChanged(); // on previent qu'il l'adapter qu'il faut changer le cursor;
        }
    }
}
