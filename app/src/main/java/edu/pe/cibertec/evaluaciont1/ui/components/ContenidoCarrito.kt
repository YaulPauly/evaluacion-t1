package edu.pe.cibertec.evaluaciont1.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import edu.pe.cibertec.evaluaciont1.viewmodel.CarritoViewModel
import kotlinx.coroutines.CoroutineScope

@Composable
fun ContenidoCarrito(
    viewModel: CarritoViewModel,
    snackbarHostState: SnackbarHostState,
    coroutineScope: CoroutineScope
)  {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        FormularioAgregarLibro(viewModel)
        Spacer(modifier = Modifier.height(16.dp))
        ListaLibrosCarrito(viewModel)
        Spacer(modifier = Modifier.height(16.dp))
        ResumenCarrito(
            viewModel,
            snackbarHostState = snackbarHostState,
            coroutineScope = coroutineScope
        )
    }
}
