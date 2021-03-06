package com.prepfully.beer

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.prepfully.beer.databinding.BeerItemBinding
import com.prepfully.beer.network.Beer

class BeerAdapter : ListAdapter<Beer, BeerAdapter.BeerViewHolder>(BeerDiffUtil()) {

    var onClickListener : ((String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = BeerItemBinding.inflate(inflater)
        return BeerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BeerViewHolder, position: Int) {
        holder.bind(getItem(position), onClickListener)
    }

    class BeerViewHolder(private val binding: BeerItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(beer: Beer, onClickListener : ((String) -> Unit)?) {
            binding.beerName.text = beer.name
            binding.beerDescription.text = beer.description
            binding.beerAbv.text = "ABV = ${beer.abv}"
            binding.beerImage.setOnClickListener {
                onClickListener?.invoke(beer.name)
            }
            with(binding.beerImage) {
                Glide.with(this)
                    .load(beer.image_url)
                    .error(R.drawable.error_image)
                    .into(this)
            }
        }
    }

}

class BeerDiffUtil : DiffUtil.ItemCallback<Beer>() {
    override fun areItemsTheSame(oldItem: Beer, newItem: Beer) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Beer, newItem: Beer) = oldItem == newItem
}