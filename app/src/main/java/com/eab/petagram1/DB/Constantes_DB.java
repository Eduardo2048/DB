package com.eab.petagram1.DB;

public class Constantes_DB {


    public static final String DATABASE_NAME = "Mascotas";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLA_MASCOTAS           = "Info_Mascotas";
    public static final String TABLA_MASCOTAS_ID        = "Id";
    public static final String TABLA_MASCOTAS_NOMBRE    = "Nombre";
    public static final String TABLA_MASCOTAS_IMAGEN  = "Imagen";
    public static final String TABLA_MASCOTAS_LIKES     = "Likes";

    public static final String TABLA_LIKES = "Likes_Mascotas";
    public static final String TABLA_LIKES_ID = "Id";
    public static final String TABLA_LIKES_MASCOTA_ID = "Id_Mascota";
    public static final String TABLA_LIKES_CANTIDAD = "Nro_Likes";
}
