package com.example.pokedexkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.pokedexkotlin.databinding.ActivityPokemonDescriptionBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*


class PokemonDescription : AppCompatActivity() {
    private lateinit var binding: ActivityPokemonDescriptionBinding
    private lateinit var numeroPokemon:String
    private lateinit var queryPokemon:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityPokemonDescriptionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        queryPokemon = intent.getStringExtra("nombrePokemon").toString()
        numeroPokemon = intent.getStringExtra("numeroPokemon").toString()

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
                    bindingData(pokemonDescription,binding)
                }else{
                    Toast.makeText(binding.getRoot().getContext(),"Algo smalió sal. \uD83D\uDE35\u200D\uD83D\uDCAB\uD83D\uDE35\u200D\uD83D\uDCAB",Toast.LENGTH_LONG).show()
                }
            }
        }
    }
    
    private fun bindingData(pokemonDescription:ResponsePokemonDescription?, binding:ActivityPokemonDescriptionBinding){
        val UrlPokeApiSprites="https://raw.githubusercontent.com/fanzeyi/pokemon.json/master/images/"
        //SETEA LA IMAGEN RECOGIDA DEL REPOSITORO
        Picasso.get().load("$UrlPokeApiSprites$numeroPokemon.png").into(binding.fotoPokemon)

        //SETEA EL NUMERO Y NOMBRE DEL POKEMON
        binding.nombrePokemon.setText(queryPokemon.capitalize())
        Util().addCeros(pokemonDescription!!.id)
        binding.numeroPokemon.setText(Util().addCeros(pokemonDescription!!.id).toString())

        //SETEA EL TIPO DE POKEMON
        if(pokemonDescription.types.size>1){
            binding.type1.setText(pokemonDescription.types[0].type.name.capitalize())
            binding.type2.setText(pokemonDescription.types[1].type.name.capitalize())
        }else{
            binding.type1.setText(pokemonDescription.types[0].type.name.capitalize())
            binding.type2.setVisibility(View.GONE)
        }
    }


}