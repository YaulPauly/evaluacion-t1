package edu.pe.cibertec.evaluaciont1.ui.components

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import edu.pe.cibertec.evaluaciont1.viewmodel.CarritoViewModel

@Composable
fun ListaLibrosCarrito(viewModel: CarritoViewModel) {
    val context = LocalContext.current
    val libros = viewModel.libros

    if (libros.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("No hay libros en el carrito")
        }
    } else {
        LazyColumn {
            items(libros) { libro ->
                Card(modifier = Modifier.padding(vertical = 4.dp)) {
                    Row(
                        modifier = Modifier
                            .padding(12.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(libro.titulo, fontWeight = FontWeight.Bold)
                            Text("Precio: S/.${libro.precioUnitario}, Cantidad: ${libro.cantidad}")
                        }
                        IconButton(onClick = {
                            viewModel.eliminarLibro(libro)
                            Toast.makeText(context, "Eliminado", Toast.LENGTH_SHORT).show()
                        }) {
                            Icon(Icons.Default.Delete, contentDescription = null)
                        }
                    }
                }
            }
        }
    }
}
