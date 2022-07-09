package com.example.watsaplicacion.adaptador

import android.widget.ImageView
import java.io.Serializable
data class ListaChat(
    var id_contacto:Int,
    var nombre_contacto: String,
    var telefono: String,
    var foto: ByteArray?,
    var descripcion: String,
    ): Serializable
/*

class ListaChat : Serializable{

    private var usuarioId: String? = null
    private var nombreUser: String? = null
    private var mensajeUser: String? = null
    private var fechaUser: String? = null
    private var perfilURL: Int? = null
    private var vistoUser:  String? = null

    constructor() {}

    constructor(
        usuarioId: String?,
        nombreUser: String?,
        mensajeUser: String?,
        fechaUser: String?,
        perfilURL: Int?,
        vistoUser: String?
    ) {
        this.usuarioId = usuarioId
        this.nombreUser = nombreUser
        this.mensajeUser = mensajeUser
        this.fechaUser = fechaUser
        this.perfilURL = perfilURL
        this.vistoUser = vistoUser
    }

    fun getUsuarioId(): String? {
        return usuarioId
    }

    fun setUsuarioId(usuarioId: String?) {
        this.usuarioId = usuarioId
    }

    fun getNombreUser(): String? {
        return nombreUser
    }

    fun setNombreUser(nombreUser: String?) {
        this.nombreUser = nombreUser
    }

    fun getMensajeUser(): String? {
        return mensajeUser
    }

    fun setMensajeUser(mensajeUser: String?) {
        this.mensajeUser = mensajeUser
    }

    fun getFechaUser(): String? {
        return fechaUser
    }

    fun setFechaUser(fechaUser: String?) {
        this.fechaUser = fechaUser
    }

    fun getPerfilURL(): Int? {
        return perfilURL
    }

   */
/* fun setPerfilURL(perfilURL: String?) {
        this.perfilURL = perfilURL
    }*//*


    fun getVistoUser(): String? {
        return vistoUser
    }

    fun setVistoUser(perfilURL: String?) {
        this.vistoUser = vistoUser
    }

}*/
