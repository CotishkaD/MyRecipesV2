package com.grandmaskitchen.myrecipes

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.grandmaskitchen.myrecipes.database.RecipesDatabase
import com.grandmaskitchen.myrecipes.databinding.ActivityMainBinding
import com.grandmaskitchen.myrecipes.repository.RecipesRepository
import com.grandmaskitchen.myrecipes.viewmodel.RecipeViewModel
import com.grandmaskitchen.myrecipes.viewmodel.RecipeViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var recipeViewModel: RecipeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        // Изменение цвета статус-бара
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val statusBarColor = ContextCompat.getColor(this, R.color.primary_green)
            window.statusBarColor = statusBarColor
        }

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        setUpViewModel()
    }

    private fun setUpViewModel() {
        val recipesRepository = RecipesRepository(RecipesDatabase(this))
        val viewModelProviderFactory = RecipeViewModelFactory(application, recipesRepository)

        recipeViewModel = ViewModelProvider(this, viewModelProviderFactory)[RecipeViewModel::class.java]
    }
}