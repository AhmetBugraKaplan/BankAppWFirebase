package com.example.bankauygulamasiwfirebase

import android.content.Context
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ACAdapter(private val mContext: Context,private val dataSetList:List<ACDesign>)
    :RecyclerView.Adapter<ACAdapter.ACViewHolder>(){

    inner class ACViewHolder(view: View):RecyclerView.ViewHolder(view){
        var imageViewAPPImage : ImageView
        var textViewAppName : TextView
        var textViewCardName : TextView
        var textViewMoney : TextView
        var textViewDate : TextView

        init {
            imageViewAPPImage = view.findViewById(R.id.imageViewAppImage)
            textViewAppName = view.findViewById(R.id.textViewAppName)
            textViewCardName = view.findViewById(R.id.textViewCardNamee)
            textViewMoney = view.findViewById(R.id.textViewMoney)
            textViewDate = view.findViewById(R.id.textViewDate)
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ACViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.acounttransactions_design,parent,false)
        return ACViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataSetList.size
    }

    override fun onBindViewHolder(holder: ACViewHolder, position: Int) {

        val data = dataSetList[position]

        holder.imageViewAPPImage.setImageResource(mContext.resources.getIdentifier
            (data.imageLogo,"drawable",mContext.packageName))
        holder.textViewAppName.text = data.appName
        holder.textViewCardName.text = data.cardName
        holder.textViewMoney.text = data.money
        holder.textViewDate.text = data.date

    }


}