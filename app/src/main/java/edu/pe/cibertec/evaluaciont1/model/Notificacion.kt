package edu.pe.cibertec.evaluaciont1.model

data class Notificacion(
    val mensaje:String,
    val tipo: TipoNotificacion,
    val visible: Boolean = false
)