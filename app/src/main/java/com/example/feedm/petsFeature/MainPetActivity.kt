package com.example.feedm.petsFeature

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.example.feedm.core.navigation.NavigationWrapper
import com.example.feedm.core.ui.theme.TailyCareTheme
import com.example.feedm.petsFeature.ui.viewmodel.AddMealViewmodel
import com.example.feedm.petsFeature.ui.viewmodel.RegisterPetViewmodel
import com.example.feedm.petsFeature.ui.viewmodel.PetDetailsViewmodel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainPetActivity : ComponentActivity() {
    private val registerPetViewModel: RegisterPetViewmodel by viewModels()
    private val addMealViewmodel: AddMealViewmodel by viewModels()
    private val petDetailsViewmodel: PetDetailsViewmodel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TailyCareTheme {
                NavigationWrapper(
                    registerPetViewModel = registerPetViewModel,
                    addMealViewmodel = addMealViewmodel,
                    dashBoardViewModel = petDetailsViewmodel
                )
            }
        }
    }
}


