package com.example.feedm.petsFeature.ui.view.screens.dashboardScreen.components.contentFields.moduleItemMealFields

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import com.example.feedm.core.ui.theme.PrimaryLightest
import com.example.feedm.core.ui.theme.SecondaryDarkest
import com.example.feedm.core.ui.theme.SecondaryLight

@Composable
fun TimeValues(
    modifier: Modifier = Modifier,
    mealHour: String,
    onTimeClicked: () -> Unit = {},
    editable: Boolean
) {
    Row(modifier, Arrangement.End) {
        val modifierForValues = if (editable) {
            Modifier
                .shadow(elevation = 2.dp, shape = RoundedCornerShape(5.dp), clip = true)
                .clip(RoundedCornerShape(5.dp))
                .background(PrimaryLightest)
                .width(30.dp)
        } else {
            Modifier
                .clip(RoundedCornerShape(5.dp))
                .background(SecondaryLight)
                .width(30.dp)
        }
        Box(
            modifier = modifierForValues
                .clickable { onTimeClicked() }
        ) {
            Text(
                mealHour.slice(0..1),
                color = SecondaryDarkest,
                modifier = Modifier.align(Alignment.Center)
            )
        }
        Spacer(modifier = Modifier.padding(horizontal = 2.dp))
        Text(":", color = SecondaryDarkest)
        Spacer(modifier = Modifier.padding(horizontal = 2.dp))
        Box(
            modifier = modifierForValues
                .clickable { onTimeClicked() }
        ) {
            Text(
                mealHour.slice(3..4), color = SecondaryDarkest,
                modifier = Modifier.align(Alignment.Center)
            )
        }
        Spacer(modifier = Modifier.padding(horizontal = 2.dp))
        Text("h", color = SecondaryDarkest)
    }
}