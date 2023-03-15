package com.shiva.hungerytime.activities

import androidx.lifecycle.ViewModelProvider
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.get
import androidx.recyclerview.widget.GridLayoutManager
import com.shiva.hungerytime.adapter.CategoriesAdapter
import com.shiva.hungerytime.adapter.CategoryMealsAdapter
import com.shiva.hungerytime.databinding.ActivityCategoryMealBinding
import com.shiva.hungerytime.fragment.HomeFragment
import com.shiva.hungerytime.viewModel.CategoryMealsViewModel

class CategoryMealActivity : AppCompatActivity() {
    lateinit var binding: ActivityCategoryMealBinding
    lateinit var categoryMealsViewModel: CategoryMealsViewModel
    lateinit var categoryMealAdapter: CategoryMealsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryMealBinding.inflate(layoutInflater)
        setContentView(binding.root)

        prepareRecyclerView()
        categoryMealsViewModel = ViewModelProvider(this)[CategoryMealsViewModel::class.java]

//       categoryMealsViewModel  = ViewModelProvider(this)[CategoryMealsViewModel::class.java]

        categoryMealsViewModel.getMealsByCategory(intent.getStringExtra(HomeFragment.CATEGORY_NAME)!!)

        categoryMealsViewModel.observeMealLivedata().observe(this, Observer{mealList->
            binding.tvCategoryCount.text = mealList.size.toString()
            categoryMealAdapter.setMealsList(mealList)
        })
    }

    private fun prepareRecyclerView() {
        categoryMealAdapter = CategoryMealsAdapter()

        binding.mealRecyclerview.apply {
             layoutManager = GridLayoutManager(context, 2 ,GridLayoutManager.VERTICAL , false)
            adapter = categoryMealAdapter

        }
    }
}