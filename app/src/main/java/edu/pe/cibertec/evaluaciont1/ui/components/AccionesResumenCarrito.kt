package edu.pe.cibertec.evaluaciont1.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import edu.pe.cibertec.evaluaciont1.viewmodel.CarritoViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun AccionesResumenCarrito(
    viewModel: CarritoViewModel,
    snackbarHostState: SnackbarHostState,
    coroutineScope: CoroutineScope,
    onLimpiarCampos: () -> Unit = {},
    onDeshabilitarResumen: () -> Unit = {}
) {
    var mostrarDialogo by remember { mutableStateOf(false) }

    if (mostrarDialogo) {
        AlertDialog(
            onDismissRequest = { mostrarDialogo = false },
            title = { Text("Confirmar limpieza") },
            text = { Text("¿Está seguro de limpiar el carrito?") },
            confirmButton = {
                TextButton(onClick = {
                    mostrarDialogo = false
                    viewModel.limpiarCarrito()
                    onLimpiarCampos()
                    onDeshabilitarResumen()
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar(
                            message = "Carrito limpiado correctamente",
                            withDismissAction = true
                        )
                    }
                }) { Text("Sí, limpiar") }
            },
            dismissButton = {
                TextButton(onClick = { mostrarDialogo = false }) {
                    Text("Cancelar")
                }
            }
        )
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(
            onClick = { mostrarDialogo = true },
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
        ) {
            Text("Limpiar")
        }

        Button(
            onClick = {
                viewModel.recalcularTotales()
                coroutineScope.launch {
                    snackbarHostState.showSnackbar("Cálculo actualizado")
                }
            }
        ) {
            Text("Calcular")
        }
    }
}
