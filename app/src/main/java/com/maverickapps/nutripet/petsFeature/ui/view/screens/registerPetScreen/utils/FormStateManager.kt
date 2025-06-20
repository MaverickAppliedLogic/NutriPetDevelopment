package com.maverickapps.nutripet.petsFeature.ui.view.screens.registerPetScreen.utils

import com.maverickapps.nutripet.petsFeature.domain.objectTasks.pet.model.PetModel
import com.maverickapps.nutripet.petsFeature.ui.view.screens.registerPetScreen.components.contentComponents.scaffoldComponents.fieldsComponents.formFields.FormFieldStates.INVALID
import com.maverickapps.nutripet.petsFeature.ui.view.screens.registerPetScreen.components.contentComponents.scaffoldComponents.fieldsComponents.formFields.FormFieldStates.VALID
import com.maverickapps.nutripet.petsFeature.ui.view.screens.registerPetScreen.components.contentComponents.scaffoldComponents.fieldsComponents.formFields.FormFieldStates.WAITING
import com.maverickapps.nutripet.petsFeature.ui.view.screens.registerPetScreen.utils.FormItemsInteractionsHandler.Companion.ACTIVITY_FIELD
import com.maverickapps.nutripet.petsFeature.ui.view.screens.registerPetScreen.utils.FormItemsInteractionsHandler.Companion.AGE_FIELD
import com.maverickapps.nutripet.petsFeature.ui.view.screens.registerPetScreen.utils.FormItemsInteractionsHandler.Companion.GOAL_FIELD
import com.maverickapps.nutripet.petsFeature.ui.view.screens.registerPetScreen.utils.FormItemsInteractionsHandler.Companion.PET_NAME_FIELD
import com.maverickapps.nutripet.petsFeature.ui.view.screens.registerPetScreen.utils.FormItemsInteractionsHandler.Companion.SEX_FIELD
import com.maverickapps.nutripet.petsFeature.ui.view.screens.registerPetScreen.utils.FormItemsInteractionsHandler.Companion.STERILIZED_FIELD
import com.maverickapps.nutripet.petsFeature.ui.view.screens.registerPetScreen.utils.FormItemsInteractionsHandler.Companion.WEIGHT_FIELD
import com.maverickapps.nutripet.petsFeature.ui.viewmodel.RegisterPetViewmodel
import javax.inject.Inject

class FormStateManager @Inject constructor(
   private val registerPetViewmodel: RegisterPetViewmodel,
    private val formItemsHandler: FormItemsInteractionsHandler,

) {

    private val fields = mutableListOf(
        PET_NAME_FIELD,
        AGE_FIELD,
        SEX_FIELD,
        WEIGHT_FIELD,
        GOAL_FIELD,
        ACTIVITY_FIELD,
        STERILIZED_FIELD
    )

    fun actionTriggered(
        action: Int,
        index: Int,
        petToBeAdded: PetModel,
        navigateBack: (Boolean) -> Unit
    ){

        when(action){
            0 -> onAddClicked(petToBeAdded, navigateBack)
            else -> onTrailingIconClicked(index)
        }
    }


    private fun onAddClicked(
        petToBeAdded: PetModel,
        navigateBack: (Boolean) -> Unit
    ) {
        var canPetBeAdded = true
        triggerAllFields().forEach{
            if (it.value == INVALID) canPetBeAdded = false
        }

        if (canPetBeAdded) {
            registerPetViewmodel.registerPet(petToBeAdded)
            navigateBack(true)
        }
    }

    fun triggerAllFields(): Map<Int, Int> {
        val validationResults = validateFields()
        validationResults.forEach { (field, state) ->
            formItemsHandler.allFieldsChanged(field, state)
        }
        return validationResults
    }


    private fun onTrailingIconClicked(index: Int) {
        formItemsHandler.onItemExpansionChanged(index)

        val validationResults = validateFields()

        validationResults.forEach { (field, state) ->
            formItemsHandler.onItemStateChanged(field, state)
        }
    }


    private fun validateFields(): Map<Int, Int> {
        val validFields = registerPetViewmodel.validatePet()
        return fields.associateWith { field ->
            when {
                validFields.contains(field) -> VALID
                field == SEX_FIELD -> WAITING
                else -> INVALID
            }
        }
    }
}