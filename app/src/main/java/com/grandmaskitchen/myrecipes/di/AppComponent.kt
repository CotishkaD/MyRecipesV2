package com.grandmaskitchen.myrecipes.di

import android.content.Context
import com.grandmaskitchen.myrecipes.repository.RecipesRepository

/**
 * Created by Pustovit V.V.
 * Date: 21.09.2024
 * Time: 10:59
 */
object AppComponent {

    lateinit var recipesRepository: RecipesRepository
        private set

    fun init(context: Context) {
        val database = RecipesDatabaseProvider.getInstance(context)
        val dao = RecipeDaoProvider.getInstance(database)
        recipesRepository = RecipesRepositoryProvider.getInstance(dao)
    }
}