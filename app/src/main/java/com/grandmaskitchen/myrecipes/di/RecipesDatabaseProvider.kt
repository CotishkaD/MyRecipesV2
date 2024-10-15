package com.grandmaskitchen.myrecipes.di

import android.content.Context
import com.grandmaskitchen.myrecipes.database.RecipesDatabase

/**
 * Created by Pustovit V.V.
 * Date: 21.09.2024
 * Time: 10:52
 */
object RecipesDatabaseProvider {

    private var instance: RecipesDatabase? = null

    fun getInstance(context: Context): RecipesDatabase {
        var inner = instance
        return if (inner != null) {
            inner
        } else {
            inner = RecipesDatabase(context)
            instance = inner
            inner
        }
    }
}