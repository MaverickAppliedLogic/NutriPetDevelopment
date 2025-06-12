package com.maverickapps.tailyCare.petsFeature

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.maverickapps.tailyCare.core.navigation.NavigationWrapper
import com.maverickapps.tailyCare.core.ui.theme.TailyCareTheme
import com.maverickapps.tailyCare.petsFeature.ui.viewmodel.AddFoodViewModel
import com.maverickapps.tailyCare.petsFeature.ui.viewmodel.AddMealViewmodel
import com.maverickapps.tailyCare.petsFeature.ui.viewmodel.DashboardViewModel
import com.maverickapps.tailyCare.petsFeature.ui.viewmodel.RegisterPetViewmodel
import com.maverickapps.tailyCare.ui.viewmodel.FoodsListViewmodel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainPetActivity : ComponentActivity() {
    private val registerPetViewModel: RegisterPetViewmodel by viewModels()
    private val addMealViewmodel: AddMealViewmodel by viewModels()
    private val dashboardViewModel: DashboardViewModel by viewModels()
    private val addFoodViewmodel: AddFoodViewModel by viewModels()
    private val foodsListViewmodel: FoodsListViewmodel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TailyCareTheme {
                NavigationWrapper(
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


