package com.maverickapps.nutripet.core.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.maverickapps.nutripet.core.ui.theme.Primary

@Composable
fun BulletPoint(content: @Composable () -> Unit) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Text("• ",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            color = Primary,
            modifier = Modifier.padding(end = 4.dp))
        content()
    }
}
