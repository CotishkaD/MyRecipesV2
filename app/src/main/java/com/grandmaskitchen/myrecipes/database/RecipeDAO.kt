package com.grandmaskitchen.myrecipes.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.grandmaskitchen.myrecipes.model.RecipeModel

@Dao
interface RecipeDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipe(recipeModel: RecipeModel)

    @Update
    suspend fun updateRecipe(recipeModel: RecipeModel)

    @Delete
    suspend fun deleteRecipe(recipeModel: RecipeModel)

    @Query("SELECT * FROM recipes WHERE id ==:id")
    fun getRecipeById(id: Int): LiveData<List<RecipeModel>>

    @Query("SELECT * FROM recipes WHERE categoryName == :categoryName")
    fun getAllRecipesByCategoryName(categoryName: String): LiveData<List<RecipeModel>>
}