package com.projet.easybusiness.helper_request;

import android.util.Log;

import com.projet.easybusiness.Annonce;
import com.squareup.moshi.FromJson;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonReader;

import java.io.IOException;

public class ApiAnnonceAdapter {

    @FromJson
    Annonce fromJson(JsonReader reader, JsonAdapter<Annonce> delegate) throws Exception{
        Annonce result= null;

        // commencons le  parsing de l'elt;
        reader.beginObject();

        while (reader.hasNext()){
            String name = reader.nextName();

            if(name.equals("success")){
                boolean success= reader.nextBoolean();

                if (!success) {
                    // @todo : récupérer le message d'erreur et le donner à l'exception
                    // @todo : créer une exception spécifique pour la distinguer des IOException
                    throw new IOException("API a répondu FALSE");
                }
            }else if(name.equals("response")){
                result=delegate.fromJson(reader);
            }else{
                // dans notre cas on ne devrait pas avoir d'autres clés que success et response dans le Json
                throw new IOException("Response contient des données non conformes");
            }

        }
        reader.endObject();
        return result;
    }
}
