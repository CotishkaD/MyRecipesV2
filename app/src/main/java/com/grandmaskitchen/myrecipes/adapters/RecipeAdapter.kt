package com.grandmaskitchen.myrecipes.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.grandmaskitchen.myrecipes.R
import com.grandmaskitchen.myrecipes.databinding.RecipeListItemBinding
import com.grandmaskitchen.myrecipes.fragments.AllRecipesFragmentDirections
import com.grandmaskitchen.myrecipes.model.RecipeModel

class RecipeAdapter : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {


    class RecipeViewHolder(val itemBinding: RecipeListItemBinding): RecyclerView.ViewHolder(itemBinding.root)

    private val differCallback = object : DiffUtil.ItemCallback<RecipeModel>() {
        override fun areItemsTheSame(oldItem: RecipeModel, newItem: RecipeModel): Boolean {
            return oldItem.id == newItem.id &&
                    oldItem.recipeTitle == newItem.recipeTitle
        }

        override fun areContentsTheSame(oldItem: RecipeModel, newItem: RecipeModel): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        return RecipeViewHolder(
            RecipeListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val currentRecipe = differ.currentList[position]

        holder.itemBinding.nameRecipeListItem.text = currentRecipe.recipeTitle

        holder.itemView.setOnClickListener {
            var direction = AllRecipesFragmentDirections.actionRecipesFragmentToRecipeItemFragment(currentRecipe)
            it.findNavController().navigate(direction)
        }
    }


}