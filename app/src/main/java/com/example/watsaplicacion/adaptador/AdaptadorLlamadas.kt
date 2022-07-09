package com.example.watsaplicacion.adaptador

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.watsaplicacion.R
import com.example.watsaplicacion.llamadas.InfoLlamadaActivity
import com.mikhaellopez.circularimageview.CircularImageView

/*
class AdaptadorLlamadas {
}*/
class AdaptadorLlamadas(list: List<ListaLlamada>) : RecyclerView.Adapter<AdaptadorLlamadas.Holder?>() {
    private var list: List<ListaLlamada>? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.lista_llamada, parent, false)
        return Holder(view)
    }


    override fun onBindViewHolder(holder: Holder, position: Int) {
        val listaLlamadas: ListaLlamada = list!![position]
        holder.name.setText(listaLlamadas.getNombreUserL())
        holder.dia.setText(listaLlamadas.getFechaUserL())

        if (listaLlamadas.getTipoLlamada().equals("Perdida")) {
            holder.tipoLlamada.setImageResource(R.drawable.llamada_recibida)
        } else if (listaLlamadas.getTipoLlamada().equals("Hecha")) {
            holder.tipoLlamada.setImageResource(R.drawable.llamada_hecha)
        }else if (listaLlamadas.getTipoLlamada().equals("Entrante")){
            holder.tipoLlamada.setImageResource(R.drawable.llamada_contestada)
        }
        Glide.with(holder.perfil).load(listaLlamadas.getPerfilURLL()).into(holder.perfil)

        holder.itemView.setOnClickListener {
            var intent: Intent = Intent(holder.itemView.context, InfoLlamadaActivity::class.java)
            intent.putExtra("Llamadas", listaLlamadas)
            holder.itemView.context.startActivity(intent)
        }
    }

     override fun getItemCount(): Int {
        return list!!.size
    }

    class Holder(itemview: View) : RecyclerView.ViewHolder(itemview) {
         val name: TextView
         val dia: TextView
         val perfil: CircularImageView
         val tipoLlamada: ImageView

        init {
            name = itemview.findViewById(R.id.usuarioLlamada)
            dia = itemview.findViewById(R.id.fechaLlamada)
            perfil = itemview.findViewById(R.id.perfilLlamada)
            tipoLlamada = itemview.findViewById(R.id.tipoLlamada)
        }
    }

    init {
        this.list = list
    }
}
