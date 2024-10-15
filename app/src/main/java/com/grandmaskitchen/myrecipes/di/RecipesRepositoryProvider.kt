package com.grandmaskitchen.myrecipes.di

import com.grandmaskitchen.myrecipes.database.RecipeDAO
import com.grandmaskitchen.myrecipes.repository.RecipesRepository

/**
 * Created by Pustovit V.V.
 * Date: 21.09.2024
 * Time: 10:48
 */
object RecipesRepositoryProvider {

    private var instance: RecipesRepository? = null

    fun getInstance(dao: RecipeDAO): RecipesRepository {
        var inner = instance
        return if (inner != null) {
            inner
        } else {
            inner = RecipesRepository(dao)
            instance = inner
            inner
        }
    }
}