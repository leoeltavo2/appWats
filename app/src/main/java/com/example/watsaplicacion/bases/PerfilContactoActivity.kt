package com.example.watsaplicacion.bases

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.watsaplicacion.MainActivity
import com.example.watsaplicacion.R
import com.example.watsaplicacion.adaptador.ListaChat
import kotlinx.android.synthetic.main.activity_perfil_contacto.*
import java.io.ByteArrayOutputStream


class PerfilContactoActivity : AppCompatActivity() {
    private lateinit var contacto: ListaChat
    var byteFoto: ByteArray? = null

    lateinit var db: BDControlador


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil_contacto)
        atrasInfo.setOnClickListener { onBackPressed() }


        contacto = intent.getSerializableExtra("Mensajes") as ListaChat


        editNombrePerfilContacto.setText(contacto.nombre_contacto)
        editDescripcionPerfilContacto.setText(contacto.descripcion)
        editTelefonoPerfilContacto.setText(contacto.telefono)
        contacto.foto?.let {
            val bmp = BitmapFactory.decodeByteArray(it, 0, it.size)
            perfilContactoFoto.setImageBitmap(
                Bitmap.createScaledBitmap(bmp, bmp.width, bmp.height, false)
            )
        }

        //listaM.getPerfilURL()?.let { imagenUsuarioM.setImageResource(it) }

        db = BDControlador(
            this,
            resources.getString(R.string.whats),
            null,
            resources.getInteger(R.integer.whatsVersion)
        )

        perfilContactoFoto.setOnClickListener {
            val getIntent = Intent(Intent.ACTION_GET_CONTENT)
            getIntent.type = "image/*"

            val pickIntent = Intent(
                Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            )
            pickIntent.type = "image/*"

            val chooserIntent = Intent.createChooser(getIntent, "Seleccione una aplicación")
            chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, arrayOf(pickIntent))

            startActivityForResult(chooserIntent, 1)
        }

        btnModificarContacto.setOnClickListener {
            contacto.foto = byteFoto
            contacto.nombre_contacto = editNombrePerfilContacto.text.toString()
            contacto.telefono = editTelefonoPerfilContacto.text.toString()
            contacto.descripcion = editDescripcionPerfilContacto.text.toString()
            try {
                db.actualizarContacto(contacto)

                Toast.makeText(this, "CONTACTO MODIFICADO CORRECTAMENTE", Toast.LENGTH_LONG).show()
                startActivity(Intent(this@PerfilContactoActivity, MainActivity::class.java))
            } catch (e: Exception) {
                Toast.makeText(this, "Error al modificar el contacto", Toast.LENGTH_LONG).show()
                finish()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1) {
            data?.let {
                try {
                    perfilContactoFoto.setImageURI(it.data)


                    val bitmap = (perfilContactoFoto.drawable as BitmapDrawable).bitmap

                    val baos = ByteArrayOutputStream()
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
                    byteFoto = baos.toByteArray()
                } catch (e: Exception) {
                    e.printStackTrace()

                    Toast.makeText(this, "Error al cargar la foto", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}