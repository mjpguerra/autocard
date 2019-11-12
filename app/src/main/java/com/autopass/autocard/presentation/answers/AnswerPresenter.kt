package com.autopass.autocard.presentation.answers

import android.content.res.Resources
import com.autopass.autocard.R
import com.autopass.autocard.core.common.BasePresenter
import com.autopass.autocard.presentation.answers.actions.Wait
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AnswerPresenter(
    private val contract: AnswerContract,
    private val wait: Wait
) : BasePresenter() {

    var title: String = ""
        set(value) {
            field = value
            notifyChange()
        }

    var message: String = ""
        set(value) {
            field = value
            notifyChange()
        }

    var dipValue: Int = 0
        set(value) {
            field = value
            notifyChange()
        }

    private val Int.dp: Int
        get() = (this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()

    fun decideTheme(success: Boolean, title: String, message: String) {
        this.title = title
        this.message = message
        with(contract) {
            dipValue = if (success) {
                setTheme(R.style.SuccessTheme)
                setBindingAndIcon(R.drawable.ic_check_circle)
                40.dp
            } else {
                setTheme(R.style.ErrorTheme)
                setBindingAndIcon(R.drawable.ic_x_circle)
                if (message.length > 45) 20.dp
                else 40.dp
            }
        }
        countBack(success)
    }

    private fun countBack(success: Boolean) {
        wait.execute(3)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                if (success) contract.goTo()
                else contract.backTo()
            }.also { addDisposable(it) }
    }
}