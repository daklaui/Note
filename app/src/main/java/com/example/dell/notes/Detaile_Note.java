package com.example.dell.notes;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Detaile_Note extends AppCompatActivity {
EditText Titre;
EditText Det;
Note note;
ActionsNote actionsNote;
FloatingActionButton floatingActionButtonupdate;
    AlertDialog.Builder dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detaile__note);
        Titre = (EditText) findViewById(R.id.editTitleupdate);
        Det = (EditText) findViewById(R.id.editDetNoteUpdate);
        final int position = Integer.parseInt(getIntent().getStringExtra("pos"));
        actionsNote = new ActionsNote();
        actionsNote.open(getApplicationContext());
        floatingActionButtonupdate=findViewById(R.id.editNotesUpdate);
        if (position >= 0) {
            note =  actionsNote.getNote(position);
        }

        if (note != null) {
            Titre.setText(note.getTitre());
            Det.setText(note.getDet_Note());
        }
        floatingActionButtonupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean vc=(boolean) actionsNote.updateNote(note.getId(),String.valueOf(Titre.getText()),String.valueOf(Det.getText()));
                if(vc!=false)
                {

                    Toast.makeText(Detaile_Note.this, "enregistrer id = " +vc, Toast.LENGTH_SHORT).show();
                    actionsNote.close();
                    Intent in = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(in);
                }
                else
                    Toast.makeText(Detaile_Note.this, "Something wrong", Toast.LENGTH_SHORT).show();

            }

        });
        dialog = new AlertDialog.Builder(Detaile_Note.this);
        dialog.setCancelable(false);
        dialog.setTitle("Dialog on Android");
        dialog.setMessage("Are you sure you want to delete this entry?" );
        dialog.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                //Action for "Delete".
            }
        })
                .setNegativeButton("Cancel ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Action for "Cancel".
                    }
                });
    }

    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Enregistrer vos modification ou annuler?")
                .setCancelable(false)
                .setPositiveButton("Enregistrer", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //finish();
                        if (Titre.getText().length() == 0 && Det.getText().length() == 0) {
                            boolean vc = (boolean) actionsNote.updateNote(note.getId(), String.valueOf(Titre.getText()), String.valueOf(Det.getText()));
                            if (vc != false) {
                                Toast.makeText(Detaile_Note.this, "enregistrer ", Toast.LENGTH_SHORT).show();
                                actionsNote.close();
                            }
                            Detaile_Note.this.onSuperBackPressed();
                            //super.onBackPressed();
                        }
                    }
                })
                .setNegativeButton("Ignorer", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Detaile_Note.this.onSuperBackPressed();
                    }
                })
                .setNeutralButton("Annuler",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    /*
    if (handleCancel()){
        super.onBackPressed();
    }
    */
    }
    public void onSuperBackPressed(){
        super.onBackPressed();

    }
   /* @Override
    public void onBackPressed() {
        super.onBackPressed();
        final AlertDialog alert = dialog.create();
        alert.show();
        boolean vc=(boolean) actionsNote.updateNote(note.getId(),String.valueOf(Titre.getText()),String.valueOf(Det.getText()));
        if(vc!=false)
        {
            Toast.makeText(Detaile_Note.this, "enregistrer ", Toast.LENGTH_SHORT).show();
            actionsNote.close();
        }
    }*/
}
