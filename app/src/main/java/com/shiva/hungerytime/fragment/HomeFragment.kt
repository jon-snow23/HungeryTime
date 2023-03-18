package com.shiva.hungerytime.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.shiva.hungerytime.R
import com.shiva.hungerytime.activities.CategoryMealActivity
import com.shiva.hungerytime.activities.MainActivity
import com.shiva.hungerytime.activities.MealActivity
import com.shiva.hungerytime.adapter.CategoriesAdapter
import com.shiva.hungerytime.adapter.MostPopularAdapter
import com.shiva.hungerytime.databinding.FragmentHomeBinding
import com.shiva.hungerytime.databinding.FragmentHomeBinding.*
import com.shiva.hungerytime.fragment.bottomsheet.MealBottomSheetFragment
import com.shiva.hungerytime.pojo.MealsByCategory
import com.shiva.hungerytime.pojo.Meal
import com.shiva.hungerytime.viewModel.HomeViewModel


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel : HomeViewModel
    private lateinit var randomMeal: Meal
    private lateinit var popularItemAdapter: MostPopularAdapter
    private lateinit var categoriesAdapter: CategoriesAdapter

    companion object {
        const val MEAL_ID = "com.shiva.hungerytime.fragment.idMeal"
        const val MEAL_NAME = "com.shiva.hungerytime.fragment.nameMeal"
        const val MEAL_THUMB = "com.shiva.hungerytime.fragment.thumbMeal"
        const val CATEGORY_NAME = "com.shiva.hungerytime.fragment.categoryName"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        homeMvvm = ViewModelProvider(this)[HomeViewModel::class.java]
//        homeMvvm = ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        viewModel = (activity as MainActivity).viewModel




        popularItemAdapter = MostPopularAdapter()
        categoriesAdapter = CategoriesAdapter()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = inflate(inflater, container , false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




        viewModel.getRandomMeal()
        observerRandomMeal()
        onRandomMealClick()

        preparePopularItemRecyclerView()
        viewModel.getPopularItem()
        observePopularItemsLivedata()
        onPopularItemClick()


        prepareCategoriesRecyclerView()
        viewModel.getCategories()
        observeCategoriesLivedata()
        onCategoryClick()


        onPopularItemLongClick()

        onSearchItemClick()


    }

    private fun onSearchItemClick() {
        binding.imgSearch.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_searchFragment)
        }
    }

    private fun onPopularItemLongClick() {
        popularItemAdapter.onLongItemClick = {
            val mealBottomSheetFragment = MealBottomSheetFragment.newInstance(it.idMeal)
            mealBottomSheetFragment.show(childFragmentManager, "Meal Info")
        }
    }

    private fun onCategoryClick() {
        categoriesAdapter.onItemClick = { category ->
            val intent = Intent(activity , CategoryMealActivity::class.java)
            intent.putExtra(CATEGORY_NAME , category.strCategory)
            startActivity(intent)
        }
    }

    private fun prepareCategoriesRecyclerView() {
        binding.recViewCategories.apply {
            layoutManager =GridLayoutManager(context , 3 , GridLayoutManager.VERTICAL , false)
            adapter = categoriesAdapter
        }
    }

    private fun observeCategoriesLivedata() {
        viewModel.observeCategoryLivedata().observe(viewLifecycleOwner ) { categorie ->
                categoriesAdapter.setCategoryList(categorie)
        }
    }

    private fun onPopularItemClick() {
        popularItemAdapter.onItemClick = { meal ->
            val intent = Intent(activity , MealActivity::class.java)
            intent.putExtra(MEAL_ID , meal.idMeal)
            intent.putExtra(MEAL_NAME , meal.strMeal)
            intent.putExtra(MEAL_THUMB , meal.strMealThumb)
            startActivity(intent)
        }
    }

    private fun preparePopularItemRecyclerView() {
        binding.recViewMealPopular.apply {
            layoutManager = LinearLayoutManager(activity , LinearLayoutManager.HORIZONTAL , false)
            adapter = popularItemAdapter
        }
    }

    private fun observePopularItemsLivedata() {
        viewModel.observePopularItemLivedata().observe(viewLifecycleOwner
        ) { mealList ->
            popularItemAdapter.setMeals(mealsList = mealList as ArrayList<MealsByCategory>)
        }
    }

    private fun onRandomMealClick() {
        binding.randomMealCard.setOnClickListener {
            val intent = Intent(activity , MealActivity::class.java)
            intent.putExtra(MEAL_ID,randomMeal.idMeal)
            intent.putExtra(MEAL_NAME,randomMeal.strMeal)
            intent.putExtra(MEAL_THUMB,randomMeal.strMealThumb)

            startActivity(intent)
        }
    }

    private fun observerRandomMeal() {
        viewModel.observeRandomMealLivedata().observe(viewLifecycleOwner
        ) { meal ->
            Glide.with(this@HomeFragment)
                .load(meal!!.strMealThumb)
                .into(binding.imgRandom)

            this.randomMeal = meal
        }
    }

}