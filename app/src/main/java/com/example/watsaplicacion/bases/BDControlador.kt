package com.example.watsaplicacion.bases

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.watsaplicacion.R
import com.example.watsaplicacion.adaptador.ListaChat
import com.example.watsaplicacion.adaptador.ListaMensaje
import java.text.SimpleDateFormat
import java.util.*
import kotlin.Throws
import kotlin.collections.ArrayList

class BDControlador(
    context: Context?,
    name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
) : SQLiteOpenHelper(context, name, factory, version) {

    override fun onCreate(db: SQLiteDatabase?) {

        db?.let {
            var qry =
                "CREATE TABLE contacto(id_contacto INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, nombre_contacto TEXT NOT NULL, telefono TEXT NOT NULL, foto BLOB, descripcion TEXT NOT NULL)"
            it.execSQL(qry)

            qry =
                "CREATE TABLE chats(id_chats INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, id_contacto INTEGER NULL, conversacion TEXT NOT NULL, propietario INTEGER NOT NULL, fecha TEXT NOT NULL, FOREIGN KEY (id_contacto) REFERENCES contacto(id_contacto))"
            it.execSQL(qry)

            qry =
                "INSERT INTO contacto(nombre_contacto, telefono, descripcion) VALUES ('Hugo','43695938','hello')"
            it.execSQL(qry)
            qry =
                "INSERT INTO contacto(nombre_contacto, telefono, descripcion) VALUES ('Fany','43695938','hell0')"
            it.execSQL(qry)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {}
//CONTACTO
    @Throws
    fun muestraContacto(): ArrayList<ListaChat> {
        val db = readableDatabase

        val cursor = db.rawQuery("SELECT * FROM contacto", null)

        val contacto = ArrayList<ListaChat>()
        while (cursor.moveToNext()) {
            val c = ListaChat(
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getBlob(3),
                cursor.getString(4)
            )
            contacto.add(c)
        }
        return contacto
    }

    @Throws
    fun actualizarContacto(contacto:ListaChat){
        val db = writableDatabase

        val qry = "UPDATE contacto SET nombre_contacto=?, telefono=?, foto=?, descripcion=? WHERE id_contacto=${contacto.id_contacto}"


        val statement = db.compileStatement(qry)
        statement.bindString(1, contacto.nombre_contacto)
        statement.bindString(2, contacto.telefono)
        contacto.foto?.let {
            statement.bindBlob(3, it)
        }
        statement.bindString(4, contacto.descripcion)
        statement.execute()

        db.close()
    }

    @Throws
    fun nuevoContacto(nombre_contacto:String, telefono:String, descripcion:String){
        val db = writableDatabase

        val qry = "INSERT INTO contacto(nombre_contacto, telefono, descripcion) VALUES('${nombre_contacto}','${telefono}','${descripcion}')"

        db.execSQL(qry)

        db.close()
    }

    @Throws
    fun eliminarContacto(id_contacto: Int){
        val db  = writableDatabase

        val qry = "DELETE FROM contacto WHERE id_contacto = $id_contacto"

        db.execSQL(qry)

        db.close()
    }
    //FIN DEL CONTACTO

    //CONVERSACIONES DEL CHAT
    @Throws
    fun agregarConversacion(id_contacto: Int, conversacion: String, propietario: Boolean) {
        val db = writableDatabase
//        val calendar = Calendar.getInstance()
        val dia = SimpleDateFormat("h:mm:s a dd-MM-yyyy ")
        var hora: String = dia.format(Date())
        val es_propietario = if (propietario) 0 else 1

        val qry =
            "INSERT INTO chats VALUES(NULL, $id_contacto, '$conversacion', $es_propietario,'${hora}')"
        db.execSQL(qry)
        db.close()
    }

    @Throws
    fun mostrarConversacion(id_contacto: Int): ArrayList<ListaMensaje> {
        val db = readableDatabase

        val cursor = db.rawQuery("SELECT * FROM chats WHERE id_contacto = $id_contacto", null)

        val chat = ArrayList<ListaMensaje>()
        while (cursor.moveToNext()) {
            val c = ListaMensaje(
                cursor.getInt(0),
                cursor.getInt(1),
                cursor.getString(2),
                cursor.getInt(3),
                cursor.getString(4)
            )
            chat.add(c)
        }
        db.close()

        return chat
    }


}


