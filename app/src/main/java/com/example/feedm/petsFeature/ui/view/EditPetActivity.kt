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
import androidx.compose.runtime.livedata.observeAsState
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
import androidx.compose.ui.unit.dp
import com.example.feedm.R
import com.example.feedm.petsFeature.domain.model.PetModel
import com.example.feedm.core.ui.components.CustomDropDownMenu
import com.example.feedm.core.ui.components.CustomRadioGroup
import com.example.feedm.core.ui.components.CustomSlider
import com.example.feedm.ui.view.theme.Orange
import com.example.feedm.ui.view.theme.RedSemiTransparent
import com.example.feedm.core.ui.theme.TailyCareTheme
import com.example.feedm.ui.viewmodel.PetViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditPetActivity : ComponentActivity() {
    private val petViewModel: PetViewModel by viewModels()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val petId = intent.extras!!.getInt("PetId")
        petViewModel.getPetById(petId)



        setContent {
            TailyCareTheme {

                var errorCommitting by remember { mutableStateOf(false) }
                val pet by petViewModel.petModel.observeAsState(
                    PetModel(
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
                )
                )


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
                                        onClick = { finish() },
                                        containerColor = Orange,
                                        modifier = Modifier.width(150.dp)
                                    ) {
                                        Text(
                                            text = "Cancelar",
                                            style = TextStyle(fontWeight = FontWeight.Bold),
                                            color = Color.White
                                        )
                                    }
                                    Spacer(modifier = Modifier.padding(20.dp))
                                    FloatingActionButton(
                                        onClick = {
                                            commitEditPet(
                                                pet= pet,
                                                onValidationFailed = {errorCommitting = true}
                                            )
                                        },
                                        containerColor = Orange,
                                        modifier = Modifier.width(150.dp)
                                    ) {
                                        Text(
                                            text = "Confirmar cambios",
                                            style = TextStyle(fontWeight = FontWeight.Bold),
                                            color = Color.White
                                        )
                                    }
                            }
                        }
                    }) { innerPadding ->
                    EditScreen(
                        modifier = Modifier.padding(innerPadding),
                        pet = pet,
                        errorCommitting = errorCommitting,
                        onAgeChange = {
                            petViewModel.editPetNotCommitting(pet.copy(age = it))
                        },
                        onGenreChange = {
                            petViewModel.editPetNotCommitting(pet.copy(genre = it))
                        },
                        onWeightChange = {
                            petViewModel.editPetNotCommitting(pet.copy(petWeight = it))
                                         },
                        onSterilizedChange = {
                            petViewModel.editPetNotCommitting(pet.copy(sterilized = it))
                                             },
                        onActivityLevelChange = {
                            petViewModel.editPetNotCommitting(pet.copy(activity = it))
                                                },
                        onObjectiveChange = {
                            petViewModel.editPetNotCommitting(pet.copy(goal = it))
                        },
                        onClickFab = { cancelAddNewPet() }
                    )
                }
            }
        }
    }


    private fun commitEditPet(
        pet: PetModel,
        onValidationFailed: (String) -> Unit
    ) {
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
            onValidationFailed("objective")
            return
        }
        petViewModel.addPet(pet)

        intent.setClass(this@EditPetActivity, PetDetailsActivity::class.java)
        startActivity(intent)
    }

    private fun cancelAddNewPet() {
      finish()
    }
}


/**
 * Muestra los atributos de [pet] y devuelve los cambios hechos en cada uno de ellos
 *
 * Cuando cambia alguno de los atributos lo notifica y lo pasa a través de lambdas
 * ```
 *  {onChange_(it)}
 * ```
 */

@Composable
fun EditScreen(
    modifier: Modifier = Modifier,
    pet: PetModel,
    errorCommitting: Boolean,
    onAgeChange: (Float) -> Unit,
    onGenreChange: (String) -> Unit,
    onWeightChange: (Float) -> Unit,
    onSterilizedChange: (Boolean) -> Unit,
    onActivityLevelChange: (String) -> Unit,
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
            PetImageAndNameNotEditable(
                petName = pet.petName,
                animal = pet.animal)
            Card(
                shape = RoundedCornerShape(5.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.25.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
            ) {
                //TODO establecer variable del año de la mascota en el resource
                val texts = stringArrayResource(id = R.array.fa_arraySpinnerEdad)
                //TODO crear stringResource de la lista
                CustomDropDownMenu(
                    options = listOf(
                        "Menos de 1 año",
                        "1 año",
                        "2 años",
                        "3 años",
                        "4 años",
                        "5 años",
                        "6 años",
                        "7 años",
                        "Más de 7 años"
                    ),
                    title = stringResource(id = R.string.ma_txtSpinnerEdad),
                    selectedOption = if (pet.age < 0.5) {
                        ""
                    } else if (pet.age < 1) {
                        texts[0]
                    } else if (pet.age < 8) {
                        texts[1]
                    } else {
                        texts[2]
                    },
                    onSelectOption = {
                        when (it) {
                            "Menos de 1 año" -> onAgeChange(0.5f)
                            "Más de 7 años" -> onAgeChange(8.0f)
                            else -> onAgeChange(it[0].code.div(10).toFloat())
                        }
                    },
                    errorCommitting = false,
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
                    enabled = false,
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
                    errorCommitting= errorCommitting,
                    valueRange =
                    if (pet.animal == "dog") 0.0f..85.0f
                    else 0.0f..25.0f
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
                    errorCommitting = false,
                    onSelectOption = { onObjectiveChange(it) },
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
                    enabled = !pet.sterilized,
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


/**
 * Muestra los atributos [petName] y [animal] del [PetModel] recibido en el [Intent]
 * pero no permite edición de estos
 */
@Composable
fun PetImageAndNameNotEditable(
    modifier: Modifier = Modifier,
    petName: String,
    animal: String,
) {
    Row(
        modifier
            .fillMaxWidth()
            .height(125.dp)
            .padding(15.dp)
    ) {
        IconButton(
            onClick = {},
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
            value = petName,
            onValueChange = { },
            label = { Text(stringResource(id = R.string.fa_hintEtPetName)) },
            enabled = false,
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

