package com.projet.easybusiness.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.projet.easybusiness.Annonce;
import com.projet.easybusiness.R;
import com.projet.easybusiness.helper_request.ApiListAnnonceAdapter;
import com.projet.easybusiness.helper_request.ResponseAnnonces;
import com.projet.easybusiness.recycler_view_helper.AnnonceAdapter;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MyAdsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MyAdsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyAdsFragment extends AllAdsFragment {
    public MyAdsFragment(int lay) {
        // Required empty public constructor
        layout= lay;
    }
    public MyAdsFragment() {
        // Required empty public constructor
        layout= R.layout.fragment_my_ads;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v=inflater.inflate(layout, container, false);

        try {
            makeHttpRequest("https://ensweb.users.info.unicaen.fr/android-api/?apikey=21913373&method=listAll");
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Inflate the layout for this fragment
        return v;
    }
    public void parseAds(String body){
        // declaration de l'instance de moshi qui va gerer le parsing
        Moshi moshi= new Moshi.Builder().add(new ApiListAnnonceAdapter()).build();
        JsonAdapter<ResponseAnnonces> adapter = moshi.adapter(ResponseAnnonces.class);
        try{
            ResponseAnnonces response = adapter.fromJson(body);
            listAnnonce=response.getAnnonces();
            filter(listAnnonce);
            AnnonceAdapter adapterListAnnonce= new AnnonceAdapter(listAnnonce);
            fillMap();

            RecyclerView recycler= v.findViewById(R.id.recycleViewFavAnnonces);
            // on inserer une linear view afin d'afficher les éléments sur une ligne
            recycler.setLayoutManager(new LinearLayoutManager(getActivity()));

            recycler.setAdapter(adapterListAnnonce);

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void filter(ArrayList<Annonce> listeA){
        SharedPreferences preferences = getActivity().getSharedPreferences("PREF", MODE_PRIVATE);
        ArrayList<Annonce> list= new ArrayList<>();
        for (int i=listeA.size()-1 ; i>=0; i--){
            Annonce ad = listeA.get(i);
            if(ad.getPseudo().equals(preferences.getString("pseudo",""))){
                Log.i("YKJ", i+" On est ici");
                if(ad.getEmailContact().equals(preferences.getString("email",""))){
                    list.add(ad);
                    Log.i("YKJ", i+" inserer");
                }
            }
        }
        listAnnonce= new ArrayList<>(list);
    }
}
