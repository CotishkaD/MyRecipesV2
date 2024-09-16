package com.grandmaskitchen.myrecipes.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.grandmaskitchen.myrecipes.repository.RecipesRepository

class RecipeViewModelFactory(val app: Application, private val recipesRepository: RecipesRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RecipeViewModel(app, recipesRepository) as T
    }
}