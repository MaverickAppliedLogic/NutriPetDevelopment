package com.maverickapps.tailyCare.petsFeature.ui.view.screens.foodListScreen.components.contentComponents

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.maverickapps.tailyCare.core.ui.theme.Neutral
import com.maverickapps.tailyCare.core.ui.theme.NeutralLight
import com.maverickapps.tailyCare.core.ui.theme.Primary
import com.maverickapps.tailyCare.core.ui.theme.dimens

@Preview(showBackground = true)
@Composable
fun ContentDisplay(
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
                .padding(
                    vertical = MaterialTheme.dimens.small1,
                    horizontal = MaterialTheme.dimens.extraSmall4
                )
        ) {
            IconButton(onClick = { onDropdownClicked() },
                modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = "Dropdown"
                )
            }
            Spacer(modifier = Modifier.height(MaterialTheme.dimens.extraSmall4))
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
                        Spacer(Modifier.height(MaterialTheme.dimens.extraLarge2))
                    }
                }
            }
        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(MaterialTheme.dimens.small1),
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.dimens.extraSmall4)
        ) {
            val shadowAnim by animateDpAsState(
                targetValue = if (isEditing) 0.dp else MaterialTheme.dimens.extraSmall4,
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