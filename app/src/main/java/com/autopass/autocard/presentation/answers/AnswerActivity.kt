package com.autopass.autocard.presentation.answers

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.autopass.autocard.R
import com.autopass.autocard.core.common.BaseActivity
import com.autopass.autocard.core.navigator.NavigateScreen
import com.autopass.autocard.databinding.ActivityAnswerBinding
import kotlinx.android.synthetic.main.activity_answer.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class AnswerActivity : BaseActivity(), AnswerContract {

    private lateinit var binding: ActivityAnswerBinding
    private val presenter: AnswerPresenter by inject { parametersOf(this) }
    private var navigateScreen: NavigateScreen? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(intent) {
            navigateScreen = getParcelableExtra(NavigateScreen.NAVIGATE)
            presenter.decideTheme(
                getBooleanExtra(SUCCESS, false),
                getStringExtra(TITLE) ?: "",
                getStringExtra(MESSAGE) ?: ""
            )
        }
    }

    override fun goTo() {
        navigateScreen?.go(this)
        finish()
    }

    override fun backTo() {
        navigateScreen?.back(this)
        finish()
    }

    override fun setBindingAndIcon(icon: Int) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_answer)
        binding.presenter = presenter
        ivFeedback.setImageResource(icon)
    }

    override fun onDestroy() {
        presenter.dispose()
        super.onDestroy()
    }
}