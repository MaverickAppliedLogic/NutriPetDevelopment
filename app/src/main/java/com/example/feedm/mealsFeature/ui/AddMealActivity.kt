package com.example.feedm.mealsFeature.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.feedm.R
import com.example.feedm.core.ui.theme.TailyCareTheme
import com.example.feedm.core.ui.components.CustomDropDownMenu
import com.example.feedm.ui.view.theme.Orange
import com.example.feedm.ui.view.theme.RedSemiTransparent

class AddMealActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TailyCareTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Screen(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Screen(name: String, modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
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
                options = listOf("Nueva comida", "option2"),
                title = "Comida",
                selectedOption = "Nueva comida",
                errorCommitting = false,
                onSelectOption = { }
            )
        }
        Spacer(modifier = Modifier.padding(10.dp))

        OutlinedTextField(
            value = name,
            supportingText = {},
            onValueChange = { },
            label = { },
            isError = false,
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.White,
                errorIndicatorColor = Color.Red, errorLabelColor = Color.Red,
                errorTextColor = Color.Red, errorContainerColor = RedSemiTransparent,
                focusedContainerColor = Color.White, focusedLabelColor = Orange,
                focusedIndicatorColor = Orange
            ),
            modifier = Modifier
                .width(300.dp)
        )
        Spacer(modifier = Modifier.padding(10.dp))
        Image(
            painter = painterResource(id = R.mipmap.test_image), contentDescription = "",
            modifier = Modifier.size(250.dp)
        )
        Text(
            text = "You can find that information on a side or on the back of the bag",
            modifier = Modifier.padding(horizontal = 70.dp)
        )
        Spacer(modifier = Modifier.padding(10.dp))
        OutlinedTextField(
            value = name,
            supportingText = {},
            onValueChange = { },
            label = { },
            isError = false,
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.White,
                errorIndicatorColor = Color.Red, errorLabelColor = Color.Red,
                errorTextColor = Color.Red, errorContainerColor = RedSemiTransparent,
                focusedContainerColor = Color.White, focusedLabelColor = Orange,
                focusedIndicatorColor = Orange
            ),
            modifier = Modifier
                .width(300.dp)
        )
        Row(horizontalArrangement = Arrangement.SpaceBetween) {
            Card(
                shape = RoundedCornerShape(5.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.25.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                modifier = Modifier
                    .padding(top = 30.dp)
                    .height(60.dp)
                    .width(125.dp)
            ) {
                CustomDropDownMenu(options = listOf("24 h", "23 h"), title = "Hora",
                    selectedOption = "24 h", errorCommitting = false, onSelectOption = { })
            }
            Spacer(modifier = Modifier.padding(20.dp))
            Card(
                shape = RoundedCornerShape(5.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.25.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                modifier = Modifier
                    .padding(top = 30.dp)
                    .height(60.dp)
                    .width(125.dp)
            ){
                CustomDropDownMenu(options = listOf("59 min","58 min"), title = "Min",
                    selectedOption = "59 min", errorCommitting = false, onSelectOption = { })}
        }
        Spacer(modifier = Modifier.padding(30.dp))
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


@Preview(showBackground = true)
@Composable
fun ScreenPreview() {
    TailyCareTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Screen(
                name = "Android",
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}