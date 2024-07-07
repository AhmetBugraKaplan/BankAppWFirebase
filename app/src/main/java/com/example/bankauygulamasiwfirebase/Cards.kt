package com.example.bankauygulamasiwfirebase

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Cards(var cardNo:String? = "",var cardName:String? = "",
                 var cardLogo:String? = "",var cardExpirationDate:String?=""
                 ,var cardKey:String? ="") {



}