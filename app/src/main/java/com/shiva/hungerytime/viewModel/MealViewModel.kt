package com.shiva.hungerytime.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shiva.hungerytime.pojo.Meal
import com.shiva.hungerytime.pojo.MealList
import com.shiva.hungerytime.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MealViewModel():ViewModel() {
    private var mealDetailsLivedata = MutableLiveData<Meal>()

    fun getMEalDeatil(id:String){
        RetrofitInstance.api.getMealDetials(id).enqueue(object :Callback<MealList>{
            override fun onResponse(call: Call<MealList>, response: Response<MealList>) {
                if (response.body()!=null){
                    mealDetailsLivedata.value = response.body()!!.meals[0]
                }else{
                    return
                }
            }

            override fun onFailure(call: Call<MealList>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    fun observeMealDetailLivedata():LiveData<Meal>{
        return mealDetailsLivedata
    }
}