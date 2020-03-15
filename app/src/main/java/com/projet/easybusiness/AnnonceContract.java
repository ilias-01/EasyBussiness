package com.projet.easybusiness;

import android.provider.BaseColumns;

public final class AnnonceContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private AnnonceContract() {}

    /* Inner class that defines the table contents */
    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "annonce";
        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_TITRE = "titre";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_PRIX = "prix";
        public static final String COLUMN_NAME_PSEUDO = "pseudo";
        public static final String COLUMN_NAME_EMAIL = "emailContact";
        public static final String COLUMN_NAME_TELEPHONE = "telContact";
        public static final String COLUMN_NAME_VILLE = "ville";
        public static final String COLUMN_NAME_CODE_POSTAL = "codePostal";
        public static final String COLUMN_NAME_IMAGES = "images";
        public static final String COLUMN_NAME_DATE = "date";
        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + AnnonceContract.FeedEntry.TABLE_NAME + " (" +
                        _ID + " INTEGER PRIMARY KEY autoincrement," +
                        COLUMN_NAME_ID + " TEXT," +
                        COLUMN_NAME_TITRE + " TEXT," +
                        COLUMN_NAME_DESCRIPTION + " TEXT," +
                        COLUMN_NAME_PRIX +" INTEGER," +
                        COLUMN_NAME_PSEUDO + " TEXT," +
                        COLUMN_NAME_EMAIL + " TEXT," +
                        COLUMN_NAME_TELEPHONE + " TEXT," +
                        COLUMN_NAME_VILLE + " TEXT," +
                        COLUMN_NAME_CODE_POSTAL + " TEXT," +
                        COLUMN_NAME_IMAGES + " TEXT," +
                        COLUMN_NAME_DATE + " INTEGER)";

        public static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + AnnonceContract.FeedEntry.TABLE_NAME;
    }


}
