package com.example.feedm.petsFeature.ui.view.screens.foodListScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.feedm.core.ui.theme.Neutral
import com.example.feedm.core.ui.theme.NeutralLight
import com.example.feedm.core.ui.theme.Primary

@Preview(showBackground = true)
@Composable
fun FoodListContent(
    modifier: Modifier = Modifier,
    foodList: List<Triple<Int, String, Int>> = emptyList(),
    isEditing: Boolean = false,
    onAddButtonClicked: () -> Unit = {},
    onEditButtonClicked: () -> Unit = {},
    onCardClicked: (Int) -> Unit = {},
    onIconClicked: (Int) -> Unit = {}
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
                .padding(vertical = 16.dp,horizontal = 10.dp)
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
                        name = foodList[item].second,
                        image = foodList[item].third,
                        isEditing = isEditing,
                        onCardClicked = {},
                        onIconClicked = { onIconClicked(it) })
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
                Icon(imageVector = if (!isEditing) Icons.Default.Edit else Icons.Default.Close
                    , contentDescription = "Edit")
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