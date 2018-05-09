package com.bove.martin.cardview.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.bove.martin.cardview.R;
import com.bove.martin.cardview.adapters.MyAdapter;
import com.bove.martin.cardview.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Movie> movies;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // cargamos los datos
        movies = getAllMovies();

        // Instanciamos los elementos
        recyclerView = findViewById(R.id.recyclerView);
        // Set layout
        layoutManager = new LinearLayoutManager(this);
        //layoutManager = new GridLayoutManager(this, 2);
        //layoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);

        adapter = new MyAdapter(movies, R.layout.movie_item, new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Movie movie, int posicion) {
                deleteMovie(posicion);
                //Toast.makeText(MainActivity.this, nombre + " - " + posicion, Toast.LENGTH_SHORT).show();
            }
        });

        // Arranca el recyclerView
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        // Mejoramos el recyclerView
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    @Override // Creamos el menuBar
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override // Manejamos el evento click del menuBar
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuAdd:
                addMovie(0);
                //Toast.makeText(MainActivity.this, "Agregamos", Toast.LENGTH_SHORT).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }


    /*
    @Override // Inflamos el contextMenu
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater contextMenuInflater = getMenuInflater();
        contextMenuInflater.inflate(R.menu.context_menu, menu);
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        menu.setHeaderTitle(movies.get(info.position).getName());

        super.onCreateContextMenu(menu, v, menuInfo);
    }


    @Override // Manejamos los eventos del contexMenu
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.itemDelete:
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                deleteMovie(info.position);
                break;
        }

        return super.onContextItemSelected(item);
    }
    */

    private List<Movie> getAllMovies() {
        return new ArrayList<Movie>(){{
            add(new Movie("Popeye",R.drawable.popeye));
            add(new Movie("Ghost Busters",R.drawable.gost_buster));
            add(new Movie("Logan",R.drawable.logan));
            add(new Movie("Linterna Verde",R.drawable.linterna_verde));
        }};
    }

    private void addMovie(int pos) {
        movies.add(pos, new Movie("Nueva peli " + (++counter), R.drawable.placeholder));
        adapter.notifyItemInserted(pos);
        layoutManager.scrollToPosition(pos);
    }

    private void deleteMovie(int pos) {
        movies.remove(pos);
        adapter.notifyItemRemoved(pos);
    }

}
