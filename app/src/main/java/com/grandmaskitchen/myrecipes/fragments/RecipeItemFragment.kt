package com.grandmaskitchen.myrecipes.fragments

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.grandmaskitchen.myrecipes.MainActivity
import com.grandmaskitchen.myrecipes.R
import com.grandmaskitchen.myrecipes.databinding.FragmentRecipeItemBinding
import com.grandmaskitchen.myrecipes.model.RecipeModel
import com.grandmaskitchen.myrecipes.utils.DeleteConfirmationDialog
import com.grandmaskitchen.myrecipes.utils.IngredientUtils
import com.grandmaskitchen.myrecipes.viewmodel.RecipeViewModel



class RecipeItemFragment : Fragment(R.layout.fragment_recipe_item) {

    private var _binding : FragmentRecipeItemBinding? = null
    private val binding get() = _binding!!

    private lateinit var recipeViewModel: RecipeViewModel
    private lateinit var currentRecipe: RecipeModel

    private val args: RecipeItemFragmentArgs by navArgs()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRecipeItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recipeViewModel = (activity as MainActivity).recipeViewModel

        currentRecipe = args.recipe!!


        recipeViewModel.getRecipeById(currentRecipe.id).observe(viewLifecycleOwner) { recipeList ->
            val recipe = recipeList.firstOrNull()
            if (recipe != null) {
                binding.tVRecipeName.text = currentRecipe.recipeTitle
                binding.descriptionTextItem.text = currentRecipe.recipeDescription

                IngredientUtils.splitAndSetIngredients(
                    currentRecipe.recipeIngredients,
                    binding.ingredientsItemTextOne,
                    binding.ingredientsItemTextTwo
                )
            }
        }


        binding.backButtonRecipeItem.setOnClickListener {
            val action = RecipeItemFragmentDirections.actionRecipeItemFragmentToRecipesFragment(currentRecipe.categoryName, currentRecipe.categoryStringId)
            it.findNavController().navigate(action)
        }

        binding.editBtnRecipeItem.setOnClickListener {
            val action = RecipeItemFragmentDirections.actionRecipeItemFragmentToEditRecipeFragment(currentRecipe)
            it.findNavController().navigate(action)
        }

        binding.deleteBtnRecipeItem.setOnClickListener {
            deleteRecipe()
        }
    }


    private fun deleteRecipe() {
        val dialog = DeleteConfirmationDialog()
        dialog.setPositiveButtonClickListener {
            recipeViewModel.deleteRecipe(currentRecipe)
            val action = RecipeItemFragmentDirections.actionRecipeItemFragmentToRecipesFragment(currentRecipe.categoryName, currentRecipe.categoryStringId)
            view?.findNavController()?.navigate(action)
        }

        dialog.show(parentFragmentManager, "DeleteConfirmationDialog")
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}