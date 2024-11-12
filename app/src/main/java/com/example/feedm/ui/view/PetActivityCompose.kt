package com.example.feedm.ui.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFrom
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawStyle
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Observer
import androidx.lifecycle.findViewTreeLifecycleOwner
import com.example.feedm.R
import com.example.feedm.domain.AddPet
import com.example.feedm.domain.model.Pet
import com.example.feedm.ui.view.ui.theme.FeedmTheme
import com.example.feedm.ui.viewmodel.PetViewModel

class PetActivityCompose : ComponentActivity() {

    val petViewModel: PetViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PetsScreen(petViewModel)
        }
    }
}


@Composable
fun PetsScreen(petViewModel: PetViewModel, modifier: Modifier = Modifier) {
    val pets: List<Pet> by petViewModel.pets.observeAsState(initial = emptyList())
    Box(
        modifier = modifier
            .fillMaxSize(1f)
            .padding(top = 20.dp),
        contentAlignment = Alignment.BottomEnd
    ) {
        Pets(pets, modifier = modifier.fillMaxSize(1f))
        AddPetButton()
    }

}

@Preview
@Composable
fun ActionsBar(modifier: Modifier = Modifier){
    Box(modifier = modifier
        .fillMaxWidth()
        ,contentAlignment = Alignment.BottomCenter) {
        Box(
            modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(color = colorResource(id = R.color.white))
            )
        {      AddPetButton()  }
        AddPetButton(modifier = Modifier.padding(bottom = 5.dp))
    }
}

@Composable
fun Pets(pets: List<Pet>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier.fillMaxWidth()) {
        items(items = pets) { pet -> Pet(pet) }
    }
}

@Composable
fun Pet(pet: Pet, modifier: Modifier = Modifier) {
    Card(
        onClick = { /*TODO*/ },
        modifier
            .fillMaxWidth()
            .padding(7.dp)

    ) {
        Row(modifier.padding(start = 20.dp, end = 0.dp, top = 25.dp, bottom = 20.dp)
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
fun AddPetButton(modifier: Modifier = Modifier) {
    ElevatedButton(onClick = { /*TODO*/ }, shape = CircleShape
        ,modifier = modifier.size(70.dp)
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

@Preview
@Composable
fun PetPreview() {
    val pet =
        Pet(
            0, "dog", "Example", "3", 5.0, "macho", "si", "alta", "bajar peso", "nada", ""
        )
    Pet(pet)
}


@Preview(showBackground = true, heightDp = 640, widthDp = 320)
@Composable
fun PetScreenPreview(modifier: Modifier = Modifier) {
    val pets: List<Pet> = List(20) {
        Pet(
            0, "dog", "Example", "3", 5.0, "macho", "si", "alta", "bajar peso", "nada", ""
        )
    }
    Pets(pets, modifier = Modifier.padding(5.dp))
    Box(
        modifier = modifier
            .fillMaxWidth(1f)
        ,contentAlignment =  Alignment.BottomCenter
    ) {
        ActionsBar()
    }
}