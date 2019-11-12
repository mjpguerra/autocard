package com.autopass.autocard.presentation.register

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.autopass.autocard.R
import com.autopass.autocard.core.common.BaseActivity
import com.autopass.autocard.databinding.ActivityRegisterBinding
import com.autopass.autopay.presentation.register.token.RegisterContract
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class RegisterActivity : BaseActivity(), RegisterContract {

    private lateinit var binding: ActivityRegisterBinding

    private val presenter: RegisterPresenter by inject { parametersOf(this) }


    override fun context() = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_register)
        binding.presenter = presenter
    }

    override fun onDestroy() {
        presenter.dispose()
        super.onDestroy()
    }

    companion object {
        fun buildIntent(context: Context): Intent = Intent(context, RegisterActivity::class.java)
    }
}
