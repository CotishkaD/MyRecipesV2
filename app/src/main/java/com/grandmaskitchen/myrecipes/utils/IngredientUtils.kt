package com.grandmaskitchen.myrecipes.utils

import android.widget.TextView

object IngredientUtils {
    fun splitAndSetIngredients(ingredients: String, textViewOne: TextView, textViewTwo: TextView) {
        val ingredientsArray = ingredients.split("\n")
        val middleIndex = (ingredientsArray.size + 1) / 2

        val ingredientsOne = StringBuilder()
        val ingredientsTwo = StringBuilder()

        for (i in 0 until middleIndex) {
            ingredientsOne.append(ingredientsArray[i]).append("\n")
        }

        for (i in middleIndex until ingredientsArray.size) {
            ingredientsTwo.append(ingredientsArray[i]).append("\n")
        }

        if (ingredientsOne.isNotEmpty() && ingredientsOne.last() == '\n') {
            ingredientsOne.setLength(ingredientsOne.length - 1)
        }
        if (ingredientsTwo.isNotEmpty() && ingredientsTwo.last() == '\n') {
            ingredientsTwo.setLength(ingredientsTwo.length - 1)
        }

        textViewOne.text = ingredientsOne.toString()
        textViewTwo.text = ingredientsTwo.toString()
    }
}