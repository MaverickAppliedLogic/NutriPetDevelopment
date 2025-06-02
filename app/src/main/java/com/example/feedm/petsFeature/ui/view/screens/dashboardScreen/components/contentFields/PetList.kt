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
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.twotone.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
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
    petModel: PetModel,
    onPetSelected: (PetModel) -> Unit,
    onEditIconClicked: () -> Unit,
    onDeleteIconClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    val coroutineScope = rememberCoroutineScope()
    val listKey = remember(petList) { petList.map { it.petId } }
    val listState = remember(listKey) { LazyListState() }
    var pet by remember { mutableStateOf<PetModel?>(null) }
    var thereArePrevious by remember { mutableStateOf(petList.size > 1) }
    var thereAreNext by remember { mutableStateOf(false) }
    var needRefresh by remember { mutableStateOf(true) }
   LaunchedEffect(listKey) {
       pet = petModel
       thereArePrevious = pet != petList.first()
       thereAreNext = pet != petList.last()
   }
    LaunchedEffect(pet) {
        if (pet != null && pet != petModel) {
            onPetSelected(pet!!)
            thereArePrevious = pet != petList.first()
            thereAreNext = pet != petList.last()
            coroutineScope.launch {
                listState.animateScrollToItem(
                    petList.indexOf(pet)
                )
            }
        }
    }

    Column(
        modifier = modifier, verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            CustomDropDownMenu(
                options = petList.map { it.petName },
                selectedOption = pet?.petName ?: "",
                errorCommitting = false,
                onSelectOption = { selectedOption ->
                    pet = petList.find { it.petName == selectedOption }!!
                },
                onDeleteIconClicked = {},
                modifier = Modifier.fillMaxWidth(0.5f)
            )

            Row(modifier = Modifier.fillMaxWidth(0.95f), horizontalArrangement = Arrangement.End) {
                IconButton(onClick = { onDeleteIconClicked(); needRefresh = true }) {
                    Icon(
                        imageVector = Icons.TwoTone.Delete,
                        contentDescription = "",
                        tint = Error
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = { onEditIconClicked() }) {
                    Icon(
                        imageVector = Icons.Default.Create,
                        contentDescription = "",
                        tint = SecondaryDarkest
                    )
                }
            }
        }

        Box(Modifier.weight(0.8f), contentAlignment = Alignment.CenterStart) {
            LazyRow(
                state = listState,
                horizontalArrangement = Arrangement.Center
            ) {
                items(petList.size, key = {petList[it].petId}) {petIndex ->
                    val petAnimal by remember { mutableStateOf(petList[petIndex].animal) }
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
                                    pet = petList[firstItem]
                                }
                            }
                    }
                }
            }
            if (petList.size > 1) {
                Row {
                    Spacer(modifier = Modifier.weight(0.15f))
                    IconButton(
                        onClick = {
                            pet = petList[petList.indexOf(pet) - 1]
                            println(pet)
                        },
                        enabled = thereArePrevious
                    ) {
                        AnimatedVisibility(
                            visible = thereArePrevious,
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
                            pet = petList[petList.indexOf(pet) + 1]
                            println(pet)
                        },
                        enabled = thereAreNext
                    ) {
                        AnimatedVisibility(
                            visible = thereAreNext,
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
}