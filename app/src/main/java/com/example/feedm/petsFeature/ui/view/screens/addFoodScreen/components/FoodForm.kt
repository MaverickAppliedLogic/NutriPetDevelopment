package com.example.feedm.petsFeature.ui.view.screens.addFoodScreen.components

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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.unit.dp
import com.example.feedm.core.ui.theme.Error
import com.example.feedm.core.ui.theme.NeutralLight
import com.example.feedm.core.ui.theme.Primary
import com.example.feedm.core.ui.theme.PrimaryDark
import com.example.feedm.core.ui.theme.PrimaryLightest
import com.example.feedm.core.ui.theme.SecondaryDark
import com.example.feedm.core.ui.theme.SecondaryDarkest

@Composable
fun FoodForm(
    modifier: Modifier = Modifier,
    foodIsValid: Boolean,
    foodName: String,
    foodCalories: String,
    onFoodNameChanged: (String) -> Unit,
    onFoodCaloriesChanged: (String) -> Unit,
    onCloseIconClicked: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .shadow(10.dp)
            .clip(
                RoundedCornerShape(
                    topStart = 0.dp,
                    topEnd = 0.dp, bottomStart = 15.dp, bottomEnd = 15.dp
                )
            )
            .background(NeutralLight),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(contentAlignment = Alignment.CenterEnd) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = "Crear Alimento", style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center
                )
            }
            IconButton(onClick = { onCloseIconClicked() }) {
                Icon(
                    Icons.Default.Close, contentDescription = "Icono",
                    modifier = Modifier.padding(end = 15.dp)
                )
            }

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


        Text(
            text = "Nombre del alimento", style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = foodName,
            singleLine = true,
            onValueChange = { onFoodNameChanged(it) },
            isError = !foodIsValid,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            colors = TextFieldDefaults.colors(
                cursorColor = SecondaryDarkest,
                unfocusedContainerColor = PrimaryLightest,
                focusedContainerColor = PrimaryLightest,
                errorContainerColor = PrimaryLightest,
                unfocusedLabelColor = PrimaryDark,
                focusedLabelColor = Primary,
                errorLabelColor = Error,
                unfocusedTextColor = SecondaryDarkest,
                focusedTextColor = SecondaryDarkest,
                errorTextColor = SecondaryDarkest,
                focusedIndicatorColor = SecondaryDarkest,
                unfocusedIndicatorColor = SecondaryDarkest,
                errorIndicatorColor = Error
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 55.dp)
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "Calorías cada 100gr", style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = foodCalories,
            onValueChange = { onFoodCaloriesChanged(it) },
            singleLine = true,
            isError = !foodIsValid,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            colors = TextFieldDefaults.colors(
                cursorColor = SecondaryDarkest,
                unfocusedContainerColor = PrimaryLightest,
                focusedContainerColor = PrimaryLightest,
                errorContainerColor = PrimaryLightest,
                unfocusedLabelColor = PrimaryDark,
                focusedLabelColor = Primary,
                errorLabelColor = Error,
                unfocusedTextColor = SecondaryDarkest,
                focusedTextColor = SecondaryDarkest,
                errorTextColor = SecondaryDarkest,
                focusedIndicatorColor = SecondaryDarkest,
                unfocusedIndicatorColor = SecondaryDarkest,
                errorIndicatorColor = Error
            ),
            trailingIcon = {
                Text(
                    text = "Kcal", style = MaterialTheme.typography.bodyMedium,
                    color = SecondaryDark
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 55.dp)
        )
        Spacer(modifier = Modifier.weight(1f))
    }

}