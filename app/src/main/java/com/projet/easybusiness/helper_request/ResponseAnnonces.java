package com.projet.easybusiness.helper_request;

import com.projet.easybusiness.Annonce;

import java.util.ArrayList;

public class ResponseAnnonces {
    private ArrayList<Annonce> annonces;
    private boolean success;

    public ResponseAnnonces(ArrayList<Annonce> annonces, boolean success) {
        this.annonces = annonces;
        this.success = success;
    }

    public ArrayList<Annonce> getAnnonces() {
        return annonces;
    }

    public void setAnnonces(ArrayList<Annonce> annonces) {
        this.annonces = annonces;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
