package edu.pe.cibertec.evaluaciont1.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import edu.pe.cibertec.evaluaciont1.model.Notificacion
import edu.pe.cibertec.evaluaciont1.model.TipoNotificacion
import edu.pe.cibertec.evaluaciont1.viewmodel.CarritoViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun ListaLibrosCarrito(viewModel: CarritoViewModel) {
    val libros = viewModel.libros
    var libroAEliminar by remember { mutableStateOf<String?>(null) }

    if (libroAEliminar != null) {
        AlertDialog(
            onDismissRequest = { libroAEliminar = null },
            title = { Text("Confirmar eliminación") },
            text = { Text("¿Eliminar '${libroAEliminar}' del carrito?") },
            confirmButton = {
                TextButton(onClick = {
                    val libro = libros.find { it.titulo == libroAEliminar }
                    libro?.let {
                        viewModel.eliminarLibro(it)
                        viewModel.recalcularTotales()

                        viewModel.notificacion.value = Notificacion(
                            mensaje = "Libro eliminado del carrito",
                            tipo = TipoNotificacion.ADVERTENCIA,
                            true
                        )
                    }
                    libroAEliminar = null
                }) {
                    Text("Sí, eliminar")
                }
            },
            dismissButton = {
                TextButton(onClick = { libroAEliminar = null }) {
                    Text("Cancelar")
                }
            }
        )
    }

    if (libros.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("No hay libros en el carrito")
        }
    } else {
        LazyColumn {
            items(libros) { libro ->
                Card(
                    modifier = Modifier
                        .padding(vertical = 4.dp)
                        .fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .padding(12.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(libro.titulo, fontWeight = FontWeight.Bold)
                            Text("Precio: S/.${libro.precioUnitario}, Cantidad: ${libro.cantidad}")
                        }
                        IconButton(onClick = { libroAEliminar = libro.titulo }) {
                            Icon(Icons.Default.Delete, contentDescription = "Eliminar libro")
                        }
                    }
                }
            }
        }
    }
}
