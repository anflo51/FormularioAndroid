package anflo.formularioandroid.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import anflo.formularioandroid.R;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tono on 26/09/17.
 */

public class ListaViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tv_nombre) TextView nombre;

    public ListaViewHolder(View itemView) {
        super(itemView);

        ButterKnife.bind(this, itemView);
    }
}
