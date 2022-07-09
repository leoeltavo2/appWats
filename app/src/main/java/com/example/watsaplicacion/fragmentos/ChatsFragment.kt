package com.example.watsaplicacion.fragmentos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.watsaplicacion.R
import com.example.watsaplicacion.adaptador.*
import com.example.watsaplicacion.bases.BDControlador
import com.example.watsaplicacion.perfil.PerfilActivity
import kotlinx.android.synthetic.main.lista_chat.*
import java.util.*
import kotlin.collections.ArrayList


class ChatsFragment : Fragment() {

//    private var recyclerView: RecyclerView? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_chats, container, false)
/*
        val list = ArrayList<ListaChat>()
        recyclerView = view.findViewById(R.id.recyclerChats)
        recyclerView?.setLayoutManager(LinearLayoutManager(context))



        list.add(
            ListaChat(
                "11",
                "Leo",
                "Hola amigo",
                "Ayer",
                R.drawable.p2,
                "visto"
            )
        )
        list.add(
            ListaChat(
                "119",
                "Angelica",
                "Hellow",
                "hoy",
                R.drawable.p1,
                "noVisto"
            )
        )

        var adaptador = AdaptadorChats(list)
        recyclerView?.adapter = adaptador*/
        actualiza(view)

        return  view
    }

    private fun actualiza(view:View){
        val recycler = view.findViewById<RecyclerView>(R.id.recyclerChats)
        recycler.layoutManager= LinearLayoutManager(view.context)
        try {
            val db = BDControlador(view.context,resources.getString(R.string.whats),null,resources.getInteger(R.integer.whatsVersion))
            val contacto = db.muestraContacto()

            recycler.adapter= AdaptadorChats(view.context,R.layout.lista_chat,contacto)
        }catch (e: Exception){
            e.printStackTrace()
            Toast.makeText(view.context,"Error al encontrar contactos",Toast.LENGTH_LONG).show()
        }
    }


}

