package com.grandmaskitchen.myrecipes.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.grandmaskitchen.myrecipes.MainActivity
import com.grandmaskitchen.myrecipes.R
import com.grandmaskitchen.myrecipes.databinding.FragmentAddRecipeBinding
import com.grandmaskitchen.myrecipes.databinding.FragmentEditRecipeBinding
import com.grandmaskitchen.myrecipes.databinding.FragmentRecipesBinding
import com.grandmaskitchen.myrecipes.model.RecipeModel
import com.grandmaskitchen.myrecipes.viewmodel.RecipeViewModel

class EditRecipeFragment : Fragment(R.layout.fragment_edit_recipe) {

    private var _binding: FragmentEditRecipeBinding? = null
    private val binding get() = _binding!!

    private lateinit var recipeViewModel: RecipeViewModel
    private lateinit var currentRecipe: RecipeModel
    private lateinit var mView: View

    private val args: RecipeItemFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentEditRecipeBinding.inflate(inflater, container, false)

        binding.backButtonEdit.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.btnEdit.setOnClickListener {
            saveAndEditRecipe()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recipeViewModel = (activity as MainActivity).recipeViewModel
        mView = view

        currentRecipe = args.recipe!!

        binding.etNameRecipe.setText(currentRecipe.recipeTitle)
        binding.etIngredients.setText(currentRecipe.recipeIngredients)
        binding.etDescription.setText(currentRecipe.recipeDescription)
    }

    private fun saveAndEditRecipe() {
        val nameRecipe = binding.etNameRecipe.text.toString().trim()
        val ingredients = binding.etIngredients.text.toString().trim()
        val description = binding.etDescription.text.toString().trim()

        if (nameRecipe.isNotEmpty()) {
            val updatedRecipe = RecipeModel(currentRecipe.id, currentRecipe.categoryName, currentRecipe.categoryStringId, nameRecipe, ingredients, description)

            recipeViewModel.updateRecipe(updatedRecipe)

            val action = EditRecipeFragmentDirections.actionEditRecipeFragmentToRecipeItemFragment(updatedRecipe)
            view?.findNavController()?.navigate(action)
        } else {
            Toast.makeText(context, "Please enter name of the dish", Toast.LENGTH_LONG).show()
        }
    }

}