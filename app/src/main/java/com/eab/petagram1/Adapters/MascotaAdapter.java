package com.eab.petagram1.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.eab.petagram1.Modelo.Mascota;
import com.eab.petagram1.R;

import java.util.ArrayList;

public class MascotaAdapter extends RecyclerView.Adapter<MascotaAdapter.MascotaViewHolder>{

    ArrayList<Mascota> mascotas;
    Activity activity;

    public MascotaAdapter(ArrayList<Mascota> mascotas, Activity activity ) {
        this.mascotas = mascotas;
        this.activity =activity;
    }

    //  Infla el LAYOUT y lo pasa al viewholder para que obtenga los datos del view.
    @NonNull
    @Override
    public MascotaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_mascota,parent,false);

        return new MascotaViewHolder(v);
    }


    // asocia un  elemento de la lista de la posicion X a un View.
    @Override
    public void onBindViewHolder(@NonNull MascotaViewHolder MascotaHolder, int position) {
    Mascota mascota=mascotas.get(position);
    MascotaHolder.imgFoto.setImageResource(mascota.getI_Imagen());
    MascotaHolder.tvLikes.setText(String.valueOf(mascota.getI_Likes()));
    MascotaHolder.tvNombre.setText(mascota.getS_Name());
    MascotaHolder.imgHueso.setImageResource(R.drawable.ic_hueso1);


    MascotaHolder.imgFoto.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
           // Toast.makeText(activity,mascota.getS_Name()+"  "+String.valueOf(mascota.getI_Likes()),Toast.LENGTH_SHORT).show();

        }
    });

    MascotaHolder.imgHueso.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
           // Toast.makeText(activity,String.valueOf(mascota.getI_Likes()),Toast.LENGTH_SHORT).show();
            mascota.setI_Likes(mascota.getI_Likes()+1);
            notifyItemChanged(position);
        }
    });

    }

    @Override
    public int getItemCount() {

        return mascotas.size();
    }

    public static class MascotaViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgFoto;
        private TextView  tvNombre;
        private  TextView tvLikes;
       private  ImageView imgHueso;

        public MascotaViewHolder(@NonNull View itemView) {
            super(itemView);
            imgFoto=(ImageView) itemView.findViewById(R.id.imgFoto);
            tvNombre = (TextView)  itemView.findViewById(R.id.tvNombre);
            tvLikes = (TextView)  itemView.findViewById(R.id.tvLikes);
            imgHueso=(ImageView)  itemView.findViewById(R.id.imghueso1);
        }
    }

}
