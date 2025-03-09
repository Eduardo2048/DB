package com.eab.petagram1.Modelo;


import android.content.ContentValues;
import android.content.Context;

import com.eab.petagram1.DB.Constantes_DB;
import com.eab.petagram1.DB.DB;
import com.eab.petagram1.R;

import java.util.ArrayList;

public class ConstructorMascotas {
    private static final int LIKE = 1;
    private Context context;
    public ConstructorMascotas(Context context) {
        this.context = context;
    }
    public ArrayList<Mascota> obtenerDatos() {
        DB db = new DB(context);
        if (db.obtenerNroMascotas() == 0)
        {
            insertarMascotas(db);
        }

        return  db.obtenerListaMascotas();
    }

    public void insertarMascota(DB db,String nombre,int imagen,int likes){
        ContentValues contentValues = new ContentValues();
        contentValues.put(Constantes_DB.TABLA_MASCOTAS_NOMBRE, nombre);
        contentValues.put(Constantes_DB.TABLA_MASCOTAS_IMAGEN, imagen);
        contentValues.put(Constantes_DB.TABLA_LIKES, likes);
        db.insertarMascota(contentValues);
    }
    public void insertarMascotas(DB db){
        insertarMascota(db,context.getResources().getString(R.string.perro1),R.drawable.perro1,0);
        insertarMascota(db,context.getResources().getString(R.string.perro2),R.drawable.perro2,0);
        insertarMascota(db,context.getResources().getString(R.string.perro3),R.drawable.perro3,0);
        insertarMascota(db,context.getResources().getString(R.string.perro5),R.drawable.perro5,0);
        insertarMascota(db,context.getResources().getString(R.string.perro6),R.drawable.perro6,0);
        insertarMascota(db,context.getResources().getString(R.string.perro4),R.drawable.perro4,0);
        insertarMascota(db,context.getResources().getString(R.string.perro7),R.drawable.perro7,0);
        insertarMascota(db,context.getResources().getString(R.string.perro8),R.drawable.perro8,0);

    }

    public void setLikeMascota(Mascota mascota){
        DB db = new DB(context);
        ContentValues contentValues = new ContentValues();
        contentValues.put(Constantes_DB.TABLA_LIKES, mascota.getI_Id());
        contentValues.put(Constantes_DB.TABLA_LIKES_CANTIDAD, LIKE);
        db.insertarLikeMascota(contentValues);
    }

    public int getLikesMascota(Mascota mascota){
        DB db = new DB(context);
        return db.obtenerLikesMascota(mascota);
    }



}
