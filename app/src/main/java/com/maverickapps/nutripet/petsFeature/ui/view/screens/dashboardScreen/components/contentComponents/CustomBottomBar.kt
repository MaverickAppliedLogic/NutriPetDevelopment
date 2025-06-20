package com.maverickapps.nutripet.petsFeature.ui.view.screens.dashboardScreen.components.contentComponents

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import com.example.feedm.R
import com.maverickapps.nutripet.core.ui.theme.Primary
import com.maverickapps.nutripet.core.ui.theme.SecondaryDarkest
import com.maverickapps.nutripet.core.ui.theme.dimens

@Composable
fun CustomBottomBar(
    bottomPadding: Dp,
    petListSize: Int,
    navTo: (String) -> Unit = {}
) {
    var buttonClicked by remember { mutableStateOf(false) }
    val widthAnimation by animateFloatAsState(
        targetValue = if (buttonClicked) 0.6f else 0f, label = "",
        animationSpec = spring( stiffness = Spring.StiffnessMediumLow,
            dampingRatio = Spring.DampingRatioMediumBouncy)
    )
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
        AnimatedVisibility(
            visible = buttonClicked,
            enter = slideInVertically(initialOffsetY = { it }),
            exit = slideOutVertically(targetOffsetY = { it })
        )  {
           Row(modifier = Modifier.fillMaxWidth(widthAnimation)
               .height(bottomPadding + MaterialTheme.dimens.extraLarge3)) {

               if (petListSize > 0) {

                   FloatingActionButton(
                   onClick = { navTo("AddMeal") },
                   shape = CircleShape, containerColor = Primary,
                   contentColor = SecondaryDarkest,
                   modifier = Modifier
                       .padding(
                           bottom = MaterialTheme.dimens.small2,
                           top = MaterialTheme.dimens.small2
                       )
                       .size(MaterialTheme.dimens.buttonSize)
               ) {
                   Icon(painter = painterResource(id = R.mipmap.ic_meal_add),
                       contentDescription = "",
                       modifier = Modifier.fillMaxSize(0.5f),
                       tint = SecondaryDarkest)
                   }}
               Spacer(modifier = Modifier.weight(1f))
                   FloatingActionButton(
                       onClick = { navTo("RegisterPet") },
                       shape = CircleShape, containerColor = Primary,
                       contentColor = SecondaryDarkest,
                       modifier = Modifier
                           .padding(bottom = MaterialTheme.dimens.small2)
                           .size(MaterialTheme.dimens.buttonSize)
                   ) {
                       Icon(painter = painterResource(id = R.mipmap.ic_pet_add),
                           contentDescription = "",
                           modifier = Modifier.fillMaxSize(0.6f),
                           tint = SecondaryDarkest)
                   }


               Spacer(modifier = Modifier.weight(1f))
               if (petListSize > 0) {
                   FloatingActionButton(
                       onClick = { navTo("AddFood") },
                       shape = CircleShape, containerColor = Primary,
                       contentColor = SecondaryDarkest,
                       modifier = Modifier
                           .padding(
                               bottom = MaterialTheme.dimens.small2,
                               top = MaterialTheme.dimens.small2)
                           .size(MaterialTheme.dimens.buttonSize)
                   ) {
                       Icon(painter = painterResource(id = R.mipmap.ic_food_add),
                           contentDescription = "",
                           modifier = Modifier.fillMaxSize(0.6f),
                           tint = SecondaryDarkest)               }
               }
           }
        }

        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Primary),
                        startY = 30f,
                        endY = Float.POSITIVE_INFINITY,
                        tileMode = TileMode.Clamp
                    )
                )
        ) {
            FloatingActionButton(
                onClick = { buttonClicked = !buttonClicked },
                shape = CircleShape, containerColor = Primary,
                contentColor = SecondaryDarkest,
                modifier = Modifier
                    .padding(bottom = bottomPadding + MaterialTheme.dimens.small1)
                    .size(MaterialTheme.dimens.buttonSize)
            ) {
                Icon(imageVector = if(!buttonClicked) Icons.Default.Add else Icons.Default.Close,
                    contentDescription = "")
            }
        }
    }
}