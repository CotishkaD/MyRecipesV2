package com.grandmaskitchen.myrecipes.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.grandmaskitchen.myrecipes.repository.RecipesRepository

class AllRecipesViewModel(app:Application, private val recipesRepository: RecipesRepository): AndroidViewModel(app) {
    fun getAllRecipesByCategoryName(categoryName: String) = recipesRepository.getAllRecipesByCategoryName(categoryName)
}