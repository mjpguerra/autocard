package com.autopass.autocard.presentation.register

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.autopass.autocard.R
import com.autopass.autocard.core.common.BaseActivity
import com.autopass.autocard.core.navigator.NavigateScreen
import com.autopass.autocard.databinding.ActivityRegisterBindingImpl
import com.autopass.autocard.presentation.answers.AnswerActivity
import com.autopass.autocard.presentation.card.CardActivity
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.startActivity
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class RegisterActivity : BaseActivity(), RegisterContract {


    private lateinit var binding: ActivityRegisterBindingImpl

    private val presenter: RegisterPresenter by inject { parametersOf(this) }

    override fun context() = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_register)
        binding.presenter = presenter

    }

    override fun documentNumber(): String {
        return etCPF.text.toString()
    }

    override fun navigateToCard() {
        startActivity<CardActivity>()
        finish()
        overridePendingTransition(R.anim.fadein, R.anim.fadeout)
    }

    override fun onDestroy() {
        presenter.dispose()
        super.onDestroy()
    }

    companion object {
        fun buildIntent(context: Context): Intent = Intent(context, RegisterActivity::class.java)
    }
}
