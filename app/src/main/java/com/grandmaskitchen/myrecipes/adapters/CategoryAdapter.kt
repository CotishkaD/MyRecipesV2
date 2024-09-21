package com.grandmaskitchen.myrecipes.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.grandmaskitchen.myrecipes.R
import com.grandmaskitchen.myrecipes.databinding.CategoryCardItemBinding
import com.grandmaskitchen.myrecipes.fragments.HomeFragmentDirections
import com.grandmaskitchen.myrecipes.model.CategoriesModel

class CategoryAdapter(val categoriesList: ArrayList<CategoriesModel>) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>(){
// Почему здесь не используешь viewBinding?
//inner class CategoryViewHolder(binding: CategoryCardItemBinding) : RecyclerView.ViewHolder(binding.root) {
    inner class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var imgCard: ImageView = itemView.findViewById(R.id.categoryImage)
        var txtCard: TextView = itemView.findViewById(R.id.categoryName)

        init {
            itemView.setOnClickListener() {
                val category = categoriesList[position]
                val action = HomeFragmentDirections.actionHomeFragmentToRecipesFragment(category.categoryName, category.txtCard)
                itemView.findNavController().navigate(action)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.category_card_item, parent, false)
        return CategoryViewHolder(v)
    }

    override fun getItemCount(): Int {
        return categoriesList.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.imgCard.setImageResource(categoriesList[position].imgCard)
        val stringResId = categoriesList[position].txtCard
        holder.txtCard.text = holder.itemView.context.getString(stringResId)
    }

    companion object {
        // Этот список должен находится в репозитории! Это же данные! И запихни это все в БД для тренировки)
        fun createDefaultCategories(): ArrayList<CategoriesModel> {
            return arrayListOf(
                CategoriesModel(R.drawable.category_soup, R.string.soups, "soup"),
                CategoriesModel(R.drawable.category_salad, R.string.salads, "salad"),
                CategoriesModel(R.drawable.category_hot_dishes, R.string.mains, "mains"),
                CategoriesModel(R.drawable.category_desserts, R.string.desserts, "desserts"),
                CategoriesModel(R.drawable.category_sause, R.string.sauce, "sauce"),
                CategoriesModel(R.drawable.category_jam, R.string.jams, "jam"),
                CategoriesModel(R.drawable.category_pickles, R.string.pickles, "pickles"),
                CategoriesModel(R.drawable.category_bake, R.string.bake, "bake"),
                CategoriesModel(R.drawable.category_drinks, R.string.drinks, "drinks")
            )
        }
    }


}