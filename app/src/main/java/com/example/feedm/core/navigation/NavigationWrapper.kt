package com.example.feedm.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.feedm.petsFeature.ui.view.screens.AddFoodScreen
import com.example.feedm.petsFeature.ui.view.screens.AddMealScreen
import com.example.feedm.petsFeature.ui.view.screens.AddPetScreen
import com.example.feedm.petsFeature.ui.view.screens.DashBoardScreen
import com.example.feedm.petsFeature.ui.view.screens.FoodListScreen

@Composable
fun NavigationWrapper() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = DashBoardScreen) {
        composable<DashBoardScreen> {
            DashBoardScreen {
                when (it) {
                    "AddPetScreen" -> navController.navigate(AddPetScreen)
                    "AddMealScreen" -> navController.navigate(AddMealScreen)
                    "AddFoodScreen" -> navController.navigate(AddFoodScreen)
                }
            }
        }
        composable<AddPetScreen> {
            AddPetScreen { navController.popBackStack() }
        }
        composable<AddMealScreen> {
            AddMealScreen(
                navToFoodList = { navController.navigate(FoodListScreen) },
                navToBackStack = { navController.popBackStack() })
        }
        composable<AddFoodScreen> {
            AddFoodScreen { navController.popBackStack() }
        }
        composable<FoodListScreen> {
            FoodListScreen(
                navToAddFood = { navController.navigate(AddMealScreen) },
                navToBackStack = { when(it){
                    "FromAddFood" -> navController.popBackStack()
                } }
            )
        }
    }
}