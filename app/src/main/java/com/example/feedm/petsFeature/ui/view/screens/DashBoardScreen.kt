package com.example.feedm.petsFeature.ui.view.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.feedm.core.ui.theme.Neutral
import com.example.feedm.core.ui.theme.NeutralLight
import com.example.feedm.core.ui.theme.Primary
import com.example.feedm.core.ui.theme.Secondary
import com.example.feedm.core.ui.theme.SecondaryDarkest
import com.example.feedm.petsFeature.ui.view.components.ModuleCard
import com.example.feedm.petsFeature.ui.view.components.ModuleItemMeal
import com.example.feedm.petsFeature.ui.viewmodel.PetDetailsViewmodel

@Composable
fun DashBoardScreen(
    viewmodel: PetDetailsViewmodel = hiltViewModel(),
    navTo: (String, Int?) -> Unit
) {
    val scrollState = rememberScrollState()
    Scaffold(modifier = Modifier.fillMaxSize(),
        bottomBar = {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Primary),
                            startY = 0f,
                            endY = Float.POSITIVE_INFINITY,
                            tileMode = TileMode.Clamp
                        )
                    )
            ) {
                FloatingActionButton(
                    onClick = {},
                    shape = CircleShape, containerColor = Primary,
                    contentColor = SecondaryDarkest,
                    modifier = Modifier.padding(bottom = 20.dp).size(65.dp)
                ) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "")
                }
            }
        }) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .background(color = Neutral)
        )
        MainContent(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .verticalScroll(scrollState)
        )
    }
}

@Composable
fun MainContent(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(horizontal = 15.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(Modifier
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(NeutralLight, Neutral),
                    startY = 0f,
                    endY = Float.POSITIVE_INFINITY,
                    tileMode = TileMode.Clamp
                ))
            .height(300.dp)) {

        }
        Column(Modifier.fillMaxSize()) {
        MealsModule()
        Spacer(Modifier.height(50.dp))
        HealthModule()
        Spacer(Modifier.height(50.dp))
        DataModule()
        }

    }
}

@Composable
fun MealsModule() {
    var editable by remember { mutableStateOf(false) }
    ModuleCard(
        modifier = Modifier
            .shadow(5.dp)
            .clip(RoundedCornerShape(5.dp))
            .background(NeutralLight),
        headerTitle = "Comidas Diarias",
        headerIcon = {
            Icon(
                imageVector = if (!editable) Icons.Default.Edit else Icons.Default.Close,
                contentDescription = "",
                tint = SecondaryDarkest,
                modifier = Modifier
                    .padding(bottom = 5.dp)
                    .clickable { editable = !editable }
            )
        },
        captionEnabled = true,
        captionHead = "Recomendado",
        captionTrailing = "Example"
    ) {
        ModuleItemMeal(state = 0, modifier = Modifier.height(40.dp), editable = editable)
        HorizontalDivider(
            modifier = Modifier.padding(vertical = 5.dp, horizontal = 5.dp), color = Secondary
        )
        ModuleItemMeal(state = 1, modifier = Modifier.height(40.dp), editable = editable)
        HorizontalDivider(
            modifier = Modifier.padding(vertical = 5.dp, horizontal = 5.dp), color = Secondary
        )
        ModuleItemMeal(state = 2, modifier = Modifier.height(40.dp), editable = editable)
    }
}

@Composable
fun HealthModule() {
    ModuleCard(
        modifier = Modifier.height(300.dp),
        headerTitle = "Salud",
    ) {}
}

@Composable
fun DataModule() {
    ModuleCard(
        modifier = Modifier.height(300.dp),

        headerTitle = "Datos",
    ) {}
}

@Preview(showBackground = true)
@Composable
fun DashBoardScreenPreview() {
    DashBoardScreen(navTo = { _, _ -> })
}