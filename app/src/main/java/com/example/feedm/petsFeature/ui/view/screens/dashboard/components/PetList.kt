package com.example.feedm.petsFeature.ui.view.screens.dashboard.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.feedm.R
import com.example.feedm.core.ui.theme.NeutralLight
import com.example.feedm.petsFeature.domain.objectTasks.pet.model.PetModel

@Composable
fun PetList(
    petList: List<PetModel>,
    modifier: Modifier = Modifier,
) {
    val listState = rememberLazyListState()
    LazyRow(
        modifier, state = listState,
        contentPadding = PaddingValues(vertical = 15.dp)
    ) {
        items(petList.size) { petIndex ->
            petList[petIndex].let {
                if (it.animal.equals("dog")) {
                    Icon(
                        painter = painterResource(R.drawable.img_dog_illustration),
                        contentDescription = "", modifier = Modifier.size(150.dp)
                    )
                } else {
                    Icon(
                        painter = painterResource(R.drawable.icono_gato_sinfondo),
                        contentDescription = "", modifier = Modifier.size(150.dp)
                    )
                }
            }
        }
    }
}
