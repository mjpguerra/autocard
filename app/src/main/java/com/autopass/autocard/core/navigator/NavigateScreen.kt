package com.autopass.autocard.core.navigator

import android.content.Context
import android.content.Intent
import android.os.Parcelable
import com.autopass.autocard.core.navigator.extensions.start
import kotlinx.android.parcel.Parcelize

@Parcelize
class NavigateScreen(
    private val goIntent: Intent? = null,
    private val backIntent: Intent? = null
) : Parcelable {

    fun go(context: Context) {
        goIntent?.start(context)
    }

    fun back(context: Context) {
        backIntent?.start(context)
    }

    companion object {
        const val NAVIGATE = "navigateScreen"
    }
}