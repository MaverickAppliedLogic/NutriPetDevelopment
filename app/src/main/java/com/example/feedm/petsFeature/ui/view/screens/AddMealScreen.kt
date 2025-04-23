package com.example.feedm.petsFeature.ui.view.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.feedm.core.ui.theme.Error
import com.example.feedm.core.ui.theme.Neutral
import com.example.feedm.core.ui.theme.NeutralDark
import com.example.feedm.core.ui.theme.NeutralLight
import com.example.feedm.core.ui.theme.Primary
import com.example.feedm.core.ui.theme.PrimaryDark
import com.example.feedm.core.ui.theme.PrimaryLight
import com.example.feedm.core.ui.theme.SecondaryDarkest
import java.util.Locale

@Composable
fun AddMealScreen(
    petId: Int,
    navToFoodList: () -> Unit,
    navToBackStack: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center
    ) {
        Spacer(modifier = Modifier.weight(1f, true))
        Text(
            text = "Add Meal Screen",
            modifier = Modifier.padding(25.dp)
        )
        Spacer(modifier = Modifier.weight(1f, true))
        Button(
            onClick = navToBackStack,
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Go Back")
        }
        Spacer(modifier = Modifier.weight(1f, true))
        Button(
            onClick = navToFoodList,
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Select Food")
        }
        Spacer(modifier = Modifier.weight(1f, true))
        Button(
            onClick = navToBackStack,
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Add Meal")
        }
        Spacer(modifier = Modifier.weight(1f, true))
    }
}

@Preview(showBackground = true)
@Composable
fun AddFoodScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Neutral),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        MealField(Modifier.weight(0.3f, true))
        DataField(modifier = Modifier.weight(0.6f, true))
        // Botón "Añadir"
        Button(
            onClick = { /* Acción para añadir comida */ },
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.1f)
        ) {
            Text(text = "Añadir")
        }

    }
}

@Composable
fun MealField(
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .shadow(5.dp)
            .clip(RoundedCornerShape(bottomEnd = 10.dp, bottomStart = 10.dp))
            .fillMaxWidth()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(NeutralDark, NeutralLight)
                )
            )
    ) {
        Column {

            Text(
                text = "Añadir Comida",
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                color = Primary,
                modifier = Modifier.padding(10.dp)
            )
            Spacer(modifier = Modifier.weight(1f, true))
            Card(
                colors = CardDefaults.cardColors(containerColor = PrimaryLight),
                elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .padding(horizontal = 10.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(text = "Seleccionar Comida")

                }
            }
            Spacer(modifier = Modifier.weight(1f, true))
        }
    }
}

@Composable
fun DataField(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(10.dp)
            .fillMaxSize()
            .background(color = Color.Transparent)
    ) {
        Spacer(modifier = Modifier.weight(1f, true))

        // Entrada de datos de ración
        Column(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 50.dp)
        ) {
            Text(
                text = "Ración",
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                color = SecondaryDarkest,
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = "25",
                onValueChange = { //TODO manejar evento cambia value Racion
                },
                isError = false,
                colors = TextFieldDefaults.colors(
                    cursorColor = SecondaryDarkest,
                    unfocusedContainerColor = NeutralLight,
                    focusedContainerColor = NeutralLight,
                    errorContainerColor = Error,
                    unfocusedLabelColor = PrimaryDark,
                    focusedLabelColor = Primary,
                    errorLabelColor = Error,
                    unfocusedTextColor = SecondaryDarkest,
                    focusedTextColor = SecondaryDarkest,
                    errorTextColor = Error,
                    focusedIndicatorColor = SecondaryDarkest,
                    unfocusedIndicatorColor = SecondaryDarkest,
                    errorIndicatorColor = Error
                ),
                modifier = Modifier.fillMaxWidth()
            )
        }
        Spacer(modifier = Modifier.weight(1f, true))

        // Entrada de datos de hora
        Column(modifier = Modifier.fillMaxWidth().padding(50.dp).height(100.dp),) {
            var timePickerIsVisible by remember { mutableStateOf(true) }
            Text(
                text = "Hora",
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                color = SecondaryDarkest,
            )
            Spacer(modifier = Modifier.height(8.dp))

            Card(
                onClick = { timePickerIsVisible = true },
                elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
                colors = CardDefaults.cardColors(
                    containerColor = NeutralLight,
                    contentColor = SecondaryDarkest
                ),
                modifier = Modifier
                    .fillMaxSize()
            ) {

                val focusManager = LocalFocusManager.current
                val keyboardController = LocalSoftwareKeyboardController


                Box(modifier = Modifier.fillMaxSize()) {
                    Text(
                        text = String.format(
                            Locale.getDefault(),
                            "%02d:%02d", 12, 37
                        ),
                        textAlign = TextAlign.Center,
                        letterSpacing = 2.sp,
                        style = MaterialTheme.typography.headlineLarge,
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.Center)
                    )
                }

            }

        }
            Spacer(modifier = Modifier.weight(1f, true))
    }
}