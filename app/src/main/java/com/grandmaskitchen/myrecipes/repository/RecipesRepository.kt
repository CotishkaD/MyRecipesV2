package com.grandmaskitchen.myrecipes.repository

import com.grandmaskitchen.myrecipes.database.RecipesDatabase
import com.grandmaskitchen.myrecipes.model.RecipeModel

class RecipesRepository(private val db: RecipesDatabase) {

    suspend fun insertRecipe(recipeModel: RecipeModel) = db.getRecipeDao().insertRecipe(recipeModel)
    suspend fun updateRecipe(recipeModel: RecipeModel) = db.getRecipeDao().updateRecipe(recipeModel)
    suspend fun deleteRecipe(recipeModel: RecipeModel) = db.getRecipeDao().deleteRecipe(recipeModel)

    fun getRecipeById(id: Int) = db.getRecipeDao().getRecipeById(id)
    fun getAllRecipesByCategoryName(categoryName: String) = db.getRecipeDao().getAllRecipesByCategoryName(categoryName)

}