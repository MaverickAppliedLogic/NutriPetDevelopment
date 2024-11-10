package com.example.feedm.ui.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
fun PetsScreen(petViewModel: PetViewModel){
    val pets: List<Pet> by petViewModel.pets.observeAsState(initial = emptyList())
    Pets(pets)
}

@Composable
fun Pets(pets: List<Pet>, modifier: Modifier = Modifier){
    LazyColumn(modifier = modifier.fillMaxWidth()) {
        items(items = pets ){ pet ->  Pet(pet)  }
    }
}

@Composable
fun Pet(pet: Pet, modifier: Modifier = Modifier){
    Card(onClick = { /*TODO*/ }, modifier.fillMaxWidth().padding(10.dp)) {
        Row(modifier.padding(start = 10.dp, end = 0.dp, top = 20.dp, bottom = 20.dp)) {
            if(pet.animal == "dog"){
                Image(painter = painterResource(id = R.drawable.img_dog_illustration)
                    , contentDescription = "dog"
                ,modifier.weight(0.30f))}
            else{Image(painter = painterResource(id = R.drawable.gato)
                , contentDescription = "dog")}
            Text(text = pet.nombre
                ,style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold)
                ,textAlign = TextAlign.Center
                ,modifier = modifier
                    .weight(1f)
                    .padding(top = 15.dp))
        }
    }
}

@Preview
@Composable
fun PetPreview(){
    val pet =
        Pet(0,"dog"
            ,"Example", "3",5.0,"macho"
            ,"si","alta","bajar peso","nada","")
    Pet(pet)
}
@Preview(showBackground = true, heightDp = 320)
@Composable
fun PetsPreview(){
    val pets: List<Pet> = List(20){Pet(0,"dog"
        ,"Example", "3",5.0,"macho"
        ,"si","alta","bajar peso","nada","")}

    Pets(pets)
}