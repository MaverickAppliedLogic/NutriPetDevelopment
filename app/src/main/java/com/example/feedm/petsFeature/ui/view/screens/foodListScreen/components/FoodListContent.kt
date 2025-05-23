package com.example.feedm.petsFeature.ui.view.screens.foodListScreen.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
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
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
    onIconClicked: (Int) -> Unit = {},
    onDropdownClicked: () -> Unit = {}
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
                .padding(vertical = 16.dp, horizontal = 10.dp)
        ) {
            IconButton(onClick = { onDropdownClicked() },
                modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = "Dropdown"
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            LazyColumn(
                state = lazyListState,
            ) {
                items(foodList.size) { item ->
                    FoodItemCard(id = foodList[item].first,
                        name = foodList[item].second,
                        image = foodList[item].third,
                        isEditing = isEditing,
                        onCardClicked = { onCardClicked(it)},
                        onIconClicked = { onIconClicked(it) })
                    if (item == foodList.size - 1) {
                        Spacer(Modifier.height(150.dp))
                    }
                }
            }
        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            val shadowAnim by animateDpAsState(
                targetValue = if (isEditing) 0.dp else 10.dp,
            )
            AnimatedVisibility(
                visible = !isEditing,
                enter = slideInVertically(initialOffsetY = { it + it / 4 }),
                exit = slideOutVertically(targetOffsetY = { it + it / 4 })
            ) {

                FloatingActionButton(
                    onClick = { onAddButtonClicked() },
                    containerColor = Primary,
                    elevation = FloatingActionButtonDefaults.elevation(shadowAnim)
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Add")
                }
            }
            FloatingActionButton(
                onClick = { onEditButtonClicked() },
                containerColor = Primary,
            ) {
                Icon(
                    imageVector = if (!isEditing) Icons.Default.Edit else Icons.Default.Close,
                    contentDescription = "Edit"
                )
            }

        }
    }
}