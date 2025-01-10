package com.example.feedm.petMealsFeature.ui.view


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.feedm.R
import com.example.feedm.core.domain.model.MealModel
import com.example.feedm.core.domain.model.PetModel
import com.example.feedm.petMealsFeature.domain.model.PetMealsModel
import com.example.feedm.petMealsFeature.ui.viewmodel.PetMealViewModel
import com.example.feedm.petsFeature.ui.view.EditPetActivity
import com.example.feedm.petsFeature.ui.view.PetsActivity
import com.example.feedm.ui.view.theme.AlmostWhite
import com.example.feedm.ui.view.theme.Orange
import com.example.feedm.ui.view.theme.TailyCareTheme
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.internal.format

@AndroidEntryPoint
class PetDetailsActivity : ComponentActivity() {

    private val petMealViewModel: PetMealViewModel by viewModels()

    @ExperimentalMaterial3Api
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val petId = intent.extras!!.getInt("PetId")
        setContent {

            TailyCareTheme {
                val petMeals: PetMealsModel by
                petMealViewModel.petMeals.observeAsState(initial = PetMealsModel())
                petMealViewModel.getPetMeals(petId)
                val scrollState = rememberScrollState()

                val topAppBarColor by animateColorAsState(
                    targetValue = if (scrollState.value > 470) Color.White else Orange, label = ""
                )


                val topAppBarElevation by animateDpAsState(
                    targetValue = if (scrollState.value > 470) 0.dp else 2.dp, label = ""
                )

                Scaffold(modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            colors = TopAppBarColors(
                                containerColor = topAppBarColor,
                                scrolledContainerColor = Color.White,
                                navigationIconContentColor = Color.Black,
                                actionIconContentColor = Color.Black,
                                titleContentColor = Color.Black
                            ),
                            title = {
                                Text(
                                    text = petMeals.pet.petName,
                                    style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.fillMaxWidth()
                                )
                            },
                            navigationIcon = {
                                IconButton(onClick = { goBack() }) {
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                                        contentDescription = "ArrowBack",
                                        tint = Color.Black
                                    )
                                }
                            },
                            actions = {
                                Row {
                                    IconButton(onClick = { editPet() }) {
                                        Icon(
                                            imageVector = Icons.Default.Create,
                                            contentDescription = "ArrowBack",
                                            tint = Color.Black
                                        )

                                    }
                                }
                            },
                            modifier = Modifier.shadow(topAppBarElevation)
                        )
                    }) { innerPadding ->

                    ScaffoldContent(
                        petMeals = petMeals,
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                            .verticalScroll(scrollState)
                            .background(Color.White)
                    )

                }
            }
        }
    }

    private fun editPet(){
        intent.setClass(this@PetDetailsActivity, EditPetActivity::class.java)
        startActivity(intent)
    }

    private fun goBack(){
        intent.setClass(this@PetDetailsActivity, PetsActivity::class.java)
        intent.removeExtra("PetId")
        startActivity(intent)
    }

}


@Composable
fun PetImage(petAnimal: String) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(125.dp)
            .background(color = Orange)
    ) {
        val imageResourceId = if (petAnimal == "dog") {
            R.drawable.img_dog_illustration
        } else {
            R.drawable.gato
        }
        Image(
            painter = painterResource(id = imageResourceId),
            contentDescription = "PetModel Image",
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 20.dp)
        )
    }
}


@Composable
fun ScaffoldContent(
    petMeals: PetMealsModel,
    modifier: Modifier = Modifier
) {

    Column(modifier = modifier.fillMaxSize()) {
        PetImage(petMeals.pet.animal)
        Spacer(modifier = Modifier.padding(15.dp))
        PetDetailsActivityModules(
            petMeals = petMeals,
            modifier = Modifier.padding(horizontal = 15.dp)
        )
        Spacer(modifier = Modifier.padding(5.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.White),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = { /*TODO*/ },
                colors = ButtonColors(
                    contentColor = Color.White,
                    containerColor = Orange,
                    disabledContentColor = Color.White,
                    disabledContainerColor = Orange
                ),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .width(150.dp)
                    .height(50.dp)
                    .shadow(elevation = 2.dp, shape = RoundedCornerShape(10.dp))
            ) {
                Text(text = "Atras")
            }
        }
        Spacer(modifier = Modifier.padding(10.dp))
    }
}

