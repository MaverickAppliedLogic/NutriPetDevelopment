
import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.maverickapps.nutripet.features.pets.ui.view.screens.registerPetScreen.utils.FormItemsInteractionsHandler

@Composable
fun rememberFieldStates(handler: FormItemsInteractionsHandler): List<Pair<Int,Boolean>> {
    return listOf(
        handler.petNameFieldState.collectAsStateWithLifecycle().value
                to handler.petNameFieldVisibility.collectAsStateWithLifecycle().value,
        handler.ageFieldState.collectAsStateWithLifecycle().value
                to handler.ageFieldVisibility.collectAsStateWithLifecycle().value,
        handler.sexFieldState.collectAsStateWithLifecycle().value
                to handler.sexFieldVisibility.collectAsStateWithLifecycle().value,
        handler.weightFieldState.collectAsStateWithLifecycle().value
                to handler.weightFieldVisibility.collectAsStateWithLifecycle().value,
        handler.goalFieldState.collectAsStateWithLifecycle().value
                to handler.goalFieldVisibility.collectAsStateWithLifecycle().value,
        handler.activityFieldState.collectAsStateWithLifecycle().value
                to handler.activityField.collectAsStateWithLifecycle().value,
        handler.sterilizedFieldState.collectAsStateWithLifecycle().value
                to handler.sterilizedFieldVisibility.collectAsStateWithLifecycle().value
    )
}

