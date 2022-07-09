package com.example.watsaplicacion.mensajes

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.watsaplicacion.R
import com.example.watsaplicacion.adaptador.ListaChat
import com.example.watsaplicacion.adaptador.ListaMensaje
import com.example.watsaplicacion.bases.BDControlador
import com.mikhaellopez.circularimageview.CircularImageView
import kotlinx.android.synthetic.main.activity_mensajes.*
import kotlinx.android.synthetic.main.activity_perfil_contacto.*
import kotlinx.android.synthetic.main.lista_mensajes_enviados.*
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MensajesActivity : AppCompatActivity() {

    private lateinit var contacto:ListaChat
    private lateinit var db: BDControlador

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mensajes)
        setSupportActionBar(toolbarMensajes)
        atrasMensajes.setOnClickListener { onBackPressed() }


        contacto = intent.getSerializableExtra("Mensajes") as ListaChat

        //OBTENER DATOS
        nombreUsuarioMensajes.setText(contacto.nombre_contacto)
        contacto.foto?.let {
            val bmp = BitmapFactory.decodeByteArray(it, 0, it.size)
            imagenUsuarioMensajes.setImageBitmap(
                Bitmap.createScaledBitmap(bmp, bmp.width, bmp.height, false)
            )
        }
        db = BDControlador(this,resources.getString(R.string.whats),null,resources.getInteger(R.integer.whatsVersion))

        actualizaMensajes()

        btnEnviarMensajes.setOnClickListener {
            val chat = editTextMensaje.text
            editTextMensaje.setText("")

            nuevoMsg(chat)
        }




//        initValues()
//        initViews()
       /* btnEnviarMensajes.setOnClickListener {
            val chat = editTextMensaje.text
            editTextMensaje.setText("")

            nuevoMsg(chat) }*/
    }
    private fun nuevoMsg(msg: CharSequence) {
        try {
            db.agregarConversacion(contacto.id_contacto, msg.toString(), false)

            db.agregarConversacion(contacto.id_contacto,"Que quieres??", true)

            actualizaMensajes()
        } catch (e: Exception) {
            e.printStackTrace()
            "Error al mostrar los mensajes".toast(this)
        }
    }

    private fun actualizaMensajes(){
        try {
            val conversacion = db.mostrarConversacion(contacto.id_contacto)
            llayout.removeAllViews()
            for (c in conversacion) {

                val layout = LinearLayout(this)
                layout.orientation = LinearLayout.VERTICAL
                layout.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )

                val textViewInfo = TextView(this)
                textViewInfo.layoutParams = ViewGroup.LayoutParams(
                    300,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )


                val textView = TextView(this)
                textView.layoutParams = ViewGroup.LayoutParams(
                    300,

                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                textView.setText(c.conversacion)

                if (c.propietario == 1) {
                    layout.gravity = Gravity.END
                    textViewInfo.setText(c.fecha)
                    textView.setBackgroundResource(R.drawable.mensajes_enviados)
                    textView.gravity = Gravity.CENTER
                    textView.setTextColor(Color.BLACK)
                    textViewInfo.gravity = Gravity.CENTER
                    textViewInfo.setBackgroundResource(R.drawable.mensajes_enviados2)
                } else {
                    layout.gravity = Gravity.START
                    textViewInfo.setText(c.fecha)
                    textView.setBackgroundResource(R.drawable.mensajes_recibidos)
                    textView.gravity = Gravity.CENTER
                    textView.setTextColor(Color.BLACK)
                    textViewInfo.gravity = Gravity.CENTER
                    textViewInfo.setBackgroundResource(R.drawable.mensajes_recibidos2)
                }
                layout.addView(textView)
                layout.addView(textViewInfo)

                llayout.addView(layout)
            }

        }catch (e: Exception){
            e.printStackTrace()
            "Error al actualizar los mensajes".toast(this)
        }
    }

    fun String.toast(ctx: Context){
        Toast.makeText(ctx,this, Toast.LENGTH_LONG).show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_mensajes, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        when (id) {
            R.id.ver_contacto -> Toast.makeText(
                this@MensajesActivity,
                "Ver contacto",
                Toast.LENGTH_LONG
            ).show()
            R.id.archivos_enlaces -> Toast.makeText(
                this@MensajesActivity,
                "Archivos, enlaces y docs",
                Toast.LENGTH_LONG
            )
                .show()
            R.id.buscar_mensajes -> Toast.makeText(
                this@MensajesActivity,
                "Buscar",
                Toast.LENGTH_LONG
            )
                .show()
            R.id.silenciar_not -> Toast.makeText(
                this@MensajesActivity,
                "Silenciar notificaciones",
                Toast.LENGTH_LONG
            )
                .show()
            R.id.fondo_pantalla -> Toast.makeText(
                this@MensajesActivity,
                "Fondo de pantalla",
                Toast.LENGTH_LONG
            )
                .show()
            R.id.mas -> Toast.makeText(this@MensajesActivity, "Mas", Toast.LENGTH_LONG)
                .show()
        }
        return super.onOptionsItemSelected(item)
    }


}
//    fun initViews(){
//
//        imagenUsuarioM = findViewById(R.id.imagenUsuarioMensajes)
//        nombreUsuarioM = findViewById(R.id.nombreUsuarioMensajes)
//    }
//
//    fun initValues(){
//
//        contacto= intent.getSerializableExtra("Mensajes") as ListaChat
//        nombreUsuarioM.setText(contacto.nombre_contacto)
//
//        listaM.foto?.let { imagenUsuarioM.setImageResource(listaM.id_contacto) }
//    }

