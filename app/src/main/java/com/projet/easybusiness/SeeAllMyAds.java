package com.projet.easybusiness;

import android.content.SharedPreferences;
import android.util.Log;
import com.projet.easybusiness.helper_request.ApiListAnnonceAdapter;
import com.projet.easybusiness.helper_request.ResponseAnnonces;
import com.projet.easybusiness.recycler_view_helper.AnnonceAdapter;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.util.ArrayList;

public class SeeAllMyAds extends SeeAllAd {


    public void parseAds(String body){
        // declaration de l'instance de moshi qui va gerer le parsing
        Moshi moshi= new Moshi.Builder().add(new ApiListAnnonceAdapter()).build();
        JsonAdapter<ResponseAnnonces> adapter = moshi.adapter(ResponseAnnonces.class);
        Log.i("YKJ","je suis dans l'adapteur 222");
        try{
            ResponseAnnonces response = adapter.fromJson(body);
            listAnnonce=response.getAnnonces();
            filter(listAnnonce);
            AnnonceAdapter adapterListAnnonce= new AnnonceAdapter(listAnnonce);
            fillMap();
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void filter(ArrayList<Annonce> listeA){
        SharedPreferences preferences = getSharedPreferences("PREF", MODE_PRIVATE);
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
            Log.i("YKJ", i+""+ preferences.getString("pseudo","")+"=="+ad.getPseudo());

        }
        listAnnonce= new ArrayList<>(list);
    }


}
