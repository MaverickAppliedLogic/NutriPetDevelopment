package com.maverickapps.tailyCare.core.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.maverickapps.tailyCare.petsFeature.ui.view.screens.addFoodScreen.AddFoodScreen
import com.maverickapps.tailyCare.petsFeature.ui.view.screens.addMealScreen.AddMealScreen
import com.maverickapps.tailyCare.petsFeature.ui.view.screens.dashboardScreen.DashBoardScreen
import com.maverickapps.tailyCare.petsFeature.ui.view.screens.foodListScreen.FoodListScreen
import com.maverickapps.tailyCare.petsFeature.ui.view.screens.registerPetScreen.RegisterPetScreen
import com.maverickapps.tailyCare.petsFeature.ui.viewmodel.AddFoodViewModel
import com.maverickapps.tailyCare.petsFeature.ui.viewmodel.AddMealViewmodel
import com.maverickapps.tailyCare.petsFeature.ui.viewmodel.DashboardViewModel
import com.maverickapps.tailyCare.petsFeature.ui.viewmodel.RegisterPetViewmodel
import com.maverickapps.tailyCare.petsFeature.ui.viewmodel.SharedDataViewmodel
import com.maverickapps.tailyCare.ui.viewmodel.FoodsListViewmodel

@Composable
fun NavigationWrapper(
    sharedDataViewmodel: SharedDataViewmodel,
    registerPetViewModel: RegisterPetViewmodel,
    addMealViewmodel: AddMealViewmodel,
    dashBoardViewModel: DashboardViewModel,
    addFoodViewModel: AddFoodViewModel,
    foodListViewModel: FoodsListViewmodel
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = DashBoardScreen(false)) {
        composable<DashBoardScreen> {
            val shouldRefresh = it.toRoute<DashBoardScreen>().shouldRefresh ?: false
            DashBoardScreen(
                dashBoardViewModel,
                needToRefresh = shouldRefresh
            ) { destination, petId, mealId ->
                sharedDataViewmodel.fetchPetId(petId)
                sharedDataViewmodel.fetchMealId(mealId)
                when (destination) {
                    "RegisterPet" -> navController.navigate(RegisterPet)
                    "AddMeal" -> navController.navigate(AddMeal)
                    "AddFood" -> navController.navigate(AddFood(""))
                    "EditPet" -> navController.navigate(RegisterPet)
                }
            }
        }
        composable<RegisterPet> {
            val petId by sharedDataViewmodel.selectedPetId.collectAsStateWithLifecycle()
            RegisterPetScreen(
                petId = petId,
                registerPetViewmodel = registerPetViewModel
            ) {
                navController.navigate(DashBoardScreen(shouldRefresh = true)) {
                    popUpTo<DashBoardScreen> { inclusive = true }
                    registerPetViewModel.setInitialPet()
                }
            }
        }
        composable<AddMeal> {
            val mealId by sharedDataViewmodel.selectedMealId.collectAsStateWithLifecycle()
            val petId by sharedDataViewmodel.selectedPetId.collectAsStateWithLifecycle()
            val foodId by sharedDataViewmodel.selectedFoodId.collectAsStateWithLifecycle()

            BackHandler {
                navController.navigate(DashBoardScreen(false)) {
                    sharedDataViewmodel.wipeData()
                    addMealViewmodel.setInitialMeal()
                    popUpTo<DashBoardScreen> { inclusive = true }
                }
            }
            AddMealScreen(
                addMealViewmodel = addMealViewmodel,
                mealId = mealId,
                foodId = foodId,
                petId = petId!!,
                navToFoodList = {
                    navController.navigate(
                        FoodList("FromAddMeal")
                    )
                },
                navToBackStack = {
                    navController.navigate(DashBoardScreen(false)) {
                        sharedDataViewmodel.wipeData()
                        addMealViewmodel.setInitialMeal()
                        popUpTo<DashBoardScreen> { inclusive = true }
                    }
                })
        }
        composable<AddFood> {
            val addFood = it.toRoute<AddFood>()
            val getBackDestination = {
                if (addFood.origin == "FromAddMeal") {
                    navController.navigate(FoodList("FromAddMeal")) {
                        sharedDataViewmodel.clearFoodId()
                        addFoodViewModel.setInitialFood()
                        popUpTo<FoodList> { inclusive = true }
                    }
                } else {
                    sharedDataViewmodel.clearFoodId()
                    addFoodViewModel.setInitialFood()
                    navController.popBackStack()
                }
            }
            val getDestination = {
                if (addFood.origin == "FromAddMeal") {
                    navController.navigate(FoodList("FromAddMeal")) {
                        sharedDataViewmodel.clearFoodId()
                        addFoodViewModel.setInitialFood()
                        popUpTo<FoodList> { inclusive = true }
                    }
                } else {
                    sharedDataViewmodel.clearFoodId()
                    addFoodViewModel.setInitialFood()
                    navController.navigate(FoodList("FromAddFood"))
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
            foodListViewModel.fetchData()
            val getBackDestination = { foodId: Int? ->
                when (foodListOrigin) {
                    "FromAddFood" -> navController.navigate(DashBoardScreen(false)) {
                        popUpTo<DashBoardScreen> { inclusive = true }
                    }

                    "FromAddMeal" -> navController.navigate(AddMeal) {
                        sharedDataViewmodel.fetchFoodId(foodId)
                        popUpTo<AddMeal> { inclusive = true }
                    }

                    else -> {}
                }
            }
            BackHandler { getBackDestination(null) }
            FoodListScreen(
                navToAddFood = { navController.navigate(AddFood(foodListOrigin)) },
                navToBackStack = { foodId -> getBackDestination(foodId) },
                foodsListViewmodel = foodListViewModel
            )
        }
    }
}