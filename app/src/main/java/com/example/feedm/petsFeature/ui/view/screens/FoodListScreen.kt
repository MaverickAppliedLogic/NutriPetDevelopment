package com.example.feedm.petsFeature.ui.view.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.feedm.R
import com.example.feedm.core.ui.theme.Neutral
import com.example.feedm.core.ui.theme.NeutralLight
import com.example.feedm.core.ui.theme.Primary
import com.example.feedm.core.ui.theme.PrimaryLight
import com.example.feedm.ui.viewmodel.FoodsListViewmodel

@Composable
fun FoodListScreen(
    foodsListViewmodel: FoodsListViewmodel,
    navToAddFood: () -> Unit,
    navToBackStack: () -> Unit
) {
    val foodList by foodsListViewmodel.foods.collectAsStateWithLifecycle()
    val formattedFoodList by remember {
        derivedStateOf {
            foodList.map { food ->
                Triple(food.foodId, food.foodName, R.drawable.ic_tailycare)
            }
        }
    }
    Scaffold(
        contentWindowInsets = WindowInsets.safeDrawing
    ) {
        FoodListContent(
            foodList = formattedFoodList,
            modifier = Modifier.padding(it),
            onAddButtonClicked = { navToAddFood() },
            onEditButtonClicked = { }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun FoodListContent(
    modifier: Modifier = Modifier,
    foodList: List<Triple<Int, String, Int>> = emptyList(),
    onAddButtonClicked: () -> Unit = {},
    onEditButtonClicked: () -> Unit = {}
) {
    val lazyListState = rememberLazyListState()

    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(NeutralLight, Neutral)
                    )
                )
                .padding(16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = "Dropdown",
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(30.dp))
            LazyColumn(
                state = lazyListState,
            ) {
                items(foodList.size) { item ->
                    FoodItemCard(id = foodList[item].first,
                        name = foodList[item].second, image = foodList[item].third)
                }

            }
        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            FloatingActionButton(
                onClick = { onEditButtonClicked() },
                containerColor = Primary
            ) {
                Icon(Icons.Default.Edit, contentDescription = "Edit")
            }

            FloatingActionButton(
                onClick = { onAddButtonClicked() },
                containerColor = Primary
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
    }
}

@Composable
fun FoodItemCard(
    id: Int,
    name: String,
    image: Int,
    onCardClicked:(Int) -> Unit = {}
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(containerColor = PrimaryLight),
        modifier = Modifier
            .padding(vertical = 16.dp)
            .fillMaxWidth()
            .height(80.dp)
            .clickable { onCardClicked(id) }
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = name,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp),
                style = MaterialTheme.typography.titleMedium
            )

            Image(
                painter = painterResource(id = image),
                contentDescription = name,
                modifier = Modifier
                    .fillMaxHeight()
                    .aspectRatio(1f),
                contentScale = ContentScale.Crop
            )
        }
    }
}
