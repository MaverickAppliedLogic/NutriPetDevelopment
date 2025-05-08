package com.example.feedm.petsFeature.ui.view.screens.registerPetScreen.components.registerPetContentComponents.formFields

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.MenuItemColors
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.unit.dp
import com.example.feedm.R
import com.example.feedm.core.ui.theme.PrimaryLight
import com.example.feedm.core.ui.theme.PrimaryLightest
import com.example.feedm.core.ui.theme.SecondaryDarkest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgeField(
    age: Float,
    fieldState: Int,
    expansionState: Boolean,
    modifier: Modifier = Modifier,
    onTrailingIconClicked: () -> Unit = {},
    onAgeChanged: (Float) -> Unit = {}
) {
    var expanded by remember { mutableStateOf(false) }
    val options = stringArrayResource(R.array.fa_arrayAgeSelected)
    val preparedSelection = {
        if (age == 0f) "Selecciona"
        else if(age == 0.5f){options[0]}
        else if(age in 1f..7f){options[1]}
        else options[2]
    }
    FormField(
        label = "Edad",
        state = fieldState,
        expanded = expansionState,
        onTrailingIconClicked = { onTrailingIconClicked() },
        modifier = modifier
    ) {
        AnimatedVisibility(
            visible = expansionState,
            enter = fadeIn() + expandVertically(),
            exit = fadeOut() + shrinkVertically(),
            modifier = Modifier.padding(top = 15.dp)
        ){
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()) {
            ExposedDropdownMenuBox(
                expanded = expanded,
                modifier = Modifier
                    .fillMaxWidth(0.6f),
                onExpandedChange = {
                    expanded = !expanded
                }
            ) {
                OutlinedTextField(
                    value = preparedSelection(),
                    onValueChange = {   },
                    readOnly = true,
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = PrimaryLight,
                        focusedContainerColor = PrimaryLight,
                        unfocusedTextColor = SecondaryDarkest,
                        focusedTextColor = SecondaryDarkest,
                        unfocusedIndicatorColor = PrimaryLight,
                        focusedIndicatorColor = PrimaryLight,
                        focusedTrailingIconColor = SecondaryDarkest,
                        unfocusedTrailingIconColor = SecondaryDarkest
                    ),
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                    },
                    modifier = Modifier
                        .menuAnchor(type = MenuAnchorType.PrimaryNotEditable)
                        .shadow(2.dp)

                )
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    matchTextFieldWidth = true,
                    containerColor = PrimaryLightest
                ) {
                    options.forEach { option ->
                        DropdownMenuItem(
                            text = { Text(option) },
                            onClick = {
                               val preparedNewSelection = {
                                    when(option){
                                        options[0] -> 0.5f
                                        options[1] -> 5f
                                        options[2] -> 7f
                                        else -> 0f
                                    }
                                }
                                onAgeChanged(preparedNewSelection.invoke())
                                expanded = false
                            },
                            colors = MenuItemColors(
                                textColor = SecondaryDarkest,
                                disabledTextColor = SecondaryDarkest,
                                leadingIconColor = Color.Transparent,
                                trailingIconColor = Color.Transparent,
                                disabledLeadingIconColor = Color.Transparent,
                                disabledTrailingIconColor = Color.Transparent
                            )
                        )
                    }
                }

            }
        }
        }
    }
}