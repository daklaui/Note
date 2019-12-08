package com.example.dell.notes;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton floatingActionButton;
    RecyclerView listeDesNotes;
    ArrayList<Note> ListedesNotesArray;
    private ActionsNote actionsNote;
    ItemTouchHelper.SimpleCallback simpleCallback;
    NoteAdapter noteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        simpleCallback= new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
             final    Note nt = ListedesNotesArray.get(viewHolder.getAdapterPosition());

                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("voulez vous supprimer cette note ?")
                            .setCancelable(false)
                            .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    //finish();
                                    if(actionsNote.DeletNote(nt.getId())) {

                                   // Toast.makeText(getApplicationContext(), "Supprimer"+nt.getId(), Toast.LENGTH_LONG).show();
                                    ListedesNotesArray=actionsNote.getAllNotes();
                                    noteAdapter= new NoteAdapter(MainActivity.this, ListedesNotesArray);
                                    new ItemTouchHelper(simpleCallback).attachToRecyclerView(listeDesNotes);
                                    listeDesNotes.setAdapter(noteAdapter);
                                   // MainActivity.this.onSuperBackPressed();
                                    //super.onBackPressed();
                                }
                                }
                            })
                            .setNegativeButton("Non", new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialog, int id) {
                                    Toast.makeText(getApplicationContext(), "Annuler supp", Toast.LENGTH_LONG).show();
                                    ListedesNotesArray=actionsNote.getAllNotes();
                                    noteAdapter= new NoteAdapter(MainActivity.this, ListedesNotesArray);
                                    new ItemTouchHelper(simpleCallback).attachToRecyclerView(listeDesNotes);
                                    listeDesNotes.setAdapter(noteAdapter);
                                }
                            });

                    AlertDialog alert = builder.create();
                    alert.show();






            }
        };

        floatingActionButton=findViewById(R.id.AjouterNote);
       listeDesNotes=(RecyclerView)findViewById(R.id.listenotes);

         actionsNote=new ActionsNote();
         actionsNote.open(MainActivity.this);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(MainActivity.this);
        listeDesNotes.setLayoutManager(manager);
        ListedesNotesArray=actionsNote.getAllNotes();
        noteAdapter= new NoteAdapter(MainActivity.this, ListedesNotesArray);
        new ItemTouchHelper(simpleCallback).attachToRecyclerView(listeDesNotes);
        listeDesNotes.setAdapter(noteAdapter);



        floatingActionButton.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v) {
                Intent in = new Intent(MainActivity.this,AjouterNote.class);
startActivity(in);


            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        ListedesNotesArray=actionsNote.getAllNotes();
        noteAdapter= new NoteAdapter(MainActivity.this, ListedesNotesArray);
        new ItemTouchHelper(simpleCallback).attachToRecyclerView(listeDesNotes);
        listeDesNotes.setAdapter(noteAdapter);
    }
}

