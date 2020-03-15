package com.projet.easybusiness.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.projet.easybusiness.Annonce;
import com.projet.easybusiness.AnnonceDb;
import com.projet.easybusiness.ListeDesAnnoncesSauvegargees;
import com.projet.easybusiness.R;
import com.projet.easybusiness.SeeAd;
import com.projet.easybusiness.SeeAllAd;
import com.projet.easybusiness.helper_request.ApiListAnnonceAdapter;
import com.projet.easybusiness.helper_request.ResponseAnnonces;
import com.projet.easybusiness.recycler_view_helper.AnnonceAdapter;
import com.projet.easybusiness.recycler_view_helper.RecyclerViewCursorAdapter;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;


public class FavoritAdsFragment extends Fragment{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    protected static final String ARG_PARAM1 = "param1";
    protected static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    protected String mParam1;
    protected String mParam2;

    protected FavoritAdsFragment.OnFragmentInteractionListener mListener;
    protected RecyclerView recyclerView;
    private AnnonceDb database;
    private RecyclerViewCursorAdapter cAdapter;
    View v;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AllAdsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FavoritAdsFragment newInstance(String param1, String param2){
        FavoritAdsFragment fragment = new FavoritAdsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    /*@Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }*/

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void voirList(View view) {
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.fragment_favorit_ads, container, false);
        SeeAllAd.frombd=true;
        Log.i("fd", "0 fragment de base de donnéé");
        database= new AnnonceDb(getContext());
        Log.i("fd", "0.5 fragment de base de donnéé");
        recyclerView= v.findViewById(R.id.AdList);
        Log.i("fd", "1 fragment de base de donnéé");
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        Log.i("fd", "2 fragment de base de donnéé");
        cAdapter=  new RecyclerViewCursorAdapter(getContext(),database.listeAnnoncesSauvegardees());
        Log.i("fd", "3 fragment de base de donnéé");
        recyclerView.setAdapter(cAdapter);
        Log.i("fd", "4 fragment de base de donnéé");
        Intent next= new Intent(getContext(), ListeDesAnnoncesSauvegargees.class);
        startActivity(next);
        return v;
    }

}
