package com.autopass.autocard.presentation.card

import android.content.Context
import android.content.Intent
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

    private val presenter: CardPresenter by inject { parametersOf(this) }


    override fun context() = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_card)
        binding.presenter = presenter
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

    override fun backTo() {
        finish()
    }

    override fun onDestroy() {
        presenter.dispose()
        super.onDestroy()
    }

    companion object {
        fun buildIntent(context: Context): Intent = Intent(context, CardActivity::class.java)
    }
}
