package com.grandmaskitchen.myrecipes.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "recipes")
@Parcelize
data class RecipeModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val categoryName: String,
    val categoryStringId: Int,
    val recipeTitle: String,
    val recipeIngredients: String,
    val recipeDescription: String
) : Parcelable
