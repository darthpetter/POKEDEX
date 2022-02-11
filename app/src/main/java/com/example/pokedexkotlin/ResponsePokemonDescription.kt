package com.example.pokedexkotlin

import com.google.gson.annotations.SerializedName

data class ResponsePokemonDescription(
    @SerializedName("weight") var weight:Long,
    @SerializedName("height") var height:Long,
    @SerializedName("id") var id:Int,
    @SerializedName("types") var types:ArrayList<ResponsePokemonDescriptionType>,
)
data class ResponsePokemonDescriptionType(
    @SerializedName("slot") var slot:Int,
    @SerializedName("type") var type:Type,
)
data class Type(
    @SerializedName("name") var name:String,
)
