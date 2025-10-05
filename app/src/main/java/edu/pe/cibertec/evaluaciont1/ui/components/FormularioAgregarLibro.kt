package edu.pe.cibertec.evaluaciont1.ui.components

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import edu.pe.cibertec.evaluaciont1.model.CategoriaLibro
import edu.pe.cibertec.evaluaciont1.model.Libro
import edu.pe.cibertec.evaluaciont1.viewmodel.CarritoViewModel

@Composable
fun FormularioAgregarLibro(viewModel: CarritoViewModel) {
    val context = LocalContext.current
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
        modifier = Modifier.fillMaxWidth()
    )
    OutlinedTextField(
        value = precio,
        onValueChange = { precio = it },
        label = { Text("Precio") },
        modifier = Modifier.fillMaxWidth()
    )
    OutlinedTextField(
        value = cantidad,
        onValueChange = { cantidad = it },
        label = { Text("Cantidad") },
        modifier = Modifier.fillMaxWidth()
    )

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
            if (titulo.isBlank() || precio.isBlank() || cantidad.isBlank() || categoria == null) {
                Toast.makeText(context, "Completa todos los campos", Toast.LENGTH_SHORT).show()
            } else {
                val nuevoLibro = Libro(
                    id = viewModel.libros.size + 1,
                    titulo = titulo,
                    precioUnitario = precio.toDouble(),
                    cantidad = cantidad.toInt(),
                    categoria = categoria!!
                )
                viewModel.agregarLibro(nuevoLibro)
                Toast.makeText(context, "Libro agregado", Toast.LENGTH_SHORT).show()
                titulo = ""; precio = ""; cantidad = ""; categoria = null
            }
        },
        modifier = Modifier.fillMaxWidth()
    ) {
        Icon(Icons.Default.Add, contentDescription = null)
        Spacer(Modifier.width(8.dp))
        Text("Agregar Libro")
    }
}
