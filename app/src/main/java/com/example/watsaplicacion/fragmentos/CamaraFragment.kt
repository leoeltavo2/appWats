package com.example.watsaplicacion.fragmentos

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.watsaplicacion.R

class CamaraFragment : Fragment() {

    var foto: Uri? = null
    private val REQUEST_CAMERA =1002

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_camara, container, false)

        return view
    }



    
}