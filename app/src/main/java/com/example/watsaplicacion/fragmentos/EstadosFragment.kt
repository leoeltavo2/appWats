package com.example.watsaplicacion.fragmentos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.watsaplicacion.R
import com.example.watsaplicacion.adaptador.AdaptadorEstados
import com.example.watsaplicacion.adaptador.listaEstados

class EstadosFragment : Fragment() {

    private var recyclerView: RecyclerView? = null
    private lateinit var adapter1: AdaptadorEstados

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
      val view = inflater.inflate(R.layout.fragment_estados, container, false)


        val list = ArrayList<listaEstados>()
        recyclerView = view.findViewById(R.id.recyclerEstados)
        recyclerView?.setLayoutManager(LinearLayoutManager(context))


        list.add(listaEstados("2","Dalila","Hace 50 minutos",R.drawable.e4,R.drawable.e4,"que maravilloso es el mundo"))
        list.add(listaEstados("4","Daniel","Ayer 12:05 a.m.",R.drawable.e5,R.drawable.e5,"amonos de fiesta"))
        list.add(listaEstados("3","ryan","Hoy 2:05 p.m.",R.drawable.e6,R.drawable.e6,"de fiesta con el buen dani"))
        list.add(listaEstados("5","Ernesto","Hace 5 minutos",R.drawable.e1,R.drawable.e1,"lo que no te mata te hace mas fuerte"))




//        recyclerView?.smoothScrollToPosition(list.size)
        adapter1 = AdaptadorEstados(list)
        recyclerView?.adapter = adapter1

    return view
    }


}