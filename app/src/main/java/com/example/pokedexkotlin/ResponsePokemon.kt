package com.example.pokedexkotlin

import com.google.gson.annotations.SerializedName

data class ResponsePokemon(
    @SerializedName("pokemon_entries") var pokemon :ArrayList<ResponsePokemonEntry>
    )
data class ResponsePokemonEntry(
    @SerializedName("entry_number") var entry_number:Long,
    @SerializedName("pokemon_species") var pokemon_species :ResponsePokemonEntrySpecie,
)
data class ResponsePokemonEntrySpecie(
    @SerializedName("name") var name :String,
    @SerializedName("url") var url:String,
)
