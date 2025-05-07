package com.example.feedm.petsFeature.ui.view.screens.registerPetScreen.components.addPetContentComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.feedm.core.ui.theme.NeutralLight
import com.example.feedm.petsFeature.domain.objectTasks.pet.model.PetModel
import com.example.feedm.petsFeature.ui.view.screens.registerPetScreen.components.addPetContentComponents.formFields.ActivityField
import com.example.feedm.petsFeature.ui.view.screens.registerPetScreen.components.addPetContentComponents.formFields.AgeField
import com.example.feedm.petsFeature.ui.view.screens.registerPetScreen.components.addPetContentComponents.formFields.GoalField
import com.example.feedm.petsFeature.ui.view.screens.registerPetScreen.components.addPetContentComponents.formFields.PetNameAndAnimalField
import com.example.feedm.petsFeature.ui.view.screens.registerPetScreen.components.addPetContentComponents.formFields.SexField
import com.example.feedm.petsFeature.ui.view.screens.registerPetScreen.components.addPetContentComponents.formFields.SterilizationField
import com.example.feedm.petsFeature.ui.view.screens.registerPetScreen.components.addPetContentComponents.formFields.WeightField
import com.example.feedm.petsFeature.ui.view.screens.registerPetScreen.utils.FormItemsInteractionsHandler.Companion.ACTIVITY_FIELD
import com.example.feedm.petsFeature.ui.view.screens.registerPetScreen.utils.FormItemsInteractionsHandler.Companion.AGE_FIELD
import com.example.feedm.petsFeature.ui.view.screens.registerPetScreen.utils.FormItemsInteractionsHandler.Companion.GOAL_FIELD
import com.example.feedm.petsFeature.ui.view.screens.registerPetScreen.utils.FormItemsInteractionsHandler.Companion.PET_NAME_FIELD
import com.example.feedm.petsFeature.ui.view.screens.registerPetScreen.utils.FormItemsInteractionsHandler.Companion.SEX_FIELD
import com.example.feedm.petsFeature.ui.view.screens.registerPetScreen.utils.FormItemsInteractionsHandler.Companion.STERILIZED_FIELD
import com.example.feedm.petsFeature.ui.view.screens.registerPetScreen.utils.FormItemsInteractionsHandler.Companion.WEIGHT_FIELD

@Composable
fun Form(
    pet: PetModel,
    petNameFieldData: Pair<Int, Boolean>,
    ageFieldData: Pair<Int, Boolean>,
    sexFieldData: Pair<Int, Boolean>,
    weightFieldData: Pair<Int, Boolean>,
    goalFieldData: Pair<Int, Boolean>,
    sterilizedFieldData: Pair<Int, Boolean>,
    activityFieldData: Pair<Int, Boolean>,
    modifier: Modifier = Modifier,
    onPetChanged: (PetModel) -> Unit = {},
    onTrailingIconClicked: (Int) -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(NeutralLight)
    ) {
        PetNameAndAnimalField(
            animal = pet.animal,
            name = pet.petName,
            expansionState = petNameFieldData.second,
            fieldState = petNameFieldData.first,
            modifier = Modifier.weight(1f, true),
            onTrailingIconClicked = {
                onTrailingIconClicked(PET_NAME_FIELD)
            },
            onNameChanged = { onPetChanged(pet.copy(petName = it)) },
            onAnimalChanged = { onPetChanged(pet.copy(animal = it)) }
        )

        AgeField(
            age = pet.age,
            expansionState = ageFieldData.second,
            fieldState = ageFieldData.first,
            modifier = Modifier.weight(1f, true),
            onTrailingIconClicked = {
                onTrailingIconClicked(AGE_FIELD)
            },
            onAgeChanged = { onPetChanged(pet.copy(age = it)) }
        )

        SexField(
            sex = pet.genre,
            expansionState = sexFieldData.second,
            fieldState = sexFieldData.first,
            modifier = Modifier.weight(1f, true),
            onTrailingIconClicked = {
                onTrailingIconClicked(SEX_FIELD)
            },
            onSexChanged = { onPetChanged(pet.copy(genre = it)) }
        )

        WeightField(
            weight = pet.petWeight,
            expansionState = weightFieldData.second,
            fieldState = weightFieldData.first,
            modifier = Modifier.weight(1f, true),
            onTrailingIconClicked = {
                onTrailingIconClicked(WEIGHT_FIELD)
            },
            onWeightChanged = { onPetChanged(pet.copy(petWeight = it)) }
        )

        GoalField(
            goal = pet.goal,
            expansionState = goalFieldData.second,
            fieldState = goalFieldData.first,
            modifier = Modifier.weight(1f, true),
            onTrailingIconClicked = {
                onTrailingIconClicked(GOAL_FIELD)
            },
            onGoalChanged = { onPetChanged(pet.copy(goal = it)) }
        )

        ActivityField(
            activity = pet.activity,
            expansionState = activityFieldData.second,
            fieldState = activityFieldData.first,
            modifier = Modifier.weight(1f, true),
            onTrailingIconClicked = {
                onTrailingIconClicked(ACTIVITY_FIELD)
            },
            onActivityChanged = { onPetChanged(pet.copy(activity = it)) }
        )
        SterilizationField(
            sterilized = pet.sterilized,
            expansionState = sterilizedFieldData.second,
            fieldState = sterilizedFieldData.first,
            modifier = Modifier.weight(1f, true),
            onTrailingIconClicked = {
                onTrailingIconClicked(STERILIZED_FIELD)
            },
            sterilizedChanged = { onPetChanged(pet.copy(sterilized = it)) }
        )

    }
}