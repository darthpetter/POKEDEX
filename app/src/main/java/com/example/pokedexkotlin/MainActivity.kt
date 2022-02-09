package com.example.pokedexkotlin

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pokedexkotlin.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: AdapterPokemon

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getPokemones()
    }
    private fun initRecyclerView(pokemones: ArrayList<String>) {
        adapter = AdapterPokemon(pokemones)
        binding.mainView.layoutManager = GridLayoutManager(this,2)
        binding.mainView.adapter = adapter
    }
    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/pokedex/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun getPokemones(){
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(APIService::class.java).getPokemons("").execute()
            val pokemones = call.body()
            runOnUiThread {
                if(call.isSuccessful){
                    var nombresPokemones=ArrayList<String>()
                    for(pokemoncito:ResponsePokemonEntry in pokemones!!.pokemon){
                        nombresPokemones.add(pokemoncito.pokemon_species.name)
                    }
                    initRecyclerView(nombresPokemones)
                }else{
                    showError()
                }
            }
        }
    }
    private fun showError(){
        Toast.makeText(this,"No se ha podido establecer conexión con la API",Toast.LENGTH_SHORT).show()
    }




}