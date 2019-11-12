package com.autopass.autocard.presentation.register

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.databinding.DataBindingUtil
import com.autopass.autocard.R
import com.autopass.autocard.core.common.BaseActivity
import com.autopass.autocard.core.extensions.Mask
import com.autopass.autocard.core.navigator.NavigateScreen
import com.autopass.autocard.databinding.ActivityRegisterBinding
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

        etCPF.addTextChangedListener(Mask.mask("###.###.###-##"))

        etCPF.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if(count == 14){
                        navigateToAnswer(R.string.completed_dots, "Usuario encontrado!")
                    }
            }
        })
    }

    override fun navigateToAnswer(title: Int, message: String) {
        startActivity<AnswerActivity>(
            SUCCESS to true,
            TITLE to getString(title),
            MESSAGE to message,
            NavigateScreen.NAVIGATE to NavigateScreen(goIntent = CardActivity.buildIntent(this))
        )
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
