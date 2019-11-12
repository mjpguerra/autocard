package com.autopass.autocard.presentation.answers.actions

import io.reactivex.Completable
import java.util.concurrent.TimeUnit

class Wait {

    fun execute(seconds: Long): Completable = Completable.timer(seconds, TimeUnit.SECONDS)
}