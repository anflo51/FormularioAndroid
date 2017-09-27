package anflo.formularioandroid.util;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import io.realm.RealmObject;
import io.realm.RealmResults;

/**
 * Created by tono on 26/09/17.
 */

public abstract class RealmRecyclerViewAdapter<T extends RealmObject, VH extends RecyclerView.ViewHolder>
        extends RecyclerView.Adapter<VH> {

    protected RealmResults<T> realmResults;
    protected Context context;

    public RealmRecyclerViewAdapter(Context context, RealmResults<T> realmResults) {
        if (context == null) {
            throw new IllegalArgumentException("Context cannot be null");
        }
        if (realmResults == null) {
            throw new IllegalArgumentException("RealmResults cannot be null");
        }

        this.realmResults = realmResults;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return realmResults.size();
    }
}
