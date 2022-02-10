package com.example.pokedexkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pokedexkotlin.databinding.ActivityPokemonDescriptionBinding


class PokemonDescription : AppCompatActivity() {
    private lateinit var binding: ActivityPokemonDescriptionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityPokemonDescriptionBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}