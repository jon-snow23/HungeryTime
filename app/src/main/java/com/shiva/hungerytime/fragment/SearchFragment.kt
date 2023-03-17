package com.shiva.hungerytime.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.shiva.hungerytime.R
import com.shiva.hungerytime.activities.MainActivity
import com.shiva.hungerytime.adapter.MealAdapter
import com.shiva.hungerytime.databinding.FragmentSearchBinding
import com.shiva.hungerytime.viewModel.HomeViewModel

class SearchFragment : Fragment() {

private lateinit var binding: FragmentSearchBinding
private lateinit var viewModel : HomeViewModel
private lateinit var searchRecyclerViewAdapter : MealAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = (activity as MainActivity).viewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSearchBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prepareRecyclerViewAdapter()
        binding.icSearch.setOnClickListener { searchMeals() }
        observeSearchedMealsLiveData()
    }

    private fun observeSearchedMealsLiveData() {
        viewModel.observeSearchedMealsLiveData().observe(viewLifecycleOwner , Observer { mealsList->

            searchRecyclerViewAdapter.differ.submitList(mealsList)
        })
    }

    private fun searchMeals() {
        val searchQuery = binding.edSearch.text.toString()
        if (searchQuery.isNotEmpty()){
            viewModel.searchMeals(searchQuery)
        }
    }

    private fun prepareRecyclerViewAdapter() {
         searchRecyclerViewAdapter = MealAdapter()
        binding.searchMealRv.apply {
            layoutManager = GridLayoutManager(context , 2 ,GridLayoutManager.VERTICAL , false)
            adapter = searchRecyclerViewAdapter
        }
    }
}