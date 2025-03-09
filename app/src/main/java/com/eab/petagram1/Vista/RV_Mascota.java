package com.eab.petagram1.Vista;

import static androidx.core.app.PendingIntentCompat.getActivity;


import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;



import com.eab.petagram1.Adapters.MascotaAdapter;
import com.eab.petagram1.Modelo.Mascota;
import com.eab.petagram1.Presentador.IRecyclerViewPresentador;
import com.eab.petagram1.Presentador.RecycleViewPresentador;
import com.eab.petagram1.R;

import java.util.ArrayList;

public class RV_Mascota  implements IRV_Mascota {
    private ArrayList<Mascota> mascotas;
    private RecyclerView rvMascotas;


    private IRecyclerViewPresentador presentador;
    @Nullable
    public View onCreate(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.card_view_mascota, container, false);
        rvMascotas = (RecyclerView) v.findViewById(R.id.rvMascotas);

        presentador = new RecycleViewPresentador(this, v.getContext());
        return v;
    }



    @Override
    public void generarLLayoutVertical() {

            LinearLayoutManager llm = new LinearLayoutManager(getActivity());
            llm.setOrientation(LinearLayoutManager.VERTICAL);
            rvMascotas.setLayoutManager(llm);
    }

    @Override
    public MascotaAdapter creaAdaptador(ArrayList<Mascota> mascotas) {
            MascotaAdapter   miAdapter = new MascotaAdapter(mascotas, getActivity()  );
            return miAdapter;
    }

    @Override
    public void inicializarAdapterRV(MascotaAdapter MiAdapter) {
            rvMascotas.setAdapter(MiAdapter);
        }

    }

