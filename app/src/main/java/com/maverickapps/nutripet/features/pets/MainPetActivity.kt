package com.maverickapps.nutripet.features.pets

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.maverickapps.nutripet.core.navigation.NavigationWrapper
import com.maverickapps.nutripet.core.ui.theme.NutriPetTheme
import com.maverickapps.nutripet.features.pets.ui.viewmodel.AddFoodViewModel
import com.maverickapps.nutripet.features.pets.ui.viewmodel.AddMealViewmodel
import com.maverickapps.nutripet.features.pets.ui.viewmodel.DashboardViewModel
import com.maverickapps.nutripet.features.pets.ui.viewmodel.RegisterPetViewmodel
import com.maverickapps.nutripet.features.pets.ui.viewmodel.SharedDataViewmodel
import com.maverickapps.nutripet.ui.viewmodel.FoodsListViewmodel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainPetActivity : ComponentActivity() {
    private val registerPetViewModel: RegisterPetViewmodel by viewModels()
    private val addMealViewmodel: AddMealViewmodel by viewModels()
    private val dashboardViewModel: DashboardViewModel by viewModels()
    private val addFoodViewmodel: AddFoodViewModel by viewModels()
    private val foodsListViewmodel: FoodsListViewmodel by viewModels()
    private val sharedDataViewmodel: SharedDataViewmodel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NutriPetTheme {
                NavigationWrapper(
                    sharedDataViewmodel = sharedDataViewmodel,
                    registerPetViewModel = registerPetViewModel,
                    addMealViewmodel = addMealViewmodel,
                    dashBoardViewModel = dashboardViewModel,
                    addFoodViewModel = addFoodViewmodel,
                    foodListViewModel = foodsListViewmodel
                )
            }
        }
    }
}


