package com.shiva.hungerytime.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.shiva.hungerytime.activities.CategoryMealActivity
import com.shiva.hungerytime.activities.MainActivity
import com.shiva.hungerytime.activities.MealActivity
import com.shiva.hungerytime.adapter.CategoriesAdapter
import com.shiva.hungerytime.databinding.FragmentCategoryBinding
import com.shiva.hungerytime.viewModel.HomeViewModel

class CategoryFragment : Fragment() {
    private lateinit var binding: FragmentCategoryBinding
    private lateinit var categoriesAdapter: CategoriesAdapter
    private lateinit var viewModel:HomeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCategoryBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prepareRecyclerView()
        observeCategories()
        onCategoryClick()
    }

    private fun onCategoryClick() {
        categoriesAdapter.onItemClick = { category ->
            val intent = Intent(activity , CategoryMealActivity::class.java)
            intent.putExtra(HomeFragment.CATEGORY_NAME, category.strCategory)
            startActivity(intent)
        }
    }

    private fun observeCategories() {
        viewModel.observeCategoryLivedata().observe(viewLifecycleOwner) { categories ->
            categoriesAdapter.setCategoryList(categories)
        }
    }
    private fun prepareRecyclerView() {
        categoriesAdapter = CategoriesAdapter()
        binding.categoryRecyclerView.apply {
            layoutManager = GridLayoutManager(context , 3 , GridLayoutManager.VERTICAL , false)
            adapter = categoriesAdapter
        }
    }
}