package com.autopass.autocard.presentation.card

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.autopass.autocard.R
import com.autopass.autocard.repository.remote.passenger.responses.CardDataResponse
import kotlinx.android.synthetic.main.dialog_operation.view.*




/**
 * @author Mario Guerra on 10/07/2019
 */

class OperationDialog : DialogFragment(), OperationAdapter.ItemClickListener {

    private lateinit var listOpera: RecyclerView

    override fun onItemClick(view: View, cardDataResponse: CardDataResponse) {
        dismiss()
        CardActivity.presenterCard!!.startCardSaving(cardDataResponse)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.dialog_operation, null, false)

        listOpera = view.listOpera

        // make white background transparent
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val operationAdapter = OperationAdapter()
        operationAdapter.setMyClickListener(this)

        listOpera.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = operationAdapter
            operationAdapter.setOperationList(cardDataResponse)
        }
        return view
    }


    companion object {
        lateinit var cardDataResponse: List<CardDataResponse>
        fun newInstance(list: List<CardDataResponse>): OperationDialog {
            this.cardDataResponse = list
            return OperationDialog()
        }
    }
}