package com.autopass.autocard.core.common.keyboards

import androidx.databinding.BaseObservable


class KeyboardController(
    private val maxLength: Int
) : BaseObservable() {

    var text: String = ""
        private set(value) {
            if (value.length <= maxLength) field = value
            notifyChange()
        }

    val hasInput: Boolean
        get() = text.isNotBlank()

    fun clear() {
        text = ""
    }

    fun deleteLast() {
        text = text.let { if (it.isNotBlank()) it.substring(0, it.length - 1) else it }
    }

    fun input(value: Char) {
        text = "$text$value"
    }
}