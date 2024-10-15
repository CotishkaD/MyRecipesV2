package com.grandmaskitchen.myrecipes.di

import com.grandmaskitchen.myrecipes.database.RecipeDAO
import com.grandmaskitchen.myrecipes.database.RecipesDatabase

/**
 * Created by Pustovit V.V.
 * Date: 21.09.2024
 * Time: 10:49
 */
object RecipeDaoProvider {

    private var instance: RecipeDAO? = null

    fun getInstance(database: RecipesDatabase): RecipeDAO {
        var inner = instance
        return if (inner != null) {
            inner
        } else {
            inner = database.getRecipeDao()
            instance = inner
            inner
        }
    }
}