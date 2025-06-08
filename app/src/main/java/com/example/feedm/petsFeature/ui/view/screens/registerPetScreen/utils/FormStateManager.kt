package com.example.feedm.petsFeature.ui.view.screens.registerPetScreen.utils

import com.example.feedm.petsFeature.domain.objectTasks.pet.model.PetModel
import com.example.feedm.petsFeature.ui.view.screens.registerPetScreen.components.contentComponents.fieldsComponents.formFields.FormFieldStates.INVALID
import com.example.feedm.petsFeature.ui.view.screens.registerPetScreen.components.contentComponents.fieldsComponents.formFields.FormFieldStates.VALID
import com.example.feedm.petsFeature.ui.view.screens.registerPetScreen.components.contentComponents.fieldsComponents.formFields.FormFieldStates.WAITING
import com.example.feedm.petsFeature.ui.view.screens.registerPetScreen.utils.FormItemsInteractionsHandler.Companion.ACTIVITY_FIELD
import com.example.feedm.petsFeature.ui.view.screens.registerPetScreen.utils.FormItemsInteractionsHandler.Companion.AGE_FIELD
import com.example.feedm.petsFeature.ui.view.screens.registerPetScreen.utils.FormItemsInteractionsHandler.Companion.GOAL_FIELD
import com.example.feedm.petsFeature.ui.view.screens.registerPetScreen.utils.FormItemsInteractionsHandler.Companion.PET_NAME_FIELD
import com.example.feedm.petsFeature.ui.view.screens.registerPetScreen.utils.FormItemsInteractionsHandler.Companion.SEX_FIELD
import com.example.feedm.petsFeature.ui.view.screens.registerPetScreen.utils.FormItemsInteractionsHandler.Companion.STERILIZED_FIELD
import com.example.feedm.petsFeature.ui.view.screens.registerPetScreen.utils.FormItemsInteractionsHandler.Companion.WEIGHT_FIELD
import com.example.feedm.petsFeature.ui.viewmodel.RegisterPetViewmodel

class FormStateManager {

    fun actionTriggered(
        action: Int,
        index: Int,
        registerPetViewmodel: RegisterPetViewmodel,
        petToBeAdded: PetModel,
        formItemsHandler: FormItemsInteractionsHandler,
        navigateBack: (Boolean) -> Unit
    ){
        val fields = mutableListOf(
            PET_NAME_FIELD,
            AGE_FIELD,
            SEX_FIELD,
            WEIGHT_FIELD,
            GOAL_FIELD,
            ACTIVITY_FIELD,
            STERILIZED_FIELD
        )
        when(action){
            0 -> onAddClicked(fields, registerPetViewmodel, petToBeAdded, formItemsHandler, navigateBack)
            else -> onTrailingIconClicked(index, fields, registerPetViewmodel, formItemsHandler)
        }
    }


    private fun onAddClicked(
        fields: List<Int>,
        registerPetViewmodel: RegisterPetViewmodel,
        petToBeAdded: PetModel,
        formItemsHandler: FormItemsInteractionsHandler,
        navigateBack: (Boolean) -> Unit
    ) {
        val validationResults = validateFields(fields, registerPetViewmodel)
        var canPetBeAdded = true

        validationResults.forEach { (field, state) ->
            formItemsHandler.allFieldsChanged(field, state)
            if (state == INVALID) canPetBeAdded = false
        }

        if (canPetBeAdded) {
            registerPetViewmodel.registerPet(petToBeAdded)
            navigateBack(true)
        }
    }


    private fun onTrailingIconClicked(
        index: Int,
        fields: List<Int>,
        registerPetViewmodel: RegisterPetViewmodel,
        formItemsHandler: FormItemsInteractionsHandler
    ) {
        formItemsHandler.onItemExpansionChanged(index)

        val validationResults = validateFields(fields, registerPetViewmodel)

        validationResults.forEach { (field, state) ->
            formItemsHandler.onItemStateChanged(field, state)
        }
    }


    private fun validateFields(fields: List<Int>, registerPetViewmodel: RegisterPetViewmodel): Map<Int, Int> {
        val validFields = registerPetViewmodel.validatePet()
        return fields.associateWith { field ->
            when {
                validFields.contains(field) -> VALID
                field == SEX_FIELD || field == STERILIZED_FIELD -> WAITING
                else -> INVALID
            }
        }
    }
}