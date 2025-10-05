package edu.pe.cibertec.evaluaciont1.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import edu.pe.cibertec.evaluaciont1.viewmodel.CarritoViewModel

@Composable
fun ResumenMontosCarrito(viewModel: CarritoViewModel) {
    Column(modifier = Modifier.fillMaxWidth()) {

        // --- Subtotal ---
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Subtotal:")
            Text(
                "S/. %.2f".format(viewModel.subtotal.value),
                fontWeight = FontWeight.Medium
            )
        }

        // --- Descuento ---
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Descuento:")
            Text(
                "(%.0f%%) S/. %.2f".format(
                    viewModel.porcentajeDescuento.value,
                    viewModel.descuento.value
                ),
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.secondary
            )
        }

        // --- Total ---
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Total:", fontWeight = FontWeight.Bold)
            Text(
                "S/. %.2f".format(viewModel.total.value),
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}
