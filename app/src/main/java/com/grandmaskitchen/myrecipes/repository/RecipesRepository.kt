package com.grandmaskitchen.myrecipes.repository

import android.util.Log
import com.grandmaskitchen.myrecipes.database.RecipeDAO
import com.grandmaskitchen.myrecipes.database.RecipesDatabase
import com.grandmaskitchen.myrecipes.model.RecipeModel
// Тебе нужна здесь только дао а не вся БД. Не передавай лишнего
class RecipesRepository(private val recipeDAO: RecipeDAO) {

    init {
        Log.d("TAG", "RecipesRepository create ${hashCode()} ")
    }

    suspend fun insertRecipe(recipeModel: RecipeModel) = recipeDAO.insertRecipe(recipeModel)
    suspend fun updateRecipe(recipeModel: RecipeModel) = recipeDAO.updateRecipe(recipeModel)
    suspend fun deleteRecipe(recipeModel: RecipeModel) = recipeDAO.deleteRecipe(recipeModel)

    fun getRecipeById(id: Int) = recipeDAO.getRecipeById(id)
    fun getAllRecipesByCategoryName(categoryName: String) = recipeDAO.getAllRecipesByCategoryName(categoryName)

}