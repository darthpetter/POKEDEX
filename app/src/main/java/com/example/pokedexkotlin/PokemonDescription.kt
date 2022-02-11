package com.example.pokedexkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.pokedexkotlin.databinding.ActivityPokemonDescriptionBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class PokemonDescription : AppCompatActivity() {
    private lateinit var binding: ActivityPokemonDescriptionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityPokemonDescriptionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val queryPokemon:String = intent.getStringExtra("nombrePokemon").toString()

        getPokemonDescription(queryPokemon)
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/pokemon/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun getPokemonDescription(queryPokemon:String){
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(APIService::class.java).getPokemonDescription("$queryPokemon").execute()
            val pokemonDescription = call.body()
            runOnUiThread {
                if(call.isSuccessful){
                    val UrlPokeApiSprites="https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iv/diamond-pearl/"
                    println("$pokemonDescription")
                    Picasso.get().load("$UrlPokeApiSprites${pokemonDescription!!.id}.png").into(binding.fotoPokemon)
                    binding.txtNombre.setText("$queryPokemon + ${pokemonDescription!!.id}")
                }else{
                    Toast.makeText(binding.getRoot().getContext(),"Algo smalió sal. \uD83D\uDE35\u200D\uD83D\uDCAB\uD83D\uDE35\u200D\uD83D\uDCAB",Toast.LENGTH_LONG).show()
                }
            }
        }
    }


}