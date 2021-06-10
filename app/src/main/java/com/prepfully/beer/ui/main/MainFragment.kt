package com.prepfully.beer.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.prepfully.beer.BeerAdapter
import com.prepfully.beer.databinding.MainFragmentBinding

class MainFragment : Fragment() {

    private val viewModel : BeerViewModel by viewModels()

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = MainFragmentBinding.inflate(layoutInflater)
        binding.beerRv.layoutManager = LinearLayoutManager(context)
        val adapter = BeerAdapter()
        binding.beerRv.adapter = adapter

        viewModel.beerList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        return binding.root
    }

}