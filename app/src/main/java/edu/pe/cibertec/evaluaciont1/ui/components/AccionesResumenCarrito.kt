package edu.pe.cibertec.evaluaciont1.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import edu.pe.cibertec.evaluaciont1.viewmodel.CarritoViewModel
import kotlinx.coroutines.launch

@Composable
fun AccionesResumenCarrito(
    viewModel: CarritoViewModel,
    onLimpiarCampos: () -> Unit = {},
    onDeshabilitarResumen: () -> Unit = {}
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    var mostrarDialogo by remember { mutableStateOf(false) }

    // 🔹 Snackbar visible en esta sección
    SnackbarHost(
        hostState = snackbarHostState,
        modifier = Modifier.fillMaxWidth()
    )

    // 🔹 Diálogo de confirmación
    if (mostrarDialogo) {
        DialogoConfirmacion(
            visible = mostrarDialogo,
            titulo = "Confirmar limpieza",
            mensaje = "¿Está seguro de limpiar el carrito?",
            textoConfirmar = "Sí, limpiar",
            textoCancelar = "Cancelar",
            onConfirmar = {
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
            },
            onCancelar = { mostrarDialogo = false }
        )
    }

    // 🔹 Botones de acción
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(
            onClick = { mostrarDialogo = true },
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.errorContainer)
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
