package com.example.pokedexkotlin

import com.google.gson.annotations.SerializedName

data class ResponsePokemonDescription(
    @SerializedName("weight") var weight:Int,
    @SerializedName("height") var height:Int,
    @SerializedName("id") var id:Int,
    @SerializedName("types") var types:ArrayList<ResponsePokemonDescriptionType>,
    @SerializedName("abilities") var abilities:ArrayList<ResponsePokemonDescriptionAbility>,
)
data class ResponsePokemonDescriptionType(
    @SerializedName("slot") var slot:Int,
    @SerializedName("type") var type:Type,
)
data class Type(
    @SerializedName("name") var name:String,
)
data class ResponsePokemonDescriptionAbility(
    @SerializedName("is_hidden") var is_hidden:Boolean,
    @SerializedName("ability") var ability:Ability,
)
data class Ability(
    @SerializedName("name") var name:String,
)
