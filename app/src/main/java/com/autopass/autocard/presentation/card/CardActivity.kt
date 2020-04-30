package com.autopass.autocard.presentation.card

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.autopass.autocard.R
import com.autopass.autocard.core.common.BaseActivity
import com.autopass.autocard.core.navigator.NavigateScreen
import com.autopass.autocard.databinding.ActivityCardBinding
import com.autopass.autocard.presentation.answers.AnswerActivity
import com.autopass.autocard.presentation.register.RegisterActivity
import org.jetbrains.anko.startActivity
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class CardActivity : BaseActivity(), CardContract {

    private lateinit var binding: ActivityCardBinding

    val presenter: CardPresenter by inject { parametersOf(this) }


    override fun context() = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_card)
        binding.presenter = presenter

        presenterCard = presenter

        presenter.onCreate()

    }

    override fun goTo(title: Int, message: String) {
        startActivity<AnswerActivity>(
            SUCCESS to true,
            TITLE to getString(title),
            MESSAGE to message,
            NavigateScreen.NAVIGATE to NavigateScreen(goIntent = RegisterActivity.buildIntent(this))
        )
        finish()
        overridePendingTransition(R.anim.fadein, R.anim.fadeout)
    }

    override fun navigateToAnswer(title: Int, message: String) {
        startActivity<AnswerActivity>(
            SUCCESS to true,
            TITLE to getString(title),
            MESSAGE to message
        )
        overridePendingTransition(R.anim.fadein, R.anim.fadeout)
    }

    override fun navigateToAnswerErro(title: Int, message: String) {
        startActivity<AnswerActivity>(
            SUCCESS to false,
            TITLE to getString(title),
            MESSAGE to message,
            NavigateScreen.NAVIGATE to NavigateScreen(backIntent = RegisterActivity.buildIntent(this))
        )
        finish()
        overridePendingTransition(R.anim.fadein, R.anim.fadeout)
    }

    override fun openDialogOperation( operationDialog : OperationDialog) {
        operationDialog.isCancelable = false
        operationDialog.show(supportFragmentManager, "OPERATION")
    }


    override fun backTo() {
        finish()
    }

    override fun onDestroy() {
        presenter.dispose()
        super.onDestroy()
    }

    companion object {
        var presenterCard: CardPresenter? = null

        fun buildIntent(context: Context): Intent = Intent(context, CardActivity::class.java)
    }
}
