package com.example.feedm.core.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.feedm.petsFeature.ui.view.screens.AddFoodScreen
import com.example.feedm.petsFeature.ui.view.screens.AddMealScreen
import com.example.feedm.petsFeature.ui.view.screens.AddPetScreen
import com.example.feedm.petsFeature.ui.view.screens.DashBoardScreen
import com.example.feedm.petsFeature.ui.view.screens.FoodListScreen

//TODO implementar los valores a compartir entre pantallas para la extraccion de datos
@Composable
fun NavigationWrapper() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = DashBoardScreen) {
        composable<DashBoardScreen> {
            DashBoardScreen {
                when (it) {
                    "AddPetScreen" -> navController.navigate(AddPetScreen)
                    "AddMealScreen" -> navController.navigate(AddMealScreen)
                    "AddFoodScreen" -> navController.navigate(AddFoodScreen())
                }
            }
        }
        composable<AddPetScreen> {
            AddPetScreen { navController.popBackStack() }
        }
        composable<AddMealScreen> {
            AddMealScreen(
                navToFoodList = { navController.navigate(FoodListScreen("FromAddMeal")) },
                navToBackStack = { navController.popBackStack() })
        }
        composable<AddFoodScreen> {
            val origin = it.toRoute<AddFoodScreen>().origin
            val getBackDestination = {
                if (origin == "FromFoodList") {
                    navController.navigate(FoodListScreen("FromAddMeal")) {
                        popUpTo<FoodListScreen> { inclusive = true }
                    }
                } else {
                    navController.popBackStack()
                }
            }
            val getDestination = {
                if (origin == "FromAddMeal") {
                    navController.navigate(FoodListScreen("FromAddMeal")) {
                        popUpTo<FoodListScreen> { inclusive = true }
                    }
                } else {
                    navController.navigate(FoodListScreen("FromAddFood"))
                }
            }
            BackHandler { getBackDestination() }
            AddFoodScreen(navToBackStack = { getBackDestination() },
                navToFoodList = {
                    getDestination()
                })
        }
        composable<FoodListScreen> {
            val foodListOrigin = it.toRoute<FoodListScreen>().origin
            val getBackDestination = {
                when (foodListOrigin) {
                    "FromAddFood" -> navController.navigate(DashBoardScreen) {
                        popUpTo<DashBoardScreen> { inclusive = true }
                    }
                    "FromAddMeal" -> navController.navigate(AddMealScreen) {
                        popUpTo<AddMealScreen> { inclusive = true }
                    }
                    else -> {}
                }
            }
            BackHandler { getBackDestination() }
            FoodListScreen(
                navToAddFood = { navController.navigate(AddFoodScreen(foodListOrigin)) },
                navToBackStack = { getBackDestination() }
            )
        }
    }
}