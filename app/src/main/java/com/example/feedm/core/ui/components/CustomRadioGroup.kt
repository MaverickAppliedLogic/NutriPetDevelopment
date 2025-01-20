package com.example.feedm.core.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.feedm.ui.view.theme.Orange
import com.example.feedm.ui.view.theme.OrangeSemiTransparent

@Composable
fun CustomRadioGroup(
    modifier: Modifier = Modifier,
    options: List<Boolean>,
    selectedOption: Boolean?,
    enabled: Boolean,
    texts: Array<String>,
    text: String,
    onOptionSelected: (Boolean) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxSize()
            .padding(vertical = 10.dp)
    ) {
        Text(
            text = text,
            style = TextStyle(
                fontSize = 19.sp, fontWeight = FontWeight.Bold,
                color = Color.Black
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp)
        )
        Spacer(modifier = Modifier.padding(5.dp))
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            options.forEach { option ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = 15.dp)
                ) {
                    RadioButton(
                        selected = selectedOption == option,
                        enabled = enabled,
                        onClick = { onOptionSelected(option) },
                        colors = RadioButtonColors(
                            selectedColor = Orange,
                            unselectedColor = Color.Black,
                            disabledSelectedColor = OrangeSemiTransparent,
                            disabledUnselectedColor = Color.Gray
                        ),
                    )
                    Text(
                        text = if (option) texts[0] else texts[1],
                        style = TextStyle(color = Color.Black),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(end = 10.dp)
                    )
                }

            }
        }
    }

}