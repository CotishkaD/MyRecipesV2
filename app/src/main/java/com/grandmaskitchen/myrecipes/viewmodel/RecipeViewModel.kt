package com.grandmaskitchen.myrecipes.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.grandmaskitchen.myrecipes.model.RecipeModel
import com.grandmaskitchen.myrecipes.repository.RecipesRepository
import kotlinx.coroutines.launch

class RecipeViewModel(app: Application, private val recipesRepository: RecipesRepository) : AndroidViewModel(app){

    fun addRecipe(recipeModel: RecipeModel) = viewModelScope.launch {
        recipesRepository.insertRecipe(recipeModel)
    }

    fun updateRecipe(recipeModel: RecipeModel) = viewModelScope.launch {
        recipesRepository.updateRecipe(recipeModel)
    }

    fun  deleteRecipe(recipeModel: RecipeModel) = viewModelScope.launch {
        recipesRepository.deleteRecipe(recipeModel)
    }

    fun getRecipeById(id: Int) = recipesRepository.getRecipeById(id)

    fun getAllRecipesByCategoryName(categoryName: String) = recipesRepository.getAllRecipesByCategoryName(categoryName)
}