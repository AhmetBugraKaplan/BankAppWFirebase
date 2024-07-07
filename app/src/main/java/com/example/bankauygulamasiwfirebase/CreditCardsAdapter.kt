package com.example.bankauygulamasiwfirebase

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class CreditCardsAdapter(private val mContext:Context,private val CardsList: List<Cards>)
    :RecyclerView.Adapter<CreditCardsAdapter.cardViewHolder>() {


    inner class cardViewHolder(view: View):RecyclerView.ViewHolder(view){
        var viewx:View
        var imageViewCardType:ImageView
        var textViewCardName:TextView
        var textViewCardNo:TextView
        var textViewExpirDate:TextView

        init {
            viewx = view.findViewById(R.id.view)
            imageViewCardType = view.findViewById(R.id.imageViewCardType)
            textViewCardName = view.findViewById(R.id.textViewCardName)
            textViewCardNo = view.findViewById(R.id.textViewCardNo)
            textViewExpirDate = view.findViewById(R.id.textViewCardExpirDate)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): cardViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.card_credit_design,parent,false)
        return cardViewHolder(view)
    }

    override fun getItemCount(): Int {
        return CardsList.size
    }

    override fun onBindViewHolder(holder: cardViewHolder, position: Int) {

        val card = CardsList[position]

        holder.imageViewCardType.setImageResource(mContext.resources.getIdentifier(card.cardLogo,"drawable",mContext.packageName))
        holder.textViewCardName.text = card.cardName
        holder.textViewCardNo.text = card.cardNo
        holder.textViewExpirDate.text= card.cardExpirationDate

        holder.viewx.setOnClickListener{




            val action = HomePageFragmentDirections.homeToSelectedCard(
                card.cardName ?: "",
                card.cardNo?: "",
                card.cardLogo?:""
                ,card.cardKey ?: ""
                ,card.cardExpirationDate ?: ""
            )
            Navigation.findNavController(it).navigate(action)

        }
    }


}


