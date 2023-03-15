package com.shiva.hungerytime.retrofit

import com.shiva.hungerytime.pojo.CategoryList
import com.shiva.hungerytime.pojo.MealsByCategoryList
import com.shiva.hungerytime.pojo.MealList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApi {
    @GET("random.php")
    fun getRandomMeal():Call<MealList>

    @GET("lookup.php?")
    fun getMealDetials(
        @Query("i") id: String
    ):Call<MealList>

    @GET("filter.php?")
    fun getPopularItem(
        @Query("c") category: String
    ) : Call<MealsByCategoryList>

    @GET("categories.php")
    fun getCategories():Call<CategoryList>

    @GET("filter.php")
    fun getMealsByCategory(@Query("c") categoryName: String ) : Call<MealsByCategoryList>



}