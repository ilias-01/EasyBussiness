package com.projet.easybusiness;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;




public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(myToolbar);

        //Intent view =  new Intent(this,SeeAllAd.class);
        //startActivity(view);
    }
    /***********MENU************/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Intent intent;
        if(id == R.id.ic_profil){
            intent = new Intent(this,UserInformation.class);
            startActivity(intent);
        }else if(id == R.id.ic_add){
            intent = new Intent(this,DepotAnnonce.class); //ajout annonce
            startActivity(intent);
        }else if(id == R.id.ic_save){
            intent = new Intent(this,ListeDesAnnoncesSauvegargees.class);
            startActivity(intent);
        }else if(id == R.id.ic_refrech){
            intent = new Intent(this, SeeAllAd.class); //ajout annonce
            startActivity(intent);

        }else{
            //envoyer à la liste des annonces
            intent = new Intent(this,SeeAllAd.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    /********************************/

    public void next(View view) {
        Intent next= new Intent(this,SeeAd.class);
        startActivity(next);
    }

    public void seeAllAd(View v){
        Intent view =  new Intent(this,SeeAllAd.class);
        startActivity(view);
    }

    public void fillProfile(View v){
        Intent view =  new Intent(this,UserInformation.class);
        startActivity(view);
    }
    static final int REQUEST_IMAGE_CAPTURE = 1;
    public void takePic(View v){
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            ImageView imageView = findViewById(R.id.testCam);
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageView.setImageBitmap(imageBitmap);
        }
    }
    public void listeAdSaved(View v) {
        /*AnnonceDb annonceDb = new AnnonceDb(v.getContext());
        Cursor resultat = annonceDb.listeAnnoncesSauvegardees();
        Log.i("ttt ", "Liste des annonces sauvegardées: " + resultat.toString());
        */
        Intent view = new Intent(this,ListeDesAnnoncesSauvegargees.class);
        startActivity(view);
    }

    public void depotAnnonce(View v){
        Intent view =  new Intent(this,DepotAnnonce.class);
        startActivity(view);

    }

    public void seeMyAds(View v) {
        Intent view = new Intent(this, SeeAllMyAds.class);
        startActivity(view);
    }
}
