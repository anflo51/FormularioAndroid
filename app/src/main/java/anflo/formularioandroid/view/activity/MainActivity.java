package anflo.formularioandroid.view.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import anflo.formularioandroid.R;
import anflo.formularioandroid.domain.PersonaRealm;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.widget.DatePicker;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements OnItemSelectedListener{

    private static Pattern pattern;
    private static Matcher matcher;
    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    @BindView(R.id.nombre) EditText nombre;
    @BindView(R.id.apellidos) EditText apellidos;
    @BindView(R.id.direccion) EditText direccion;
    @BindView(R.id.telefono) EditText telefono;
    @BindView(R.id.email) EditText email;
    @BindView(R.id.nombre_usuario) EditText nombreUsuario;
    @BindView(R.id.contrasena) EditText contrasena;
    @BindView(R.id.fecha) TextView fecha;
    @BindView(R.id.spinner_estado_civil) Spinner estadoCivil;
    @BindView(R.id.listado) Button listado;
    @BindView(R.id.guardar) Button guardar;

    private DatePickerDialog.OnDateSetListener onDateSetListener;

    private String[] listaEstadoCivil = {"Soltero", "Casado", "Separado", "Divorciado", "Viudo"};

    private Realm realm;

    private String itemEstadoCivil = "Soltero";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        realm = Realm.getDefaultInstance();

        estadoCivil.setOnItemSelectedListener(this);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listaEstadoCivil);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        estadoCivil.setAdapter(dataAdapter);

        fecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int anio = cal.get(Calendar.YEAR);
                int mes = cal.get(Calendar.MONTH);
                int dia = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        MainActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        onDateSetListener,
                        anio,mes,dia);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int anio, int mes, int dia) {
                mes = mes + 1;
                String date = mes + "/" + dia + "/" + anio  ;
                fecha.setText(date);
            }
        };

        listado.setOnClickListener(e-> mostrarListadoActivity());

        guardar.setOnClickListener(e-> guardarRealm());
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        itemEstadoCivil = adapterView.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void mostrarListadoActivity(){
        Intent i = new Intent(this, ListaActivity.class);
        startActivity(i);
    }

    private boolean validarCampos(){

        boolean valid = true;

        if (TextUtils.isEmpty(nombre.getText().toString())){
            nombre.setError("Requerido");
            valid = false;
        }

        if (TextUtils.isEmpty(apellidos.getText().toString())){
            apellidos.setError("Requerido");
            valid = false;
        }

        if (TextUtils.isEmpty(direccion.getText().toString())){
            direccion.setError("Requerido");
            valid = false;
        }

        if (TextUtils.isEmpty(telefono.getText().toString())){
            telefono.setError("Requerido");
            valid = false;
        }

        if (TextUtils.isEmpty(email.getText().toString())){
            email.setError("Requerido");
            valid = false;
        }

        if (TextUtils.isEmpty(nombreUsuario.getText().toString())){
            nombreUsuario.setError("Requerido");
            valid = false;
        }

        if (TextUtils.isEmpty(contrasena.getText().toString())){
            contrasena.setError("Requerido");
            valid = false;
        }

        if (!validateEmail(email.getText().toString())){
            Toast toast = Toast.makeText(getApplicationContext(), "Ingrese un correo valido", Toast.LENGTH_SHORT);
            toast.show();
            valid = false;
        }

        return valid;
    }

    private static boolean validateEmail(String email) {
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private void guardarRealm(){
        if (!validarCampos()) {
            return;
        }

        realm.beginTransaction();
        PersonaRealm personaRealm = realm.createObject(PersonaRealm.class);

        personaRealm.setNombre(nombre.getText().toString());
        personaRealm.setApellidos(apellidos.getText().toString());
        personaRealm.setDireccion(direccion.getText().toString());
        personaRealm.setTelefono(telefono.getText().toString());
        personaRealm.setEmail(email.getText().toString());
        personaRealm.setFechaNacimiento(fecha.getText().toString());
        personaRealm.setEstadoCivil(itemEstadoCivil);
        personaRealm.setNombreUsuario(nombreUsuario.getText().toString());
        personaRealm.setContrasena(contrasena.getText().toString());

        realm.commitTransaction();
        limpiar();
    }

    private void limpiar(){
        Toast toast = Toast.makeText(getApplicationContext(), "Información guardada", Toast.LENGTH_SHORT);
        toast.show();

        nombre.setText("");
        apellidos.setText("");
        direccion.setText("");
        telefono.setText("");
        email.setText("");
        nombreUsuario.setText("");
        contrasena.setText("");
    }
}
