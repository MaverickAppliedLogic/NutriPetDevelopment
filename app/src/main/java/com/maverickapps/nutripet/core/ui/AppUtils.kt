package com.maverickapps.nutripet.core.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember
import com.maverickapps.nutripet.core.ui.dimens.CompactDimens
import com.maverickapps.nutripet.core.ui.dimens.Dimens

@Composable
fun AppUtils(
    dimens: Dimens,
    content: @Composable () -> Unit
) {

    val appDimens = remember { dimens }

    CompositionLocalProvider(LocalAppDimens provides appDimens) {
        content()
    }
}

val LocalAppDimens = compositionLocalOf { CompactDimens }