package com.example.watsaplicacion.adaptador

import java.io.Serializable


class listaEstados: Serializable {
    private var usuarioIdEstados: String? = null
    private var nombreUserEstados: String? = null
    private var fechaUserEstados: String? = null
    private var perfilURLEstados: Int? = null
    private var imagenEstados: Int? = null
    private var textoEstados: String? = null


    constructor(
        usuarioIdEstados: String?,
        nombreUserEstados: String?,
        fechaUserEstados: String?,
        perfilURLEstados: Int?,
        imagenEstados: Int?,
        textoEstados: String?

    ) {
        this.usuarioIdEstados = usuarioIdEstados
        this.nombreUserEstados = nombreUserEstados
        this.fechaUserEstados = fechaUserEstados
        this.perfilURLEstados = perfilURLEstados
        this.imagenEstados = imagenEstados
        this.textoEstados = textoEstados
    }

    fun getUsuarioIdEstados(): String? {
        return usuarioIdEstados
    }
    fun getNombreUserEstados(): String? {
        return nombreUserEstados
    }
    fun getFechaUserEstados(): String? {
        return fechaUserEstados
    }
    fun getPerfilURLEstados(): Int? {
        return perfilURLEstados
    }
    fun getImagenEstados(): Int? {
        return imagenEstados
    }
    fun getTextoEstados(): String? {
        return textoEstados
    }

}