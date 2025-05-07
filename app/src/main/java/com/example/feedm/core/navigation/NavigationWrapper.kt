package com.example.feedm.core.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.feedm.petsFeature.ui.view.screens.Content
import com.example.feedm.petsFeature.ui.view.screens.DashBoardScreen
import com.example.feedm.petsFeature.ui.view.screens.FoodListScreen
import com.example.feedm.petsFeature.ui.view.screens.addMealScreen.AddMealScreen
import com.example.feedm.petsFeature.ui.view.screens.registerPetScreen.RegisterPetScreen
import com.example.feedm.petsFeature.ui.viewmodel.AddMealViewmodel
import com.example.feedm.petsFeature.ui.viewmodel.AddPetViewmodel
import com.example.feedm.petsFeature.ui.viewmodel.PetDetailsViewmodel

@Composable
fun NavigationWrapper(
    addPetViewModel: AddPetViewmodel,
    addMealViewmodel: AddMealViewmodel,
    dashBoardViewModel: PetDetailsViewmodel
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = DashBoardScreen) {
        composable<DashBoardScreen> {
            DashBoardScreen(dashBoardViewModel) { destination, petId ->
                when (destination) {
                    "RegisterPet" -> navController.navigate(RegisterPet(petId))
                    "AddMeal" -> navController.navigate(AddMeal(petId))
                    "AddFood" -> navController.navigate(AddFood(""))
                }
            }
        }
        composable<RegisterPet> {
            val registerPet = it.toRoute<RegisterPet>()
            RegisterPetScreen(petId = registerPet.petId,
                addPetViewmodel = addPetViewModel) { navController.popBackStack() }
        }
        composable<AddMeal> {
            val addMeal = it.toRoute<AddMeal>()
            AddMealScreen(
                addMealViewmodel = addMealViewmodel,
                foodId = -1,
                petId = 0,
                navToFoodList = { navController.navigate(FoodList("FromAddMeal", addMeal.petId)) },
                navToBackStack = { navController.popBackStack() })
        }
        composable<AddFood> {
            val addFood = it.toRoute<AddFood>()
            val getBackDestination = {
                if (addFood.origin == "FromFoodList") {
                    navController.navigate(FoodList("FromAddMeal")) {
                        popUpTo<FoodList> { inclusive = true }
                    }
                } else {
                    navController.popBackStack()
                }
            }
            val getDestination = {
                if (addFood.origin == "FromAddMeal") {
                    navController.navigate(FoodList("FromAddMeal")) {
                        popUpTo<FoodList> { inclusive = true }
                    }
                } else {
                    navController.navigate(FoodList("FromAddFood"))
                }
            }
            BackHandler { getBackDestination() }
            Content(navToBackStack = { getBackDestination() },
                navToFoodList = {
                    getDestination()
                })
        }
        composable<FoodList> {
            val foodListOrigin = it.toRoute<FoodList>().origin
            val petId = it.toRoute<FoodList>().petId
            val getBackDestination =  {
                when (foodListOrigin) {
                    "FromAddFood" -> navController.navigate(DashBoardScreen) {
                        popUpTo<DashBoardScreen> { inclusive = true }
                    }
                    "FromAddMeal" -> navController.navigate(AddMeal(petId)) {
                        popUpTo<AddMeal> { inclusive = true }
                    }
                    else -> {}
                }
            }
            BackHandler { getBackDestination() }
            FoodListScreen(
                navToAddFood = { navController.navigate(AddFood(foodListOrigin)) },
                navToBackStack = { getBackDestination() }
            )
        }
    }
}