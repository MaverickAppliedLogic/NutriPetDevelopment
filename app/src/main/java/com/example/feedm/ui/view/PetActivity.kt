package com.example.feedm.ui.view


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.feedm.R
import com.example.feedm.ui.view.theme.Orange
import com.example.feedm.ui.view.ui.ui.theme.FeedmTheme
import com.example.feedm.ui.viewmodel.PetViewModel

class PetActivity : ComponentActivity() {

    private val petViewModel : PetViewModel by viewModels()

    @ExperimentalMaterial3Api
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        petViewModel.onCreate()
        setContent {
            FeedmTheme {
                Scaffold(modifier = Modifier.fillMaxSize(),
                    topBar = { TopAppBar(
                        colors = TopAppBarColors(containerColor = Orange,
                            scrolledContainerColor = Color.White,
                            navigationIconContentColor = Color.Black,
                            actionIconContentColor = Color.Black,
                            titleContentColor = Color.Black
                        ),
                        title = {Text(text = "")},
                        navigationIcon = {
                            IconButton(onClick = { /*TODO*/ }) {
                                Icon(imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                                    contentDescription = "ArrowBack",
                                    tint = Color.Black)
                            }
                        },
                        actions = {
                            Row {
                                IconButton(onClick = { /*TODO*/ }) {
                                    Icon(imageVector = Icons.Default.Create,
                                        contentDescription = "ArrowBack",
                                        tint = Color.Black)
                                }
                            }
                        },
                        modifier = Modifier.shadow(5.dp))}) { innerPadding ->
                    PetActivityUI(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun PetActivityUI(modifier: Modifier = Modifier){
    Column(modifier = modifier
        .fillMaxWidth()
        .verticalScroll(rememberScrollState())) {
        PetImage()
    }
}

@Preview
@Composable
fun PetImage(){
    Row(horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(125.dp)
            .background(color = Orange)){
        Image(painter = painterResource(id = R.drawable.img_dog_illustration),
           contentDescription = "Pet Image",
           modifier = Modifier.padding(horizontal = 20.dp, vertical = 20.dp))
    }
}

@ExperimentalMaterial3Api
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PetActivityScreenPreview(modifier: Modifier = Modifier){
    FeedmTheme {
        Scaffold(modifier = Modifier.fillMaxSize(),
            topBar = { TopAppBar(
                colors = TopAppBarColors(containerColor = Orange,
                    scrolledContainerColor = Color.White,
                    navigationIconContentColor = Color.Black,
                    actionIconContentColor = Color.Black,
                    titleContentColor = Color.Black
                    ),
                title = {      Text(text = "Toby",
                    style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth())},
                navigationIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                            contentDescription = "ArrowBack",
                            tint = Color.Black)
                    }
                },
                actions = {
                    Row {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(imageVector = Icons.Default.Create,
                                contentDescription = "ArrowBack",
                                tint = Color.Black)
                        }
                    }
                },modifier = Modifier.shadow(5.dp))}) { innerPadding ->
            PetActivityUI(modifier = Modifier.padding(innerPadding))
        }
    }
}
