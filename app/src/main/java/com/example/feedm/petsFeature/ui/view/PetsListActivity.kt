package com.example.feedm.petsFeature.ui.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.feedm.R
import com.example.feedm.petsFeature.domain.objectTasks.pet.model.PetModel
import com.example.feedm.ui.view.theme.Orange
import com.example.feedm.core.ui.theme.TailyCareTheme
import com.example.feedm.ui.viewmodel.PetsListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PetsActivity : ComponentActivity() {
    private val petsListViewModel: PetsListViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TailyCareTheme {
                val snackBarHostState = remember { SnackbarHostState() }
                val scope = rememberCoroutineScope()
                Scaffold(modifier = Modifier.fillMaxSize(),
                    snackbarHost = { SnackbarHost(snackBarHostState) },
                    bottomBar = {
                        BottomAppBar(
                            containerColor = Orange,
                            modifier = Modifier
                                .clip(
                                    shape = CircleShape.copy(
                                        topEnd = CornerSize(30.dp),
                                        topStart = CornerSize(30.dp),
                                        bottomEnd = CornerSize(0.dp),
                                        bottomStart = CornerSize(0.dp)
                                    )
                                )
                                .shadow(20.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Orange),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                AddPetButton(onClick = { addNewPet() })
                            }
                        }
                    }) { innerPadding ->
                    var showDeleteDialog by remember { mutableStateOf(false) }
                    var petToDelet by remember {
                        mutableStateOf(
                            PetModel(
                                0, "", "", 0.0f,
                                0.0f, "", false, "", "",
                                ""
                            )
                        )
                    }


                    PetsScreen(
                        petsListViewModel,
                        onItemClicked = { goToPetDetails(it) },
                        onEditIconClicked = { goToEditPet(it) },
                        onDeleteIconClicked = {
                            petToDelet = it; showDeleteDialog = true
                        },
                        modifier = Modifier
                            .padding(innerPadding)
                            .background(Color.White)
                    )
                    DeleteDialog(
                        title = "Eliminar mascota",
                        text = "¿Seguro que desea eliminar a " +
                                "${petToDelet.petName.replace(" ", "")}? \n" +
                                "Esta acción no se puede deshacer.",
                        confirmButton = { deletePet(petToDelet); showDeleteDialog = false
                            scope.launch {
                                snackBarHostState
                                    .showSnackbar(
                                        "Se elimino a ${petToDelet.petName.replace(" ",
                                            "")}"
                                    )
                            }},
                        cancelButton = { showDeleteDialog = false }, visible = showDeleteDialog
                    )

                }
            }
        }
    }

    private fun addNewPet() {
        val intent = Intent(this@PetsActivity, AddPetActivity::class.java)
        startActivity(intent)
    }

    private fun goToPetDetails(petModel: PetModel) {
        val intent = Intent(this@PetsActivity, PetDetailsActivity::class.java)
        intent.putExtra("PetId", petModel.petId)
        startActivity(intent)
    }

    private fun goToEditPet(petModel: PetModel) {
        val intent = Intent(this@PetsActivity, EditPetActivity::class.java)
        intent.putExtra("PetId", petModel.petId)
        startActivity(intent)
    }


    private fun deletePet(petModel: PetModel) {
        petsListViewModel.deletePet(petModel)
    }


}

@Composable
fun DeleteDialog(
    title: String, text: String, visible: Boolean,
    confirmButton: () -> Unit, cancelButton: () -> Unit
) {
    if (visible) {
        AlertDialog(
            title = { Text(text = title) },
            text = { Text(text = text) },
            onDismissRequest = { },
            confirmButton = {
                TextButton(onClick = confirmButton) {
                    Text(
                        text = "Aceptar", style = TextStyle(color = Color.Red),
                        fontWeight = FontWeight.Bold
                    )
                }
            },
            dismissButton = {
                TextButton(onClick = cancelButton) {
                    Text(
                        text = "Cancelar", style = TextStyle(color = Color.Black),
                        fontWeight = FontWeight.Bold
                    )
                }
            },
            containerColor = Color.White,
            shape = RoundedCornerShape(5.dp)
        )
    }
}

