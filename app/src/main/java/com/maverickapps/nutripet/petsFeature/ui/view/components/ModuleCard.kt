package com.maverickapps.nutripet.petsFeature.ui.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.maverickapps.nutripet.core.ui.theme.NeutralLight
import com.maverickapps.nutripet.core.ui.theme.SecondaryDarkest

@Composable
fun ModuleCard(
    modifier: Modifier = Modifier,
    headerTitle: String = "",
    headerIcon: @Composable () -> Unit = {},
    captionEnabled: Boolean = false,
    captionHead: String = "",
    captionTrailing: String = "",
    content: @Composable () -> Unit
) {
    val modifierForElements = Modifier
        .fillMaxWidth()
        .background(color = NeutralLight)
        .padding(start = 7.dp, top = 5.dp, end = 7.dp)
    Card(
        elevation = CardDefaults.cardElevation(5.dp),
        shape = RoundedCornerShape(5.dp),
        modifier = modifier
            .fillMaxWidth()
    ) {
        Column(modifier = modifierForElements)  {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End,
                modifier = modifierForElements
            ){
                Text(
                    text = headerTitle,
                    color = SecondaryDarkest,
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                    textAlign = TextAlign.Start,
                    modifier = Modifier.weight(0.85f).padding(bottom = 5.dp)
                )
                headerIcon()
            }
        HorizontalDivider(
            modifier = Modifier.padding(top = 5.dp, bottom = 5.dp),
            color = SecondaryDarkest
            )
        content()
        if (captionEnabled) {
            HorizontalDivider(
                modifier = Modifier.padding(top = 5.dp),
                color = SecondaryDarkest
            )
            Row(modifier = modifierForElements.padding(bottom = 5.dp)){
                Text(
                    text = captionHead,
                    color = SecondaryDarkest,
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.weight(0.5f)
                )
                Text(
                    text = captionTrailing,
                    color = SecondaryDarkest,
                    textAlign = TextAlign.End,
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.weight(0.5f)
                )
            }
        }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun ModuleCardPreview() {
    ModuleCard(
        modifier = Modifier,
        headerTitle = "Example",
        headerIcon = {
            Icon(imageVector = Icons.Default.Edit, contentDescription = "",
                modifier = Modifier.scale(0.8f).padding(bottom = 5.dp))
                     },
        captionEnabled = true,
        captionHead = "Example",
        captionTrailing = "Example",
        content = {}
    )
}