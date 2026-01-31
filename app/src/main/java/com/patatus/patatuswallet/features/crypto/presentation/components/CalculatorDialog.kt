package com.patatus.patatuswallet.features.crypto.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.patatus.patatuswallet.features.crypto.domain.entities.CryptoCoin
import java.util.Locale

@Composable
fun CalculatorDialog(
    coin: CryptoCoin,
    inputAmount: String,
    resultTokens: Double,
    onInputChange: (String) -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(text = "Calcular ${coin.name}")
        },
        text = {
            Column {
                Text(text = "Precio actual: MX$ ${String.format(Locale.US, "%,.2f", coin.currentPrice)}")

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = inputAmount,
                    onValueChange = onInputChange,
                    label = { Text("Inversión en MXN") },
                    placeholder = { Text("Ej. 500") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                if (resultTokens > 0) {
                    Text(text = "Te alcanzan:", style = MaterialTheme.typography.bodyMedium)
                    Text(
                        text = "${String.format(Locale.US, "%.6f", resultTokens)} ${coin.symbol.uppercase()}",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        },
        confirmButton = {
            Button(onClick = onDismiss) {
                Text("Entendido")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancelar")
            }
        }
    )
}