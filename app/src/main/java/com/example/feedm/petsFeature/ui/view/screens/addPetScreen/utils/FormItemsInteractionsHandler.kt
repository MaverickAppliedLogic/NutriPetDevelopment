package com.example.feedm.petsFeature.ui.view.screens.addPetScreen.utils

import com.example.feedm.petsFeature.ui.view.screens.addPetScreen.components.FormFieldStates.WAITING
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FormItemsInteractionsHandler(itemsCount: Int) {

    private val listSize = itemsCount
    private val _statesList = MutableStateFlow(List(itemsCount) {WAITING})
    private val _expansionList = MutableStateFlow(List(itemsCount) {false})

    val statesList: StateFlow<List<Int>> = _statesList
    val expansionList: StateFlow<List<Boolean>> = _expansionList


    fun onItemExpansionChanged(index: Int){
        val newStates = _expansionList.value.toMutableList()
        for (i in 0..<listSize) {
            if (i != index){
                newStates[i] = false
            }}
        newStates[index] = !newStates[index]
        _expansionList.value = newStates.toList()
    }

    fun onItemStateChanged(index: Int, state: Int){
        val newStates = _statesList.value.toMutableList()
        newStates[index] = state
        _statesList.value = newStates.toList()
    }
}