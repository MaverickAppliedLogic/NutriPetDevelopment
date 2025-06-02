package com.example.feedm.core.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.feedm.petsFeature.ui.view.screens.addFoodScreen.AddFoodScreen
import com.example.feedm.petsFeature.ui.view.screens.addMealScreen.AddMealScreen
import com.example.feedm.petsFeature.ui.view.screens.dashboardScreen.DashBoardScreen
import com.example.feedm.petsFeature.ui.view.screens.foodListScreen.FoodListScreen
import com.example.feedm.petsFeature.ui.view.screens.registerPetScreen.RegisterPetScreen
import com.example.feedm.petsFeature.ui.viewmodel.AddFoodViewModel
import com.example.feedm.petsFeature.ui.viewmodel.AddMealViewmodel
import com.example.feedm.petsFeature.ui.viewmodel.DashboardViewModel
import com.example.feedm.petsFeature.ui.viewmodel.RegisterPetViewmodel
import com.example.feedm.ui.viewmodel.FoodsListViewmodel

@Composable
fun NavigationWrapper(
    registerPetViewModel: RegisterPetViewmodel,
    addMealViewmodel: AddMealViewmodel,
    dashBoardViewModel: DashboardViewModel,
    addFoodViewModel: AddFoodViewModel,
    foodListViewModel: FoodsListViewmodel
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = DashBoardScreen(false)) {
        composable<DashBoardScreen> {
          val shouldRefresh = it.toRoute<DashBoardScreen>().shouldRefresh?:false
            DashBoardScreen(dashBoardViewModel, needToRefresh = shouldRefresh) { destination, petId, mealId ->
                when (destination) {
                    "RegisterPet" -> navController.navigate(RegisterPet(null))
                    "AddMeal" -> navController.navigate(AddMeal(petId = petId!!, mealId = mealId))
                    "AddFood" -> navController.navigate(AddFood(""))
                    "EditPet" -> navController.navigate(RegisterPet(petId))
                }
            }
        }
        composable<RegisterPet> {
            val registerPet = it.toRoute<RegisterPet>()
            RegisterPetScreen(
                petId = registerPet.petId,
                registerPetViewmodel = registerPetViewModel
            ) {
                navController.navigate(DashBoardScreen(shouldRefresh = true)) {
                    popUpTo<DashBoardScreen> { inclusive = true }
                    registerPetViewModel.setInitialPet()
                }
            }
        }
        composable<AddMeal> {
            val addMeal = it.toRoute<AddMeal>()
            BackHandler {  navController.navigate(DashBoardScreen(false)) {
                popUpTo<DashBoardScreen> { inclusive = true }
                addMealViewmodel.setInitialSelectedFood()
            } }
            AddMealScreen(
                addMealViewmodel = addMealViewmodel,
                mealId = addMeal.mealId ?: -1,
                foodId = addMeal.foodId ?: -1,
                petId = addMeal.petId!!,
                navToFoodList = { navController.navigate(FoodList("FromAddMeal", addMeal.petId)) },
                navToBackStack = {
                    navController.navigate(DashBoardScreen(false)) {
                        popUpTo<DashBoardScreen> { inclusive = true }
                        addMealViewmodel.setInitialSelectedFood()
                    }
                })
        }
        composable<AddFood> {
            val addFood = it.toRoute<AddFood>()
            val petId = it.toRoute<AddFood>().petId
            println("AddFoodNavigationWrapper")
            println(petId)
            val getBackDestination = {
                if (addFood.origin == "FromAddMeal") {
                    navController.navigate(FoodList("FromAddMeal", petId)) {
                        popUpTo<FoodList> { inclusive = true }
                        addFoodViewModel.setInitialFood()
                    }
                } else {
                    navController.popBackStack()
                    addFoodViewModel.setInitialFood()
                }
            }
            val getDestination = {
                if (addFood.origin == "FromAddMeal") {
                    navController.navigate(FoodList("FromAddMeal", petId)) {
                        popUpTo<FoodList> { inclusive = true }
                        addFoodViewModel.setInitialFood()
                    }
                } else {
                    navController.navigate(FoodList("FromAddFood"))
                    addFoodViewModel.setInitialFood()
                }
            }
            BackHandler { getBackDestination() }
            AddFoodScreen(
                addFoodViewModel = addFoodViewModel,
                navToBackStack = { getBackDestination() },
                navToFoodList = {
                    getDestination()
                })
        }
        composable<FoodList> {
            val foodListOrigin = it.toRoute<FoodList>().origin
            val petId = it.toRoute<FoodList>().petId
            foodListViewModel.fetchData()
            val getBackDestination = { foodId: Int? ->
                when (foodListOrigin) {
                    "FromAddFood" -> navController.navigate(DashBoardScreen(false)) {
                        popUpTo<DashBoardScreen> { inclusive = true }
                    }

                    "FromAddMeal" -> navController.navigate(AddMeal(petId, foodId)) {
                        popUpTo<AddMeal> { inclusive = true }
                    }

                    else -> {}
                }
            }
            BackHandler { getBackDestination(null) }
            FoodListScreen(
                navToAddFood = { navController.navigate(AddFood(foodListOrigin,petId)) },
                navToBackStack = { foodId -> getBackDestination(foodId) },
                foodsListViewmodel = foodListViewModel
            )
        }
    }
}