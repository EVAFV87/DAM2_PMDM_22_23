package es.alejandra.android.ejemplorecyclerview.adapters;

import android.content.Intent;
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
import es.alejandra.android.ejemplorecyclerview.activities.VerEquipoActivity;
import es.alejandra.android.ejemplorecyclerview.modelo.Equipo;

public class AdaptadorEquipos extends RecyclerView.Adapter<AdaptadorEquipos.EquiposViewHolder>{
    // DATOS
    List<Equipo> listaEquipos;
    // INTERFACE escuchador de OnItemClicks
    private OnItemClickListener mListener;

    public AdaptadorEquipos(List<Equipo> listaEquipos) {
        this.listaEquipos = listaEquipos;
    }

    /** Método que asigna el escuchador listener a mi escuchador de OnItemClicks eventos
     *
     * @param listener el listener que se debe asignar a nuestro escuchador
     */
    public void setOnItemClickListener(OnItemClickListener listener){
        this.mListener=listener;
    }

    @NonNull
    @Override
    public EquiposViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout_recyclerview,parent,false);
        return new EquiposViewHolder(itemView,mListener);
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

        public EquiposViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);
            //asigno listener de clicks
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // llamo a mi listener pasándole el equipo sobre el que se hizo click y su posición
                    if(listener!=null){
                        listener.onItemClick(getAdapterPosition());
                    }
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

        /** Método que lanza la activity que muestra los datos del equipo de fútbol cuya posición
         *  se indica
         *
         * @param posicion posición de ese equipo de fútbol en la lista.
         */
        private void lanzarActivityVerEquipo(int posicion){
            // CUIDADO: this aquí hace referencia a la clase ViewHolder y no es un Context
            // Pero las VIEW si tienen Context.
            Intent i = new Intent(this.itemView.getContext(), VerEquipoActivity.class);
            i.putExtra(VerEquipoActivity.EXTRA_POSICION_ARRAY,posicion);
            this.itemView.getContext().startActivity(i);

        }
    }


    /** Interface que hará de escuchador de eventos OnItemClick sobre cualquier elemento de la
     *  RecyclerView
     */
    public interface OnItemClickListener{
        // el parámetro será la posición que ocupa en la lista el equipo pulsado
        void onItemClick(int posicion);
    }
}
