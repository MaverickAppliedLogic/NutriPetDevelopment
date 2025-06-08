package com.example.feedm.core.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember
import com.example.feedm.core.ui.dimens.CompactDimens
import com.example.feedm.core.ui.dimens.Dimens

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