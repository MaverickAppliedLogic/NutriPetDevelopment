package com.example.feedm.petsFeature.ui.view.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.example.feedm.core.ui.theme.PrimaryLightest
import com.example.feedm.core.ui.theme.SecondaryDarkest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * A scrollable selector that lets users scroll through a list of items and highlights
 * the currently selected one with animations for size, opacity, and box scaling.
 *
 * Features:
 * - Various coroutines for smooth scrolling and avoid crashes because of overworking
 * - Smooth animations for the selected item's text size, opacity, and height.
 * - Automatically updates the selected item when scrolling stops.
 * - Customizable with additional paddings at the start and end for smooth scrolling.
 * - Trigger a callback (`onItemSelected`) when a new item is selected.
 *
 * Parameters:
 * @param items List of items to display.
 * @param modifier Modifier to customize the layout.
 * @param onItemSelected Callback triggered when an item is selected.
 */

@Composable
fun ScrollableSelector(
    items: List<String>,
    modifier: Modifier = Modifier,
    onItemSelected: (String) -> Unit = {}
) {
    val coroutineScope = rememberCoroutineScope()
    val lazyListState = rememberLazyListState()
    var firstItem by remember { mutableIntStateOf(0) }
    val selectedItem = remember { derivedStateOf { firstItem + 1 } }
    val preparedItems = items.toMutableList()
    preparedItems.add(0, "")
    preparedItems.add(preparedItems.size, "")
    preparedItems.add(preparedItems.size, "")
    preparedItems.toList()

    LazyColumn(
        state = lazyListState,
        modifier = modifier
            .clip(RoundedCornerShape(5.dp))
            .border(1.5.dp, color = SecondaryDarkest,
                shape = RoundedCornerShape(5.dp)
            )
    ) {
        items(preparedItems.size) {
            val textAlphaAnim by animateFloatAsState(
                targetValue = if (it == selectedItem.value) 1f else 0.5f
            )
            val textSizeAnim by animateFloatAsState(
                targetValue = if (it == selectedItem.value) 24f else 8f
            )
            val boxSizeAnim by animateFloatAsState(
                targetValue = if (it == selectedItem.value) 0.4f else 0.3f
            )
            Box(
                modifier = Modifier
                    .background(PrimaryLightest)
                    .width(60.dp)
                    .fillParentMaxHeight(boxSizeAnim),
                contentAlignment = Alignment.TopCenter
            ) {
                    Text(
                        text = preparedItems[it],
                        fontSize = TextUnit(textSizeAnim, TextUnitType.Sp),
                        textAlign = TextAlign.Center,
                        color = SecondaryDarkest,
                        modifier = Modifier.alpha(textAlphaAnim)
                    )
            }
        }
    }
    LaunchedEffect(lazyListState) {
        lazyListState.animateScrollToItem(firstItem)
        snapshotFlow { lazyListState.isScrollInProgress }
            .distinctUntilChanged()
            .collect { scrolling ->
                withContext(Dispatchers.Default) {
                    firstItem = lazyListState.firstVisibleItemIndex
                }
                coroutineScope.launch {
                    if(!lazyListState.canScrollForward){
                        firstItem = preparedItems.size - 4
                    }
                    lazyListState.animateScrollToItem(firstItem)
                }
                if (!scrolling) {
                    onItemSelected(preparedItems[selectedItem.value - 1])
                }
            }
    }
}