//    fun mandarMensaje(){
//        val chat = editTextMensaje.text
//        editTextMensaje.setText("")
//
//        nuevoMsg(chat)
//    }

  /*  private fun nuevoMsg(msg: CharSequence){
        try {
            db.agregarConversacion(contacto.id_contacto, msg.toString(),false)

            val conversacion = db.mostrarConversacion(contacto.id_contacto)
            llayout.removeAllViews()
            for (c in conversacion){
                val textView = TextView(this)
                textView.text = c.conversacion

                if (c.propietario==1){
                   textView.gravity = Gravity.END
                }else{
                    textView.gravity = Gravity.START
                }
                llayout.addView(textView)
            }
        }catch (e: Exception){
            e.printStackTrace()
            "Error al mostrar los mensajes".toast(this)
        }
    }*/




/*private lateinit var imagenUsuarioM: CircularImageView
private lateinit var nombreUsuarioM: TextView

private lateinit var adapter1:AdaptadorMensajes
private lateinit var listaM:ListaChat
private var recyclerView: RecyclerView? = null


override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_mensajes)
    setSupportActionBar(toolbarMensajes)
    atrasMensajes.setOnClickListener { onBackPressed() }



    initViews()
    initValues()
//      PONER MENSAJES
    val list = ArrayList<ListaMensaje>()
    recyclerView = findViewById(R.id.recyclerViewMensajes)
    val manager = LinearLayoutManager(this@MensajesActivity, RecyclerView.VERTICAL, false)
    recyclerView?.setLayoutManager(manager)

    list.add(ListaMensaje("kkwkw", "10:00 p.m.", 0))
    list.add(ListaMensaje("kkwkw", "12:21 p.m.", 1))


    recyclerView?.smoothScrollToPosition(list.size)
    adapter1 = AdaptadorMensajes(list)
    recyclerView?.adapter = adapter1

//        ENVIAR MENSAJE
    btnEnviarMensajes.setOnClickListener {

        var simpleDateFormat = SimpleDateFormat("h:mm a")
        var hora: String = simpleDateFormat.format(Date())


        val msg: String = editTextMensaje.getText().toString()
        list.add(ListaMensaje(msg, hora, 0))

        recyclerView?.smoothScrollToPosition(list.size)
        adapter1.notifyDataSetChanged()
        editTextMensaje.setText("")


    }

}



fun initViews(){

    imagenUsuarioM = findViewById(R.id.imagenUsuarioMensajes)
    nombreUsuarioM = findViewById(R.id.nombreUsuarioMensajes)
}

fun initValues(){

    listaM = (intent.extras?.getSerializable("Mensajes") as ListaChat?)!!
    nombreUsuarioM.setText(listaM.getNombreUser())
    listaM.getPerfilURL()?.let { imagenUsuarioM.setImageResource(it) }
}

override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.menu_mensajes, menu)
    return true
}

override fun onOptionsItemSelected(item: MenuItem): Boolean {
    val id = item.itemId
    when (id) {
        R.id.ver_contacto -> Toast.makeText(
            this@MensajesActivity,
            "Ver contacto",
            Toast.LENGTH_LONG
        ).show()
        R.id.archivos_enlaces -> Toast.makeText(
            this@MensajesActivity,
            "Archivos, enlaces y docs",
            Toast.LENGTH_LONG
        )
            .show()
        R.id.buscar_mensajes -> Toast.makeText(
            this@MensajesActivity,
            "Buscar",
            Toast.LENGTH_LONG
        )
            .show()
        R.id.silenciar_not -> Toast.makeText(
            this@MensajesActivity,
            "Silenciar notificaciones",
            Toast.LENGTH_LONG
        )
            .show()
        R.id.fondo_pantalla -> Toast.makeText(
            this@MensajesActivity,
            "Fondo de pantalla",
            Toast.LENGTH_LONG
        )
            .show()
        R.id.mas -> Toast.makeText(this@MensajesActivity, "Mas", Toast.LENGTH_LONG)
            .show()
    }
    return super.onOptionsItemSelected(item)
}*/
//}