@Composable
fun PetDetailsActivityModules(
    petMeals: PetMealsModel,
    modifier: Modifier = Modifier
) {
    val spacerPadding = 20.dp
    MealsModule(petMeals.meals, modifier)
    Spacer(modifier = Modifier.padding(spacerPadding))
    HealthModule(petMeals.pet, modifier)
    Spacer(modifier = Modifier.padding(spacerPadding))
    RecordModule(petMeals.pet, modifier)
    Spacer(modifier = Modifier.padding(spacerPadding))
}

@Composable
fun MealsModule(
    meals: List<MealModel>,
    modifier: Modifier = Modifier
) {
    val modifierForElements = Modifier
        .fillMaxWidth()
        .background(color = Color.White)
        .padding(start = 7.dp, top = 5.dp)
    Card(
        elevation = CardDefaults.cardElevation(2.dp),
        shape = RoundedCornerShape(5.dp),
        modifier = modifier
            .fillMaxWidth()
            .background(color = Color.White)
    ) {
        Column(modifier = modifierForElements) {
            Row(modifier = modifierForElements) {
                Text(
                    text = stringResource(R.string.pia_FoodModuleTitle),
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                )
            }
            HorizontalDivider(
                modifier = Modifier.padding(top = 5.dp, end = 5.dp),
                color = Color.Gray
            )
            for (meal in meals) {
                MealItem(meal, modifierForElements)
                HorizontalDivider(
                    modifier = Modifier.padding(top = 5.dp, end = 15.dp, start = 5.dp)
                )

            }
            Row(modifier = modifierForElements.padding(bottom = 5.dp)) {
                Text(
                    text = stringResource(R.string.pia_FoodModuleBottomTxt),
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                )
                Spacer(modifier = Modifier.width(65.dp))
                Text(
                    text = "195/200 gr",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                )
            }
        }
    }
}

@Composable
fun MealItem(
    meal: MealModel,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Text(
            text = "Pienso",
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
        )
        Spacer(modifier = Modifier.width(110.dp))
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(5.dp)
                .height(25.dp)
                .width(35.dp)
                .background(color = AlmostWhite)
                .border(width = 1.dp, color = Color.LightGray)

        ) {
            Text(
                text = meal.ration.toInt().toString(),
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
            )
        }
        Text(
            text = "gr",
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
        )
        Spacer(modifier = Modifier.width(5.dp))
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(5.dp)
                .height(25.dp)
                .width(35.dp)
                .clip(RoundedCornerShape(7.dp))
                .background(color = AlmostWhite)
                .border(width = 1.dp, color = Color.LightGray, shape = RoundedCornerShape(7.dp))

        ) {
            Text(
                text = "10",
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
            )
        }
        Text(
            text = ":",
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
        )
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(5.dp)
                .height(25.dp)
                .width(35.dp)
                .clip(RoundedCornerShape(7.dp))
                .background(color = AlmostWhite)
                .border(width = 1.dp, color = Color.LightGray, shape = RoundedCornerShape(7.dp))

        ) {
            Text(
                text = "30",
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
            )
        }
        Text(
            text = "h",
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
        )
    }
}

@Composable
fun HealthModule(
    pet: PetModel,
    modifier: Modifier = Modifier
) {
    val modifierForElements = Modifier
        .fillMaxWidth()
        .background(color = Color.White)
        .padding(start = 7.dp, top = 10.dp, bottom = 10.dp)
    Card(
        elevation = CardDefaults.cardElevation(2.dp),
        shape = RoundedCornerShape(5.dp),
        modifier = modifier
            .fillMaxWidth()
            .background(color = Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.White)
                .padding(start = 7.dp)
        ) {
            Text(
                text = stringResource(R.string.pia_HealthModuleTitle),
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(start = 7.dp, top = 10.dp)
            )
            HorizontalDivider(
                modifier = Modifier.padding(top = 5.dp, end = 5.dp),
                color = Color.Gray
            )
            val weightToString = format("%.2f", pet.petWeight)
            Row(modifier = modifierForElements.padding(top = 5.dp)) {
                Text(
                    text = stringResource(R.string.pia_HealthModuleTxt1),
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                )
                Text(
                    textAlign = TextAlign.End,
                    text = "$weightToString Kg",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 15.dp)
                )
            }
            HorizontalDivider(
                modifier = Modifier.padding(top = 5.dp, bottom = 5.dp, end = 15.dp, start = 5.dp)
            )
            Row(modifier = modifierForElements) {
                Text(
                    text = stringResource(R.string.pia_HealthModuleTxt2),
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                )
                Text(
                    textAlign = TextAlign.End,
                    text = pet.goal,
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 15.dp)
                )
            }
            HorizontalDivider(
                modifier = Modifier.padding(top = 5.dp, bottom = 5.dp, end = 15.dp, start = 5.dp)
            )
            Row(modifier = modifierForElements.padding(bottom = 5.dp)) {
                Text(
                    text = stringResource(R.string.pia_HealthModuleTxt3),
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                )
                Text(
                    textAlign = TextAlign.End,
                    text = pet.activity ?: "--",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 15.dp)
                )
            }
        }
    }
}

