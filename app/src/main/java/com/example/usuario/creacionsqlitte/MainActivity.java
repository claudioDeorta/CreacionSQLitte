package com.example.usuario.creacionsqlitte;

import android.database.Cursor;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private DateBaseManager manager ;
    Cursor cursor;
    ListView lista;
    SimpleCursorAdapter adapter ;
    private TextView tv ;
    private Button bt ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manager = new DateBaseManager(this);
        cursor = manager.cargarCursosrContactod();

      /*  manager.insertar("Kiara","58555");
        manager.insertar("Maru","652982");*/


        String [] from = new String [] {manager.CN_NAME,manager.CN_PHONE};
        int[] to = new int[] {android.R.id.text1,android.R.id.text2};

        lista = (ListView) findViewById(R.id.ListView);
        adapter = new SimpleCursorAdapter(this,android.R.layout.two_line_list_item,cursor,from,to,0);
        lista.setAdapter(adapter);

        tv = (TextView) findViewById(R.id.editText);
        bt= (Button) findViewById(R.id.button);
            bt.setOnClickListener(this);

    }

    //utiliza el este metodo para llenar un cursor on solo los valores que llama el metodo buscarcontacto
    //una ves los tiene cambia el cursor que llena el ListView
    //recordar repasar algunas partes que no me quedan claras https://www.youtube.com/watch?v=sD-pz-vKlnI 25:00

    @Override
    public void onClick(View v) {

        if (v.getId()==R.id.button){

            //esta opcion llama al metodo para evitar ANR
            new BuscarTask().execute();

            /* estas linias estaban antes pero oara evitar un posible ANR se re ubican en el metodo de abajo
            Cursor c =  manager.buscarContacto( tv.getText().toString());
            adapter.changeCursor(c);*/
        }
    }

    private class BuscarTask extends AsyncTask<Void,Void,Void>{


        //esto se ejecuta en el hilo prinsipal
        @Override
        protected void onPreExecute() {
            Toast.makeText(getApplicationContext(),"Buscando....",Toast.LENGTH_SHORT).show();


        }


        //se ejecuta en segundo plano
        @Override
        protected Void  doInBackground(Void... voids) {

            cursor =  manager.buscarContacto(tv.getText().toString());
            return null;
        }

        //despues de ejecutar la aplicacione en segundo plano se ejecuta esto
        @Override
        protected void onPostExecute(Void aVoid) {

            adapter.changeCursor(cursor);

            Toast.makeText(getApplicationContext(),"Finalizado .....",Toast.LENGTH_SHORT).show();

        }
    }




}
