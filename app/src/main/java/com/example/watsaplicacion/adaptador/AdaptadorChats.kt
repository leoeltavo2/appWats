package com.example.watsaplicacion.adaptador

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat.startActivity
import androidx.core.view.isInvisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.watsaplicacion.MainActivity
import com.example.watsaplicacion.R
import com.example.watsaplicacion.bases.BDControlador
import com.example.watsaplicacion.bases.PerfilContactoActivity
import com.example.watsaplicacion.mensajes.MensajesActivity
import com.example.watsaplicacion.perfil.PerfilActivity
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.mikhaellopez.circularimageview.CircularImageView

class AdaptadorChats(val context: Context, val res: Int, val lista: ArrayList<ListaChat>) :
    RecyclerView.Adapter<AdaptadorChats.HolderContacto>() {
    lateinit var db: BDControlador

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AdaptadorChats.HolderContacto {
        val view = View.inflate(context, res, null)
        return HolderContacto(view)
    }

    override fun onBindViewHolder(holder: AdaptadorChats.HolderContacto, position: Int) {
        val contacto = lista.get(position)
        holder.bind(contacto)
        holder.itemView.setOnClickListener {
            var intent: Intent = Intent(holder.itemView.context, MensajesActivity::class.java)
            intent.putExtra("Mensajes", contacto)
            holder.itemView.context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return lista.size
    }

    inner class HolderContacto(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(contacto: ListaChat) {
            db = BDControlador(
                context,
                context.resources.getString(R.string.whats),
                null,
                context.resources.getInteger(R.integer.whatsVersion)
            )

            val img = itemView.findViewById<ImageView>(R.id.perfilChat)
            val fab = itemView.findViewById<FloatingActionButton>(R.id.ModificarContacto)
            val fabEliminar = itemView.findViewById<FloatingActionButton>(R.id.eliminarContacto)

            val nombreUsuario = itemView.findViewById<TextView>(R.id.usuarioChat)
            nombreUsuario.setText(contacto.nombre_contacto)
            val descUsuario = itemView.findViewById<TextView>(R.id.mensajeChat)
            descUsuario.setText(contacto.descripcion)

            contacto.foto?.let {
                val bmp = BitmapFactory.decodeByteArray(it, 0, it.size)
                img.setImageBitmap(
                    Bitmap.createScaledBitmap(bmp, bmp.width, bmp.height, false)
                )
            }

            fab.setOnClickListener {
                var intent: Intent = Intent(itemView.context, PerfilContactoActivity::class.java)
                intent.putExtra("Mensajes", contacto)
                itemView.context.startActivity(intent)
            }
            fabEliminar.setOnClickListener {
                val dialog = AlertDialog.Builder(context)
                dialog.setTitle("Confirmar acción")
                dialog.setMessage("¿ESTA SEGURO QUE DESEA ELIMINAR A ${nombreUsuario.text} DE SUS CONTACTOS?")
                dialog.setNegativeButton("Cancelar") { dialogInterface: DialogInterface, i: Int ->
                    dialogInterface.dismiss()
                }
                dialog.setPositiveButton("Eliminar") { dialogInterface: DialogInterface, i: Int ->
                    try {

                        db.eliminarContacto(contacto.id_contacto)

                        Toast.makeText(context,"CONTACTO ELIMINADO",Toast.LENGTH_LONG).show()

                    } catch (e:Exception) {
                        e.printStackTrace()

                        Toast.makeText(context,"No se pudo eliminar",Toast.LENGTH_LONG).show()
                    }
                    dialogInterface.dismiss()
                }
                dialog.show()
            }

        }
    }
}
/*class AdaptadorChats(list: List<ListaChat>) : RecyclerView.Adapter<AdaptadorChats.Holder?>() {

    private val list: List<ListaChat>
//    private val context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(parent?.context).inflate(R.layout.lista_chat, parent, false)
        return Holder(view)
    }
    override fun onBindViewHolder(holder: Holder, position: Int) {
        val listachat: ListaChat = list[position]
        holder.name.setText(listachat.getNombreUser())
        holder.mess.setText(listachat.getMensajeUser())
        holder.dia.setText(listachat.getFechaUser())

        if (listachat.getVistoUser().equals("visto")) {
            holder.visto.setImageResource(R.drawable.visto_azul)
        } else if (listachat.getVistoUser().equals("noVisto")){
            holder.visto.setImageResource(R.drawable.visto)
        }else{
            holder.visto.isInvisible
        }
        Glide.with(holder.perfil).load(listachat.getPerfilURL()).into(holder.perfil)

        holder.itemView.setOnClickListener {
            var intent: Intent= Intent(holder.itemView.context, MensajesActivity::class.java)
            intent.putExtra("Mensajes", listachat)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

     class Holder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        val name: TextView
         val mess: TextView
         val dia: TextView
         val visto: ImageView
         val perfil: CircularImageView

        init {
            name = itemview.findViewById(R.id.usuarioChat)
            mess = itemview.findViewById(R.id.mensajeChat)
            dia = itemview.findViewById(R.id.fechaChat)
            perfil = itemview.findViewById(R.id.perfilChat)
            visto = itemview.findViewById(R.id.tipoVerificacion)
        }
    }

    init {
        this.list = list
    }
}*/

