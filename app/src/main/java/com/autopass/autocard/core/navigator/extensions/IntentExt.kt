package com.autopass.autocard.core.navigator.extensions

import android.content.Context
import android.content.Intent

fun Intent.start(context: Context) {
    context.startActivity(this)
}