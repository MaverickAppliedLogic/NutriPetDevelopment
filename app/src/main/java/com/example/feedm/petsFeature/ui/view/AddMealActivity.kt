package com.example.feedm.petsFeature.ui.view

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.feedm.core.ui.components.CustomDropDownMenu
import com.example.feedm.core.ui.components.CustomTimePicker
import com.example.feedm.core.ui.theme.TailyCareTheme
import com.example.feedm.petsFeature.domain.objectTasks.food.model.FoodModel
import com.example.feedm.petsFeature.domain.objectTasks.meal.model.MealModel
import com.example.feedm.petsFeature.ui.viewmodel.AddMealViewmodel
import com.example.feedm.ui.view.theme.Orange
import com.example.feedm.ui.view.theme.OrangeSemiTransparent
import com.example.feedm.ui.view.theme.RedSemiTransparent
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale
import kotlin.time.measureTime


@AndroidEntryPoint
class AddMealActivity : ComponentActivity() {

    //TODO cambiar imagen flor
    //TODO implementar TimeSetter
    //TODO implementar borrar comida
    private val addMealViewmodel: AddMealViewmodel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val petId = intent.extras!!.getInt("PetId")
        val emptyFood = FoodModel(
            foodName = "",
            foodWeight = 0f,
            calories = 0f,
            brand = "test"
        )
        setContent {
            TailyCareTheme {
                val foodsList: List<FoodModel> by addMealViewmodel.foods
                    .observeAsState(initial = emptyList())

                var isNewFood by remember { mutableStateOf(true) }
                var food by remember { mutableStateOf(emptyFood) }
                var hour by remember { mutableIntStateOf(0) }
                var min by remember { mutableIntStateOf(0) }
                var calories by remember { mutableStateOf("") }
                var ration by remember { mutableStateOf("") }
                LaunchedEffect(Unit) {
                    val measureTime = measureTime { addMealViewmodel.getFoodsByPetId(petId) }
                    Log.i("Time to fetch foods: $measureTime", "Time to fetch foods: $measureTime")
                }
                var timePickerIsVisible by remember { mutableStateOf(false) }
                val timePickerBackground by animateDpAsState(
                    targetValue = if (timePickerIsVisible) 20.dp
                    else 0.dp
                )

                val namePadding by animateDpAsState(
                    targetValue = if (isNewFood) 105.dp else 25.dp,
                    animationSpec = spring(dampingRatio = 0.7f, stiffness = 150f),
                    label = "PaddingAnimation"
                )

                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = Color.White)
                ) { innerPadding ->
                    Screen(
                        isNewFood = isNewFood,
                        foodsList = foodsList,
                        food = food,
                        calories = calories,
                        ration = ration,
                        hour = hour,
                        min = min,
                        onNameChange = { food = food.copy(foodName = it) },
                        onCaloriesChange = { calories = it },
                        onRationChange = { ration = it },
                        onCancelClicked = {
                            if (!timePickerIsVisible) {
                                intent.setClass(
                                    this@AddMealActivity,
                                    PetDetailsActivity::class.java
                                )
                                startActivity(intent)
                            }
                        },
                        onCommitClicked = {
                            if (!timePickerIsVisible) {
                                food = food.copy(calories = calories.toFloat())
                                commit(
                                    ration,
                                    min,
                                    hour,
                                    calories,
                                    food,
                                    petId
                                )
                            }
                        },
                        onFoodSelected = {
                            isNewFood = it == "Nueva comida"
                            if (it != "Nueva comida") {
                                foodsList.forEach { foodModel ->
                                    if (it == foodModel.foodName) {
                                        food = foodModel
                                        calories = food.calories.toString()
                                    }
                                }
                            } else food = emptyFood
                        },
                        timePickerIsVisible = timePickerIsVisible,
                        namePadding = namePadding,
                        modifier = Modifier
                            .padding(innerPadding)
                            .blur(timePickerBackground),
                    )
                    val timePickerPaddingTopAnim by animateDpAsState(
                        targetValue = if (timePickerIsVisible) 200.dp
                        else {
                            495.dp + namePadding
                        },
                        animationSpec = spring(dampingRatio = 0.7f, stiffness = 150f),
                        label = "TimePickerAnimation"
                    )
                    val timePickerPaddingBottomAnim by animateDpAsState(
                        targetValue = if (timePickerIsVisible) 200.dp
                        else {
                            if (isNewFood) 195.dp
                            else 275.dp

                        },
                        animationSpec = tween(),
                        label = "TimePickerAnimation"
                    )

                    val timePickerHorizontalPaddingAnim by animateDpAsState(
                        targetValue =
                        if (timePickerIsVisible) 30.dp else 125.dp,
                    )
                    if (timePickerIsVisible) {
                        Box(modifier = Modifier
                            .fillMaxSize()
                            .alpha(0f)) {
                            Card(
                                onClick = { timePickerIsVisible = false },
                                modifier = Modifier
                                    .fillMaxSize()
                                    .alpha(0f)
                            ) { }
                        }

                    }
                    Card(
                        onClick = { timePickerIsVisible = true },
                        elevation = CardDefaults.cardElevation(defaultElevation = 1.25.dp),
                        colors = CardDefaults.cardColors(containerColor = OrangeSemiTransparent),
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(
                                top = timePickerPaddingTopAnim,
                                bottom = timePickerPaddingBottomAnim,
                                start = timePickerHorizontalPaddingAnim,
                                end = timePickerHorizontalPaddingAnim
                            )
                    ) {

                        if (timePickerIsVisible) {
                            CustomTimePicker(
                                onConfirm = { timePickerIsVisible = false },
                                onDismiss = { timePickerIsVisible = false },
                                modifier = Modifier
                                    .align(Alignment.CenterHorizontally)
                                    .padding(vertical = 10.dp)
                            )
                        } else {
                            Box(modifier = Modifier.fillMaxSize()) {
                                Text(
                                    text = String.format(
                                        Locale.getDefault(),
                                        "%02d:%02d", hour, min
                                    ),
                                    textAlign = TextAlign.Center,
                                    letterSpacing = 2.sp,
                                    style = MaterialTheme.typography.headlineLarge,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .align(Alignment.Center)
                                )
                            }
                        }

                    }

                }
            }
        }
    }

    private fun commit(
        ration: String, hour: Int, min: Int, calories: String, food: FoodModel,
        petId: Int
    ) {
        addMealViewmodel.addMeal(
            ration = ration.toFloat(),
            hour = hour,
            min = min,
            mealCalories = calories.toDouble(),
            petId = petId,
            food = food,
            mealModel = null
        )
        intent.setClass(this@AddMealActivity, PetDetailsActivity::class.java)
        startActivity(intent)
    }

}


