package com.apps.nabungemas.utils

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import java.text.NumberFormat
import java.util.Currency
import java.util.Locale

object CurrencyAmount {
    fun currencyId(data:String):String{
        try{
            val numberFormat =
                NumberFormat.getCurrencyInstance(Locale("ID")).apply {
                    currency = Currency.getInstance("IDR")
                }
            val dataFloat=data.toFloat()
            return numberFormat.format(dataFloat)
        }catch (e:Exception){
            return "Rp.0"
        }


    }
}