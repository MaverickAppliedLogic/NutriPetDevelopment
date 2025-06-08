package com.example.feedm.petsFeature.ui.view.screens.foodListScreen.components.contentComponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.feedm.R
import com.example.feedm.core.ui.theme.Error
import com.example.feedm.core.ui.theme.PrimaryLight
import com.example.feedm.core.ui.theme.PrimaryLightest

@Composable
fun FoodItemCard(
    id: Int,
    name: String,
    image: Int,
    isEditing: Boolean = true,
    onCardClicked: (Int) -> Unit = {},
    onIconClicked: (Int) -> Unit = {}
) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopEnd) {

        Card(
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(8.dp),
            colors = CardDefaults.cardColors(containerColor = PrimaryLight),
            modifier = Modifier
                .padding(vertical = 16.dp, horizontal = 10.dp)
                .fillMaxWidth()
                .height(80.dp)
                .clickable { onCardClicked(id) }
        ) {

            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = name,
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 16.dp),
                    style = MaterialTheme.typography.titleMedium
                )

                Image(
                    painter = painterResource(id = image),
                    contentDescription = name,
                    modifier = Modifier
                        .fillMaxHeight()
                        .aspectRatio(1f),
                    contentScale = ContentScale.Crop
                )
            }

        }
        if (isEditing) {
            IconButton(onClick = { onIconClicked(id) },
                colors = IconButtonDefaults.iconButtonColors(containerColor = Error),
                modifier = Modifier.clip(CircleShape).align(Alignment.TopEnd).size(30.dp)) {
                Icon(imageVector = Icons.Outlined.Delete,
                    tint = PrimaryLightest,
                    contentDescription = "Delete",
                    modifier = Modifier)
            }
        }
    }
}

@Preview
@Composable
fun FoodItemCardPreview() {
    FoodItemCard(
        id = 1,
        name = "Purina",
        isEditing = true,
        image = R.drawable.ic_tailycare,
        onCardClicked = {}
    )
}