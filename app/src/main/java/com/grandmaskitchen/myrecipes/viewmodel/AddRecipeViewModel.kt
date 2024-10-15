package com.grandmaskitchen.myrecipes.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.grandmaskitchen.myrecipes.model.RecipeModel
import com.grandmaskitchen.myrecipes.repository.RecipesRepository
import kotlinx.coroutines.launch

class AddRecipeViewModel(app: Application, private val recipesRepository: RecipesRepository): AndroidViewModel(app) {
    fun addRecipe(recipeModel: RecipeModel) = viewModelScope.launch {
        recipesRepository.insertRecipe(recipeModel)
    }
}