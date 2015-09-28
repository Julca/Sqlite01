package Coneccion;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by alumno05 on 28/09/2015.
 */
public class sqlite extends SQLiteOpenHelper {
    String table_usuario="CREATE TABLE usuario(idusuario INTEGER PRIMARY KEY AUTOINCREMENT ,campo1 TEXT,campo2 TEXT)";
    String table_persona="CREATE TABLE persona(idpersopna INTEGER PRIMARY KEY AUTOINCREMENT ,nombre TEXT,apellido TEXT)";
    public sqlite(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(table_usuario);
        db.execSQL(table_persona);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS usuario");
        db.execSQL("DROP TABLE IF EXISTS persona");
        this.onCreate(db);

    }

}
