package com.projet.easybusiness;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

public class AdPictureDialog extends DialogFragment  {
    public interface MyDialogListener {
        public void uploadGalery(DialogFragment dialog);
        public void takePicture(DialogFragment dialog);
    }

    MyDialogListener ecouteur;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            ecouteur = (MyDialogListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " DOIT implémenter MyDialogListener");
        }
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(" Image d'annonce ")
                .setNegativeButton("Camera", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        ecouteur.takePicture(AdPictureDialog.this);
                    }
                })
                .setPositiveButton("Photos", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        ecouteur.uploadGalery(AdPictureDialog.this);
                    }
                });
        return builder.create();


    }
}
