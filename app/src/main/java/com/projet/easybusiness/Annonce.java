package com.projet.easybusiness;

import android.os.Parcel;
import android.os.Parcelable;

public class Annonce implements Parcelable {
    private String id;
    private String titre;
    private String description;
    private int prix;
    private String pseudo;
    private String emailContact;
    private String telContact;
    private String ville;
    private String cp;
    private String[] images;
    private Long date;

    public Annonce(String id, String titre, String description, int  prix, String pseudo, String emailContact, String telContact, String ville, String cp, String[] image, Long date) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.prix = prix;
        this.pseudo = pseudo;
        this.emailContact = emailContact;
        this.telContact = telContact;
        this.ville = ville;
        this.cp = cp;
        this.images = image;
        this.date = date;
    }
    public Annonce(String titre, String description, int prix, String pseudo, String emailContact, String telContact, String ville) {

        this.titre = titre;
        this.description = description;
        this.prix = prix;
        this.pseudo = pseudo;
        this.emailContact = emailContact;
        this.telContact = telContact;
        this.ville = ville;
        this.cp = cp;
    }

    protected Annonce(Parcel in) {
        id = in.readString();
        titre = in.readString();
        description = in.readString();
        prix = in.readInt();
        pseudo = in.readString();
        emailContact = in.readString();
        telContact = in.readString();
        ville = in.readString();
        cp = in.readString();
        images = in.createStringArray();
        if (in.readByte() == 0) {
            date = null;
        } else {
            date = in.readLong();
        }
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getEmailContact() {
        return emailContact;
    }

    public void setEmailContact(String emailContact) {
        this.emailContact = emailContact;
    }

    public String getTelContact() {
        return telContact;
    }

    public void setTelContact(String telContact) {
        this.telContact = telContact;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String[] getImages() {
        return images;
    }

    public void setImage(String[] images) {
        this.images = images;
    }

    public Long getDate(){
        return date;
    }

    public String getAdresse(){
        return this.cp+", "+ this.ville;
    }

    public void setDate(Long date) {
        this.date = date;
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(titre);
        dest.writeString(description);
        dest.writeFloat(prix);
        dest.writeString(pseudo);
        dest.writeString(emailContact);
        dest.writeString(telContact);
        dest.writeString(ville);
        dest.writeString(cp);
        dest.writeStringArray(images);
        if (date == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(date);
        }
    }


    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Annonce> CREATOR = new Creator<Annonce>() {
        @Override
        public Annonce createFromParcel(Parcel in) {
            return new Annonce(in);
        }

        @Override
        public Annonce[] newArray(int size) {
            return new Annonce[size];
        }
    };


}
