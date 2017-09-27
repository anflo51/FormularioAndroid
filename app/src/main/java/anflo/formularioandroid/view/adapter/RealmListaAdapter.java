package anflo.formularioandroid.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import anflo.formularioandroid.R;
import anflo.formularioandroid.domain.PersonaRealm;
import anflo.formularioandroid.util.RealmRecyclerViewAdapter;
import io.realm.RealmResults;

/**
 * Created by tono on 26/09/17.
 */

public class RealmListaAdapter extends RealmRecyclerViewAdapter<PersonaRealm, RecyclerView.ViewHolder> {

    public RealmListaAdapter(Context context, RealmResults<PersonaRealm> realmResults) {
        super(context, realmResults);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_lista, parent, false);
        context = parent.getContext();
        return new ListaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        PersonaRealm personaRealm = realmResults.get(position);

        ListaViewHolder listaViewHolder = (ListaViewHolder) holder;
        listaViewHolder.nombre.setText(personaRealm.getNombre() +  " " + personaRealm.getApellidos());
    }
}
