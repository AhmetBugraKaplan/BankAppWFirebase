package com.example.bankauygulamasiwfirebase

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ForexsAdapter(private val mContext: Context, private val  ForexList:List<Forexs>)
    :RecyclerView.Adapter<ForexsAdapter.ForexsViewHolder>() {

    inner class ForexsViewHolder(view: View):RecyclerView.ViewHolder(view){
        var imageViewForex:ImageView
        var textViewForexName:TextView
        var textViewForexSellPrice:TextView
        var textViewForexBuyPrice:TextView

        //Constructor
        init {
            imageViewForex=view.findViewById(R.id.imageViewForexImage)
            textViewForexName=view.findViewById(R.id.textViewForexName)
            textViewForexBuyPrice=view.findViewById(R.id.textViewForexBuyPrice)
            textViewForexSellPrice=view.findViewById(R.id.textViewPriceSellPrice)
        }
    }

    //Card tasarımını kod ile bağlıyoruz
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForexsViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.forex_design,parent,false)
        return ForexsViewHolder(view)
    }

    //Kaç tane cardview olucağının sayısını listedeki eleman sayısı ile veriyoruz
    override fun getItemCount(): Int {
        return ForexList.size
    }


    override fun onBindViewHolder(holder: ForexsViewHolder, position: Int) {
        val forex = ForexList[position]

        holder.imageViewForex.setImageResource(mContext.resources.getIdentifier(forex.forexImage,"drawable",mContext.packageName))
        holder.textViewForexName.text=forex.forexName
        holder.textViewForexBuyPrice.text=forex.forexBuyPrice.toString()
        holder.textViewForexSellPrice.text=forex.forexSellPrice.toString()
    }

    companion object {
        fun notifyDataSetChanged() {
            TODO("Not yet implemented")
        }
    }


}