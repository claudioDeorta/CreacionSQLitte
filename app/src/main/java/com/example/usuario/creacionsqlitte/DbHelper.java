package com.example.usuario.creacionsqlitte;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by usuario on 21-abr-17.
 */

public class DbHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "contactos.sqlite";
    private static final int DB_SCHEM_VERSION = 1 ;
    

    public DbHelper(Context context) {
        super(context,DB_NAME, null, DB_SCHEM_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(DateBaseManager.CREATE_TABEL);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
