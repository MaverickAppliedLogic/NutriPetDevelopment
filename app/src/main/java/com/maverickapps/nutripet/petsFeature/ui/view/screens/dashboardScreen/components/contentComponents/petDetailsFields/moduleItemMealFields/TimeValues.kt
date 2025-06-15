package com.maverickapps.nutripet.petsFeature.ui.view.screens.dashboardScreen.components.contentComponents.petDetailsFields.moduleItemMealFields

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import com.maverickapps.nutripet.core.ui.theme.PrimaryLightest
import com.maverickapps.nutripet.core.ui.theme.SecondaryDarkest
import com.maverickapps.nutripet.core.ui.theme.SecondaryLight
import com.maverickapps.nutripet.core.ui.theme.dimens

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
                .shadow(elevation = MaterialTheme.dimens.extraSmall1,
                    shape = RoundedCornerShape(MaterialTheme.dimens.extraSmall2),
                    clip = true)
                .clip(RoundedCornerShape(MaterialTheme.dimens.extraSmall2))
                .background(PrimaryLightest)
                .width(MaterialTheme.dimens.medium1)
        } else {
            Modifier
                .clip(RoundedCornerShape(MaterialTheme.dimens.extraSmall2))
                .background(SecondaryLight)
                .width(MaterialTheme.dimens.medium1)
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
        Spacer(modifier = Modifier.padding(horizontal = MaterialTheme.dimens.extraSmall1))
        Text(":", color = SecondaryDarkest)
        Spacer(modifier = Modifier.padding(horizontal = MaterialTheme.dimens.extraSmall1))
        Box(
            modifier = modifierForValues
                .clickable { onTimeClicked() }
        ) {
            Text(
                mealHour.slice(3..4), color = SecondaryDarkest,
                modifier = Modifier.align(Alignment.Center)
            )
        }
        Spacer(modifier = Modifier.padding(horizontal = MaterialTheme.dimens.extraSmall1))
        Text("h", color = SecondaryDarkest)
    }
}