package com.example.pokedexkotlin

import android.text.TextUtils.indexOf
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedexkotlin.databinding.PokemonItemBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import java.util.*

class AdapterPokemon(private val pokemones:List<String>): RecyclerView.Adapter<PokemonViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PokemonViewHolder(layoutInflater.inflate(R.layout.pokemon_item, parent, false))
    }

    override fun getItemCount(): Int = pokemones.size
    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val item = pokemones[position]
        holder.bind(item,addCeros(position+1))
    }
    private fun addCeros(numero:Int) :Formatter{
        var fmt=Formatter()

        return when(numero){
            in 1..9 -> fmt.format("%03d",numero)
            in 10..99 -> fmt.format("%03d",numero)
            else ->fmt.format("%03d",numero)
        }
    }



}
class PokemonViewHolder(view: View):RecyclerView.ViewHolder(view){

    private val binding = PokemonItemBinding.bind(view)
    private val repositorioSpritesUrl="https://raw.githubusercontent.com/fanzeyi/pokemon.json/master/images/"
    fun bind(nombrePokemon:String,numeroPokemon:Formatter){
        binding.numeroPokemon.setText("#$numeroPokemon")
        Picasso.get().load("$repositorioSpritesUrl$numeroPokemon.png").into(binding.fotoPokemon)
        binding.nombrePokemon.setText("${nombrePokemon.capitalize()}")
    }

}