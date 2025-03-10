package com.eab.petagram1;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

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



public class MainActivity extends AppCompatActivity {
    private String TAG= "DBG_EAB";
    private MenuItem menuItem;
    static ArrayList<Mascota> mascotas;
    RecyclerView   ListaMascotas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        Toolbar miToolbar=(Toolbar) findViewById(R.id.miActionBar);


        setSupportActionBar(miToolbar);

       getSupportActionBar().setIcon(R.drawable.ic_pata);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
       getSupportActionBar().setTitle(getString(R.string.app_name));


       setSupportActionBar(miToolbar);



        ListaMascotas=(RecyclerView) findViewById(R.id.rvMascotas);
     //   GridLayoutManager   LLM= new GridLayoutManager(this,2);
        LinearLayoutManager  LLM= new LinearLayoutManager(this);
        LLM.setOrientation(LinearLayoutManager.VERTICAL);
        ListaMascotas.setLayoutManager(LLM);
       // InicializarListaMascotas();
       // InicializarAdaptador();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu,menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()== R.id.mn_favoritos)
        {
            CambiaAFavoritos();
        }
        else
        {
            Toast.makeText(this,"Seleccion "+String.valueOf(item.getItemId()),Toast.LENGTH_SHORT).show();

        }

        return super.onOptionsItemSelected(item);
    }

/*
    private void InicializarAdaptador() {
        MascotaAdapter Adapter = new MascotaAdapter(mascotas,this);
        ListaMascotas.setAdapter(Adapter);
    }

 */
    /*
    private void  InicializarListaMascotas()
    {
        mascotas=new ArrayList<Mascota>();
        mascotas.add(new Mascota(R.drawable.perro1,getResources().getString(R.string.perro1),3,R.drawable.ic_hueso1));
        mascotas.add(new Mascota(R.drawable.perro2,getResources().getString(R.string.perro2),1,R.drawable.ic_hueso1));
        mascotas.add(new Mascota(R.drawable.perro5,getResources().getString(R.string.perro5),6,R.drawable.ic_hueso1));
        mascotas.add(new Mascota(R.drawable.perro3,getResources().getString(R.string.perro3),1,R.drawable.ic_hueso1));
        mascotas.add(new Mascota(R.drawable.perro4,getResources().getString(R.string.perro4),4,R.drawable.ic_hueso1));
        mascotas.add(new Mascota(R.drawable.perro6,getResources().getString(R.string.perro6),3,R.drawable.ic_hueso1));
        mascotas.add(new Mascota(R.drawable.perro7,getResources().getString(R.string.perro7),2,R.drawable.ic_hueso1));
        mascotas.add(new Mascota(R.drawable.perro8,getResources().getString(R.string.perro8),5,R.drawable.ic_hueso1));


    }

     */
    private void CambiaAFavoritos(){
        Intent intent =new Intent(MainActivity.this, MasBuscados.class);
        startActivity(intent);

    }
}