package com.example.dell.notes;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
                if (Titre.getText().length() != 0 || Detaile.getText().length() != 0) {
                    long vc = (long) actionsNote.createNote(String.valueOf(Titre.getText()), String.valueOf(Detaile.getText()));
                    if (vc != -1) {

                        Toast.makeText(AjouterNote.this, "enregistrer id = " + vc, Toast.LENGTH_SHORT).show();
                        actionsNote.close();
                        Intent in = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(in);
                    }
                    else
                        Toast.makeText(AjouterNote.this, "Something wrong", Toast.LENGTH_SHORT).show();

                }
                }



        });

        Titre.addTextChangedListener(new EditTextListener());
        Detaile.addTextChangedListener(new EditTextListener2());


    }


    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Enregistrer vos modification ou annuler?")
                .setCancelable(false)
                .setPositiveButton("Enregistrer", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //finish();

                        if (Titre.getText().length() != 0 || Detaile.getText().length() != 0) {
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
    private class EditTextListener implements TextWatcher {


        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // Toast.makeText(AjouterNote.this,Titre.getText().toString(),Toast.LENGTH_LONG).show();
            if(Titre.getText().length()>0)
            {
                floatingActionButton.setVisibility(View.VISIBLE);

            }
            else
            {
                floatingActionButton.setVisibility(View.INVISIBLE);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }

    private class EditTextListener2 implements TextWatcher {


        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // Toast.makeText(AjouterNote.this,Titre.getText().toString(),Toast.LENGTH_LONG).show();
            if(Detaile.getText().length()>0)
            {
                floatingActionButton.setVisibility(View.VISIBLE);

            }
            else
            {
                floatingActionButton.setVisibility(View.INVISIBLE);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }

}
