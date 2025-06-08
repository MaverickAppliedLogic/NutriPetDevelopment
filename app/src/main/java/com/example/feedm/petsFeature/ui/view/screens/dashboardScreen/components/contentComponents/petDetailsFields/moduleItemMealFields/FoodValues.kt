package com.example.feedm.petsFeature.ui.view.screens.dashboardScreen.components.contentComponents.petDetailsFields.moduleItemMealFields

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.unit.dp
import com.example.feedm.core.ui.theme.PrimaryLightest
import com.example.feedm.core.ui.theme.SecondaryDark
import com.example.feedm.core.ui.theme.SecondaryDarkest
import com.example.feedm.core.ui.theme.SecondaryLight

@Composable
fun FoodValues(
    modifier: Modifier = Modifier,
    mealRation: String,
    onRationClicked: () -> Unit = {},
    editable: Boolean
) {

    Row(modifier, horizontalArrangement = Arrangement.End) {
        Box(
            modifier = if (editable) {
                Modifier
                    .clip(RoundedCornerShape(1.dp))
                    .border(1.5.dp, SecondaryDark, RoundedCornerShape(1.dp))
                    .background(PrimaryLightest)
                    .width(30.dp)
                    .clickable { onRationClicked() }
            } else {
                Modifier
                    .clip(RoundedCornerShape(1.dp))
                    .background(SecondaryLight)
                    .width(30.dp)
            }
        ) {
            Text(
                mealRation, color = SecondaryDarkest,
                modifier = Modifier.align(Alignment.Center)
            )
        }
        Spacer(modifier = Modifier.padding(horizontal = 2.dp))
        Text("gr", color = SecondaryDarkest)
        Spacer(modifier = Modifier.padding(horizontal = 5.dp))
    }
}