package anflo.formularioandroid.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import anflo.formularioandroid.R;
import anflo.formularioandroid.domain.PersonaRealm;
import anflo.formularioandroid.util.RecyclerItemClickListener;
import anflo.formularioandroid.view.adapter.RealmListaAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by tono on 26/09/17.
 */

public class ListaActivity extends AppCompatActivity {

    @BindView(R.id.rv_home) RecyclerView recyclerView;

    private Realm realm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        ButterKnife.bind(this);
        realm = Realm.getDefaultInstance();

        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {

            @Override
            public void onItemClick(View view, int position) {
                mostrarDetailActivity(position);
            }
        }));

        readRealm();
    }

    private void readRealm(){
        RealmResults<PersonaRealm> data;
        data = realm.where(PersonaRealm.class).findAllAsync();
        data.load();

        recyclerView.setAdapter(new RealmListaAdapter(this, data));
    }

    private void mostrarDetailActivity(int position){
        Intent i = new Intent(this, DetailActivity.class);
        i.putExtra("id", String.valueOf(position));
        startActivity(i);
    }
}
