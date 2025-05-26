package com.example.feedm.petsFeature.ui.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.twotone.Delete
import androidx.compose.material.icons.twotone.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.feedm.core.ui.theme.Error
import com.example.feedm.core.ui.theme.Good
import com.example.feedm.core.ui.theme.Pending
import com.example.feedm.core.ui.theme.PrimaryLightest
import com.example.feedm.core.ui.theme.SecondaryDark
import com.example.feedm.core.ui.theme.SecondaryDarkest
import com.example.feedm.core.ui.theme.SecondaryLight

@Composable
fun ModuleItemMeal(
    modifier: Modifier = Modifier,
    mealHour: String = "00:00",
    mealRation: String = "0",
    foodName: String = "Food",
    state: Int = 1,
    editable: Boolean = true,
    iconClicked: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .padding(vertical = 5.dp, horizontal = 5.dp)
            .background(Color.Transparent),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        val valuesWeight = if(state == 0) 0.07f else 0.15f
        Text(text = foodName, color = SecondaryDarkest,
            modifier = Modifier.weight(0.3f, true))
        TimeValues(mealHour, modifier = Modifier.weight(0.25f, true), editable)
        FoodValues(mealRation, modifier = Modifier.weight(0.20f, true), editable)
        if (editable) {
            IconButton(onClick = iconClicked) {
                Icon(imageVector = Icons.TwoTone.Delete, contentDescription = "Eliminar", tint = Error)
            }
        }
        StateIndicator(state, modifier = Modifier.weight(valuesWeight, true))
    }
}

@Composable
fun TimeValues(mealHour: String, modifier: Modifier = Modifier, editable: Boolean) {
    Row(modifier, Arrangement.End) {
        val modifierForValues = if (editable) { Modifier
            .shadow(elevation = 2.dp, shape = RoundedCornerShape(5.dp), clip = true)
            .clip(RoundedCornerShape(5.dp))
            .background(PrimaryLightest)
            .width(30.dp)}
        else{
            Modifier
                .clip(RoundedCornerShape(5.dp))
                .background(SecondaryLight)
                .width(30.dp)
        }
        Box(
            modifier = modifierForValues
        ) {
            Text(mealHour.slice(0..1),
                color = SecondaryDarkest,
                modifier = Modifier.align(Alignment.Center))
        }
        Spacer(modifier = Modifier.padding(horizontal = 2.dp))
        Text(":", color = SecondaryDarkest)
        Spacer(modifier = Modifier.padding(horizontal = 2.dp))
        Box(
            modifier = modifierForValues
        ) {
            Text(mealHour.slice(3..4), color = SecondaryDarkest,
                modifier = Modifier.align(Alignment.Center))
        }
        Spacer(modifier = Modifier.padding(horizontal = 2.dp))
        Text("h", color = SecondaryDarkest)
    }
}

@Composable
fun FoodValues(mealRation: String, modifier: Modifier = Modifier, editable: Boolean) {
    Row(modifier, horizontalArrangement = Arrangement.End) {
        Box(
            modifier = if (editable) { Modifier
                .clip(RoundedCornerShape(1.dp))
                .border(1.5.dp, SecondaryDark, RoundedCornerShape(1.dp))
                .background(PrimaryLightest)
                .width(30.dp)}
            else{
                Modifier
                    .clip(RoundedCornerShape(1.dp))
                    .background(SecondaryLight)
                    .width(30.dp)
            }
        ) {
            Text(mealRation, color = SecondaryDarkest,
                modifier = Modifier.align(Alignment.Center))
        }
        Spacer(modifier = Modifier.padding(horizontal = 2.dp))
        Text("gr", color = SecondaryDarkest)
        Spacer(modifier = Modifier.padding(horizontal = 5.dp))
    }
}

@Composable
fun StateIndicator(state: Int, modifier: Modifier = Modifier) {
    Row(
        modifier.fillMaxHeight(), verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {
        when (state) {
            0->{
                Icon(
                    imageVector = Icons.Default.Check, contentDescription = "",
                    tint = Good, modifier = Modifier.size(25.dp)
                )
            }
            1 -> {
                Icon(
                    imageVector = Icons.TwoTone.Warning, contentDescription = "",
                    tint = Pending, modifier = Modifier.weight(0.5f)
                )
                IconButton(onClick = {}, modifier = Modifier.weight(0.5f)) {
                    Icon(
                        imageVector = Icons.Default.Add, contentDescription = "",
                        tint = SecondaryDarkest
                    )
                }
            }
            2 -> {
                Icon(
                    imageVector = Icons.Default.Info, contentDescription = "",
                    tint = Error, modifier = Modifier.weight(0.5f)
                )
                IconButton(onClick = {}, modifier = Modifier.weight(0.5f)) {
                    Icon(
                        imageVector = Icons.Default.Add, contentDescription = "",
                        tint = Error
                    )

                }

            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun ModuleItemMealPreview() {
    ModuleItemMeal(modifier = Modifier.fillMaxWidth())
}