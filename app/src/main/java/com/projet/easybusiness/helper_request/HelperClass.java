package com.projet.easybusiness.helper_request;

import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.projet.easybusiness.Annonce;
import com.projet.easybusiness.AnnonceContract;
import com.projet.easybusiness.R;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class HelperClass {
    public static String formatDate(long date){
        String dateString = new SimpleDateFormat("MM/dd/yyyy").format( date);
        return dateString;
    }

    public static Long dateToLong(Date date){
        long longDate=0;
        try {
            DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
            date = (Date)formatter.parse(""+date+"");
            longDate=date.getTime();
        }
        catch (ParseException e){
            System.out.println("Exception :"+e);
        }
        return longDate;
    }

    public static Date stringToDate(String s){
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        Date d = null;
        try {
             d = sdf.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return d;
    }

    public static String[] changeToPng(String[] img){
        for(int i=0; i< img.length; i++){
            img[i]=img[i].replaceAll(".jpg",".png");
        }
        return img;
    }

    public static float stringToFloat(String str){
        float f=0;
        try
        {
             f = Float.valueOf(str.trim()).floatValue();
        }
        catch (NumberFormatException nfe)
        {
            System.err.println("NumberFormatException: " + nfe.getMessage());
        }
        return f;
    }

    public static String fromLongToDate(Long time){
       // String dateAsString = String.valueOf(valeur);
       /* Date date = null;
        try{
            date = new SimpleDateFormat("dd/MM/yyyy").parse(dateAsString);
        }catch(Exception e){
            e.printStackTrace();
        }
                */

        try{
            DateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            Date netDate = (new Date(time));
            Log.i("date",netDate.toString());
            return sdf.format(netDate);
        } catch (Exception ignored) {
            return "xx";
        }
      /*  Calendar cal = Calendar.getInstance(Locale.FRANCE);
        cal.setTimeInMillis(time * 1000);
        String date = DateFormat.format("dd-MM-yyyy", cal).toString();*/

        //return date ;

    }

    public static Annonce bind(Cursor cursor) {
        cursor.moveToPosition(0);
        String ctitre = cursor.getString(cursor.getColumnIndex(AnnonceContract.FeedEntry.COLUMN_NAME_TITRE));
        Long cdate = cursor.getLong(cursor.getColumnIndex(AnnonceContract.FeedEntry.COLUMN_NAME_DATE));
        String cdescription = cursor.getString(cursor.getColumnIndex(AnnonceContract.FeedEntry.COLUMN_NAME_DESCRIPTION));
        String[] cimage = {cursor.getString(cursor.getColumnIndex(AnnonceContract.FeedEntry.COLUMN_NAME_IMAGES))};
        String cid = cursor.getString(cursor.getColumnIndex(AnnonceContract.FeedEntry.COLUMN_NAME_ID));
        String cville = cursor.getString(cursor.getColumnIndex(AnnonceContract.FeedEntry.COLUMN_NAME_VILLE));
        String ccodePostal = cursor.getString(cursor.getColumnIndex(AnnonceContract.FeedEntry.COLUMN_NAME_CODE_POSTAL));
        int cprix = cursor.getInt(cursor.getColumnIndex(AnnonceContract.FeedEntry.COLUMN_NAME_PRIX));
        String cpseudo = cursor.getString(cursor.getColumnIndex(AnnonceContract.FeedEntry.COLUMN_NAME_PSEUDO));
        String cemail = cursor.getString(cursor.getColumnIndex(AnnonceContract.FeedEntry.COLUMN_NAME_EMAIL));
        String ctel = cursor.getString(cursor.getColumnIndex(AnnonceContract.FeedEntry.COLUMN_NAME_TELEPHONE));
        return new Annonce(cid, ctitre, cdescription, cprix, cpseudo, cemail, ctel, cville, ccodePostal, cimage, cdate);
    }

    public static void wait(int time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
        }
    }
}
