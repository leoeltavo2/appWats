package com.example.watsaplicacion.llamadas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.watsaplicacion.R
import com.example.watsaplicacion.adaptador.ListaChat
import com.example.watsaplicacion.adaptador.ListaLlamada
import com.mikhaellopez.circularimageview.CircularImageView
import kotlinx.android.synthetic.main.activity_info_llamada.*
import kotlinx.android.synthetic.main.activity_mensajes.*
import org.w3c.dom.Text

class InfoLlamadaActivity : AppCompatActivity() {

    private lateinit var imagenUsuarioL: CircularImageView
    private lateinit var tipoVistaL: ImageView
    private lateinit var nombreUsuarioL: TextView
    private lateinit var nombreTipoLlamada: TextView
    private lateinit var fechaLlamada: TextView

    private lateinit var listaL:ListaLlamada

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_llamada)
        setSupportActionBar(toolbarLlamadas)
        atrasLlamadas.setOnClickListener { onBackPressed() }

        initViews()
        initValues()
    }



    fun initViews(){
        imagenUsuarioL = findViewById(R.id.imgInfoLlamada)
        nombreUsuarioL = findViewById(R.id.nombreUsuarioLlamada)
        tipoVistaL = findViewById(R.id.tipoInfoLlamada)
        nombreTipoLlamada = findViewById(R.id.textViewTipoInfoLlamada)
        fechaLlamada = findViewById(R.id.fechaInfoLlamada)
    }

    fun initValues(){

        listaL = (intent.extras?.getSerializable("Llamadas") as ListaLlamada?)!!
        nombreUsuarioL.setText(listaL.getNombreUserL())
        listaL.getPerfilURLL()?.let { imagenUsuarioL.setImageResource(it) }
        fechaLlamada.setText(listaL.getFechaUserL())
        nombreTipoLlamada.setText(listaL.getTipoLlamada())

        if (listaL.getTipoLlamada()=="Perdida"){
            tipoVistaL.setImageDrawable(getDrawable(R.drawable.llamada_recibida))
        }else if (listaL.getTipoLlamada()=="Hecha"){
            tipoVistaL.setImageDrawable(getDrawable(R.drawable.llamada_hecha))
        }else if(listaL.getTipoLlamada()=="Entrante")
            tipoVistaL.setImageDrawable(getDrawable(R.drawable.llamada_contestada))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_llamadas, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        when (id) {
            R.id.eliminar_registro -> Toast.makeText(
                this@InfoLlamadaActivity,
                "Elimar del registro de llamadas",
                Toast.LENGTH_LONG
            ).show()
            R.id.bloquear -> Toast.makeText(this@InfoLlamadaActivity, "Bloquear", Toast.LENGTH_LONG)
                .show()
        }
        return super.onOptionsItemSelected(item)
    }
}

