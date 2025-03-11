package com.eab.petagram1;

import android.app.ActionBar;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.eab.petagram1.DB.Constantes_DB;
import com.eab.petagram1.DB.DB;

import java.util.ArrayList;



public class MainActivity extends AppCompatActivity {
    private String TAG= "DBG_EAB";
    private MenuItem menuItem;
    static ArrayList<Mascota> mascotas;
    RecyclerView   ListaMascotas;

    static DB DataBase;

    private Context context;

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

        context = this.getApplicationContext();
        DataBase=new DB(this.getApplicationContext());

        ListaMascotas=(RecyclerView) findViewById(R.id.rvMascotas);
     //   GridLayoutManager   LLM= new GridLayoutManager(this,2);
        LinearLayoutManager  LLM= new LinearLayoutManager(this);
        LLM.setOrientation(LinearLayoutManager.VERTICAL);
        ListaMascotas.setLayoutManager(LLM);

        mascotas = DataBase.InicializarListaMascotas();
        InicializarAdaptador();

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


    private void InicializarAdaptador() {
        MascotaAdapter Adapter = new MascotaAdapter(mascotas,this,DataBase);
        ListaMascotas.setAdapter(Adapter);
    }


/*
    private void  InicializarListaMascotas()
    {
        mascotas=new ArrayList<Mascota>();
        if( DataBase.obtenerNroMascotas() == 0)
        {
            insertarMascotas(DataBase);
        }
        mascotas=DataBase.obtenerListaMascotas();


    }
    public void insertarMascota(DB db,String nombre,int imagen,int likes){
        ContentValues contentValues = new ContentValues();
        contentValues.put(Constantes_DB.TABLA_MASCOTAS_NOMBRE, nombre);
        contentValues.put(Constantes_DB.TABLA_MASCOTAS_IMAGEN, imagen);
        contentValues.put(Constantes_DB.TABLA_MASCOTAS_LIKES, likes);
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

 */
    private void CambiaAFavoritos(){
        Intent intent =new Intent(MainActivity.this, MasBuscados.class);
        startActivity(intent);

    }
}