@Composable
fun Screen(
    isNewFood: Boolean,
    foodsList: List<FoodModel>,
    food: FoodModel,
    calories: String,
    ration: String,
    hour: Int,
    min: Int,
    onNameChange: (String) -> Unit,
    onCaloriesChange: (String) -> Unit,
    onRationChange: (String) -> Unit,
    onCancelClicked: () -> Unit,
    onCommitClicked: () -> Unit,
    onFoodSelected: (String) -> Unit,
    timePickerIsVisible: Boolean,
    namePadding: Dp,
    modifier: Modifier = Modifier
) {

    val imageScaleAnim by animateDpAsState(
        targetValue = if (isNewFood) 250.dp else 0.dp,
        animationSpec = spring(),
        label = "FoodAnimation"
    )

    val textColorAnim by animateColorAsState(
        targetValue = if (isNewFood) Color.Black else Color.LightGray,
        animationSpec = tween(),
        label = "FoodAnimation"
    )




    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .fillMaxSize()
                .background(color = Color.White)
        ) {
            Spacer(Modifier.padding(10.dp))
            Box(modifier = Modifier) {
                OutlinedTextField(
                    value = food.foodName,
                    supportingText = {},
                    onValueChange = { onNameChange(it) },
                    label = { Text(text = "Nombre") },
                    enabled = isNewFood,
                    isError = false,
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color.White,
                        errorIndicatorColor = Color.Red, errorLabelColor = Color.Red,
                        errorTextColor = Color.Red, errorContainerColor = RedSemiTransparent,
                        focusedContainerColor = Color.White, focusedLabelColor = Orange,
                        focusedIndicatorColor = Orange, disabledContainerColor = Color.White,
                        disabledTextColor = Color.White, disabledLabelColor = Color.White,
                        focusedTrailingIconColor = Orange,
                    ),
                    modifier = Modifier
                        .padding(top = namePadding)
                        .width(300.dp)
                )
                Card(
                    shape = RoundedCornerShape(5.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.25.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    modifier = Modifier
                        .padding(top = 30.dp)
                        .height(60.dp)
                        .width(300.dp)
                ) {
                    CustomDropDownMenu(
                        options = foodsList.map { it.foodName }.plus("Nueva comida"),
                        title = "Comida",
                        selectedOption = if (isNewFood) "Nueva comida" else food.foodName,
                        errorCommitting = false,
                        onSelectOption = {
                            onFoodSelected(it)
                        },
                    )
                }
            }
            /* Image(
                 painter = painterResource(id = R.mipmap.test_image), contentDescription = "",
                 modifier = Modifier.size(imageScaleAnim)
             )
             */

            Spacer(modifier = Modifier.padding(20.dp))
            Text(
                text = "Aporte calorico",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = if (isNewFood) calories else food.calories.toString(),
                enabled = isNewFood,
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                supportingText = {},
                onValueChange = { onCaloriesChange(it) },
                trailingIcon = {
                    Text(
                        text = "Kcal/100gr",
                        modifier = Modifier.padding(end = 10.dp)
                    )
                },
                label = { },
                isError = false,
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White,
                    errorIndicatorColor = Color.Red, errorLabelColor = Color.Red,
                    errorTextColor = Color.Red, errorContainerColor = RedSemiTransparent,
                    focusedContainerColor = Color.White, focusedLabelColor = Orange,
                    focusedIndicatorColor = Orange, focusedTrailingIconColor = Orange
                ),
                modifier = Modifier
                    .width(300.dp)
            )
            Text(
                text = "You can find that information on a side or on the back of the bag",
                fontSize = 17.sp,
                color = textColorAnim,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 50.dp)
            )

            Spacer(modifier = Modifier.padding(10.dp))
            Text(
                text = "Racion",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = ration,
                supportingText = {},
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                onValueChange = { onRationChange(it) },
                trailingIcon = { Text(text = "gr", modifier = Modifier.padding(end = 10.dp)) },
                label = { },
                isError = false,
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White,
                    errorIndicatorColor = Color.Red, errorLabelColor = Color.Red,
                    errorTextColor = Color.Red, errorContainerColor = RedSemiTransparent,
                    focusedContainerColor = Color.White, focusedLabelColor = Orange,
                    focusedIndicatorColor = Orange, focusedTrailingIconColor = Orange
                ),
                modifier = Modifier
                    .width(300.dp)
            )
            Spacer(modifier = Modifier.padding(10.dp))
            Text(
                text = "Hora",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )


        }
        Box(modifier.fillMaxSize()) {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp)
                    .align(Alignment.BottomCenter)

            ) {
                FloatingActionButton(
                    onClick =  onCancelClicked ,
                    elevation = FloatingActionButtonDefaults.elevation(1.25.dp),
                    containerColor = Orange,
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.width(150.dp)
                ) {
                    Text(
                        text = "Cancelar",
                        style = TextStyle(fontWeight = FontWeight.Bold),
                        color = Color.White
                    )
                }
                Spacer(modifier = Modifier.padding(15.dp))
                FloatingActionButton(
                    onClick = onCommitClicked,
                    elevation = FloatingActionButtonDefaults.elevation(1.25.dp),
                    containerColor = Orange,
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.width(150.dp)
                ) {
                    Text(
                        text = "Agregar",
                        style = TextStyle(fontWeight = FontWeight.Bold),
                        color = Color.White
                    )
                }
            }
        }
    }


}


