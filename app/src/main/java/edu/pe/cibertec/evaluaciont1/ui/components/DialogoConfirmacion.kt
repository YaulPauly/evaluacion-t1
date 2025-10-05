package edu.pe.cibertec.evaluaciont1.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun DialogoConfirmacion(
    visible: Boolean,
    titulo: String,
    mensaje: String,
    textoConfirmar: String = "Aceptar",
    textoCancelar: String = "Cancelar",
    onConfirmar: () -> Unit,
    onCancelar: () -> Unit
) {
    if (!visible) return

    AlertDialog(
        onDismissRequest = onCancelar,
        title = { Text(titulo) },
        text = { Text(mensaje) },
        confirmButton = {
            TextButton(onClick = onConfirmar) {
                Text(textoConfirmar)
            }
        },
        dismissButton = {
            TextButton(onClick = onCancelar) {
                Text(textoCancelar)
            }
        }
    )
}
