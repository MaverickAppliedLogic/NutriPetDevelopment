package com.example.feedm.petsFeature.ui.view.screens.dashboardScreen.components.contentFields

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.feedm.R
import com.example.feedm.petsFeature.domain.objectTasks.pet.model.PetModel
import com.example.feedm.petsFeature.ui.view.components.CustomDropDownMenu
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

@Composable
fun PetList(
    petList: List<PetModel>,
    modifier: Modifier = Modifier,
) {
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    var isExpanded by remember { mutableStateOf(false) }
    Column(
        modifier = modifier, verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CustomDropDownMenu(options = petList.map { it.petName },
            title = "",
            selectedOption = petList.first().petName,
            errorCommitting = false,
            onSelectOption = {},
            onDeleteIconClicked = {},
            modifier = Modifier.fillMaxWidth(0.5f)
        )
        LazyRow(
            Modifier.weight(0.8f), state = listState,
            contentPadding = PaddingValues(vertical = 40.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            items(petList.size) { petIndex ->
                Image(
                    painter = if (petList[petIndex].animal != "dog")
                        painterResource(R.drawable.icono_gato_sinfondo)
                    else painterResource(R.drawable.img_dog_illustration),
                    contentDescription = "", modifier = Modifier
                        .fillParentMaxSize()
                )
                LaunchedEffect(listState) {
                    snapshotFlow { listState.isScrollInProgress }
                        .distinctUntilChanged()
                        .collect {
                            val firstItem = listState.firstVisibleItemIndex
                            if (!it) {
                                coroutineScope.launch {
                                    listState.animateScrollToItem(firstItem)

                                }
                            }
                        }
                }
            }


        }
    }

}