package com.autopass.autocard.presentation.card

import com.autopass.autocard.R
import com.autopass.autocard.core.common.BasePresenter
import com.autopass.autocard.presentation.answers.actions.Wait
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CardPresenter(private val contract: CardContract,
                    private val wait: Wait
) : BasePresenter() {

    fun sync(success: Boolean) {
        wait.execute(4)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { loading = true }
            .subscribe {
                if (success) {
                    loading = false
                    contract.goTo(R.string.completed_dots, "O cartão foi impresso\n com sucesso")
                }else {
                    contract.backTo()
                }
            }.also { addDisposable(it) }
    }
}