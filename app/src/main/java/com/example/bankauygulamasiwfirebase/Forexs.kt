package com.example.bankauygulamasiwfirebase

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Forexs(var forexName:String? ="",
             var forexSellPrice:String? ="",var forexBuyPrice:String? ="",var forexImage:String? = ""){
}