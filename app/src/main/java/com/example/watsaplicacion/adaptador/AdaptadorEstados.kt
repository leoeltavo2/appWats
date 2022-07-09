package com.example.watsaplicacion.adaptador

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.watsaplicacion.R
import com.example.watsaplicacion.estados.MostrarEstadosActivity
import com.mikhaellopez.circularimageview.CircularImageView


class AdaptadorEstados(list: List<listaEstados>) : RecyclerView.Adapter<AdaptadorEstados.Holder>() {

    private val list: List<listaEstados>


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdaptadorEstados.Holder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.lista_estados, parent, false)
        return AdaptadorEstados.Holder(view)
    }

    override fun onBindViewHolder(holder: AdaptadorEstados.Holder, position: Int) {
        val estados: listaEstados = list[position]

        holder.hora.setText(estados.getFechaUserEstados())
        holder.usuario.setText(estados.getNombreUserEstados())
        Glide.with(holder.perfilEstadoV).load(estados.getPerfilURLEstados()).into(holder.perfilEstadoV)

        holder.itemView.setOnClickListener {
            var intent: Intent = Intent(holder.itemView.context, MostrarEstadosActivity::class.java)
            intent.putExtra("Estados", estados)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

       /* fun bind(estadoModel: listaEstados) {
            estadoVisto.setText(estadoModel.getNombreUserEstados())
            horaVisto.setText(estadoModel.getFechaUserEstados())
            estadoModel.getPerfilURLEstados()?.let { perfilEstadoV.setImageResource(it) }
        }*/

    class Holder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        val hora: TextView
        val usuario: TextView
        val perfilEstadoV: CircularImageView

        init {
            hora = itemview.findViewById(R.id.fechaEstadoMiEstado)
            perfilEstadoV = itemview.findViewById(R.id.perfilEstadoMiEstado)
            usuario = itemview.findViewById(R.id.usuarioEstadoMiEstado)
        }
    }


    init {
        this.list = list
    }


}