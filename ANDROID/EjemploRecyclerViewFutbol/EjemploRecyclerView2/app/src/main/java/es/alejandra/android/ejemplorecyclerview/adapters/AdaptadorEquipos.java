package es.alejandra.android.ejemplorecyclerview.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import es.alejandra.android.ejemplorecyclerview.R;
import es.alejandra.android.ejemplorecyclerview.modelo.Equipo;

public class AdaptadorEquipos extends RecyclerView.Adapter<AdaptadorEquipos.EquiposViewHolder>{
    List<Equipo> listaEquipos;

    public AdaptadorEquipos(List<Equipo> listaEquipos) {
        this.listaEquipos = listaEquipos;
    }

    @NonNull
    @Override
    public EquiposViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout_recyclerview,parent,false);
        return new EquiposViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull EquiposViewHolder holder, int position) {
        Equipo equipo=listaEquipos.get(position);
        holder.bindEquipo(equipo);

    }

    @Override
    public int getItemCount() {
        return listaEquipos.size();
    }

    public static class EquiposViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivEscudo;
        private TextView tvNombre,tvPuntos;

        public EquiposViewHolder(@NonNull View itemView) {
            super(itemView);
            //asigno listener de clicks
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(view.getContext(), "Has pulsado el elemento en la posición: " + getAdapterPosition() , Toast.LENGTH_SHORT).show();
                }
            });
            ivEscudo=itemView.findViewById(R.id.ivEscudo);
            tvNombre=itemView.findViewById(R.id.tvNombreEquipo);
            tvPuntos=itemView.findViewById(R.id.tvPuntos);
        }

        public void bindEquipo(Equipo equipo){
            ivEscudo.setImageDrawable(equipo.getEscudo());
            tvPuntos.setText(String.valueOf(equipo.getPuntos()));
            tvNombre.setText(equipo.getNombre());
        }
    }
}
