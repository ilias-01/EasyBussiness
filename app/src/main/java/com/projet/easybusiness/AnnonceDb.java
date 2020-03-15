package com.projet.easybusiness;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.projet.easybusiness.helper_request.HelperClass;
import com.projet.easybusiness.recycler_view_helper.RecyclerViewCursorAdapter;


public class AnnonceDb {

    private AnnonceDbOpener annonceDbOpener;

    public AnnonceDb(Context context)
    {
        annonceDbOpener = new AnnonceDbOpener(context);
    }

    public long ajouter(Annonce annonce)
    {
        SQLiteDatabase db = annonceDbOpener.getWritableDatabase();
        ContentValues values = new ContentValues();
        //int price = Integer.parseInt(String.valueOf(AnnoceContract.FeedEntry.COLUMN_NAME_PRIX));
        values.put( AnnonceContract.FeedEntry.COLUMN_NAME_ID , annonce.getId());
        values.put( AnnonceContract.FeedEntry.COLUMN_NAME_TITRE , annonce.getTitre());
        values.put( AnnonceContract.FeedEntry.COLUMN_NAME_DESCRIPTION , annonce.getDescription());
       values.put( AnnonceContract.FeedEntry.COLUMN_NAME_PRIX,  annonce.getPrix());
        Log.i("nnnn", "Prix : "+(annonce.getPrix()));
        values.put( AnnonceContract.FeedEntry.COLUMN_NAME_PSEUDO , annonce.getPseudo());
        values.put( AnnonceContract.FeedEntry.COLUMN_NAME_EMAIL , annonce.getEmailContact());
        values.put( AnnonceContract.FeedEntry.COLUMN_NAME_TELEPHONE , annonce.getTelContact());
        values.put( AnnonceContract.FeedEntry.COLUMN_NAME_VILLE , annonce.getVille());
        values.put( AnnonceContract.FeedEntry.COLUMN_NAME_CODE_POSTAL , annonce.getCp());
        if(annonce.getImages().length <0){
            values.put( AnnonceContract.FeedEntry.COLUMN_NAME_IMAGES, annonce.getImages()[0]);
        }else{
            values.put( AnnonceContract.FeedEntry.COLUMN_NAME_IMAGES, "no url");
        }

        values.put(AnnonceContract.FeedEntry.COLUMN_NAME_DATE,   annonce.getDate());
        Log.i("nnnn", "Date : "+  HelperClass.formatDate(annonce.getDate())/*HelperClass.stringToDate(HelperClass.formatDate(annonce.getDate()))*/ + " "+ HelperClass.stringToDate(HelperClass.formatDate(annonce.getDate())).getClass().getName());
        long res = db.insert(AnnonceContract.FeedEntry.TABLE_NAME, null, values);

        return res;
    }

    public boolean deleteAnnonce(String id){
        SQLiteDatabase database = this.annonceDbOpener.getWritableDatabase();
        String[] select = {id};
        int res=database.delete(
                AnnonceContract.FeedEntry.TABLE_NAME,
                AnnonceContract.FeedEntry.COLUMN_NAME_ID+ "= ? ",
                select
        );
        return (res > 0 ) ? true : false;
    }

    public boolean exist(String id){
            String[] projection = {
                    AnnonceContract.FeedEntry.COLUMN_NAME_ID,
            };
            String select = AnnonceContract.FeedEntry.COLUMN_NAME_ID+ " LIKE ?";
            String[] selectArg= {
                   id
            };
        Cursor cursor;
        SQLiteDatabase db = annonceDbOpener.getReadableDatabase();
        cursor = db.query(AnnonceContract.FeedEntry.TABLE_NAME, projection,select, selectArg, null, null, null);

            return (cursor.getCount() > 0)? true : false;
    }



    public Cursor listeAnnoncesSauvegardees(){

        String[] projection = {
                AnnonceContract.FeedEntry.COLUMN_NAME_ID,
                AnnonceContract.FeedEntry.COLUMN_NAME_TITRE,
                AnnonceContract.FeedEntry.COLUMN_NAME_DESCRIPTION,
                AnnonceContract.FeedEntry.COLUMN_NAME_PRIX,
                AnnonceContract.FeedEntry.COLUMN_NAME_PSEUDO,
                AnnonceContract.FeedEntry.COLUMN_NAME_EMAIL,
                AnnonceContract.FeedEntry.COLUMN_NAME_TELEPHONE,
                AnnonceContract.FeedEntry.COLUMN_NAME_VILLE,
                AnnonceContract.FeedEntry.COLUMN_NAME_CODE_POSTAL,
                AnnonceContract.FeedEntry.COLUMN_NAME_IMAGES,
                AnnonceContract.FeedEntry.COLUMN_NAME_DATE
        };

        SQLiteDatabase db = annonceDbOpener.getReadableDatabase();


        Cursor cursor =   db.query(AnnonceContract.FeedEntry.TABLE_NAME, projection,null,null,null,null,null);
        return cursor;
    }


    public Cursor getElt(String titreClick) {

        String[] projection = {
                AnnonceContract.FeedEntry.COLUMN_NAME_ID,
                AnnonceContract.FeedEntry.COLUMN_NAME_TITRE,
                AnnonceContract.FeedEntry.COLUMN_NAME_DESCRIPTION,
                AnnonceContract.FeedEntry.COLUMN_NAME_PRIX,
                AnnonceContract.FeedEntry.COLUMN_NAME_PSEUDO,
                AnnonceContract.FeedEntry.COLUMN_NAME_EMAIL,
                AnnonceContract.FeedEntry.COLUMN_NAME_TELEPHONE,
                AnnonceContract.FeedEntry.COLUMN_NAME_VILLE,
                AnnonceContract.FeedEntry.COLUMN_NAME_CODE_POSTAL,
                AnnonceContract.FeedEntry.COLUMN_NAME_IMAGES,
                AnnonceContract.FeedEntry.COLUMN_NAME_DATE
        };

        String[] select = {titreClick};

        SQLiteDatabase db = annonceDbOpener.getReadableDatabase();

        Log.i("tttt", "après ouverture de la requete");

        Cursor cursor =   db.query(AnnonceContract.FeedEntry.TABLE_NAME, projection,AnnonceContract.FeedEntry.COLUMN_NAME_ID+" LIKE ?",select,null,null,null);
        Log.i("tttt", "après l'execution de la requete"+ cursor);
        return cursor;
    }
}


