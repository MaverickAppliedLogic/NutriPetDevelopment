package com.maverickapps.nutripet.features.pets.ui.view.screens.addMealScreen.components.contentComponents.MealDataFields

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import com.maverickapps.nutripet.core.ui.theme.Error
import com.maverickapps.nutripet.core.ui.theme.NeutralLight
import com.maverickapps.nutripet.core.ui.theme.Primary
import com.maverickapps.nutripet.core.ui.theme.PrimaryDark
import com.maverickapps.nutripet.core.ui.theme.SecondaryDarkest
import com.maverickapps.nutripet.core.ui.theme.dimens

@Composable
fun DataField(
    modifier: Modifier = Modifier,
    errorAppearing: Boolean,
    ration: String,
    onRationChanged: (String) -> Unit
) {

    Column(
        modifier = modifier
            .padding(horizontal = MaterialTheme.dimens.large1)
            .fillMaxSize()
            .background(color = Color.Transparent)
    ) {
        Spacer(modifier = Modifier.weight(0.2f, true))
        Text(
            text = "Ración",
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
            color = SecondaryDarkest,
        )
        Spacer(modifier = Modifier.height(MaterialTheme.dimens.extraSmall2))
        OutlinedTextField(
            value = ration,
            trailingIcon = {
                Text(text="gr", color = SecondaryDarkest)
            },
            onValueChange = { onRationChanged(it) },
            isError = errorAppearing,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)   ,
            colors = TextFieldDefaults.colors(
                cursorColor = SecondaryDarkest,
                unfocusedContainerColor = NeutralLight,
                focusedContainerColor = NeutralLight,
                errorContainerColor = NeutralLight,
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
        Spacer(modifier = Modifier.weight(0.8f, true))
    }
}