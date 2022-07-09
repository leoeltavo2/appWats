package com.example.watsaplicacion.fragmentos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.watsaplicacion.R
import com.example.watsaplicacion.adaptador.*
import com.mikhaellopez.circularimageview.CircularImageView
import java.util.*


class LlamadasFragment : Fragment() {


    private var recyclerView: RecyclerView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       val view = inflater.inflate(R.layout.fragment_llamadas, container, false)

        val list = ArrayList<ListaLlamada>()
        recyclerView = view.findViewById(R.id.recyclerLlamadas)
        recyclerView?.setLayoutManager(LinearLayoutManager(context))


        list.add(
            ListaLlamada("11","Alna","9 de diciembre 6:21 p.m.",R.drawable.p3,"Perdida"
            )
        )
        list.add(
            ListaLlamada("22","Diego","17 diciembre 9:50 a.m.",R.drawable.p4,"Hecha"
            )
        )
        list.add(
            ListaLlamada("2","Maria","10 enero 1:21 p.m.",R.drawable.p6,"Entrante"
            )
        )

        var adaptador = AdaptadorLlamadas(list)
        recyclerView?.adapter = adaptador

        return view
    }


}