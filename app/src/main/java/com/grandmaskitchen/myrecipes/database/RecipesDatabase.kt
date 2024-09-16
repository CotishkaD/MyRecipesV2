package com.grandmaskitchen.myrecipes.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.grandmaskitchen.myrecipes.model.RecipeModel

@Database(entities = [RecipeModel::class], version = 1)
abstract class RecipesDatabase : RoomDatabase() {

    abstract fun getRecipeDao(): RecipeDAO

    companion object {
        @Volatile
        private var instance : RecipesDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also {
                instance = it
            }
        }

        private fun createDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            RecipesDatabase::class.java,
            name = "recipes_db"
        ).build()
    }

}