package com.example.feedm.petsFeature.ui.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
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
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.feedm.R
import com.example.feedm.core.ui.components.CustomDropDownMenu
import com.example.feedm.core.ui.components.CustomRadioGroup
import com.example.feedm.core.ui.components.CustomSlider
import com.example.feedm.core.ui.theme.TailyCareTheme
import com.example.feedm.petsFeature.domain.objectTasks.pet.model.PetModel
import com.example.feedm.petsFeature.ui.viewmodel.AddPetViewmodel
import com.example.feedm.ui.view.theme.Orange
import com.example.feedm.ui.view.theme.RedSemiTransparent
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AddPetActivity : ComponentActivity() {

    private val addPetViewModel: AddPetViewmodel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //TODO mejorar seleccion de mascota

        setContent {
            TailyCareTheme {
                var invalidName by remember { mutableStateOf(false) }
                var invalidWeight by remember { mutableStateOf(false) }
                var invalidAge by remember { mutableStateOf(false) }
                var invalidGoal by remember { mutableStateOf(false) }
                var pet by remember {
                    mutableStateOf(
                        PetModel(
                            animal = "dog",
                            petName = "",
                            age = 0.0f,
                            petWeight = 0.0f,
                            genre = null,
                            sterilized = false,
                            activity = null,
                            goal = "",
                            allergies = null
                        )
                    )
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
                                            pet = pet,
                                            onValidationFailed = {
                                                when (it) {
                                                    "name" -> invalidName = true
                                                    "age" -> invalidAge = true
                                                    "weight" -> invalidWeight = true
                                                    "goal" -> invalidGoal = true
                                                }
                                            }
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
                        pet = pet,
                        invalidName = invalidName,
                        invalidAge = invalidAge,
                        invalidWeight = invalidWeight,
                        invalidGoal = invalidGoal,
                        onImageChange = { pet = pet.copy(animal = it) },
                        onNameChange = {
                            pet = pet.copy(petName = it)
                            invalidName = false
                        },
                        onAgeChange = {
                            pet = pet.copy(age = it)
                            invalidAge = false
                        },
                        onGenreChange = { pet = pet.copy(genre = it) },
                        onWeightChange = {
                            pet = pet.copy(petWeight = it)
                            invalidWeight = false
                        },
                        onSterilizedChange = { pet = pet.copy(sterilized = it) },
                        onActivityLevelChange = { pet = pet.copy(activity = it) },
                        onGoalChange = {
                            pet = pet.copy(goal = it)
                            invalidGoal = false
                        },
                        onClickFab = { cancelAddNewPet() }
                    )
                }
            }
        }
    }


    private fun commitAddNewPet(
        pet: PetModel,
        onValidationFailed: (String) -> Unit
    ) {
        val intent = Intent(this@AddPetActivity, PetsActivity::class.java)
        if (pet.petName == "") {
            onValidationFailed("name")
            return
        }
        if (pet.age == 0.0f) {
            onValidationFailed("age")
            return
        }
        if (pet.petWeight == 0.0f) {
            onValidationFailed("weight")
            return
        }
        if (pet.goal == "") {
            onValidationFailed("goal")
            return
        }
        addPetViewModel.addPet(pet)
        startActivity(intent)
    }

    private fun cancelAddNewPet() {
        val intent = Intent(this@AddPetActivity, PetsActivity::class.java)
        startActivity(intent)
    }
}


/**
 * Muestra los atributos de [pet] y devuelve los cambios hechos en cada uno de ellos
 *
 * Cuando cambia alguno de los atributos lo notifica y lo pasa a través de lambdas
 * ```
 *  {onChange_(it)}
 * ```
 * Los parametros [invalidName], [invalidAge], [invalidWeight], [invalidGoal], deciden la apariencia
 * de los componentes de campos obligatorios en el formulario:
 * en caso de que alguno de estos
 * parametros sea `invalid_ = true`, el o los respectivos componentes mostraran su apariencia
 * en modo [Error], para indicar al usuario los campos obligatorios que debe revisar.
 */
