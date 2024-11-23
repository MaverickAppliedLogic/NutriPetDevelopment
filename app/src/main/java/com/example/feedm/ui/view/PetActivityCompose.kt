package com.example.feedm.ui.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.feedm.R
import com.example.feedm.domain.model.Pet
import com.example.feedm.ui.view.ui.theme.FeedmTheme
import com.example.feedm.ui.viewmodel.PetViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PetActivityCompose : ComponentActivity() {

    private  val petViewModel: PetViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        petViewModel.onCreate()
        setContent {
            FeedmTheme {
                Scaffold(modifier = Modifier.fillMaxSize(),
                    bottomBar = { BottomAppBar {
                        Row(modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center) {
                            AddPetButton(onClick = {AddNewPet()})
                        }
                    }}) { innerPadding ->
                    PetsScreen(petViewModel, modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }

    fun AddNewPet(){
        val intent = Intent(this@PetActivityCompose,FromActivityCompose::class.java)
        startActivity(intent)
    }


}


@Composable
fun PetsScreen(petViewModel: PetViewModel, modifier: Modifier = Modifier) {
    val pets: List<Pet> by petViewModel.pets.observeAsState(initial = emptyList())
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 20.dp),
        contentAlignment = Alignment.BottomEnd
    ) {
        PetsList(pets, modifier = modifier.fillMaxSize(1f))
    }

}


@Composable
fun PetsList(pets: List<Pet>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier.fillMaxWidth()) {
        items(items = pets) { pet -> PetItem(pet) }
    }
}

@Composable
fun PetItem(pet: Pet, modifier: Modifier = Modifier) {
    Card(
        onClick = { /*TODO*/ },
        modifier
            .fillMaxWidth()
            .padding(7.dp)
    ) {
        Row(
            modifier.padding(start = 20.dp, end = 0.dp, top = 25.dp, bottom = 20.dp)
        ) {
            if (pet.animal == "dog") {
                Image(
                    painter = painterResource(id = R.drawable.img_dog_illustration),
                    contentDescription = "dog",
                    modifier.weight(0.25f)
                )
            } else {
                Image(
                    painter = painterResource(id = R.drawable.gato), contentDescription = "dog"
                )
            }
            Text(
                text = pet.nombre,
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                textAlign = TextAlign.Center,
                modifier = modifier
                    .weight(1f)
                    .padding(top = 10.dp)
            )
        }
    }
}

@Composable
fun AddPetButton(modifier: Modifier = Modifier, onClick: () -> Unit)  {
    ElevatedButton(
        onClick = onClick, shape = CircleShape, modifier = modifier.size(70.dp)
    ) {
        Row {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add Icon",
                modifier = Modifier.fillMaxSize(1f)
            )
        }
    }
}


@Preview(showBackground = true, showSystemUi = true, heightDp = 640)
@Composable
fun PetScreenPreview(modifier: Modifier = Modifier) {
    val pets: List<Pet> = List(20) {
        Pet(
            0, "dog", "Example", "3", 5.0, "macho", "si", "alta", "bajar peso", "nada", ""
        )
    }
    FeedmTheme {
        Scaffold(modifier = Modifier.fillMaxSize(),
            bottomBar = { BottomAppBar { Row(modifier= Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center) {
                AddPetButton(onClick = {})
            }
            }}) { innerpadding ->
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .padding(innerpadding),
                contentAlignment = Alignment.BottomEnd
            ) {
                PetsList(pets, modifier = modifier.fillMaxSize(1f))
            }
        }
    }
}