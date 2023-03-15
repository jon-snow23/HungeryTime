package com.shiva.hungerytime.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shiva.hungerytime.databinding.PopularItemBinding
import com.shiva.hungerytime.pojo.MealsByCategory

class MostPopularAdapter():RecyclerView.Adapter<MostPopularAdapter.PopularMealViewHolder>(){
    lateinit var OnItemClick:((MealsByCategory) -> Unit)
    private var mealList = ArrayList<MealsByCategory>()


    fun setMeals(mealsList:ArrayList<MealsByCategory>){
        this.mealList = mealsList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMealViewHolder {
        return PopularMealViewHolder(PopularItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return mealList.size
    }

    override fun onBindViewHolder(holder: PopularMealViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(mealList[position].strMealThumb)
            .into(holder.binding.imgPopularMeal)

        holder.itemView.setOnClickListener {
            OnItemClick.invoke(mealList[position])
        }
    }

    class PopularMealViewHolder(var binding: PopularItemBinding): RecyclerView.ViewHolder(binding.root)
}