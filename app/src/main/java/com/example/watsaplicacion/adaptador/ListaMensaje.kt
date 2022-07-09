package com.example.watsaplicacion.adaptador

import java.io.Serializable

data class ListaMensaje(
    var id_chats: Int,
    var id_contacto: Int,
    var conversacion: String,
    var propietario: Int,
    var fecha: String
) : Serializable
/*

    private var mensaje: String? = null
    private var horario: String? = null
    private var tipoVista: Int? = null

    constructor(mensaje: String?, horario: String?, tipoVista: Int?) {
        this.mensaje = mensaje
        this.horario = horario
        this.tipoVista = tipoVista
    }

    fun getMensaje(): String?{
        return mensaje
    }

    fun getHorario(): String?{
        return horario
    }

    fun getTipoVista(): Int?{
        return tipoVista
    }

}
*/
