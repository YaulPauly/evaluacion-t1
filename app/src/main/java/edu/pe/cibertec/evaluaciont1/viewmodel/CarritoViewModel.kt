package edu.pe.cibertec.evaluaciont1.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import edu.pe.cibertec.evaluaciont1.model.Libro
import kotlin.collections.sumOf

class CarritoViewModel : ViewModel() {

    val libros = mutableStateListOf<Libro>()
    val subtotal = mutableStateOf(0.0)
    val descuento = mutableStateOf(0.0)
    val total = mutableStateOf(0.0)
    val porcentajeDescuento = mutableStateOf(value = 0.0)

    fun agregarLibro(libro: Libro) {
        libros.add(libro)
        calcularSubTotal()
    }

    fun eliminarLibro(libro: Libro) {
        libros.remove(libro)
        calcularSubTotal()
    }

    fun limpiarCarrito() {
        libros.clear()
        porcentajeDescuento.value = 0.0
        subtotal.value = 0.0
        descuento.value = 0.0
        total.value = 0.0
    }

    private fun calcularSubTotal() {
        subtotal.value = libros.sumOf { it.subtotal() }
    }

    private fun calcularTotales() {
        val cantidadTotal = libros.sumOf { it.cantidad }
        val porcentaje = obtenerDescuentoPorCantidad(cantidadTotal)
        porcentajeDescuento.value = porcentaje * 100
        descuento.value = subtotal.value * porcentaje
        total.value = subtotal.value - descuento.value
    }

    fun recalcularTotales() {
        calcularTotales()
    }
    private fun obtenerDescuentoPorCantidad(cantidad: Int): Double {
        return when (cantidad) {
            in 1..2 -> 0.0
            in 3..5 -> 0.10
            in 6..10 -> 0.15
            else -> 0.20
        }
    }
}
