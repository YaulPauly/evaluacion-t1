package edu.pe.cibertec.evaluaciont1.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ReceiptLong
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import edu.pe.cibertec.evaluaciont1.ui.components.*
import edu.pe.cibertec.evaluaciont1.viewmodel.CarritoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaCarrito(viewModel: CarritoViewModel = viewModel()) {
    Scaffold(
        topBar = { TopBarCarrito() },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* acción pendiente */ },
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(Icons.Default.ReceiptLong, contentDescription = "Resumen")
            }
        }
    ) { paddingValues ->
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
        ) {
            ContenidoCarrito(viewModel)
        }
    }
}
