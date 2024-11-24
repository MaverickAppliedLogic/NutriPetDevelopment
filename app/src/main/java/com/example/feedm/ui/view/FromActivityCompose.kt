package com.example.feedm.ui.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.MenuItemColors
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderColors
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.SliderState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.feedm.R
import com.example.feedm.domain.model.Pet
import com.example.feedm.ui.view.ui.theme.Orange
import com.example.feedm.ui.view.ui.theme.OrangeSemiSoft
import com.example.feedm.ui.view.ui.theme.OrangeSoft
import com.example.feedm.ui.view.ui.theme.TailyCareTheme
import com.example.feedm.ui.viewmodel.PetViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FromActivityCompose : ComponentActivity() {

    val petViewModel: PetViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        petViewModel.onCreate()
        setContent {
            TailyCareTheme {

                var id = -1
                var name by remember { mutableStateOf("") }
                var age by remember { mutableStateOf("") }
                var sex by remember { mutableStateOf("") }
                var weight by remember { mutableFloatStateOf(0f) }
                var sterilized by remember { mutableStateOf("") }
                var activityLevel by remember { mutableStateOf("") }
                var objective by remember { mutableStateOf("") }

                if (petViewModel.pets.value!!.size > 0) {
                    var foundId = 0
                    for (i in petViewModel.pets.value as List<Pet>) {
                        if (i.id != foundId) id = foundId
                        else {
                            foundId++
                            id = foundId
                        }
                    }
                }

                Scaffold(modifier = Modifier.fillMaxSize(), containerColor = Color.White,
                    bottomBar = {
                        BottomAppBar(
                            containerColor = Color.White,
                            tonalElevation = 20.dp
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.Center,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                FloatingActionButton(
                                    onClick = {
                                        commitAddNewPet(
                                            Pet(
                                                id = id,
                                                animal = "dog",
                                                nombre = name,
                                                edad = age,
                                                sexo = sex,
                                                peso = weight.toDouble(),
                                                esterilizado = sterilized,
                                                actividad = activityLevel,
                                                objetivo = objective
                                            )
                                        )
                                    },
                                    containerColor = Orange,
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
                    FormScreen(
                        modifier = Modifier.padding(innerPadding),
                        name = name,
                        onNameChange = { name = it },
                        age = age,
                        onAgeChange = { age = it },
                        sex = sex,
                        onSexChange = { sex = it },
                        weight = weight,
                        onWeightChange = { weight = it },
                        sterilized = sterilized,
                        onSterilizedChange = { sterilized = it },
                        activityLevel = activityLevel,
                        onActivityLevelChange = { activityLevel = it },
                        objective = objective,
                        onObjectiveChange = { objective = it },
                        onClickFab = { cancelAddNewPet() }
                    )
                }
            }
        }
    }

    fun commitAddNewPet(pet: Pet) {
        val intent = Intent(this@FromActivityCompose, PetActivityCompose::class.java)
        petViewModel.addPet(pet)
        startActivity(intent)
    }

    fun cancelAddNewPet() {
        val intent = Intent(this@FromActivityCompose, PetActivityCompose::class.java)
        startActivity(intent)
    }
}


@Composable
fun FormScreen(
    modifier: Modifier = Modifier,
    name: String,
    onNameChange: (String) -> Unit,
    age: String,
    onAgeChange: (String) -> Unit,
    sex: String,
    onSexChange: (String) -> Unit,
    weight: Float,
    onWeightChange: (Float) -> Unit,
    sterilized: String,
    onSterilizedChange: (String) -> Unit,
    activityLevel: String,
    onActivityLevelChange: (String) -> Unit,
    objective: String,
    onObjectiveChange: (String) -> Unit,
    onClickFab: () -> Unit
) {
    val scrollState = rememberScrollState()
    Column(
        modifier
            .fillMaxSize(1f)
            .padding(horizontal = 30.dp)
    ) {
        FloatingActionButton(
            onClick = { onClickFab() },
            containerColor = Color.White,
            elevation = FloatingActionButtonDefaults.elevation(0.dp),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = "",
                tint = Color.Black,
                modifier = Modifier.background(color = Color.White)
            )
        }

        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(scrollState)
                .padding(top = 10.dp)
        ) {
            val spacerPadding = 15.dp
            PetName(name = name, onTextChange = { onNameChange(it) })
            Card(
                elevation = CardDefaults.cardElevation(5.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
            ) {
                CustomDropDownMenu(
                    options = stringArrayResource(id = R.array.fa_arraySpinnerEdad).toList(),
                    title = stringResource(id = R.string.ma_txtSpinnerEdad),
                    selectedOption = age,
                    onSelectOption = { onAgeChange(it) },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(vertical = 10.dp)
                )
            }

            Spacer(modifier = Modifier.padding(spacerPadding))

            Card(
                elevation = CardDefaults.cardElevation(5.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                val optionsSex = stringArrayResource(id = R.array.fa_arraySpinnerSexo).toList()
                CustomRadioGroup(
                    text = stringResource(id = R.string.fa_txtSpinnerSexo),
                    selectedOption = sex,
                    onOptionSelected = { onSexChange(it) },
                    options = optionsSex, modifier = Modifier.fillMaxWidth()
                )
            }

            Spacer(modifier = Modifier.padding(spacerPadding))

            Card(
                elevation = CardDefaults.cardElevation(5.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                CustomSlider(weight = weight, onWeightChanged = { onWeightChange(it) })
            }

            Spacer(modifier = Modifier.padding(spacerPadding))

            Card(
                elevation = CardDefaults.cardElevation(5.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {

                val optionsSterilized =
                    stringArrayResource(id = R.array.fa_arraySpinnerEsterilizado).toList()
                CustomRadioGroup(
                    text = stringResource(id = R.string.fa_txtSpinnerEsterilizado),
                    selectedOption = sterilized,
                    onOptionSelected = { onSterilizedChange(it) },
                    options = optionsSterilized, modifier = Modifier.fillMaxWidth()
                )
            }

            Spacer(modifier = Modifier.padding(10.dp))

            Card(
                elevation = CardDefaults.cardElevation(5.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                modifier = Modifier.padding(vertical = 10.dp)
            ) {
                CustomDropDownMenu(
                    options = stringArrayResource(id = R.array.fa_arraySpinnerActividadFisica).toList(),
                    title = stringResource(id = R.string.fa_txtSpinnerActividadFisica),
                    selectedOption = activityLevel,
                    onSelectOption = { onActivityLevelChange(it) },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(vertical = 10.dp)
                )
            }

            Spacer(modifier = Modifier.padding(10.dp))

            Card(
                elevation = CardDefaults.cardElevation(5.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
            ) {
                CustomDropDownMenu(
                    options = stringArrayResource(id = R.array.fa_arraySpinnerObjetivo).toList(),
                    title = stringResource(id = R.string.fa_txtSpinnerObjetivo),
                    selectedOption = objective,
                    onSelectOption = { onObjectiveChange(it) },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(vertical = 10.dp)
                )
            }

            Spacer(modifier = Modifier.padding(spacerPadding))
        }
    }
}


@Composable
fun PetName(
    modifier: Modifier = Modifier,
    name: String,
    onTextChange: (String) -> Unit
) {
    Row(
        modifier
            .fillMaxWidth()
            .height(125.dp)
            .padding(15.dp)
    ) {
        IconButton(
            onClick = { /*TODO*/ },
            Modifier
                .weight(0.3f)
                .fillMaxHeight()
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_dog_illustration),
                contentDescription = "",
                modifier = Modifier.padding(10.dp)
            )
        }
        OutlinedTextField(
            value = name,
            onValueChange = { onTextChange(it) },
            label = { Text("Nombre") },
            modifier = Modifier
                .weight(0.5f)
                .align(Alignment.CenterVertically)
                .padding(bottom = 10.dp)
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDropDownMenu(
    options: List<String>, title: String,
    selectedOption: String,
    modifier: Modifier = Modifier,
    onSelectOption: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    ExposedDropdownMenuBox(expanded = expanded,
        onExpandedChange = { expanded = it }) {

        TextField(
            value = selectedOption,
            textStyle = TextStyle(fontSize = 16.sp),
            onValueChange = {},
            trailingIcon = {
                Icon(imageVector = Icons.Default.KeyboardArrowDown, contentDescription = "")
            },
            label = {
                Text(
                    text = title, style = TextStyle(
                        fontSize = 19.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                    )
                )
            },
            readOnly = true,
            modifier = modifier
                .fillMaxWidth()
                .menuAnchor(type = MenuAnchorType.PrimaryNotEditable, enabled = true),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedContainerColor = Color.Transparent
            )
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            containerColor = Color.White,
            modifier = Modifier.clip(shape = RoundedCornerShape(5.dp))
        ) {
            options.forEach { option ->
                DropdownMenuItem(text = { Text(text = option) },
                    onClick = {
                        onSelectOption(option)
                        expanded = false
                    }
                )
            }
        }


    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomSlider(
    weight: Float,
    modifier: Modifier = Modifier,
    onWeightChanged: (Float) -> Unit
) {
    Column(modifier = modifier.padding(start = 15.dp, end = 15.dp, top = 10.dp)) {
        Text(
            stringResource(id = R.string.fa_txtSpinnerPeso),
            style = TextStyle(
                fontSize = 19.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        )
        Text(
            String.format("%.2f Kg", weight),
            style = TextStyle(fontSize = 19.sp, color = Color.Black),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 10.dp)
        )
        Slider(
            value = weight,
            onValueChange = { onWeightChanged(it) },
            valueRange = 0f..85f,
            modifier = Modifier.padding(
                top = 0.dp,
                bottom = 10.dp, start = 10.dp, end = 10.dp
            ), colors = SliderDefaults.colors(
                thumbColor = Color.Transparent,
                activeTrackColor = Orange,
                inactiveTrackColor = OrangeSoft
            )
        )
    }
}

@Composable
fun CustomRadioGroup(
    modifier: Modifier = Modifier,
    options: List<String>,
    selectedOption: String,
    text: String,
    onOptionSelected: (String) -> Unit
) {
    Column(verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 10.dp)) {
        Text(
            text = text,
            style = TextStyle(
                fontSize = 19.sp, fontWeight = FontWeight.Bold,
                color = Color.Black
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp)
        )
        Spacer(modifier = Modifier.padding(5.dp))
        Row(horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()) {
            options.forEach { option ->
                Row(verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = 15.dp)
                ) {
                    RadioButton(
                        selected = selectedOption == option,
                        onClick = { onOptionSelected(option) },
                        colors = RadioButtonColors(
                            selectedColor = Orange,
                            unselectedColor = Color.Black,
                            disabledSelectedColor = OrangeSoft,
                            disabledUnselectedColor = Color.Gray
                        ),
                    )
                    Text(
                        text = option, style = TextStyle(color = Color.Black),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(end = 10.dp)
                    )
                }

            }
        }
    }

}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun FormScreenPreview() {
    TailyCareTheme {
        Scaffold(modifier = Modifier.fillMaxSize(), containerColor = Color.White,
            bottomBar = {
                BottomAppBar {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        FloatingActionButton(
                            onClick = { /*TODO*/ },
                            containerColor = Orange,
                            modifier = Modifier.width(200.dp)
                        ) {
                            Text(
                                text = "Agregar",
                                style = TextStyle(fontWeight = FontWeight.Bold),
                                color = Orange
                            )
                        }
                    }
                }
            }) { innerPadding ->
            FormScreen(
                modifier = Modifier.padding(innerPadding),
                name = "example",
                onNameChange = {},
                age = "example",
                onAgeChange = {},
                sex = "example",
                onSexChange = {},
                weight = 0.0f,
                onWeightChange = {},
                sterilized = "example",
                onSterilizedChange = {},
                activityLevel = "example",
                onActivityLevelChange = {},
                objective = "example",
                onObjectiveChange = {},
                onClickFab = {}
            )
        }
    }
}