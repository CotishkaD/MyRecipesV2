package com.grandmaskitchen.myrecipes.viewmodel

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.grandmaskitchen.myrecipes.model.RecipeModel
import com.grandmaskitchen.myrecipes.repository.RecipesRepository
import kotlinx.coroutines.launch


// viewModel служит двум целям
// 1 - содержать в себе состояние экрана(список данных итд)
// 2 - содержать в себе бизнесс логику
// 1 viewModel для всего приложения - это путь в никуда) У каждого фрагмента должна быть своя вьюмодель. Но есть кейсы когда одна и таже вьюмодель шарится между экранами
// например процесс регистрации или она может использоваться в диалоге.
// Но я почти всегда создаю под каждый экран свою вьюмодель, если надо пошарить какие-то данные между двумя экранами - есть куча способов это сделать
// Так что сделай под каждый экран свою вьюмодель.
// НО RecipesRepository - должен быть 1 на все приложение!
class RecipeViewModel(app: Application, private val recipesRepository: RecipesRepository) : AndroidViewModel(app){

    init {
        Log.d("TAG", "RecipeViewModel create ${hashCode()} ")
    }
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