@Composable
fun FormScreen(
    modifier: Modifier = Modifier,
    pet: PetModel,
    invalidName: Boolean,
    invalidAge: Boolean,
    invalidWeight: Boolean,
    invalidGoal: Boolean,
    onImageChange: (String) -> Unit,
    onNameChange: (String) -> Unit,
    onAgeChange: (Float) -> Unit,
    onGenreChange: (String) -> Unit,
    onWeightChange: (Float) -> Unit,
    onSterilizedChange: (Boolean) -> Unit,
    onActivityLevelChange: (String) -> Unit,
    onGoalChange: (String) -> Unit,
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
            PetImageAndName(
                errorCommitting = invalidName,
                name = pet.petName,
                animal = pet.animal,
                onImageChange = { onImageChange(it) },
                onTextChange = { onNameChange(it) })
            Card(
                shape = RoundedCornerShape(5.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.25.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
            ) {
                val ageOptions = stringArrayResource(R.array.ap_arraySelectAge).toList()
                CustomDropDownMenu(
                    options = ageOptions,
                    title = stringResource(id = R.string.ma_txtSpinnerEdad),
                    selectedOption =
                    when (pet.age) {
                        0.0f -> {
                            ageOptions.first()
                        }
                        11.0f -> {
                            ageOptions.last()
                        }
                        else -> {
                            stringResource( id = R.string.apa_selectedOptionDropDownMenu,
                                pet.age.toInt())
                        }
                    },
                    onSelectOption = {
                        when (it) {
                            ageOptions.first() -> onAgeChange(0.5f)
                            ageOptions.last() -> onAgeChange(11.0f)
                            else -> onAgeChange(it[0].code.div(10).toFloat())
                        }
                    },
                    errorCommitting = invalidAge,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(vertical = 10.dp)
                )
            }

            Spacer(modifier = Modifier.padding(spacerPadding))

            Card(
                shape = RoundedCornerShape(5.dp),
                elevation = CardDefaults.cardElevation(1.25.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                val texts = stringArrayResource(id = R.array.fa_arraySpinnerSexo)

                CustomRadioGroup(
                    text = stringResource(id = R.string.fa_txtSpinnerSexo),
                    selectedOption = when (pet.genre) {
                        texts[0] -> {
                            true
                        }

                        texts[1] -> {
                            false
                        }

                        else -> {
                            null
                        }
                    },
                    onOptionSelected = {
                        if (it) onGenreChange(texts[0])
                        else onGenreChange(texts[1])
                    },
                    options = listOf(true, false), modifier = Modifier.fillMaxWidth(),
                    enabled = true,
                    texts = texts
                )
            }

            Spacer(modifier = Modifier.padding(spacerPadding))

            Card(
                shape = RoundedCornerShape(5.dp),
                elevation = CardDefaults.cardElevation(1.25.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                CustomSlider(
                    weight = pet.petWeight, onWeightChanged = { onWeightChange(it) },
                    errorCommitting = invalidWeight,
                    valueRange =
                    if (pet.animal == "dog") 0.0f..100.0f
                    else 0.0f..11.0f
                )
            }

            Spacer(modifier = Modifier.padding(10.dp))

            Card(
                shape = RoundedCornerShape(5.dp),
                elevation = CardDefaults.cardElevation(1.25.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
            ) {
                CustomDropDownMenu(
                    options = stringArrayResource(id = R.array.fa_arraySpinnerObjetivo).toList(),
                    title = stringResource(id = R.string.fa_txtSpinnerObjetivo),
                    selectedOption = pet.goal,
                    errorCommitting = invalidGoal,
                    onSelectOption = { onGoalChange(it) },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(vertical = 10.dp)
                )
            }
            Spacer(modifier = Modifier.padding(spacerPadding))

            Card(
                shape = RoundedCornerShape(5.dp),
                elevation = CardDefaults.cardElevation(1.25.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                val texts = stringArrayResource(id = R.array.fa_arraySpinnerEsterilizado)

                CustomRadioGroup(
                    text = stringResource(id = R.string.fa_txtSpinnerEsterilizado),
                    selectedOption = pet.sterilized,
                    onOptionSelected = { onSterilizedChange(it) },
                    options = listOf(true, false), modifier = Modifier.fillMaxWidth(),
                    enabled = true,
                    texts = texts
                )
            }

            Spacer(modifier = Modifier.padding(10.dp))

            Card(
                shape = RoundedCornerShape(5.dp),
                elevation = CardDefaults.cardElevation(1.25.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                modifier = Modifier.padding(vertical = 10.dp)
            ) {
                CustomDropDownMenu(
                    options = stringArrayResource(id = R.array.fa_arraySpinnerActividadFisica).toList(),
                    title = stringResource(id = R.string.fa_txtSpinnerActividadFisica),
                    selectedOption = pet.activity,
                    onSelectOption = { onActivityLevelChange(it) },
                    errorCommitting = false,
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
fun PetImageAndName(
    modifier: Modifier = Modifier,
    errorCommitting: Boolean,
    name: String,
    animal: String,
    onImageChange: (String) -> Unit,
    onTextChange: (String) -> Unit
) {
    Row(
        modifier
            .fillMaxWidth()
            .height(125.dp)
            .padding(15.dp)
    ) {
        IconButton(
            onClick = { onImageChange(if (animal == "dog") "cat" else "dog") },
            modifier = Modifier
                .clip(shape = RoundedCornerShape(10.dp))
                .weight(0.3f)
                .fillMaxHeight()

        ) {
            val animalImg = if (animal == "dog") R.drawable.img_dog_illustration else
                R.drawable.gato
            Image(
                painter = painterResource(id = animalImg),
                contentDescription = "",
                modifier = Modifier.padding(10.dp)
            )

        }
        OutlinedTextField(
            value = name,
            supportingText = {
                if (errorCommitting) Text(
                    text = "No tiene nombre :(",
                    color = Color.Red
                )
            },
            onValueChange = { onTextChange(it) },
            label = { Text(stringResource(id = R.string.fa_hintEtPetName)) },
            isError = errorCommitting,
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.White,
                errorIndicatorColor = Color.Red, errorLabelColor = Color.Red,
                errorTextColor = Color.Red, errorContainerColor = RedSemiTransparent,
                focusedContainerColor = Color.White, focusedLabelColor = Orange,
                focusedIndicatorColor = Orange
            ),
            modifier = Modifier
                .weight(0.5f)
                .align(Alignment.CenterVertically)
                .padding(bottom = 10.dp),
        )
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
                            onClick = { },
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
                pet = PetModel(
                    -1,
                    "dog",
                    "",
                    0.0f,
                    0f,
                    null,
                    false,
                    null,
                    "",
                    null
                ),
                invalidName = false,
                invalidAge = false,
                invalidWeight = false,
                invalidGoal = false,
                onImageChange = { },
                onNameChange = { },
                onAgeChange = { },
                onGenreChange = { },
                onWeightChange = { },
                onSterilizedChange = { },
                onActivityLevelChange = { },
                onGoalChange = { },
                onClickFab = { }
            )
        }
    }
}