package com.example.watsaplicacion.adaptador

import java.io.Serializable

class ListaLlamada:Serializable {

    private var usuarioIdL: String? = null
    private var nombreUserL: String? = null
    private var fechaUserL: String? = null
    private var perfilURLL: Int? = null
    private var tipoLlamada: String? = null

    constructor() {}

    constructor(
        usuarioIdL: String?,
        nombreUserL: String?,
        fechaUserL: String?,
        perfilURLL: Int?,
        tipoLlamada: String?
    ) {
        this.usuarioIdL = usuarioIdL
        this.nombreUserL = nombreUserL
        this.fechaUserL = fechaUserL
        this.perfilURLL = perfilURLL
        this.tipoLlamada = tipoLlamada
    }

    fun getUsuarioIdL(): String? {
        return usuarioIdL
    }

    fun setUsuarioIdL(usuarioIdL: String?) {
        this.usuarioIdL = usuarioIdL
    }

    fun getNombreUserL(): String? {
        return nombreUserL
    }

    fun setNombreUserL(nombreUserL: String?) {
        this.nombreUserL = nombreUserL
    }

    fun getFechaUserL(): String? {
        return fechaUserL
    }

    fun setFechaUserL(fechaUserL: String?) {
        this.fechaUserL = fechaUserL
    }

    fun getPerfilURLL(): Int? {
        return perfilURLL
    }

  /*  fun setPerfilURLL(perfilURLL: String?) {
        this.perfilURLL = perfilURLL
    }*/

    fun getTipoLlamada(): String? {
        return tipoLlamada
    }

 /*   fun setTipoLlamada(tipoLlamada: String?) {
        this.tipoLlamada = tipoLlamada
    }*/

}