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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.feedm.core.ui.theme.Error
import com.example.feedm.core.ui.theme.Neutral
import com.example.feedm.core.ui.theme.NeutralLight
import com.example.feedm.core.ui.theme.Primary
import com.example.feedm.core.ui.theme.PrimaryDark
import com.example.feedm.core.ui.theme.PrimaryLightest
import com.example.feedm.core.ui.theme.SecondaryDark
import com.example.feedm.core.ui.theme.SecondaryDarkest

@Composable
fun Content(
    navToBackStack: () -> Unit,
    navToFoodList: () -> Unit
) {
    /**
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center
    ) {
        Spacer(modifier = Modifier.weight(1f, true))
        Text(
            text = "Add Food Screen",
            modifier = Modifier.padding(25.dp)
        )
        Spacer(modifier = Modifier.weight(1f, true))
        Button(
            onClick = navToBackStack,
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Go back")
        }
        Spacer(modifier = Modifier.weight(1f, true))
        Button(
            onClick = navToFoodList,
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Add Food")
        }
        Spacer(modifier = Modifier.weight(1f, true))
    }
    **/
    AddFoodScreen()
}

@Preview(showBackground = true)
@Composable
fun AddFoodScreen() {
    Scaffold(
        bottomBar = {
            BottomAppBar(
                containerColor = Neutral
            ) {
                Row (modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center){
                Button(
                    onClick = { /* confirmar acción */ },
                    elevation = ButtonDefaults.buttonElevation(10.dp),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .padding(bottom = 24.dp)
                        .fillMaxWidth(0.5f)
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFD59E))
                ) {
                    Text("Confirmar", color = Color.Black)
                }
                }
            }
        },
    )
    {
        CrearAlimentoScreen(modifier = Modifier.padding(it))
    }
}

@Composable
fun CrearAlimentoScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Neutral),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FoodForm(Modifier.weight(0.95f))
        Spacer(modifier = Modifier.weight(0.05f))
    }
}

@Composable
fun FoodForm(modifier: Modifier = Modifier) {
        Column(
            modifier = modifier.fillMaxSize()
                .shadow(10.dp)
                .clip(RoundedCornerShape(topStart = 0.dp,
                    topEnd = 0.dp, bottomStart = 15.dp, bottomEnd = 15.dp))
                .background(NeutralLight),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(contentAlignment = Alignment.CenterEnd) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(top = 20.dp),
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.End
                ) {
                    Text(
                        text = "Crear Alimento", style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center
                    )
                }
                Icon(Icons.Default.Close, contentDescription = "Icono",
                    modifier = Modifier.padding(end = 15.dp))
            }
            Spacer(modifier = Modifier.weight(1f))
            Box(
                modifier = Modifier
                    .size(180.dp)
                    .background(Color(0xFFD9D9D9), shape = RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    Icons.Default.Close,
                    contentDescription = "Icono",
                    tint = Color.Gray,
                    modifier = Modifier.size(64.dp)
                )
            }

            Spacer(modifier = Modifier.weight(1f))


            Text(text = "Nombre del alimento", style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(
                value = "",
                onValueChange = { },
                isError = false,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                colors = TextFieldDefaults.colors(
                    cursorColor = SecondaryDarkest,
                    unfocusedContainerColor = PrimaryLightest,
                    focusedContainerColor = PrimaryLightest,
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
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 55.dp)
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(text = "Calorías cada 100gr", style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(
                value = "",
                onValueChange = { },
                isError = false,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                colors = TextFieldDefaults.colors(
                    cursorColor = SecondaryDarkest,
                    unfocusedContainerColor = PrimaryLightest,
                    focusedContainerColor = PrimaryLightest,
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
                trailingIcon = {
                    Text(text = "Kcal", style = MaterialTheme.typography.bodyMedium,
                        color = SecondaryDark)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 55.dp)
            )
            Spacer(modifier = Modifier.weight(1f))
        }

}
