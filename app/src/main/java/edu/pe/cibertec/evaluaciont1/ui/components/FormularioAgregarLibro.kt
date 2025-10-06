package edu.pe.cibertec.evaluaciont1.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import edu.pe.cibertec.evaluaciont1.model.CategoriaLibro
import edu.pe.cibertec.evaluaciont1.model.Libro
import edu.pe.cibertec.evaluaciont1.model.Notificacion
import edu.pe.cibertec.evaluaciont1.model.TipoNotificacion
import edu.pe.cibertec.evaluaciont1.viewmodel.CarritoViewModel

@Composable
fun FormularioAgregarLibro(viewModel: CarritoViewModel) {

    var titulo by remember { mutableStateOf("") }
    var precio by remember { mutableStateOf("") }
    var cantidad by remember { mutableStateOf("") }
    var categoria by remember { mutableStateOf<CategoriaLibro?>(null) }
    var expanded by remember { mutableStateOf(false) }

    Text("Agregar Libro", style = MaterialTheme.typography.titleMedium)

    OutlinedTextField(
        value = titulo,
        onValueChange = { titulo = it },
        label = { Text("Título") },
        modifier = Modifier.fillMaxWidth(),
        isError = titulo.isNotBlank() && titulo.length < 4
    )
    if (titulo.isNotBlank() && titulo.length < 4) {
        Text(
            text = "El título debe tener al menos 4 caracteres",
            color = MaterialTheme.colorScheme.error,
            style = MaterialTheme.typography.bodySmall
        )
    }

    OutlinedTextField(
        value = precio,
        onValueChange = { precio = it },
        label = { Text("Precio") },
        modifier = Modifier.fillMaxWidth(),
        isError = precio.isNotEmpty() && precio.toDoubleOrNull()?.let { it <= 0 } != false
    )
    if (precio.isNotEmpty() && (precio.toDoubleOrNull() == null || precio.toDouble() <= 0)) {
        Text(
            text = "El precio debe ser un número mayor a 0",
            color = MaterialTheme.colorScheme.error,
            style = MaterialTheme.typography.bodySmall
        )
    }

    OutlinedTextField(
        value = cantidad,
        onValueChange = { cantidad = it },
        label = { Text("Cantidad") },
        modifier = Modifier.fillMaxWidth(),
        isError = cantidad.isNotEmpty() && cantidad.toIntOrNull()?.let { it <= 0 } != false
    )
    if (cantidad.isNotEmpty() && (cantidad.toIntOrNull() == null || cantidad.toInt() <= 0)) {
        Text(
            text = "La cantidad debe ser un número mayor a 0",
            color = MaterialTheme.colorScheme.error,
            style = MaterialTheme.typography.bodySmall
        )
    }

    Box(modifier = Modifier.fillMaxWidth()) {
        OutlinedButton(onClick = { expanded = true }, modifier = Modifier.fillMaxWidth()) {
            Text(categoria?.texto ?: "Seleccionar categoría")
        }
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            CategoriaLibro.values().forEach {
                DropdownMenuItem(
                    text = { Text(it.texto) },
                    onClick = {
                        categoria = it
                        expanded = false
                    }
                )
            }
        }
    }

    Spacer(modifier = Modifier.height(12.dp))

    Button(
        onClick = {
            when {
                titulo.isBlank() || precio.isBlank() || cantidad.isBlank() || categoria == null -> {
                    viewModel.notificacion.value = Notificacion(
                        mensaje = "Todos los campos son obligatorios",
                        tipo = TipoNotificacion.ERROR,
                        true
                    )
                }
                titulo.length < 4 -> {
                    viewModel.notificacion.value = Notificacion(
                        mensaje = "El título debe tener al menos 4 caracteres",
                        tipo = TipoNotificacion.ADVERTENCIA,
                        true
                    )
                }
                precio.toDoubleOrNull() == null || precio.toDouble() <= 0 -> {
                    viewModel.notificacion.value = Notificacion(
                        mensaje = "El precio debe ser un número mayor a 0",
                        tipo = TipoNotificacion.ADVERTENCIA,
                        true
                    )
                }
                cantidad.toIntOrNull() == null || cantidad.toInt() <= 0 -> {
                    viewModel.notificacion.value = Notificacion(
                        mensaje = "La cantidad debe ser un número mayor a 0",
                        tipo = TipoNotificacion.ADVERTENCIA,
                        true
                    )
                }
                else -> {
                    val nuevoLibro = Libro(
                        id = viewModel.libros.size + 1,
                        titulo = titulo,
                        precioUnitario = precio.toDouble(),
                        cantidad = cantidad.toInt(),
                        categoria = categoria!!
                    )
                    viewModel.agregarLibro(nuevoLibro)
                    viewModel.notificacion.value = Notificacion(
                        mensaje = "Libro agregado correctamente",
                        tipo = TipoNotificacion.EXITO,
                        true
                    )
                    titulo = ""; precio = ""; cantidad = ""; categoria = null
                }
            }
        },
        modifier = Modifier.fillMaxWidth()
    ) {
        Icon(Icons.Default.Add, contentDescription = null)
        Spacer(Modifier.width(8.dp))
        Text("Agregar Libro")
    }
}
