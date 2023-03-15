package com.shiva.hungerytime.activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.shiva.hungerytime.R
import com.shiva.hungerytime.databinding.ActivityMealBinding
import com.shiva.hungerytime.fragment.HomeFragment
import com.shiva.hungerytime.pojo.Meal
import com.shiva.hungerytime.viewModel.MealViewModel

class MealActivity : AppCompatActivity() {
    private lateinit var mealId:String
    private lateinit var mealName: String
    private lateinit var mealThumb:String
    private lateinit var youtubeLink:String
    private lateinit var binding: ActivityMealBinding
    private lateinit var mealMvvm: MealViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMealBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mealMvvm = ViewModelProvider(this)[MealViewModel::class.java]

        getMealInformationFromIntent()

        setInformationInViews()
        loadingCase()
        mealMvvm.getMEalDeatil(mealId)
        observeMealDetailsLivedata()
        onYoutubeImgClick()


    }

    private fun onYoutubeImgClick() {
             binding.imgYoutube.setOnClickListener {
                 val intent =  Intent(Intent.ACTION_VIEW, Uri.parse(youtubeLink))
                 startActivity(intent)
             }
    }

    private fun observeMealDetailsLivedata() {
        mealMvvm.observeMealDetailLivedata().observe(this , object :Observer<Meal>{
            override fun onChanged(t: Meal) {
                onResponseCase()
                val meal = t
                binding.tvCategoryInfo.text = "Category : ${meal!!.strCategory}"
                binding.tvAreaInfo.text = "Area : ${meal!!.strArea}"
                binding.tvInstructions.text = meal.strInstructions
                youtubeLink = meal.strYoutube
            }

        })
    }

    private fun setInformationInViews() {
        Glide.with(applicationContext)
            .load(mealThumb)
            .into(binding.imgMealDetail)
        binding.collapsingToolbar.title = mealName
        binding.collapsingToolbar.setCollapsedTitleTextColor(resources.getColor(R.color.white))
        binding.collapsingToolbar.setExpandedTitleColor(resources.getColor(R.color.white))
    }

    private fun getMealInformationFromIntent() {
        val intent = intent
        mealId = intent.getStringExtra(HomeFragment.MEAL_ID)!!
        mealName = intent.getStringExtra(HomeFragment.MEAL_NAME)!!
        mealThumb = intent.getStringExtra(HomeFragment.MEAL_THUMB)!!

    }
    private fun loadingCase(){
        binding.progressBar.visibility = View.VISIBLE
        binding.btnSave.visibility = View.INVISIBLE
        binding.tvInstructions.visibility = View.INVISIBLE
        binding.tvCategoryInfo.visibility = View.INVISIBLE
        binding.tvAreaInfo.visibility = View.INVISIBLE
        binding.imgYoutube.visibility = View.INVISIBLE
    }

    private fun onResponseCase(){
        binding.progressBar.visibility = View.INVISIBLE
        binding.btnSave.visibility = View.VISIBLE
        binding.tvInstructions.visibility = View.VISIBLE
        binding.tvCategoryInfo.visibility = View.VISIBLE
        binding.tvAreaInfo.visibility = View.VISIBLE
        binding.imgYoutube.visibility = View.VISIBLE

    }
}