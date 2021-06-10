package com.prepfully.beer.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.prepfully.beer.BeerAdapter
import com.prepfully.beer.databinding.BeerFragmentBinding

class BeerFragment : Fragment() {

    private val viewModel: BeerViewModel by lazy {
        requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        ViewModelProvider(this, BeerViewModel.Factory())
            .get(BeerViewModel::class.java)
    }
    companion object {
        fun newInstance() = BeerFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = BeerFragmentBinding.inflate(layoutInflater)
        binding.beerRv.layoutManager = LinearLayoutManager(context)
        val adapter = BeerAdapter()
        adapter.onClickListener  = { name ->
            Toast.makeText(context, "Clicked on $name", Toast.LENGTH_LONG).show()
        }
        binding.beerRv.adapter = adapter

        viewModel.beerList.observe(viewLifecycleOwner, adapter::submitList)

        viewModel.errorMessage.observe(viewLifecycleOwner) { errorMessage ->
            Toast.makeText(context, "Error loading  page: $errorMessage", Toast.LENGTH_LONG).show()
        }

        return binding.root
    }

}