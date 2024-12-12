package com.example.feedm.ui.view.ui

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
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.feedm.R
import com.example.feedm.domain.model.Pet
import com.example.feedm.ui.view.theme.Orange
import com.example.feedm.ui.view.theme.TailyCareTheme
import com.example.feedm.ui.viewmodel.PetViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PetActivityCompose : ComponentActivity() {

    private  val petViewModel: PetViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        petViewModel.onCreate()
        setContent {
            TailyCareTheme {
                Scaffold(modifier = Modifier.fillMaxSize(),
                    bottomBar = { BottomAppBar(containerColor = Orange,
                        modifier = Modifier
                            .clip(
                                shape = CircleShape.copy(
                                    topEnd = CornerSize(30.dp),
                                    topStart = CornerSize(30.dp),
                                    bottomEnd = CornerSize(0.dp),
                                    bottomStart = CornerSize(0.dp)
                                )
                            )
                            .shadow(20.dp)) {
                        Row(modifier = Modifier
                            .fillMaxWidth()
                            .background(Orange),
                            horizontalArrangement = Arrangement.Center) {
                            AddPetButton(onClick = {addNewPet()})
                        }
                    }}) { innerPadding ->
                    PetsScreen(petViewModel, onIconClicked = {deletePet(it)},
                        modifier = Modifier.padding(innerPadding).background(Color.White))
                }
            }
        }
    }

    private fun addNewPet(){
        val intent = Intent(this@PetActivityCompose, FromActivityCompose::class.java)
        startActivity(intent)
    }

    private fun deletePet(pet: Pet){
        petViewModel.deletePet(pet)
    }


}


@Composable
fun PetsScreen(petViewModel: PetViewModel, onIconClicked: (Pet) -> Unit ,modifier: Modifier = Modifier) {
    val pets: List<Pet> by petViewModel.pets.observeAsState(initial = emptyList())
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 20.dp),
        contentAlignment = Alignment.BottomEnd
    ) {
        PetsList(pets, onIconClicked = {onIconClicked(it)}, modifier = modifier.fillMaxSize(1f))
    }

}


@Composable
fun PetsList(pets: List<Pet>, onIconClicked: (Pet) -> Unit, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier.fillMaxWidth()) {
        items(items = pets) { pet -> PetItem(pet,onIconClicked) }
    }
}

@Composable
fun PetItem(pet: Pet, onIconClicked: (Pet) -> Unit , modifier: Modifier = Modifier) {
    Card(
        colors = CardColors(containerColor = Color.White, contentColor = Color.Black,
            disabledContentColor = Color.White,
            disabledContainerColor = Color.White),
        shape = RoundedCornerShape(5.dp),
        elevation = CardDefaults.cardElevation(2.dp),
        onClick = { /*TODO*/ },
        modifier = modifier
            .fillMaxWidth()
            .padding(7.dp)
    ) {
        Row(
            Modifier.padding(start = 20.dp, end = 0.dp, top = 25.dp, bottom = 25.dp)
        ) {
            if (pet.animal == "dog") {
                Image(
                    painter = painterResource(id = R.drawable.img_dog_illustration),
                    contentDescription = "dog",
                    modifier.weight(0.30f)
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

            IconButton(onClick = { onIconClicked(pet) }, modifier = Modifier.align(Alignment.CenterVertically)) {
                Icon(imageVector = Icons.Default.Delete,
                    contentDescription = "", tint = Color.Gray,
                    modifier = Modifier.align(Alignment.CenterVertically))
            }
        }
    }
}

@Composable
fun AddPetButton(modifier: Modifier = Modifier, onClick: () -> Unit)  {
    ElevatedButton(
        onClick = onClick, colors = ButtonColors(contentColor = Orange,
            containerColor = Color.White, disabledContainerColor = Color.White,
            disabledContentColor = Color.White),shape = CircleShape, modifier = modifier.size(70.dp)
    ) {
        Row {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add Icon",
                tint = Orange,
                modifier = Modifier.fillMaxSize(1f)
            )
        }
    }
}


@Preview(showBackground = true, showSystemUi = true, heightDp = 640)
@Composable
fun PetScreenPreview(modifier: Modifier = Modifier) {
    val pets: List<Pet> = List(20) {
        Pet( 0,"dog", "Example", "3", 5.0, "macho", "si", "alta", "bajar peso", "nada", ""
        )
    }
    TailyCareTheme {
        Scaffold(modifier = Modifier.fillMaxSize(),
            bottomBar = { BottomAppBar {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White),
                    horizontalArrangement = Arrangement.Center) {
                    AddPetButton(onClick = {})
                }
            }}){ innerPadding ->
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.BottomEnd
            ) {
                PetsList(pets, onIconClicked = {} ,modifier = modifier.fillMaxSize(1f))
            }
        }
    }
}