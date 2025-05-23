package com.example.feedm.petsFeature.ui.view.screens.addMealScreen.components.MealDataFields

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
import com.example.feedm.core.ui.theme.NeutralDark
import com.example.feedm.core.ui.theme.NeutralLight
import com.example.feedm.core.ui.theme.Primary
import com.example.feedm.core.ui.theme.PrimaryLight
import com.example.feedm.petsFeature.domain.objectTasks.food.model.FoodModel

@Composable
fun FoodField(
    food: FoodModel,
    navToFoodList: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .shadow(5.dp)
            .clip(RoundedCornerShape(bottomEnd = 10.dp, bottomStart = 10.dp))
            .fillMaxWidth()
            .background(brush = Brush.verticalGradient(colors = listOf(NeutralDark, NeutralLight)))
    ) {
        Column {
            Text(
                text = "Añadir Comida",
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                color = Primary,
                modifier = Modifier.padding(10.dp)
            )
            Spacer(modifier = Modifier.weight(1f, true))
            Card(
                onClick = { navToFoodList() },
                colors = CardDefaults.cardColors(containerColor = PrimaryLight),
                elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .padding(horizontal = 10.dp)
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