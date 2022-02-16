package com.example.pokedexkotlin

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface APIService {
    @GET
    fun getPokemons(@Url url: String): Call<ResponsePokemon>

    //https://github.com/PokeAPI/sprites/blob/master/sprites/pokemon/2.png

    @GET
    fun getPokemonDescription(@Url url: String): Call<ResponsePokemonDescription>
}