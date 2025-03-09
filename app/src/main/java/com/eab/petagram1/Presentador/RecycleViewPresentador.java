package com.eab.petagram1.Presentador;
import android.content.Context;

import com.eab.petagram1.Modelo.Mascota;

import java.util.ArrayList;


public class RecycleViewPresentador implements IRecyclerViewPresentador{
    private Context context;
    private ConstructorContactos constructorContactos;
    private ArrayList<Mascota> contactos;







    @Override
    public void obtenerMascotas() {

    }

    @Override
    public void mostrarMascotasRV() {

        iRecyclerViewFragmentView.inicializarAdaptadorRV(iRecyclerViewFragmentView.crearAdaptador(contactos));
        iRecyclerViewFragmentView.generarLinearLayoutVertical();
    }
}




/**
 * Created by anahisalgado on 21/04/16.
 */
public class RecyclerViewFragmentPresenter implements IRecylerViewFragmentPresenter {

    private IRecyclerViewFragmentView iRecyclerViewFragmentView;
    private Context context;
    private ConstructorContactos constructorContactos;
    private ArrayList<Contacto> contactos;

    public  RecyclerViewFragmentPresenter(IRecyclerViewFragmentView iRecyclerViewFragmentView, Context context) {
        this.iRecyclerViewFragmentView = iRecyclerViewFragmentView;
        this.context = context;
        obtenerContactos();
    }

    @Override
    public void obtenerContactos() {
        constructorContactos = new ConstructorContactos(context);
        contactos = constructorContactos.obtenerDatos();
        mostrarContactosRV();
    }


    @Override
    public void mostrarContactosRV() {

    }
}
