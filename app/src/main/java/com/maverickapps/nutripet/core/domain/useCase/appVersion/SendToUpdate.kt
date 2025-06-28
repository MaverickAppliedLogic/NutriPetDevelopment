package com.maverickapps.nutripet.core.domain.useCase.appVersion

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import javax.inject.Inject

class SendToUpdate @Inject constructor(
    private val context: Context
) {
    operator fun invoke() {
        val appPackageName = context.packageName
        val playStoreIntent =
            Intent(
                Intent.ACTION_VIEW, Uri.parse("market://details?id=$appPackageName")
            )
        playStoreIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

        val webIntent =
            Intent(Intent.ACTION_VIEW,
                Uri.parse(
                    "https://play.google.com/store/apps/details?id=$appPackageName")
            )
        webIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

        try {
            context.startActivity(playStoreIntent)
        } catch (e: ActivityNotFoundException) {
            context.startActivity(webIntent)
        }

    }
}