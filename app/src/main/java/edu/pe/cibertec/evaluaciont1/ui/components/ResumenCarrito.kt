package edu.pe.cibertec.evaluaciont1.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import edu.pe.cibertec.evaluaciont1.viewmodel.CarritoViewModel

@Composable
fun ResumenCarrito(
    viewModel: CarritoViewModel,
    onLimpiarCampos: () -> Unit = {},
    onDeshabilitarResumen: () -> Unit = {}
) {
    Column {
        Text(
            text = "Resumen de Venta",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(8.dp))

        ResumenMontosCarrito(viewModel)

        AccionesResumenCarrito(
            viewModel = viewModel,
            onLimpiarCampos = onLimpiarCampos,
            onDeshabilitarResumen = onDeshabilitarResumen
        )
    }
}
