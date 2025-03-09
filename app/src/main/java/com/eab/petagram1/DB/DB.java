package com.eab.petagram1.DB;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import com.eab.petagram1.Modelo.Mascota;

import java.util.ArrayList;


public class DB extends SQLiteOpenHelper {

    private Context context;

    public DB(Context context) {
        super(context, Constantes_DB.DATABASE_NAME, null, Constantes_DB.DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryCrearTablaMascota = "CREATE TABLE " + Constantes_DB.TABLA_MASCOTAS + "(" +
                Constantes_DB.TABLA_MASCOTAS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Constantes_DB.TABLA_MASCOTAS_NOMBRE + " TEXT, " +
                Constantes_DB.TABLA_MASCOTAS_IMAGEN + " INTEGER, " +
                Constantes_DB.TABLA_MASCOTAS_LIKES + " INTEGER, " +
                ")";
        String queryCrearTablaLikes = "CREATE TABLE " + Constantes_DB.TABLA_LIKES + "(" +
                Constantes_DB.TABLA_LIKES_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Constantes_DB.TABLA_LIKES_CANTIDAD + " INTEGER, " +
                "FOREIGN KEY (" + Constantes_DB.TABLA_LIKES_MASCOTA_ID + ") " +
                "REFERENCES " + Constantes_DB.TABLA_MASCOTAS + "(" + Constantes_DB.TABLA_MASCOTAS_ID + ")" +
                ")";

        db.execSQL(queryCrearTablaMascota);
        db.execSQL(queryCrearTablaLikes);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST " + Constantes_DB.TABLA_MASCOTAS);
        db.execSQL("DROP TABLE IF EXIST " + Constantes_DB.TABLA_LIKES);
        onCreate(db);
    }

    public ArrayList<Mascota> obtenerListaMascotas() {
        ArrayList<Mascota> mascotas = new ArrayList<>();

        String query = "SELECT * FROM " + Constantes_DB.TABLA_MASCOTAS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);

        while (registros.moveToNext()){
            Mascota mascotaActual    = new Mascota();
            mascotaActual.setI_Id(registros.getInt(0));
            mascotaActual.setS_Name(registros.getString(1));
            mascotaActual.setI_Likes(registros.getInt(3));
            mascotaActual.setI_Imagen(registros.getInt(2));

            String queryLikes = "SELECT COUNT("+ Constantes_DB.TABLA_LIKES+") as Likes " +
                    " FROM " + Constantes_DB.TABLA_LIKES_MASCOTA_ID +
                    " WHERE " + Constantes_DB.TABLA_LIKES_MASCOTA_ID + "=" + mascotaActual.getI_Id();

            Cursor registrosLikes = db.rawQuery(queryLikes, null);
            if (registrosLikes.moveToNext()){
                mascotaActual.setI_Likes(registrosLikes.getInt(0));
            }else {
                mascotaActual.setI_Likes(0);
            }

            mascotas.add(mascotaActual);

        }

        db.close();

        return mascotas;
    }

    public void insertarMascota(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(Constantes_DB.TABLA_MASCOTAS,null, contentValues);
        db.close();
    }

    public void insertarLikeMascota(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(Constantes_DB.TABLA_LIKES, null, contentValues);
        db.close();
    }


    public int obtenerLikesMascota(Mascota mascota){
        int likes = 0;

        String query = "SELECT COUNT("+ Constantes_DB.TABLA_LIKES_CANTIDAD+")" +
                " FROM " + Constantes_DB.TABLA_LIKES +
                " WHERE " + Constantes_DB.TABLA_LIKES_MASCOTA_ID + "="+mascota.getI_Id();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);

        if (registros.moveToNext()){
            likes = registros.getInt(0);
        }

        db.close();

        return likes;
    }
}