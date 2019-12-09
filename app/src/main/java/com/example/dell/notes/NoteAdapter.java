package com.example.dell.notes;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {
    Context context;
    private ArrayList<Note> mData;
    private LayoutInflater mInflater;
    ItemTouchHelper.SimpleCallback simpleCallback;

    public NoteAdapter(Context context, ArrayList<Note> Notes) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = Notes;
        this.context = context;
    }

    @NonNull
    @Override
    public NoteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.listedesnotes, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteAdapter.ViewHolder holder, int position) {
        final  Note note = mData.get(position);
       holder.Titre.setText(note.getTitre());
        holder.Date.setText(note.getDate_Creation_Note());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  Toast.makeText(context, "click student " + note.getId(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context,Detaile_Note.class);
                intent.putExtra("pos",note.getId());
                context.startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView Titre;
        TextView Date;
CardView cardView;

        ViewHolder(View itemView) {
            super(itemView);
            Titre = itemView.findViewById(R.id.Titre);
            Date = itemView.findViewById(R.id.Date);
            cardView=itemView.findViewById(R.id.ll_container);

        }


    }

}
