package com.eab.petagram1.DB;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;


import com.eab.petagram1.Mascota;
import com.eab.petagram1.R;

import java.util.ArrayList;


public class DB extends SQLiteOpenHelper {

    private Context context;
    private ArrayList<Mascota> Mascotas;

    public DB(Context context) {
        super(context, Constantes_DB.DATABASE_NAME, null, Constantes_DB.DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryCrearTablaMascota = "CREATE TABLE " + Constantes_DB.TABLA_MASCOTAS + " (" +
                Constantes_DB.TABLA_MASCOTAS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Constantes_DB.TABLA_MASCOTAS_NOMBRE + " TEXT, " +
                Constantes_DB.TABLA_MASCOTAS_IMAGEN + " INTEGER, " +
                Constantes_DB.TABLA_MASCOTAS_LIKES + " INTEGER " +
                ")";
        String queryCrearTablaLikes = "CREATE TABLE " + Constantes_DB.TABLA_LIKES + " (" +
                Constantes_DB.TABLA_LIKES_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Constantes_DB.TABLA_LIKES_CANTIDAD + " INTEGER, " +
                Constantes_DB.TABLA_LIKES_MASCOTA_ID+ " INTEGER  "+
//                "FOREIGN KEY (" + Constantes_DB.TABLA_LIKES_MASCOTA_ID + ") " +
//                "REFERENCES " + Constantes_DB.TABLA_MASCOTAS + "(" + Constantes_DB.TABLA_MASCOTAS_ID + ")" +
                ")";

        db.execSQL(queryCrearTablaMascota);
        Toast.makeText(context, "Creada Tabla "+Constantes_DB.TABLA_MASCOTAS, Toast.LENGTH_SHORT).show();

        db.execSQL(queryCrearTablaLikes);
        Toast.makeText(context,"Creada tabla "+Constantes_DB.TABLA_LIKES,Toast.LENGTH_SHORT).show();
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

            String queryLikes = "SELECT COUNT("+ Constantes_DB.TABLA_LIKES_CANTIDAD+" ) as Likes " +
                    " FROM " + Constantes_DB.TABLA_LIKES +
                    " WHERE " + Constantes_DB.TABLA_LIKES_MASCOTA_ID + "=" + mascotaActual.getI_Id();

           /* Cursor registrosLikes = db.rawQuery(queryLikes, null);
            if (registrosLikes.moveToNext()){
                mascotaActual.setI_Likes(registrosLikes.getInt(0));
            }else {
                mascotaActual.setI_Likes(0);
            }
            */
            mascotas.add(mascotaActual);

        }

        db.close();

        return mascotas;
    }



    public ArrayList<Mascota> obtenerListaMasBuscadas() {
        ArrayList<Mascota> mascotas = new ArrayList<>();

        String query = "SELECT * FROM " + Constantes_DB.TABLA_MASCOTAS +  " ORDER BY " + Constantes_DB.TABLA_MASCOTAS_LIKES+" DESC";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);
        int Cantidad=0;
        while (registros.moveToNext() && Cantidad ++ <5){
            Mascota mascotaActual    = new Mascota();
            mascotaActual.setI_Id(registros.getInt(0));
            mascotaActual.setS_Name(registros.getString(1));
            mascotaActual.setI_Likes(registros.getInt(3));
            mascotaActual.setI_Imagen(registros.getInt(2));
            Toast.makeText(context, mascotaActual.getS_Name() +"  "+String.valueOf( mascotaActual.getI_Likes()), Toast.LENGTH_SHORT).show();

           /* String queryLikes = "SELECT COUNT("+ Constantes_DB.TABLA_LIKES_CANTIDAD+" ) as Likes " +
                    " FROM " + Constantes_DB.TABLA_LIKES +
                    " WHERE " + Constantes_DB.TABLA_LIKES_MASCOTA_ID + "=" + mascotaActual.getI_Id();

            Cursor registrosLikes = db.rawQuery(queryLikes, null);
            if (registrosLikes.moveToNext()){
                mascotaActual.setI_Likes(registrosLikes.getInt(0));
            }else {
                mascotaActual.setI_Likes(0);
            }
            */
            mascotas.add(mascotaActual);

        }

        db.close();

        return mascotas;
    }
    private void insertarMascota(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(Constantes_DB.TABLA_MASCOTAS,null, contentValues);
        db.close();
    }

    public void insertarLikeMascota(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(Constantes_DB.TABLA_LIKES, null, contentValues);
        db.close();
    }

    public void actualizarLikeMascota(Mascota mascota){
        String query = "UPDATE "+ Constantes_DB.TABLA_MASCOTAS + " SET  "+
                Constantes_DB.TABLA_MASCOTAS_LIKES +" = "+ String.valueOf(mascota.getI_Likes())+
                " WHERE "+ Constantes_DB.TABLA_MASCOTAS_ID + " = "+ String.valueOf(mascota.getI_Id());
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(query);
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


    private int obtenerNroMascotas(){
        int nroMascotas = 0;

        String query = "SELECT COUNT("+ Constantes_DB.TABLA_MASCOTAS_ID+")" +
                " FROM " + Constantes_DB.TABLA_MASCOTAS ;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);

        if (registros.moveToNext()){
            nroMascotas = registros.getInt(0);
        }

        db.close();

        return nroMascotas;
    }


    public ArrayList<Mascota> InicializarListaMascotas()
    {

        if( obtenerNroMascotas() == 0)
        {
            insertarMascotas();
        }
        return  obtenerListaMascotas();


    }
   private void insertarMascota(String nombre,int imagen,int likes){
        ContentValues contentValues = new ContentValues();
        contentValues.put(Constantes_DB.TABLA_MASCOTAS_NOMBRE, nombre);
        contentValues.put(Constantes_DB.TABLA_MASCOTAS_IMAGEN, imagen);
        contentValues.put(Constantes_DB.TABLA_MASCOTAS_LIKES, likes);
        this.insertarMascota(contentValues);
    }
    private void insertarMascotas(){
        insertarMascota(context.getResources().getString(R.string.perro1),R.drawable.perro1,0);
        insertarMascota(context.getResources().getString(R.string.perro2),R.drawable.perro2,0);
        insertarMascota(context.getResources().getString(R.string.perro3),R.drawable.perro3,0);
        insertarMascota(context.getResources().getString(R.string.perro5),R.drawable.perro5,0);
        insertarMascota(context.getResources().getString(R.string.perro6),R.drawable.perro6,0);
        insertarMascota(context.getResources().getString(R.string.perro4),R.drawable.perro4,0);
        insertarMascota(context.getResources().getString(R.string.perro7),R.drawable.perro7,0);
        insertarMascota(context.getResources().getString(R.string.perro8),R.drawable.perro8,0);

    }

}