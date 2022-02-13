package com.example.ejercicio3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText nomb,apellido,edad,email,direc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nomb=(EditText) findViewById(R.id.txtNombre);
        apellido=(EditText) findViewById(R.id.txtApellido);
        edad=(EditText) findViewById(R.id.txtEdad);
        email=(EditText) findViewById(R.id.txtCorreoE);
        direc=(EditText) findViewById(R.id.txtDir);
    }
    //guardar los datos
    public void guardar(View view){
        AdminSQLite admin = new AdminSQLite(this, "personas", null, 1);
        SQLiteDatabase bdPersonas = admin.getReadableDatabase();

        String nombre = nomb.getText().toString();
        String ape = apellido.getText().toString();
        String ed = edad.getText().toString();
        String corre = email.getText().toString();
        String dir = direc.getText().toString();

        //validar que datos no esten vacios
        if(!nombre.isEmpty() && !ape.isEmpty() && !ed.isEmpty() && !corre.isEmpty() && !dir.isEmpty()){

           /* Cursor cursor = bdPersonas.rawQuery("SELECT * FROM personas WHERE nombre='"+nombre+"'", null);

            if(!cursor.moveToFirst()){
                Toast.makeText(this, "Dato ya Existe!", Toast.LENGTH_SHORT).show();
                bdPersonas.close();

            }else{*/
                //guardar el dato
                ContentValues registro = new ContentValues();

                registro.put("nombre", nombre);
                registro.put("apellido", ape);
                registro.put("edad", ed);
                registro.put("correo", corre);
                registro.put("direccion", dir);

                bdPersonas.insert("personas", null, registro);

                bdPersonas.close();

                Toast.makeText(this, "LOS DATOS SE GUARDARON CORRECTAMENTE", Toast.LENGTH_SHORT).show();

                nomb.setText("");
                apellido.setText("");
                edad.setText("");
                email.setText("");
                direc.setText("");
            }
        }
       /* else{
            Toast.makeText(this,"INGRESE TODOS LOS DATOS", Toast.LENGTH_LONG).show();
        }*/
    }
