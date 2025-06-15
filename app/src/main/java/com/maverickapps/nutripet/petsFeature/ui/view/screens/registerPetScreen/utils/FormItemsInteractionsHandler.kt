package com.maverickapps.nutripet.petsFeature.ui.view.screens.registerPetScreen.utils

import com.maverickapps.nutripet.petsFeature.ui.view.screens.registerPetScreen.components.contentComponents.fieldsComponents.formFields.FormFieldStates.WAITING
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FormItemsInteractionsHandler {

    companion object {
        const val PET_NAME_FIELD = 0
        const val AGE_FIELD = 1
        const val SEX_FIELD = 2
        const val WEIGHT_FIELD = 3
        const val GOAL_FIELD = 4
        const val ACTIVITY_FIELD = 5
        const val STERILIZED_FIELD = 6
    }
    private val fieldWasVisible = mutableListOf(false, false, false, false, false, false, false)

    private val _petNameFieldVisibility = MutableStateFlow(false)
    private val _ageFieldVisibility = MutableStateFlow(false)
    private val _sexFieldVisibility = MutableStateFlow(false)
    private val _weightFieldVisibility = MutableStateFlow(false)
    private val _goalFieldVisibility = MutableStateFlow(false)
    private val _sterilizedFieldVisibility = MutableStateFlow(false)
    private val _activityFieldVisibility = MutableStateFlow(false)

    var petNameFieldVisibility: StateFlow<Boolean> = _petNameFieldVisibility
    var ageFieldVisibility: StateFlow<Boolean> = _ageFieldVisibility
    var sexFieldVisibility: StateFlow<Boolean> = _sexFieldVisibility
    var weightFieldVisibility: StateFlow<Boolean> = _weightFieldVisibility
    var goalFieldVisibility: StateFlow<Boolean> = _goalFieldVisibility
    var sterilizedFieldVisibility: StateFlow<Boolean> = _sterilizedFieldVisibility
    var activityField : StateFlow<Boolean> = _activityFieldVisibility

    private val _petNameFieldState = MutableStateFlow(WAITING)
    private val _ageFieldState = MutableStateFlow(WAITING)
    private val _sexFieldState = MutableStateFlow(WAITING)
    private val _weightFieldState = MutableStateFlow(WAITING)
    private val _goalFieldState = MutableStateFlow(WAITING)
    private val _sterilizedFieldState = MutableStateFlow(WAITING)
    private val _activityFieldState = MutableStateFlow(WAITING)

    var petNameFieldState: StateFlow<Int> = _petNameFieldState
    var ageFieldState: StateFlow<Int> = _ageFieldState
    var sexFieldState: StateFlow<Int> = _sexFieldState
    var weightFieldState: StateFlow<Int> = _weightFieldState
    var goalFieldState: StateFlow<Int> = _goalFieldState
    var sterilizedFieldState: StateFlow<Int> = _sterilizedFieldState
    var activityFieldState: StateFlow<Int> = _activityFieldState

    fun allFieldsChanged(index: Int, state: Int){
        when (index) {
            PET_NAME_FIELD ->
                    _petNameFieldState.value = state
            AGE_FIELD ->
                    _ageFieldState.value = state
            SEX_FIELD ->
                    _sexFieldState.value = state
            WEIGHT_FIELD ->
                    _weightFieldState.value = state
            GOAL_FIELD ->
                    _goalFieldState.value = state
            STERILIZED_FIELD ->
                    _sterilizedFieldState.value = state
            ACTIVITY_FIELD ->
                    _activityFieldState.value = state
        }
    }

    fun onItemExpansionChanged(index: Int){
        if (!fieldWasVisible[index]) fieldWasVisible[index] = true
        when(index){
            PET_NAME_FIELD ->
                togglePetNameFieldVisibility()
            AGE_FIELD ->
                toggleAgeFieldVisibility()
            SEX_FIELD ->
                toggleSexFieldVisibility()
            WEIGHT_FIELD ->
                toggleWeightFieldVisibility()
            GOAL_FIELD ->
                toggleGoalFieldVisibility()
            STERILIZED_FIELD ->
                toggleSterilizedFieldVisibility()
            ACTIVITY_FIELD ->
                toggleActivityFieldVisibility()
        }
    }

    fun onItemStateChanged(index: Int, state: Int) {
        when (index) {
            PET_NAME_FIELD ->
                if (fieldWasVisible[index] && !_petNameFieldVisibility.value) {
                    _petNameFieldState.value = state
                }
            AGE_FIELD ->
                if (fieldWasVisible[index] && !_ageFieldVisibility.value) {
                    _ageFieldState.value = state
                }
            SEX_FIELD ->
                if (fieldWasVisible[index] && !_sexFieldVisibility.value) {
                    _sexFieldState.value = state
                }
            WEIGHT_FIELD ->
                if (fieldWasVisible[index] && !_weightFieldVisibility.value) {
                    _weightFieldState.value = state
                }
            GOAL_FIELD ->
                if (fieldWasVisible[index] && !_goalFieldVisibility.value) {
                    _goalFieldState.value = state
                }
            STERILIZED_FIELD ->
                if (fieldWasVisible[index] && !_sterilizedFieldVisibility.value) {
                    _sterilizedFieldState.value = state
                }
            ACTIVITY_FIELD ->
                if (fieldWasVisible[index] && !_activityFieldVisibility.value) {
                    _activityFieldState.value = state
                }
        }
    }



    private fun togglePetNameFieldVisibility() {
        _petNameFieldVisibility.value = !_petNameFieldVisibility.value
        _ageFieldVisibility.value = false
        _sexFieldVisibility.value = false
        _weightFieldVisibility.value = false
        _goalFieldVisibility.value = false
        _sterilizedFieldVisibility.value = false
        _activityFieldVisibility.value = false
    }

    private fun toggleAgeFieldVisibility() {
        _petNameFieldVisibility.value = false
        _ageFieldVisibility.value = !_ageFieldVisibility.value
        _sexFieldVisibility.value = false
        _weightFieldVisibility.value = false
        _goalFieldVisibility.value = false
        _sterilizedFieldVisibility.value = false
        _activityFieldVisibility.value = false
    }

    private fun toggleSexFieldVisibility() {
        _petNameFieldVisibility.value = false
        _ageFieldVisibility.value = false
        _sexFieldVisibility.value = !_sexFieldVisibility.value
        _weightFieldVisibility.value = false
        _goalFieldVisibility.value = false
        _sterilizedFieldVisibility.value = false
        _activityFieldVisibility.value = false
    }

    private fun toggleWeightFieldVisibility() {
        _petNameFieldVisibility.value = false
        _ageFieldVisibility.value = false
        _sexFieldVisibility.value = false
        _weightFieldVisibility.value = !_weightFieldVisibility.value
        _goalFieldVisibility.value = false
        _sterilizedFieldVisibility.value = false
        _activityFieldVisibility.value = false
    }

    private fun toggleGoalFieldVisibility() {
        _petNameFieldVisibility.value = false
        _ageFieldVisibility.value = false
        _sexFieldVisibility.value = false
        _weightFieldVisibility.value = false
        _goalFieldVisibility.value = !_goalFieldVisibility.value
        _sterilizedFieldVisibility.value = false
        _activityFieldVisibility.value = false
    }

    private fun toggleSterilizedFieldVisibility() {
        _petNameFieldVisibility.value = false
        _ageFieldVisibility.value = false
        _sexFieldVisibility.value = false
        _weightFieldVisibility.value = false
        _goalFieldVisibility.value = false
        _sterilizedFieldVisibility.value = !_sterilizedFieldVisibility.value
        _activityFieldVisibility.value = false
    }

    private fun toggleActivityFieldVisibility() {
        _petNameFieldVisibility.value = false
        _ageFieldVisibility.value = false
        _sexFieldVisibility.value = false
        _weightFieldVisibility.value = false
        _goalFieldVisibility.value = false
        _sterilizedFieldVisibility.value = false
        _activityFieldVisibility.value = !_activityFieldVisibility.value
    }


}