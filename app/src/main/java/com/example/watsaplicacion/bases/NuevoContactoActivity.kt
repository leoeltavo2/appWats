package com.example.watsaplicacion.bases

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import com.example.watsaplicacion.MainActivity
import com.example.watsaplicacion.R
import com.example.watsaplicacion.adaptador.ListaChat
import kotlinx.android.synthetic.main.activity_nuevo_contacto.*
import kotlinx.android.synthetic.main.activity_perfil_contacto.*
import java.io.ByteArrayOutputStream

class NuevoContactoActivity : AppCompatActivity() {
    lateinit var db: BDControlador


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nuevo_contacto)
        atrasNuevoContacto.setOnClickListener { onBackPressed() }


        db = BDControlador(
            this,
            resources.getString(R.string.whats),
            null,
            resources.getInteger(R.integer.whatsVersion)
        )



        btnNuevoContacto.setOnClickListener {
            if (editNombrePerfilNuevoContacto.text.toString() != "" && editTelefonoPerfilNuevoContacto.text.toString() != "" && editDescripcionPerfilNuevoContacto.text.toString() != "") {
                try {
                    db.nuevoContacto(
                        editNombrePerfilNuevoContacto.text.toString(),
                        editTelefonoPerfilNuevoContacto.text.toString(),
                        editDescripcionPerfilNuevoContacto.text.toString()
                    )

                    Toast.makeText(this, "DATOS GURADADOS CORRECTAMENTE", Toast.LENGTH_LONG).show()
                    startActivity(Intent(this@NuevoContactoActivity, MainActivity::class.java))
                } catch (e: Exception) {
                    e.printStackTrace()

                    Toast.makeText(this, "Error al guardar", Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(this, "ERROR! FAVOR DE NO DEJAR LOS CAMPOS EN BLANCO", Toast.LENGTH_LONG).show()
            }

        }
    }

}