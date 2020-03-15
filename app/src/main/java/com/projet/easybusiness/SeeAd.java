package com.projet.easybusiness;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;


import android.content.Context;
import android.content.Intent;

import android.content.SharedPreferences;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.projet.easybusiness.helper_request.HelperClass;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import static com.google.android.material.snackbar.Snackbar.LENGTH_LONG;

public class SeeAd extends AppCompatActivity {
    Annonce ad=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(haveInternetConnection()){
            setContentView(R.layout.activity_see_ad);
        }else{
            setContentView(R.layout.activity_see_ad);
        }
       // makeHttpRequest("https://ensweb.users.info.unicaen.fr/android-api/mock-api/liste.json");

        Intent intent= getIntent();

        Annonce ad= intent.getParcelableExtra("idAnnonce");

        //Intent inten = Intent(this,this.getParent().getLocalClassName().class);

        Toolbar toolbarItem = findViewById(R.id.tool_br);
        toolbarItem.setTitle("");
        setSupportActionBar(toolbarItem);


        this.ad= intent.getParcelableExtra("idAnnonce");

        if(ad!=null){
            Log.i("annonceId",ad.getId());
            rempliAnnonce(this.ad);
            Log.i("annonceId",ad.getId());
        }else{
            String idAdd = intent.getStringExtra("idAnn");
            Log.i("idann", idAdd);
            //makeHttpRequest("https://ensweb.users.info.unicaen.fr/android-api/mock-api/completeAdWithImages.json");
            makeHttpRequest("https://ensweb.users.info.unicaen.fr/android-api/?apikey=21913373&method=details&id="+idAdd);

            //   makeHttpRequest("https://ensweb.users.info.unicaen.fr/android-api/?apikey=21913373&method=details&id=5e44171ab5bbc");
        }
    }

    /***********MENU************/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.see_ad_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Intent intent;
        if(id == R.id.ic_refrech){
            intent = new Intent(this,SeeAllAd.class); //ajout annonce
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
    /********************************/
    public void okhttp(View View){
        //makeHttpRequest("https://ensweb.users.info.unicaen.fr/android-api/mock-api/completeAdWithImages.json");
    }

    public void okhttp404(View view){
        //makeHttpRequest("https://ensweb.users.info.unicaen.fr/android-api/mock-api/erreur.json");

    }

    public void parseAd(String body){
        Moshi moshi= new Moshi.Builder().add(new ApiAnnonceAdapter()).build();
        JsonAdapter<Annonce> jsonAdapter = moshi.adapter(Annonce.class);
        try{
            Log.i("body",""+body);
            this.ad= jsonAdapter.fromJson(body);
            rempliAnnonce(ad);
        }catch(IOException e){
            Log.i("YKJ",e.getMessage());
        }

    }

    private void makeHttpRequest(String url){
        OkHttpClient client = new OkHttpClient();
        Request req= new Request.Builder()
                .url(url)
                .build();
        client.newCall(req).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // on recupere le corps de la reponce
                try(ResponseBody responseBody =response.body()){
                    // si la requete n'a pas reussi
                    if(!response.isSuccessful()){
                        // afficher un message d'erreur
                        throw new IOException("Unexpected HTTP Code "+response);
                    }
                    final String adBody= responseBody.string();
                    runOnUiThread(
                            new Runnable() {
                                @Override
                                public void run() {
                                    parseAd(adBody);
                                }
                            }
                    );
                }
            }
        });
    }
    /*
     * Permet de remplir une annonce
     * @param Ad qui correspond à une annonce
     */
    public void rempliAnnonce(Annonce ad){
        TextView titre = ((TextView) findViewById(R.id.titre));
        TextView prix = (TextView) findViewById(R.id.prix);
        TextView date = (TextView) findViewById(R.id.date);
        TextView proprietaire =  (TextView) findViewById(R.id.proprietaire);
        TextView email = (TextView) findViewById(R.id.email);
        TextView telephone= (TextView) findViewById(R.id.tel);
        TextView adresse = (TextView) findViewById(R.id.adresse);
        TextView description= (TextView) findViewById(R.id.description);

        TextView slideNumber= (TextView) findViewById(R.id.slideNumber);

      // Log.i ("YKJ", "l'image de "+ ad.getPseudo() +" est " +ad.getImages()[0]);
        ViewPager slider= findViewById(R.id.slide);
        SliderAdapter sliderAdapter=new SliderAdapter(this,ad.getImages(),slideNumber);
        slider.setAdapter(sliderAdapter);

        titre.setText(ad.getTitre());
        prix.setText(" "+ad.getPrix()+" $");
        proprietaire.setText(ad.getPseudo());
        email.setText(ad.getEmailContact());
        telephone.setText(ad.getTelContact());
        adresse.setText(ad.getAdresse());
        description.setText(ad.getDescription());
        date.setText(" "+ HelperClass.fromLongToDate(ad.getDate()));
        Log.i("YKJ", "logo fin");
        AnnonceDb annonceDb = new AnnonceDb(this);

        Log.i("xxxx", "on verifie si l'element est dans la base");
        try {
            boolean res =  annonceDb.exist(this.ad.getId());
            if(res){
                Button btn= findViewById(R.id.btSave);
                btn.setText("UNSAVE");
            }
            Log.i("xxxx","Dans le try : " + res);
        } catch (Exception e) {
            e.printStackTrace();
            Log.i("xxxx","Erreur de verification: ");
        }

    }

    public void callPers(View v){
        TextView telephone= (TextView) findViewById(R.id.tel);
        startActivity(new Intent(Intent.ACTION_DIAL,Uri.parse("tel: "+telephone.getText().toString())));
    }

    public void sendEmail(View v){
        TextView mail= (TextView) findViewById(R.id.email);
        SharedPreferences preferences=  getSharedPreferences("PREF",MODE_PRIVATE);
        Log.i("YKJ",preferences.getString("pseudo","inconnu"));
        Log.i("YKJ",preferences.getString("email", "inconnu"));
        Log.i("YKJ", preferences.getString("tel","pas de numero"));
        startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("mailto: "+ mail.getText().toString())));
    }

    public void saveAd(View v){
        AnnonceDb annonceDb = new AnnonceDb(this);

        if(!annonceDb.exist(ad.getId())){
            Log.i("xxxx", "on va enregistrer");
            try {
                long res =  annonceDb.ajouter(this.ad);
                Log.i("xxxx","Dans le try : sddf" + res);
                Button btn= findViewById(R.id.btSave);
                btn.setText("UNSAVE");
                Snackbar make = Snackbar.make(findViewById(R.id.line), "l'annoce N°"+ ad.getId() +" peut etre consulter sans connexion" , LENGTH_LONG);
                make.show();
            } catch (Exception e) {
                e.printStackTrace();
                Log.i("xxxx","Erreur d'insertion : ");
            }
            Log.i("xxxx","Après insertion : ");
        }else{
            unsaveAd();
            Snackbar make = Snackbar.make(findViewById(R.id.line), "l'annoce N° "+ ad.getId() +" ne peut plus etre consulter sans connexion" , LENGTH_LONG);
            make.show();
        }

    }

    public boolean unsaveAd(){
        AnnonceDb annonceDb = new AnnonceDb(this);

        if(annonceDb.deleteAnnonce(ad.getId())){
            Button btn= findViewById(R.id.btSave);
            btn.setText("SAVE");
            return true;
        }else{
            return false;
        }
    }

    public void returnParent(View v){
        Intent intent = new Intent(this,SeeAllAd.class);
        startActivity(intent);
    }


    //requete de suppression dans l'API
    OkHttpClient clientSuppression = new OkHttpClient();

    public void suppressionId(String urlSuppression)  {

            Request requestSuppression = new Request.Builder()
                    .url(urlSuppression)
                    .build();
            clientSuppression.newCall(requestSuppression).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    e.printStackTrace();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    try (ResponseBody responseBody = response.body()) {
                        Log.i("try","on est dans le try");
                        if (!response.isSuccessful())
                            throw new IOException("Unexpected code " + response);
                        System.out.println(responseBody.string());
                    }catch(IOException e)
                    {
                       Log.i("catch","on est dans le catch");
                    }
                }
            });
    }


    //suppression en local
    public void supprimer(View view) {
      if(haveInternetConnection()){
          SharedPreferences preferences=  getSharedPreferences("PREF",MODE_PRIVATE);


          if(view.getId()==R.id.supprimer && preferences.getString("email","inconnu").equalsIgnoreCase(ad.getEmailContact())) {
              suppressionId("https://ensweb.users.info.unicaen.fr/android-api/?apikey=21913373&method=delete&id=" + ad.getId());
              Snackbar.make(findViewById(R.id.line),"votre annonce a été supprimer avec succes", Snackbar.LENGTH_LONG).show();
              HelperClass.wait(1500);
              Intent next= new Intent(this,SeeAllAd.class);
              startActivity(next);
          }else
          {
              Snackbar.make(findViewById(R.id.line),"vous ne disposez pas les droit de supprimer cette annonce", Snackbar.LENGTH_LONG).show();
          }
      }else{
          Snackbar.make(findViewById(R.id.line),"Impossible de supprimer l'annonce vous n'êtes pas connecté à internet", Snackbar.LENGTH_LONG).show();
      }
    }
    public void modifierAnnonce(View v)
    {
        if(haveInternetConnection()){
            if(v.getId()==R.id.modifier)
            {
                SharedPreferences preferences=  getSharedPreferences("PREF",MODE_PRIVATE);

                if(preferences.getString("email","inconnu").equalsIgnoreCase(ad.getEmailContact())
                        &&preferences.getString("pseudo","inconnu").equalsIgnoreCase(ad.getPseudo())) {

                    Intent next= new Intent(this,ModifAnnonce.class);
                    next.putExtra("idAnnonce", ad);
                    startActivity(next);

                }else
                {
                    // snackbar a faire
                    Snackbar.make(findViewById(R.id.line),"vous ne disposez pas le droit de modifier cette annonce", Snackbar.LENGTH_LONG).show();
                }



            }
        }else{
            Snackbar.make(findViewById(R.id.line),"Impossible de modifier l'annonce vous n'êtes pas connecté à internet", Snackbar.LENGTH_LONG).show();
        }

    }

    public boolean haveInternetConnection(){
        // Fonction haveInternetConnection : return true si connecté, return false dans le cas contraire
        NetworkInfo network = ((ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE))
                .getActiveNetworkInfo();


        if (network==null || !network.isConnected())
        {
            // Le périphérique n'est pas connecté à Internet
            return false;
        }

        // Le périphérique est connecté à Internet
        return true;
    }
}
