package edu.pe.cibertec.evaluaciont1.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ReceiptLong
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import edu.pe.cibertec.evaluaciont1.ui.components.*
import edu.pe.cibertec.evaluaciont1.viewmodel.CarritoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaCarrito(viewModel: CarritoViewModel = viewModel()) {
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = { TopBarCarrito() },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* acción pendiente */ },
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(Icons.Default.ReceiptLong, contentDescription = "Resumen")
            }
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            ContenidoCarrito(
                viewModel = viewModel,
                snackbarHostState = snackbarHostState,
                coroutineScope = coroutineScope
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 16.dp),
                contentAlignment = Alignment.BottomCenter
            ) {
                BarraNotificacion(
                    notificacion = viewModel.notificacion.value,
                    onDismiss = { viewModel.ocultarNotificacion() }
                )
            }
        }
    }
}
