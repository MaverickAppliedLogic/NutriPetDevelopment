package com.example.feedm.petsFeature.ui.view.screens.dashboardScreen.components.contentFields

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.twotone.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import com.example.feedm.R
import com.example.feedm.core.ui.theme.Error
import com.example.feedm.core.ui.theme.SecondaryDark
import com.example.feedm.core.ui.theme.SecondaryDarkest
import com.example.feedm.petsFeature.domain.objectTasks.pet.model.PetModel
import com.example.feedm.petsFeature.ui.view.components.CustomDropDownMenu
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

@Composable
fun PetList(
    petList: List<PetModel>,
    onPetSelected: (Int) -> Unit,
    onEditIconClicked: (Int) -> Unit,
    onDeleteIconClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    var petIndexSelected by remember { mutableIntStateOf(0) }
    var thereIsPrevious by remember { mutableStateOf(petIndexSelected != 0 ) }
    var thereIsNext by remember { mutableStateOf(petIndexSelected != petList.lastIndex) }
    LaunchedEffect(petIndexSelected) {
        onPetSelected(petList[petIndexSelected].petId)
        thereIsPrevious = petIndexSelected != 0
        thereIsNext = petIndexSelected != petList.lastIndex
    }

    Column(
        modifier = modifier, verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            CustomDropDownMenu(
                options = petList.map { it.petName },
                selectedOption = petList[petIndexSelected].petName,
                errorCommitting = false,
                onSelectOption = { selectedOption ->
                    petIndexSelected = petList.indexOfFirst { it.petName == selectedOption }
                    coroutineScope.launch {
                        listState.animateScrollToItem(petIndexSelected)
                    }
                },
                onDeleteIconClicked = {},
                modifier = Modifier.fillMaxWidth(0.5f)
            )

            Row(modifier = Modifier.fillMaxWidth(0.95f), horizontalArrangement = Arrangement.End) {
                IconButton(onClick = { onDeleteIconClicked()}) {
                    Icon(imageVector = Icons.TwoTone.Delete,
                        contentDescription = "",
                        tint = Error)
                }
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = { onEditIconClicked(petList[petIndexSelected].petId) }) {
                    Icon(imageVector = Icons.Default.Create,
                        contentDescription = "",
                        tint = SecondaryDarkest)
                }
            }
        }

        Box(Modifier.weight(0.8f), contentAlignment = Alignment.CenterStart) {
            LazyRow(
                state = listState,
                horizontalArrangement = Arrangement.Center
            ) {
                items(petList.size, key = { petList[it].petId }) { petIndex ->
                    val petAnimal by remember { mutableStateOf(petList[petIndex].animal)}
                    Box(
                        modifier = Modifier.fillParentMaxSize(),
                        contentAlignment = Alignment.BottomCenter
                    ) {
                        Image(
                            painter = if (petAnimal != "dog")
                                painterResource(R.drawable.icono_gato_sinfondo)
                            else painterResource(R.drawable.img_dog_illustration),
                            contentDescription = "", modifier = Modifier
                                .fillParentMaxSize()
                                .scale(0.65f)
                        )
                    }
                    LaunchedEffect(listState) {
                        snapshotFlow { listState.isScrollInProgress }
                            .distinctUntilChanged()
                            .collect {
                                val firstItem = listState.firstVisibleItemIndex
                                if (!it) {
                                    if (firstItem != petIndexSelected) {
                                        petIndexSelected = firstItem
                                    }
                                    listState.animateScrollToItem(petIndexSelected)
                                }
                            }
                    }
                    LaunchedEffect(petIndexSelected) {
                        onPetSelected(petIndexSelected)
                    }
                }


            }
            Row {
                Spacer(modifier = Modifier.weight(0.15f))
                IconButton(
                    onClick = {
                        petIndexSelected = petList.indexOfFirst {
                            it.petId == petList[petIndexSelected - 1].petId
                        }
                        coroutineScope.launch {
                            listState.animateScrollToItem(petIndexSelected)
                        }
                    },
                    enabled = thereIsPrevious
                ) {
                    AnimatedVisibility(
                        visible = thereIsPrevious,
                        enter = fadeIn(),
                        exit = fadeOut(),
                    ) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowDown,
                            contentDescription = "",
                            tint = SecondaryDark,
                            modifier = Modifier
                                .rotate(90f)
                                .scale(1.75f)
                                .alpha(0.5f)
                        )
                    }
                }
                Spacer(modifier = Modifier.weight(0.7f))
                IconButton(
                    onClick = {
                        petIndexSelected = petList.indexOfFirst {
                            it.petId == petList[petIndexSelected + 1].petId
                        }
                        coroutineScope.launch {
                            listState.animateScrollToItem(petIndexSelected)
                        }
                    },
                    enabled = thereIsNext
                ) {
                    AnimatedVisibility(
                        visible = thereIsNext,
                        enter = fadeIn(animationSpec = tween()),
                        exit = fadeOut(animationSpec = tween()),
                    ) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowDown,
                            contentDescription = "",
                            tint = SecondaryDark,
                            modifier = Modifier
                                .rotate(-90f)
                                .scale(1.75f)
                                .alpha(0.5f)
                        )
                    }
                }
                Spacer(modifier = Modifier.weight(0.15f))
            }
        }
    }
}