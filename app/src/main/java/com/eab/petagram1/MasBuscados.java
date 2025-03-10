package com.eab.petagram1;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.eab.petagram1.Adapters.MascotaAdapter;
import com.eab.petagram1.Modelo.Mascota;

import java.util.ArrayList;

public class MasBuscados extends AppCompatActivity   {
    private String TAG= "DBG_EAB";
    ArrayList<Mascota> mascotas;
    RecyclerView ListaMascotas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.mas_buscados);
       Toolbar miToolbar=(Toolbar) findViewById(R.id.miActionBarMB);
        setSupportActionBar(miToolbar);
        getSupportActionBar().setIcon(R.drawable.ic_pata);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setTitle(getString(R.string.app_name));


        setSupportActionBar(miToolbar);


        ListaMascotas=(RecyclerView) findViewById(R.id.rvMascotasMB);
        // GridLayoutManager   LLM= new GridLayoutManager(this,2);
        LinearLayoutManager LLM= new LinearLayoutManager(this);
        LLM.setOrientation(LinearLayoutManager.VERTICAL);
        ListaMascotas.setLayoutManager(LLM);
      //  InicializarListaMascotas();
       // InicializarAdaptador();


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mas_buscados), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


    }
/*
    private void InicializarAdaptador() {
        MascotaAdapter Adapter = new MascotaAdapter(mascotas,this);
        ListaMascotas.setAdapter(Adapter);
    }
  */  /*
    private void  InicializarListaMascotas()
    {
        mascotas=new ArrayList<Mascota>();
        mascotas.add(new Mascota(R.drawable.perro5,getResources().getString(R.string.perro5),6,R.drawable.ic_hueso1));
        mascotas.add(new Mascota(R.drawable.perro8,getResources().getString(R.string.perro8),5,R.drawable.ic_hueso1));
        mascotas.add(new Mascota(R.drawable.perro4,getResources().getString(R.string.perro4),4,R.drawable.ic_hueso1));
        mascotas.add(new Mascota(R.drawable.perro1,getResources().getString(R.string.perro1),3,R.drawable.ic_hueso1));
        mascotas.add(new Mascota(R.drawable.perro7,getResources().getString(R.string.perro7),2,R.drawable.ic_hueso1));


    }*/
}
