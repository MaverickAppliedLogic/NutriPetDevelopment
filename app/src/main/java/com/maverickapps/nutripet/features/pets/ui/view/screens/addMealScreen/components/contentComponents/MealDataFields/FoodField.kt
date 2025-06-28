package com.maverickapps.nutripet.features.pets.ui.view.screens.addMealScreen.components.contentComponents.MealDataFields

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.feedm.R
import com.maverickapps.nutripet.core.ui.theme.Error
import com.maverickapps.nutripet.core.ui.theme.NeutralDark
import com.maverickapps.nutripet.core.ui.theme.NeutralLight
import com.maverickapps.nutripet.core.ui.theme.Primary
import com.maverickapps.nutripet.core.ui.theme.PrimaryLight
import com.maverickapps.nutripet.core.ui.theme.SecondaryDarkest
import com.maverickapps.nutripet.core.ui.theme.dimens
import com.maverickapps.nutripet.features.pets.domain.objectTasks.food.model.FoodModel

@Composable
fun FoodField(
    food: FoodModel,
    errorAppearing: Boolean,
    navToFoodList: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .shadow(2.dp)
            .clip(RoundedCornerShape(
                bottomEnd = MaterialTheme.dimens.extraSmall3,
                bottomStart = MaterialTheme.dimens.extraSmall3)
            )
            .fillMaxWidth()
            .background(brush = Brush.verticalGradient(colors = listOf(NeutralDark, NeutralLight)))
    ) {
        Column {
            Text(
                text = "Añadir Comida",
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                color = Primary,
                modifier = Modifier.padding(MaterialTheme.dimens.extraSmall3)
            )
            Spacer(modifier = Modifier.weight(1f, true))
            Card(
                onClick = { navToFoodList() },
                colors = CardDefaults.cardColors(containerColor = PrimaryLight,
                    contentColor = if (errorAppearing) Error else SecondaryDarkest),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = MaterialTheme.dimens.extraSmall3
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(MaterialTheme.dimens.large4)
                    .padding(horizontal = MaterialTheme.dimens.extraSmall3)
            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(text = if (food.foodId == -1) "Añadir Comida" else food.foodName,
                        modifier = Modifier.weight(0.7f), textAlign = TextAlign.Center)
                    if (food.foodId != -1) {
                    Image(painter = painterResource(R.drawable.ic_tailycare),
                        contentDescription = "",
                        alignment = Alignment.CenterEnd,
                        modifier = Modifier.weight(0.3f))

                    }
                }
            }
            Spacer(modifier = Modifier.weight(1f, true))
        }
    }
}