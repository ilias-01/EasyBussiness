package com.projet.easybusiness;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.util.prefs.Preferences;

import static com.google.android.material.snackbar.Snackbar.LENGTH_LONG;

public class UserInformation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_information);
    }

    public void saveProfile(View v){
        TextView pseudo= (TextView) findViewById(R.id.pseudo);
        TextView email = (TextView) findViewById(R.id.email);
        TextView tel= (TextView)findViewById(R.id.tel);

        SharedPreferences preferences=  getSharedPreferences("PREF",MODE_PRIVATE);
        SharedPreferences.Editor managerPreference = preferences.edit();
        managerPreference.putString("pseudo",pseudo.getText().toString());
        managerPreference.putString("email",email.getText().toString());
        managerPreference.putString("tel", tel.getText().toString());
        if(managerPreference.commit()){
            Snackbar make = Snackbar.make(findViewById(R.id.userProfile), "Votre profil a bien été mis à jour" , LENGTH_LONG);
            make.show();
            managerPreference.apply();
        }else{
            Snackbar make = Snackbar.make(findViewById(R.id.userProfile), "Votre profil n'a pas été mis à jour" , LENGTH_LONG);
            make.show();
        }


        Intent intent = new Intent(this,SeeAllAd.class);
        startActivity(intent);
        
    }
}
