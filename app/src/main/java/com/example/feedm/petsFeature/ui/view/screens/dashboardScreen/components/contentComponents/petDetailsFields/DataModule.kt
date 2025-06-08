package com.example.feedm.petsFeature.ui.view.screens.dashboardScreen.components.contentComponents.petDetailsFields

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.feedm.core.ui.theme.Secondary
import com.example.feedm.core.ui.theme.dimens
import com.example.feedm.petsFeature.ui.view.components.ModuleCard
import com.example.feedm.petsFeature.ui.view.components.ModuleItem

@Composable
fun DataModule(
    petSterilize: String,
    petGenre: String,
    petAge: String
) {
    ModuleCard(headerTitle = "Datos") {
        ModuleItem(
            headerText = "Esterilizado",
            trailingText = petSterilize,
            modifier = Modifier
                .height(MaterialTheme.dimens.medium3)
                .padding(end = MaterialTheme.dimens.extraSmall2)
        )
        HorizontalDivider(
            modifier = Modifier.padding(
                vertical = MaterialTheme.dimens.extraSmall2,
                horizontal = MaterialTheme.dimens.extraSmall2
            ), color = Secondary
        )
        ModuleItem(
            headerText = "Sexo",
            trailingText = petGenre,
            modifier = Modifier
                .height(MaterialTheme.dimens.medium3)
                .padding(end = MaterialTheme.dimens.extraSmall2)

        )
        HorizontalDivider(
            modifier = Modifier.padding(
                vertical = MaterialTheme.dimens.extraSmall2,
                horizontal = MaterialTheme.dimens.extraSmall2
            )
            , color = Secondary
        )
        ModuleItem(
            headerText = "Edad",
            trailingText = petAge,
            modifier = Modifier
                .height(MaterialTheme.dimens.medium3)
                .padding(end = MaterialTheme.dimens.extraSmall2)
        )
    }
}