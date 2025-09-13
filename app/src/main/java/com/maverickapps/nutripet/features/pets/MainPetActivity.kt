package com.maverickapps.nutripet.features.pets

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.maverickapps.nutripet.core.navigation.NavigationWrapper
import com.maverickapps.nutripet.core.ui.theme.NutriPetTheme
import com.maverickapps.nutripet.features.events.ui.permissionDialogs.notification.NotificationPermissionDialog
import com.maverickapps.nutripet.features.events.ui.viewmodel.EventsViewModel
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
    private val eventsViewModel: EventsViewModel by viewModels()
    
    //TODO revisar creación de notificaciones para comidas puntuales para el dia siguiente
    //TODO revisar rescheduling de notificaciones cuando se apaga el movil y asi no depender de abrir la app

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val showSplashScreen by dashboardViewModel.showSplashScreen.collectAsState()
            if(!showSplashScreen){
                NutriPetTheme {
                    val showPostPermissionDialog by
                    eventsViewModel.mustRequestPostPermissionDialog.collectAsState()
                    val showExactAlarmPermissionDialog by
                    eventsViewModel.mustRequestExactAlarmDialog.collectAsState()
                    var requestPostPermission by rememberSaveable {
                        mutableStateOf(showPostPermissionDialog)
                    }
                    var requestExactAlarmPermission by rememberSaveable {
                        mutableStateOf(showExactAlarmPermissionDialog)
                    }

                    if(requestPostPermission || requestExactAlarmPermission){
                        NotificationPermissionDialog(
                            postPermissionGranted = !requestPostPermission,
                            schedulePermissionGranted = !requestExactAlarmPermission,
                            postPermissionRequest = {
                                eventsViewModel.requestPostPermission(this)
                                requestPostPermission = false
                            },
                            schedulePermissionRequest = {
                                eventsViewModel.requestExactAlarmPermission()
                                requestExactAlarmPermission = false
                            },
                            dismiss = {
                                if(requestPostPermission){
                                    requestPostPermission = false
                                }else{
                                    requestExactAlarmPermission = false
                                }
                            },
                            modifier = Modifier.fillMaxHeight(0.7f)
                        )
                    }
                    else{
                        LaunchedEffect(Unit) {
                            if(!showPostPermissionDialog && !showExactAlarmPermissionDialog){
                                eventsViewModel.scheduleDayChanger()
                                eventsViewModel.fetchMealEvents()
                            }
                        }
                    }
                    NavigationWrapper(
                        sharedDataViewmodel = sharedDataViewmodel,
                        registerPetViewModel = registerPetViewModel,
                        addMealViewmodel = addMealViewmodel,
                        dashBoardViewModel = dashboardViewModel,
                        addFoodViewModel = addFoodViewmodel,
                        foodListViewModel = foodsListViewmodel,)
                }
            }
        }
    }
}


