package com.maverickapps.nutripet.features.pets

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.maverickapps.nutripet.core.navigation.NavigationWrapper
import com.maverickapps.nutripet.core.ui.theme.NutriPetTheme
import com.maverickapps.nutripet.features.events.ui.permissionDialogs.notification.NotificationPermissionDialog
import com.maverickapps.nutripet.features.events.ui.viewmodel.PermissionsViewmodel
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
    private val permissionsViewmodel: PermissionsViewmodel by viewModels()

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()



        setContent {
            NutriPetTheme {
                var showPermissionRequestDialog by rememberSaveable {
                    mutableStateOf(!permissionsViewmodel.postPermissionIsGranted())
                }
                if(showPermissionRequestDialog){
                    NotificationPermissionDialog(
                        postPermissionGranted = false,
                        schedulePermissionGranted = true,
                        postPermissionRequest = {
                            permissionsViewmodel.requestPostPermission(this)
                            showPermissionRequestDialog = false
                                                },
                        schedulePermissionRequest = {},
                        dismiss = { showPermissionRequestDialog = false },
                        modifier = Modifier.fillMaxHeight(0.7f)
                    )
                }
                NavigationWrapper(
                    sharedDataViewmodel = sharedDataViewmodel,
                    registerPetViewModel = registerPetViewModel,
                    addMealViewmodel = addMealViewmodel,
                    dashBoardViewModel = dashboardViewModel,
                    addFoodViewModel = addFoodViewmodel,
                    foodListViewModel = foodsListViewmodel,
                    permissionsViewmodel = permissionsViewmodel
                )
            }
        }
    }
}


