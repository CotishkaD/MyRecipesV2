package com.grandmaskitchen.myrecipes.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.grandmaskitchen.myrecipes.repository.RecipesRepository

class RecipeViewModelFactory(val app: Application, private val recipesRepository: RecipesRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(AllRecipesViewModel::class.java) -> AllRecipesViewModel(app, recipesRepository) as T
            modelClass.isAssignableFrom(AddRecipeViewModel::class.java) -> AddRecipeViewModel(app, recipesRepository) as T
            modelClass.isAssignableFrom(EditRecipeViewModel::class.java) -> EditRecipeViewModel(app, recipesRepository) as T
            modelClass.isAssignableFrom(RecipeViewModel::class.java) -> RecipeViewModel(app, recipesRepository) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
//        return RecipeViewModel(app, recipesRepository) as T
    }
}