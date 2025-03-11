package com.eab.petagram1.Vista;

import com.eab.petagram1.Adapters.MascotaAdapter;
import com.eab.petagram1.Modelo.Mascota;

import java.util.ArrayList;

public interface IRV_Mascota {

    public void generarLLayoutVertical();

    public MascotaAdapter creaAdaptador(ArrayList<Mascota> mascota);

    public void inicializarAdapterRV(MascotaAdapter adapter);
}
