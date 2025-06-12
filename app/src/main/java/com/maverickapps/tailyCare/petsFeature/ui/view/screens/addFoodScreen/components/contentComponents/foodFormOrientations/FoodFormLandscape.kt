package com.maverickapps.tailyCare.petsFeature.ui.view.screens.addFoodScreen.components.contentComponents.foodFormOrientations

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.unit.dp
import com.maverickapps.tailyCare.core.ui.theme.Error
import com.maverickapps.tailyCare.core.ui.theme.NeutralLight
import com.maverickapps.tailyCare.core.ui.theme.Primary
import com.maverickapps.tailyCare.core.ui.theme.PrimaryDark
import com.maverickapps.tailyCare.core.ui.theme.PrimaryLightest
import com.maverickapps.tailyCare.core.ui.theme.SecondaryDark
import com.maverickapps.tailyCare.core.ui.theme.SecondaryDarkest
import com.maverickapps.tailyCare.core.ui.theme.dimens

@Composable
fun FoodFormPLandscape(
    modifier: Modifier = Modifier,
    foodIsValid: Boolean,
    foodName: String,
    foodCalories: String,
    onFoodNameChanged: (String) -> Unit,
    onFoodCaloriesChanged: (String) -> Unit,
    onCommitButtonClicked: () -> Unit = {},
    onCloseIconClicked: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .shadow(MaterialTheme.dimens.extraSmall3)
            .clip(
                RoundedCornerShape(
                    topStart = 0.dp,
                    topEnd = 0.dp,
                    bottomStart = MaterialTheme.dimens.small1,
                    bottomEnd = MaterialTheme.dimens.small1
                )
            )
            .background(NeutralLight),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(horizontalArrangement = Arrangement.Center,
            modifier = modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(
                    top = MaterialTheme.dimens.extraSmall2,
                    end = MaterialTheme.dimens.extraSmall2,
                    start = MaterialTheme.dimens.extraSmall2)) {
            IconButton(onClick = { onCloseIconClicked() }) {
                Icon(
                    Icons.Default.Close, contentDescription = "Icono",
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = { onCommitButtonClicked() },
                elevation = ButtonDefaults.buttonElevation(),
                shape = RoundedCornerShape(MaterialTheme.dimens.extraSmall1),
                modifier = Modifier.height(MaterialTheme.dimens.buttonSize),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFD59E))
            ) {
                Text("Confirmar", color = Color.Black)
            }
        }
        Text(
            text = "Nombre del alimento", style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(MaterialTheme.dimens.extraSmall3))
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
                .padding(horizontal = MaterialTheme.dimens.medium4)
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "Calorías cada 100gr", style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(MaterialTheme.dimens.extraSmall3))
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
                .padding(horizontal = MaterialTheme.dimens.medium4)
        )
        Spacer(modifier = Modifier.weight(1f))
    }

}