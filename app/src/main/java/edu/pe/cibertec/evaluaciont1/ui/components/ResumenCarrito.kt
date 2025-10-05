package edu.pe.cibertec.evaluaciont1.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import edu.pe.cibertec.evaluaciont1.viewmodel.CarritoViewModel
import kotlinx.coroutines.CoroutineScope

@Composable
fun ResumenCarrito(
    viewModel: CarritoViewModel,
    onLimpiarCampos: () -> Unit = {},
    onDeshabilitarResumen: () -> Unit = {},
    snackbarHostState: SnackbarHostState,
    coroutineScope: CoroutineScope
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
            snackbarHostState = snackbarHostState,
            coroutineScope = coroutineScope
        )
    }
}