@Composable
fun PetsScreen(
    petsListViewModel: PetsListViewModel,
    onItemClicked: (PetModel) -> Unit,
    onEditIconClicked: (PetModel) -> Unit,
    onDeleteIconClicked: (PetModel) -> Unit,
    modifier: Modifier = Modifier
) {
    val petModels: List<PetModel> by petsListViewModel.pets.observeAsState(initial = emptyList())
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 20.dp),
        contentAlignment = Alignment.BottomEnd
    ) {
        PetsList(
            petModels,
            onEditIconClicked = { onEditIconClicked(it) },
            onDeleteIconClicked = { onDeleteIconClicked(it) },
            onItemClicked = { onItemClicked(it) },
            modifier = modifier.fillMaxSize(1f)
        )
    }

}


@Composable
fun PetsList(
    petModels: List<PetModel>,
    onItemClicked: (PetModel) -> Unit,
    onEditIconClicked: (PetModel) -> Unit,
    onDeleteIconClicked: (PetModel) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier.fillMaxWidth()) {
        items(items = petModels) { pet ->
            PetItem(
                pet,
                onItemClicked,
                onEditIconClicked,
                onDeleteIconClicked
            )
        }
    }
}

@Composable
fun PetItem(
    petModel: PetModel,
    onItemClicked: (PetModel) -> Unit,
    onEditIconClicked: (PetModel) -> Unit,
    onDeleteIconClicked: (PetModel) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        colors = CardColors(
            containerColor = Color.White, contentColor = Color.Black,
            disabledContentColor = Color.White,
            disabledContainerColor = Color.White
        ),
        shape = RoundedCornerShape(5.dp),
        elevation = CardDefaults.cardElevation(2.dp),
        onClick = { onItemClicked(petModel) },
        modifier = modifier
            .fillMaxWidth()
            .padding(7.dp)
    ) {
        Row(
            Modifier.padding(start = 20.dp, end = 0.dp, top = 25.dp, bottom = 25.dp)
        ) {
            //TODO cambiar imagen del gato
            var expanded by remember { mutableStateOf(false) }
            if (petModel.animal == "dog") {
                Image(
                    painter = painterResource(id = R.drawable.img_dog_illustration),
                    contentDescription = "dog",
                    modifier.weight(0.30f)
                )
            } else {
                Image(
                    painter = painterResource(id = R.drawable.gato),
                    contentDescription = "dog",
                    modifier.weight(0.30f)
                )
            }
            Text(
                text = petModel.petName,
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                textAlign = TextAlign.Center,
                modifier = modifier
                    .weight(1f)
                    .padding(top = 10.dp)
            )
            Box(
                modifier = modifier
                    .background(color = Color.White)
                    .align(Alignment.CenterVertically)
            ) {
                IconButton(onClick = { expanded = !expanded }) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = "", tint = Color.Gray,
                    )
                }
                DropdownMenu(
                    expanded = expanded, onDismissRequest = { expanded = false },
                    containerColor = Color.White
                ) {
                    DropdownMenuItem(text = { Text(text = "Editar") },
                        onClick = { onEditIconClicked(petModel) },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Create,
                                contentDescription = "", tint = Color.Gray
                            )
                        })
                    DropdownMenuItem(text = { Text(text = "Eliminar") },
                        onClick = { onDeleteIconClicked(petModel) },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "", tint = Color.Gray
                            )
                        })
                }

            }

        }
    }
}

@Composable
fun AddPetButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    ElevatedButton(
        elevation = ButtonDefaults.elevatedButtonElevation(2.dp),
        onClick = onClick, colors = ButtonColors(
            contentColor = Orange,
            containerColor = Orange, disabledContainerColor = Orange,
            disabledContentColor = Orange
        ), shape = CircleShape,
        modifier = modifier.size(70.dp)
    ) {
        Row {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add Icon",
                tint = Color.White,
                modifier = Modifier.fillMaxSize(1f)
            )
        }

    }


}


@Preview(showBackground = true, showSystemUi = true, heightDp = 640)
@Composable
fun PetScreenPreview(modifier: Modifier = Modifier) {
    val petModels: List<PetModel> = List(20) {
        PetModel(
            0, "dog", "Example", 3.0f, 0.5f, "macho", true, "alta", "bajar peso", "nada"
        )
    }
    TailyCareTheme {
        Scaffold(modifier = Modifier.fillMaxSize(),
            bottomBar = {
                BottomAppBar {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        AddPetButton(onClick = {})
                    }
                }
            }) { innerPadding ->
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.BottomEnd
            ) {
                PetsList(
                    petModels, onDeleteIconClicked = {},
                    onItemClicked = {},
                    onEditIconClicked = {}, modifier = modifier.fillMaxSize(1f)
                )
            }
        }
    }
}