@Composable
fun RecordModule(
    pet: PetModel,
    modifier: Modifier = Modifier
) {
    val modifierForElements = Modifier
        .fillMaxWidth()
        .background(color = Color.White)
        .padding(start = 7.dp, top = 10.dp, bottom = 10.dp)
    Card(
        elevation = CardDefaults.cardElevation(2.dp),
        shape = RoundedCornerShape(5.dp),
        modifier = modifier
            .fillMaxWidth()
            .background(color = Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.White)
                .padding(start = 7.dp)
        ) {
            Text(
                text = stringResource(R.string.pia_RecordModuleTitle),
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(start = 7.dp, top = 10.dp)
            )
            HorizontalDivider(
                modifier = Modifier.padding(top = 5.dp, end = 5.dp),
                color = Color.Gray
            )
            Row(modifier = modifierForElements.padding(top = 5.dp)) {
                Text(
                    text = stringResource(R.string.pia_RecordModuleTxt1),
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                )
                Text(
                    textAlign = TextAlign.End,
                    text = if (pet.sterilized) "Si" else "No",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 15.dp)
                )
            }
            HorizontalDivider(
                modifier = Modifier.padding(top = 5.dp, bottom = 5.dp, end = 15.dp, start = 5.dp)
            )
            Row(modifier = modifierForElements) {
                Text(
                    text = stringResource(R.string.pia_RecordModuleTxt2),
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                )
                Text(
                    textAlign = TextAlign.End,
                    text = pet.genre ?: "--",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 15.dp)
                )
            }
            HorizontalDivider(
                modifier = Modifier.padding(top = 5.dp, bottom = 5.dp, end = 15.dp, start = 5.dp)
            )
            Row(modifier = modifierForElements.padding(bottom = 5.dp)) {
                val ageToString = if (pet.age < 1) "Menos de 1" else pet.age.toInt().toString()
                Text(
                    text = stringResource(R.string.pia_RecordModuleTxt3),
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                )
                Text(
                    textAlign = TextAlign.End,
                    text = "$ageToString años",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 15.dp)
                )
            }
        }
    }
}


@ExperimentalMaterial3Api
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PetActivityScreenPreview(modifier: Modifier = Modifier) {
    TailyCareTheme {

        val scrollState = rememberScrollState()

        val topAppBarColor by animateColorAsState(
            targetValue = if (scrollState.value > 425) Color.White else Orange, label = ""
        )


        val topAppBarElevation by animateDpAsState(
            targetValue = if (scrollState.value > 425) 0.dp else 2.dp, label = ""
        )

        Scaffold(modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    colors = TopAppBarColors(
                        containerColor = topAppBarColor,
                        scrolledContainerColor = Color.White,
                        navigationIconContentColor = Color.Black,
                        actionIconContentColor = Color.Black,
                        titleContentColor = Color.Black
                    ),
                    title = {
                        Text(
                            text = "Toby",
                            style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                                contentDescription = "ArrowBack",
                                tint = Color.Black
                            )
                        }
                    },
                    actions = {
                        Row {
                            IconButton(onClick = { /*TODO*/ }) {
                                Icon(
                                    imageVector = Icons.Default.Create,
                                    contentDescription = "ArrowBack",
                                    tint = Color.Black
                                )
                            }
                        }
                    },
                    modifier = Modifier.shadow(topAppBarElevation)
                )
            }) { innerPadding ->
            ScaffoldContent(
                petMeals = PetMealsModel(),
                modifier = Modifier
                    .padding(innerPadding)
                    .verticalScroll(scrollState)
            )
        }
    }
}
