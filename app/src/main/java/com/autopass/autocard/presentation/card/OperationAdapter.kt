package com.autopass.autocard.presentation.card

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.marginBottom
import androidx.recyclerview.widget.RecyclerView
import com.autopass.autocard.R
import com.autopass.autocard.repository.remote.passenger.responses.CardDataResponse
import kotlinx.android.synthetic.main.item_holder.view.*


/**
 * @author Mario Guerra on 10/07/2019
 */

class OperationAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var listOfMovies = mutableListOf<CardDataResponse>()
    lateinit var clickListener: ItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val rootView = LayoutInflater.from(parent.context).inflate(R.layout.item_holder, null, false)
        val lp = RecyclerView.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            100
        )
        rootView.layoutParams = lp

        return OperationListViewHolder(rootView)
    }

    override fun getItemCount(): Int {
        return listOfMovies.size
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        val movieViewHolder = viewHolder as OperationListViewHolder
        movieViewHolder.bindView(listOfMovies[position])
    }

    inner class OperationListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener  {
        var operation : CardDataResponse? = null

        init {
            itemView.setOnClickListener(this)
        }

        fun bindView(cardDataResponse: CardDataResponse) {
            operation = cardDataResponse
            itemView.txidOperation.text = cardDataResponse.cardIssueData.operationId.toString()
        }

        override fun onClick(v: View?) {
            clickListener.onItemClick(v!!, operation!!)
        }

    }

    fun setOperationList(listOfMovies: List<CardDataResponse>) {
        this.listOfMovies = listOfMovies.toMutableList()
        notifyDataSetChanged()
    }

    interface ItemClickListener {
        fun onItemClick(view: View, id: CardDataResponse)
    }

    fun setMyClickListener(itemClickListener: ItemClickListener?) {
        this.clickListener = itemClickListener!!
    }
}