package com.projet.easybusiness;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AnnonceDbOpener extends SQLiteOpenHelper {

    public static  int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME ="annonces.db";


    public AnnonceDbOpener(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    public void onCreate(SQLiteDatabase db) {
        db.execSQL(AnnonceContract.FeedEntry.SQL_CREATE_ENTRIES);
    }

    public void setDatabaseVersion(int version){
        this.DATABASE_VERSION = version;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       db.execSQL(AnnonceContract.FeedEntry.SQL_DELETE_ENTRIES);
        onCreate(db);
        setDatabaseVersion(newVersion);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

}
