package com.eab.petagram1.Presentador;
import android.content.Context;

import androidx.core.app.ActivityCompat;

import com.eab.petagram1.Modelo.ConstructorMascotas;
import com.eab.petagram1.Modelo.Mascota;
import com.eab.petagram1.Vista.IRV_Mascota;

import java.util.ArrayList;


public class RecycleViewPresentador implements IRecyclerViewPresentador{
    private Context context;
    private ConstructorMascotas constructorMascotas;
    private ArrayList<Mascota> mascotas;

    private IRV_Mascota iRecyclerViewMascotas;



    public RecycleViewPresentador(IRV_Mascota irv_mascota,Context context) {
        this.context = context;
        this.iRecyclerViewMascotas = irv_mascota;
        obtenerMascotas();
    }

    @Override
    public void obtenerMascotas() {
        constructorMascotas = new ConstructorMascotas(context);
        mascotas = constructorMascotas.obtenerDatos();
        mostrarMascotasRV();
    }

    @Override
    public void mostrarMascotasRV() {

        //ActivityCompat activityCompat = ActivityCompat();
        iRecyclerViewMascotas.inicializarAdapterRV(iRecyclerViewMascotas.creaAdaptador(mascotas));
        iRecyclerViewMascotas.generarLLayoutVertical();
    }
}



