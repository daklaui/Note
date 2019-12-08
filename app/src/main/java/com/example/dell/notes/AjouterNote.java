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

public class AjouterNote extends AppCompatActivity {
    FloatingActionButton floatingActionButton;
    EditText Titre;
    EditText Detaile;
    private ActionsNote actionsNote;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_note);
        Titre=(EditText)findViewById(R.id.editTitle);
        Detaile=(EditText)findViewById(R.id.editDetNote);
        floatingActionButton =findViewById(R.id.notesadd);

actionsNote=new ActionsNote();
 actionsNote.open(this);
        floatingActionButton.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v) {
               // Toast.makeText(AjouterNote.this, "Click", Toast.LENGTH_SHORT).show();
                long vc=(long) actionsNote.createNote(String.valueOf(Titre.getText()),String.valueOf(Detaile.getText()));
           if(vc!=-1)
           {

               Toast.makeText(AjouterNote.this, "enregistrer id = " +vc, Toast.LENGTH_SHORT).show();
               actionsNote.close();
               Intent in = new Intent(getApplicationContext(),MainActivity.class);
               startActivity(in);
           }
           else
               Toast.makeText(AjouterNote.this, "Something wrong", Toast.LENGTH_SHORT).show();

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

                        if (Titre.getText().length() == 0 && Detaile.getText().length() == 0) {
                            Toast.makeText(AjouterNote.this, "Ignorer ", Toast.LENGTH_SHORT).show();
                            AjouterNote.this.onSuperBackPressed();

                        } else {
                            long vc = (long) actionsNote.createNote(String.valueOf(Titre.getText()), String.valueOf(Detaile.getText()));
                            if (vc != -1) {
                                Toast.makeText(AjouterNote.this, "enregistrer ", Toast.LENGTH_SHORT).show();
                                // actionsNote.close();
                            }
                            AjouterNote.this.onSuperBackPressed();
                            //super.onBackPressed();
                        }
                    }
                })
                .setNegativeButton("Ignorer", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        AjouterNote.this.onSuperBackPressed();
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

}
