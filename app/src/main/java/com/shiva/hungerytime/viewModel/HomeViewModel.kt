package com.shiva.hungerytime.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shiva.hungerytime.pojo.*
import com.shiva.hungerytime.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel():ViewModel() {

    private var randomMealLiveData = MutableLiveData<Meal>()
    private var popularItemLiveData = MutableLiveData<List<MealsByCategory>>()
    private var categoriesLiveData = MutableLiveData<List<Category>>()


    fun getRandomMeal() {
        RetrofitInstance.api.getRandomMeal().enqueue(object : Callback<MealList> {
            override fun onResponse(call: Call<MealList>, response: Response<MealList>) {
                if (response.body()!=null) {
                    val randMeal : Meal = response.body()!!.meals[0]
                    randomMealLiveData.value = randMeal
                }else {
                    return
                }
            }

            override fun onFailure(call: Call<MealList>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    fun getPopularItem(){
        RetrofitInstance.api.getPopularItem("Vegetarian").enqueue(object :Callback<MealsByCategoryList>{
            override fun onResponse(call: Call<MealsByCategoryList>, response: Response<MealsByCategoryList>) {
                if (response.body()!=null){
                    popularItemLiveData.value = response.body()!!.meals
                }
            }

            override fun onFailure(call: Call<MealsByCategoryList>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }


    fun getCategories () {
        RetrofitInstance.api.getCategories().enqueue(object :Callback<CategoryList>{
            override fun onResponse(call: Call<CategoryList>, response: Response<CategoryList>) {
                if (response.body()!= null) {
                    categoriesLiveData.value = response.body()!!.categories
                }
            }

            override fun onFailure(call: Call<CategoryList>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    fun observeRandomMealLivedata():LiveData<Meal>{
        return randomMealLiveData
    }
    fun observePopularItemLivedata():LiveData<List<MealsByCategory>>{
        return popularItemLiveData
    }
    fun observeCategoryLivedata() : LiveData<List<Category>>{
        return categoriesLiveData
    }
}