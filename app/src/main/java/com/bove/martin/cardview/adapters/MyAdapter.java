package com.bove.martin.cardview.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.bove.martin.cardview.R;
import com.bove.martin.cardview.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Martín Bove on 13/04/2018.
 * E-mail: mbove77@gmail.com
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>  {

    private List<Movie> movies;
    private int layout;
    private OnItemClickListener listener;


    public MyAdapter(List<Movie> movie, int layout, OnItemClickListener listener) {
        this.movies = movie;
        this.layout = layout;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Inflamos la vista
        View view = LayoutInflater.from(parent.getContext()).inflate(layout,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(movies.get(position), listener);
    }

    @Override
    public int getItemCount() { return movies.size();}


    public  class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {
        private TextView textViewName;
        private ImageView imageViewPoster;

        public ViewHolder(View itemView) {
            super(itemView);
            this.textViewName = itemView.findViewById(R.id.textViewName);
            this.imageViewPoster = itemView.findViewById(R.id.imageViewPoster);
            itemView.setOnCreateContextMenuListener(this);
        }

        // Aca es donde se cargan las datos reales
        public void bind(final Movie movie, final OnItemClickListener listener) {
            this.textViewName.setText(movie.getName());
            Picasso.get().load(movie.getPoster()).fit().centerCrop().into(this.imageViewPoster);
            //this.imageViewPoster.setImageResource(movie.getPoster());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(movie,getAdapterPosition());
                }
            });
        }

        @Override // Creamos el contex menu para cada vista
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            MenuInflater menuInflate = new MenuInflater(v.getContext());
            menuInflate.inflate(R.menu.context_menu, menu);

            Movie movieSel = movies.get(getAdapterPosition());
            menu.setHeaderTitle(movieSel.getName());

            // Le añadimos el click listener a cada elemento del menu
            for (int i=0; i < menu.size(); i++) {
                menu.getItem(i).setOnMenuItemClickListener(this);
            }
        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.itemDelete :

                    movies.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                    break;
            }
            return false;
        }
    }


    public interface OnItemClickListener {
        void onItemClick(Movie movie, int posicion);
    }
}


