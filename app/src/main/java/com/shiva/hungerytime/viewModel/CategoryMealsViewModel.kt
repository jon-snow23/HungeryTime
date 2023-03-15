package com.shiva.hungerytime.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shiva.hungerytime.pojo.MealsByCategory
import com.shiva.hungerytime.pojo.MealsByCategoryList
import com.shiva.hungerytime.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryMealsViewModel: ViewModel() {

    val mealLivedata = MutableLiveData<List<MealsByCategory>>()

    fun getMealsByCategory(categoryName:String){
        RetrofitInstance.api.getMealsByCategory(categoryName).enqueue(object :Callback<MealsByCategoryList>{
            override fun onResponse(
                call: Call<MealsByCategoryList>,
                response: Response<MealsByCategoryList>
            ) {
                response.body()?.let { mealList->
                    mealLivedata.postValue(mealList.meals)
                }
            }

            override fun onFailure(call: Call<MealsByCategoryList>, t: Throwable) {
                Log.d("CategoryMealsViewModel" , t.message.toString())

            }


        })
    }

    fun observeMealLivedata():LiveData<List<MealsByCategory>>{
        return mealLivedata
    }
}