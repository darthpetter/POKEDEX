package com.example.pokedexkotlin

import java.util.*

class Util {
    fun addCeros(numero:Int) : Formatter {
        var fmt= Formatter()

        return when(numero){
            in 1..9 -> fmt.format("%03d",numero)
            in 10..99 -> fmt.format("%03d",numero)
            else ->fmt.format("%03d",numero)
        }
    }

    fun convertToMeter(height:Int):Float = height.toFloat()/10
    fun convertToKG(weight:Int):Float = weight.toFloat()/10
}