package com.example.feedm.petsFeature.ui.view.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.feedm.R
import com.example.feedm.core.ui.theme.Neutral
import com.example.feedm.core.ui.theme.NeutralLight
import com.example.feedm.core.ui.theme.Primary
import com.example.feedm.core.ui.theme.Secondary
import com.example.feedm.core.ui.theme.SecondaryDarkest
import com.example.feedm.petsFeature.domain.objectTasks.pet.model.PetModel
import com.example.feedm.petsFeature.ui.view.components.ModuleCard
import com.example.feedm.petsFeature.ui.view.components.ModuleItem
import com.example.feedm.petsFeature.ui.view.components.ModuleItemMeal
import com.example.feedm.petsFeature.ui.viewmodel.PetDetailsViewmodel
import com.example.feedm.petsFeature.utils.loopListHandler.LoopListHandler
import com.example.feedm.petsFeature.utils.loopListHandler.LoopListHandler.Companion.RECOVER_DATA

@Composable
fun DashBoardScreen(
    viewmodel: PetDetailsViewmodel,
    navTo: (String, Int?) -> Unit
) {
    val loopListHandler = LoopListHandler<Int>()
    loopListHandler.manageLoop(listOf(1,3,4).toMutableList(),RECOVER_DATA, onModifiedList = {})

    val scrollState = rememberScrollState()
    Scaffold(modifier = Modifier.fillMaxSize(),
        bottomBar = {

        }) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(NeutralLight, Neutral),
                        startY = 0f,
                        endY = Float.POSITIVE_INFINITY,
                        tileMode = TileMode.Clamp
                    )
                )
        )
        MainContent(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .verticalScroll(scrollState)
        )
        CustomBottomBar(navto = { navTo(it, 0) })
    }
}

@Composable
fun CustomBottomBar(
    navto: (String) -> Unit = {}
) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Primary),
                        startY = 30f,
                        endY = Float.POSITIVE_INFINITY,
                        tileMode = TileMode.Clamp
                    )
                )
        ) {
            FloatingActionButton(
                onClick = {navto("RegisterPet")},
                shape = CircleShape, containerColor = Primary,
                contentColor = SecondaryDarkest,
                modifier = Modifier
                    .padding(bottom = 20.dp)
                    .size(65.dp)
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "")
            }
        }
    }
}

@Composable
fun MainContent(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.background(Neutral),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val pets =  listOf(
            PetModel(
                petId = 1,
                animal = "Dog",
                petName = "Buddy",
                age = 3.5f,
                petWeight = 30.5f,
                genre = "Male",
                sterilized = true,
                activity = "High",
                goal = "Maintain weight",
                allergies = "None"
            ), PetModel(
                petId = 2,
                animal = "cat",
                petName = "Whiskers",
                age = 5.0f,
                petWeight = 4.2f,
                genre = "Female",
                sterilized = true,
                activity = "Low",
                goal = "Lose weight",
                allergies = "Fish"
            ),
            PetModel(
                petId = 3,
                animal = "dog",
                petName = "Kiwi",
                age = 1.0f,
                petWeight = 0.5f,
                genre = null,
                sterilized = false,
                activity = "Medium",
                goal = "Gain weight",
                allergies = "Nuts"
            ),
            PetModel(
                petId = 4,
                animal = "dog",
                petName = "Max",
                age = 7.0f,
                petWeight = 38.0f,
                genre = "Male",
                sterilized = true,
                activity = "Low",
                goal = "Maintain weight",
                allergies = null
            )
        )
        PetList(
            pets,
            modifier =
            Modifier
                .background(NeutralLight)
                .fillMaxWidth()
                .weight(1f,true)
        )
        Row(
            Modifier
                .height(50.dp)
                .fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(NeutralLight, Neutral),
                            startY = 0f,
                            endY = Float.POSITIVE_INFINITY,
                            tileMode = TileMode.Clamp
                        )
                    )
            )
        }
        Column(
            Modifier
                .fillMaxSize()
                .padding(horizontal = 15.dp)
        ) {
            MealsModule()
            Spacer(Modifier.height(50.dp))
            HealthModule()
            Spacer(Modifier.height(50.dp))
            DataModule()
            Spacer(Modifier.height(100.dp))
        }

    }
}

@Composable
fun PetList(
    petList: List<PetModel>,
    modifier: Modifier = Modifier,
) {
    val listState = rememberLazyListState()
    //TODO scrollable list with pets
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


@Composable
fun MealsModule() {
    var editable by remember { mutableStateOf(false) }
    ModuleCard(
        headerTitle = "Comidas Diarias",
        headerIcon = {
            Icon(
                imageVector = if (!editable) Icons.Default.Edit else Icons.Default.Close,
                contentDescription = "",
                tint = SecondaryDarkest,
                modifier = Modifier
                    .padding(bottom = 5.dp)
                    .clickable { editable = !editable }
            )
        },
        captionEnabled = true,
        captionHead = "Recomendado",
        captionTrailing = "Example"
    ) {
        ModuleItemMeal(state = 0, modifier = Modifier.height(40.dp), editable = editable)
        HorizontalDivider(
            modifier = Modifier.padding(vertical = 5.dp, horizontal = 5.dp), color = Secondary
        )
        ModuleItemMeal(state = 1, modifier = Modifier.height(40.dp), editable = editable)
        HorizontalDivider(
            modifier = Modifier.padding(vertical = 5.dp, horizontal = 5.dp), color = Secondary
        )
        ModuleItemMeal(state = 2, modifier = Modifier.height(40.dp), editable = editable)
    }
}

@Composable
fun HealthModule() {
    ModuleCard(headerTitle = "Salud") {
        ModuleItem(
            Modifier
                .height(50.dp)
                .padding(end = 5.dp)
        )
        HorizontalDivider(
            modifier = Modifier.padding(vertical = 5.dp, horizontal = 5.dp), color = Secondary
        )
        ModuleItem(
            Modifier
                .height(50.dp)
                .padding(end = 5.dp)
        )
        HorizontalDivider(
            modifier = Modifier.padding(vertical = 5.dp, horizontal = 5.dp), color = Secondary
        )
        ModuleItem(
            Modifier
                .height(50.dp)
                .padding(end = 5.dp)
        )
    }

}

@Composable
fun DataModule() {
    ModuleCard(headerTitle = "Datos") {
        ModuleItem(
            Modifier
                .height(50.dp)
                .padding(end = 5.dp)
        )
        HorizontalDivider(
            modifier = Modifier.padding(vertical = 5.dp, horizontal = 5.dp), color = Secondary
        )
        ModuleItem(
            Modifier
                .height(50.dp)
                .padding(end = 5.dp)
        )
        HorizontalDivider(
            modifier = Modifier.padding(vertical = 5.dp, horizontal = 5.dp), color = Secondary
        )
        ModuleItem(
            Modifier
                .height(50.dp)
                .padding(end = 5.dp)
        )
    }
}

