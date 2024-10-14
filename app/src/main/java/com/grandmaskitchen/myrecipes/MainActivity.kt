package com.grandmaskitchen.myrecipes

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModelProvider
import com.grandmaskitchen.myrecipes.database.RecipesDatabase
import com.grandmaskitchen.myrecipes.databinding.ActivityMainBinding
import com.grandmaskitchen.myrecipes.di.AppComponent
import com.grandmaskitchen.myrecipes.repository.RecipesRepository
import com.grandmaskitchen.myrecipes.viewmodel.RecipeViewModel
import com.grandmaskitchen.myrecipes.viewmodel.RecipeViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        // Splash Screen
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            installSplashScreen()
        }

        super.onCreate(savedInstanceState)



        // Изменение цвета статус-бара
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val statusBarColor = ContextCompat.getColor(this, R.color.primary_green)
            window.statusBarColor = statusBarColor
        }

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

    }

    fun getRecipesRepository(): RecipesRepository {
        return AppComponent.recipesRepository
    }

//    private fun setUpRepository() {
//        // Тут важный момент. Когда убивается активити(например при повороте экрана, или при смене темной темы на светлую)
//        // Активити пересоздается и весь код отработает еще раз. Но новая вью модель не создатся(потому что вьюмодель и создана для этого - она переживает)
//        // Я прописал логи в RecipesRepository и в RecipeViewModel
//        // Запусти приложение, потом переверни экран и увидишь что репозиторий создается сново - а нам этого не надо.
//        // Пока у тебя нет DI я написал некий код в папке di  посмотри что он делает
//        // RecipesRepository получай AppComponent.recipesRepository
//
//        val recipesRepository = AppComponent.recipesRepository
//
//    }
}