package edu.pe.cibertec.evaluaciont1.model

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class Libro(
    val id: Int,
    val titulo: String,
    val precioUnitario: Double,
    val cantidad: Int,
    val categoria: CategoriaLibro,
    val fechaCreacion: Date = Date()
) {
    fun subtotal(): Double = precioUnitario * cantidad

    fun fechaFormateada(): String {
        val formato = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return formato.format(fechaCreacion)
    }
}