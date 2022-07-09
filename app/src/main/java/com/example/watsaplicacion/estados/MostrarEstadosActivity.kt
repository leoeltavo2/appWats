package com.example.watsaplicacion.estados

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.watsaplicacion.R
import com.example.watsaplicacion.adaptador.AdaptadorEstados
import com.example.watsaplicacion.adaptador.listaEstados
import com.mikhaellopez.circularimageview.CircularImageView
import kotlinx.android.synthetic.main.activity_mostrar_estados.*

class MostrarEstadosActivity : AppCompatActivity() {

    private lateinit var imagenUsuarioE: CircularImageView
    private lateinit var imagenEstado: ImageView
    private lateinit var nombreUsuarioE: TextView
    private lateinit var fechaE: TextView
    private lateinit var textoEstado: TextView

    private lateinit var listaE: listaEstados

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mostrar_estados)
        setSupportActionBar(toolbarEstados)
        atrasEstados.setOnClickListener { onBackPressed() }

        initViews()
        initValues()

    }

    fun initViews(){

        imagenUsuarioE = findViewById(R.id.imagenUsuarioEstados)
        imagenEstado = findViewById(R.id.imgEstados)
        nombreUsuarioE = findViewById(R.id.nombreUsuarioEstados)
        fechaE = findViewById(R.id.fechaUserEstados)
        textoEstado = findViewById(R.id.textViewEstados)
    }

    fun initValues(){

        listaE = (intent.extras?.getSerializable("Estados") as listaEstados?)!!
        nombreUsuarioE.setText(listaE.getNombreUserEstados())
        listaE.getPerfilURLEstados()?.let { imagenUsuarioE.setImageResource(it) }
        listaE.getImagenEstados()?.let { imagenEstado.setImageResource(it) }
        fechaE.setText(listaE.getFechaUserEstados())
        textoEstado.setText(listaE.getTextoEstados())
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_estados, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        when (id) {
            R.id.silenciar -> Toast.makeText(
                this@MostrarEstadosActivity,
                "Silenciar",
                Toast.LENGTH_LONG
            ).show()
        }
        return super.onOptionsItemSelected(item)
    }
}