@Preview(showBackground = true)
@Composable
fun ScreenPreview() {
    TailyCareTheme {
        var isNewMeal by remember { mutableStateOf(true) }
        var meal by remember {
            mutableStateOf(
                MealModel(
                    mealId = -1,
                    petId = -1,
                    mealTime = 0,
                    ration = 0f
                )
            )
        }
        var food by remember {
            mutableStateOf(
                FoodModel(
                    foodId = -1,
                    foodName = "test",
                    foodWeight = 1f,
                    calories = 0f,
                    brand = "test"
                )
            )
        }
        var calories by remember { mutableStateOf("") }
        var ration by remember { mutableStateOf("") }


        Scaffold(modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
            bottomBar = {
                BottomAppBar(containerColor = Color.White) {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = Color.White)
                    ) {
                        FloatingActionButton(
                            onClick = {
                            },
                            elevation = FloatingActionButtonDefaults.elevation(1.25.dp),
                            containerColor = Orange,
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier.width(200.dp)
                        ) {
                            Text(
                                text = "Agregar",
                                style = TextStyle(fontWeight = FontWeight.Bold),
                                color = Color.White
                            )
                        }
                    }
                }
            }) { innerPadding ->
            Screen(
                isNewFood = isNewMeal,
                foodsList = emptyList(),
                food = food,
                calories = calories,
                ration = ration,
                hour = 0,
                min = 0,
                onNameChange = { food = food.copy(foodName = it) },
                onCaloriesChange = { calories = it },
                onRationChange = { ration = it },
                onCancelClicked = { meal = meal.copy(mealTime = 0) },
                onCommitClicked = { meal = meal.copy(mealTime = 1) },
                onFoodSelected = {
                    isNewMeal = it == "Nueva comida"
                },
                timePickerIsVisible = false,
                namePadding = 10.dp,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}