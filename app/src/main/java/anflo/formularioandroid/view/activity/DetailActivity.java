package anflo.formularioandroid.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import anflo.formularioandroid.R;
import anflo.formularioandroid.domain.PersonaRealm;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by tono on 26/09/17.
 */

public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.detail_nombre) TextView nombre;
    @BindView(R.id.detail_apellidos) TextView apellidos;
    @BindView(R.id.detail_direccion) TextView direccion;
    @BindView(R.id.detail_telefono) TextView telefono;
    @BindView(R.id.detail_email) TextView email;
    @BindView(R.id.detail_fecha_nacimiento) TextView fechaNacimiento;
    @BindView(R.id.detail_estado_civil) TextView estadoCivil;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ButterKnife.bind(this);

        getInfoRealm();
    }

    private void getInfoRealm(){

        Realm realm = Realm.getDefaultInstance();
        RealmResults<PersonaRealm> result = realm.where(PersonaRealm.class)
                .findAll();

        int position = Integer.valueOf(getIntent().getStringExtra("id"));

        String nombreString = "Nombre: " + result.get(position).getNombre();
        String apellidosString = "Apellidos: " + result.get(position).getApellidos();
        String direccionString = "Dirección: " + result.get(position).getDireccion();
        String telefonoString = "Telefono: " + result.get(position).getTelefono();
        String emailString = "Email: " + result.get(position).getEmail();
        String fechaNacimientoString = "Fecha de Nacimiento: " + result.get(position).getFechaNacimiento();
        String estadoCivilString = "Estado Civil: " + result.get(position).getEstadoCivil();

        nombre.setText(nombreString);
        apellidos.setText(apellidosString);
        direccion.setText(direccionString);
        telefono.setText(telefonoString);
        email.setText(emailString);
        fechaNacimiento.setText(fechaNacimientoString);
        estadoCivil.setText(estadoCivilString);
    }
}
