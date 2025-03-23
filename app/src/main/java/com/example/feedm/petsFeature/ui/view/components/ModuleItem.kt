package com.example.feedm.petsFeature.ui.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.feedm.core.ui.theme.SecondaryDarkest

@Composable
fun ModuleItem(
    modifier: Modifier = Modifier,
    headerText: String = "00:00",
    trailingText: String = " -",
) {
    Row(
        modifier = modifier
            .padding(vertical = 5.dp, horizontal = 5.dp)
            .background(Color.Transparent),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = headerText, color = SecondaryDarkest, textAlign = TextAlign.Start,
            modifier = Modifier.weight(0.3f, true))

        Text(text = trailingText, color = SecondaryDarkest, textAlign = TextAlign.End,
            modifier = Modifier.weight(0.3f, true))
        
    }
}

@Preview(showBackground = true)
@Composable
fun ModuleItemPreview(){
    ModuleItem()
}