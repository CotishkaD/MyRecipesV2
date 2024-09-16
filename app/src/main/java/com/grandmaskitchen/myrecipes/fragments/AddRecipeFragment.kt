package com.grandmaskitchen.myrecipes.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels // Импортируйте это
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.grandmaskitchen.myrecipes.MainActivity
import com.grandmaskitchen.myrecipes.R
import com.grandmaskitchen.myrecipes.databinding.FragmentAddRecipeBinding
import com.grandmaskitchen.myrecipes.model.RecipeModel
import com.grandmaskitchen.myrecipes.viewmodel.RecipeViewModel

class AddRecipeFragment : Fragment(R.layout.fragment_add_recipe) {

    private var _binding: FragmentAddRecipeBinding? = null
    private val binding get() = _binding!!

    private lateinit var recipeViewModel: RecipeViewModel
    private lateinit var mView: View


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddRecipeBinding.inflate(inflater, container, false)

        binding.backButtonCreate.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.btnCreate.setOnClickListener {
            saveRecipe(mView)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recipeViewModel = (activity as MainActivity).recipeViewModel
        mView = view
    }

    private fun saveRecipe(view: View) {
        val recipeName = binding.editTextNameRecipeCreate.text.toString().trim()
        val recipeIngredients = binding.editTextIngredientsCreate.text.toString().trim()
        val recipeDescription = binding.editTextDescriptionCreate.text.toString().trim()

        if (recipeName.isNotEmpty()) {
            val categoryName = arguments?.getString("categoryName") ?: "Default Category"
            val categoryStringId = arguments?.getInt("categoryStringId") ?: R.string.default_string
            val recipe = RecipeModel(0, categoryName,categoryStringId, recipeName, recipeIngredients, recipeDescription)

            recipeViewModel.addRecipe(recipe)

            Toast.makeText(mView.context, "Recipe saved, name $recipeName", Toast.LENGTH_LONG).show()

            val action = AddRecipeFragmentDirections.actionAddRecipeFragmentToRecipesFragment(categoryName, categoryStringId)
            view.findNavController().navigate(action)

        } else {
            Toast.makeText(mView.context, "Please enter the name of the dish", Toast.LENGTH_LONG).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
