package com.example.watsaplicacion.adaptador

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.watsaplicacion.R

/*class AdaptadorMensajes(list: List<ListaMensaje>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val list: List<ListaMensaje>

    private val VIEW_TYPE_MESSAGE_SENT = 1
    private val VIEW_TYPE_MESSAGE_RECEIVED = 2

    override fun getItemViewType(position: Int): Int {
        var mensaje: ListaMensaje = list.get(position)
        if(mensaje.getTipoVista() === 0){
            Log.e("getItemViewType", "0")
            return VIEW_TYPE_MESSAGE_SENT
        }else{
            Log.e("getItemViewType", "1")
            return VIEW_TYPE_MESSAGE_RECEIVED
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
//        var view: View
        return if(viewType == VIEW_TYPE_MESSAGE_SENT){
            HolderEnviar(
                LayoutInflater.from(parent?.context).inflate(
                    R.layout.lista_mensajes_enviados,
                    parent,
                    false
                )
            )
        }
        else {
            HolderRecibir(
                LayoutInflater.from(parent?.context).inflate(
                    R.layout.lista_mensajes_recibidos,
                    parent,
                    false
                )
            )
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val mensaje: ListaMensaje = list[position]
        when (holder.itemViewType) {
            VIEW_TYPE_MESSAGE_SENT -> (holder as HolderEnviar).bind(mensaje)
            VIEW_TYPE_MESSAGE_RECEIVED -> (holder as HolderRecibir).bind(mensaje)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

//    class Holder(itemview: View) : RecyclerView.ViewHolder(itemview){
//
//
//
//    }
    private class HolderEnviar(itemview: View) : RecyclerView.ViewHolder(itemview){
        val mensaje: TextView
        val horario: TextView

        init {
            mensaje = itemview.findViewById(R.id.textViewmensajeEnviado)
            horario = itemview.findViewById(R.id.textViewFechaEnviado)
        }
   fun bind(messageModel: ListaMensaje) {
        mensaje.setText(messageModel.getMensaje())
        horario.setText(messageModel.getHorario())
    }
    }

    private class HolderRecibir(itemview: View) : RecyclerView.ViewHolder(itemview){
        val mensaje: TextView
        val horario: TextView

        init {
            mensaje = itemview.findViewById(R.id.textViewMensajeRecibido)
            horario = itemview.findViewById(R.id.textViewFechaRecibido)
        }
         fun bind(messageModel: ListaMensaje) {
            mensaje.setText(messageModel.getMensaje())
            horario.setText(messageModel.getHorario())
        }
    }



    init {
        this.list = list
    }


}*/


