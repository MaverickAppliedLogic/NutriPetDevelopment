package com.maverickapps.nutripet.petsFeature.ui.view.components.alerts

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import com.maverickapps.nutripet.core.ui.theme.Error
import com.maverickapps.nutripet.core.ui.theme.NeutralLight
import com.maverickapps.nutripet.core.ui.theme.SecondaryDarkest
import com.maverickapps.nutripet.petsFeature.ui.viewmodel.DashboardViewModel
import kotlinx.coroutines.launch

@Composable
fun ConfirmPetDialog(
    petName: String,
    petIdSelected: Int?,
    dashboardViewModel: DashboardViewModel,
    snackBarHostState: SnackbarHostState,
    onOpenDialogChange: (Boolean) -> Unit,
){
    val scope = rememberCoroutineScope()
    AlertDialog(
        onDismissRequest = { onOpenDialogChange(false)  },
        title = {
            Text(buildAnnotatedString {
                withStyle(style = SpanStyle(color = SecondaryDarkest)){
                    append("¿Desea eliminar a")
                }
                withStyle(style = SpanStyle(color = Error,
                    fontWeight = FontWeight.Bold,
                )
                ) {
                    append(" $petName ")
                }
                withStyle(style = SpanStyle(color = SecondaryDarkest)){
                    append("?")
                }
            })
        },
        text = {
            Text(text = "Una vez eliminado no podrá recuperar ninguno de sus datos",
                color = SecondaryDarkest
            )
        },
        confirmButton = {
            TextButton(onClick = {
                onOpenDialogChange(false)
            }){ Text(text = "Cancelar", color = SecondaryDarkest, fontWeight = FontWeight.Bold) }
        },
        dismissButton = {
            TextButton(onClick = {
                dashboardViewModel.deletePet(petIdSelected!!);onOpenDialogChange(false)
                scope.launch {
                    snackBarHostState.showSnackbar("$petName eliminado/a correctamente.")
                }
            }){ Text(text = "Eliminar", color = Error) } },
        containerColor = NeutralLight,
    )
}