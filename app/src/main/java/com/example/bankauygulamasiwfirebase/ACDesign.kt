package com.example.bankauygulamasiwfirebase

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class ACDesign(var imageLogo:String? ="",var appName:String? =""
,var cardName:String? ="",var money:String? ="",var date:String? ="") {
}