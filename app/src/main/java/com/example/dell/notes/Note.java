package com.example.dell.notes;

import android.text.Editable;

import java.util.Date;

public class Note {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String id;
    public String  titre;
    public  String det_Note;
    public  String date_Creation_Note;

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDet_Note() {
        return det_Note;
    }

    public void setDet_Note(String det_Note) {
        this.det_Note = det_Note;
    }

    public String getDate_Creation_Note() {
        return date_Creation_Note;
    }

    public void setDate_Creation_Note(String date_Creation_Note) {
        this.date_Creation_Note = date_Creation_Note;
    }

    public Note(String id,String titre, String det_Note, String date_Creation_Note) {
        this.id=id;
        this.titre = titre;
        this.det_Note = det_Note;
        this.date_Creation_Note = date_Creation_Note;
    }

    public Note() {
    }
}
