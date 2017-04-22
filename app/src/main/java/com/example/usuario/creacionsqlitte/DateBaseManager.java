package com.example.usuario.creacionsqlitte;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by claudio on 21/4/2017.
 */

public class DateBaseManager {
    //nombre de la tabla
    public static final String TABLE_NAME = "contactos";

    //nombre de las columnas
    public static final String CN_ID = "_id";
    public static final String CN_NAME = "nombre";
    public static final String CN_PHONE = "telefono";

    //sentencia SQL

    public static final String CREATE_TABEL = "create table " + TABLE_NAME + " ("
            + CN_ID + " integer primary key autoincrement,"
            + CN_NAME + " text not null,"
            + CN_PHONE + " text);";

    private DbHelper helper;
    private SQLiteDatabase db ;

    /*Metodo para crear el objeto SQLite y el helper opcionalmente se puede crear mtodos para poder crear el objeto SQlite en modo
    escritura o en modo lectura*/

    public DateBaseManager(Context context ) {

        helper = new DbHelper(context);
        db = helper.getWritableDatabase();

        // dos metodos primero el android
        /* se puede manejar la insercion como hisimos en java creando un clase con los parmetros y pasando la clase luego
         en este ejemplo no la usan */

    }

    //por un tema de reutilizacion se crea la clase a continuacion para generar los ContentValues

    private ContentValues generarContentValues (String nombre , String telefono ){

        ContentValues valores = new ContentValues();
        valores.put(CN_NAME,nombre);
        valores.put(CN_PHONE,telefono);

        return valores ;

    }
    private void Lectura (Context context) {
        db = helper.getReadableDatabase();
    }

    // dos metodos primero el android
        /* se puede manejar la insercion como hisimos en java creando un clase con los parmetros y pasando la clase luego
         en este ejemplo no la usan */

    public void insertar (String nombre , String telefono){
        db.insert(TABLE_NAME,null,generarContentValues(nombre, telefono));
        // si debuelve un -1 ubo un problema
    }    //segundo ejemplo
    public void insertar2 (String nombre , String telefono ){
        db.execSQL("inserte into " +TABLE_NAME+ " values (null,'"+nombre + "'" + telefono + ")");
    }
    public void eliminar (String nombre){

        db.delete(TABLE_NAME,CN_NAME+"=?" ,new String [] {nombre});
    }

    public void eliminarMultiple (String nom1 , String nom2){

        db.delete(TABLE_NAME,CN_NAME + "IN (?,?)", new String []{nom1,nom2});
    }

    public void modificarTelefono (String nombre ,String nuevoTelefono ){

        db.update(TABLE_NAME,generarContentValues(nombre,nuevoTelefono),CN_NAME+"=?" ,new String [] {nombre});

    }

    //debulve todo los reguistro de la base de datos
    public Cursor cargarCursosrContactod (){

        String [] columnas = new String [] {CN_ID,CN_NAME,CN_PHONE};



      return   db.query(TABLE_NAME,columnas,null,null,null,null,null);
    }

    public Cursor buscarContacto (String nombre){

        String [] columnas = new String [] {CN_ID,CN_NAME,CN_PHONE};

        try{
            Thread.sleep(7000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return   db.query(TABLE_NAME,columnas,CN_NAME + "=?",new String [] {nombre},null,null,null);
    }




}
