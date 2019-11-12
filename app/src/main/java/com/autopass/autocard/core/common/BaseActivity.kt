package com.autopass.autocard.core.common

import android.view.View
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    companion object {
        const val TRANSACTION_TYPE = "TRANSACTION_TYPE"
        const val TRANSACTION_AMOUNT = "TRANSACTION_AMOUNT"
        const val SUCCESS = "success"
        const val TITLE = "title"
        const val MESSAGE = "message"
        const val COMPANY = "company"
        const val OPERATION = "operation"
        const val TOKEN = "token"
        const val LINE = "linha"
    }

    private var lineId: String = ""
    private var codeLine: String = ""
    private var startCourse: String = ""
    private var destinationCourse: String = ""

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) setFullscreen()
    }

    private fun setFullscreen() {
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                or View.SYSTEM_UI_FLAG_FULLSCREEN)
    }

    override fun onBackPressed() {
        /** Do nothing!! */
    }

}