package com.grandmaskitchen.myrecipes.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.grandmaskitchen.myrecipes.MainActivity
import com.grandmaskitchen.myrecipes.R
import com.grandmaskitchen.myrecipes.adapters.RecipeAdapter
import com.grandmaskitchen.myrecipes.databinding.FragmentHomeBinding
import com.grandmaskitchen.myrecipes.databinding.FragmentRecipesBinding
import com.grandmaskitchen.myrecipes.viewmodel.RecipeViewModel


class AllRecipesFragment : Fragment(R.layout.fragment_recipes) {

    private var _binding : FragmentRecipesBinding? = null
    private val binding get() = _binding!!

    private lateinit var recipeAdapter: RecipeAdapter
    private lateinit var recipeViewModel: RecipeViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRecipesBinding.inflate(inflater, container, false)

        recipeViewModel = (activity as MainActivity).recipeViewModel

        setupRecyclerView()

        val categoryName = arguments?.getString("categoryName") ?: "Default Category"
        val categoryStringId = arguments?.getInt("categoryStringId") ?: R.string.default_string

        binding.recipesTopBarTextView.text = getString(categoryStringId)

        binding.backButton.setOnClickListener{
            findNavController().navigate(R.id.action_recipesFragment_to_homeFragment)
        }

        binding.addFabButton.setOnClickListener{
            val action = AllRecipesFragmentDirections.actionRecipesFragmentToAddRecipeFragment(categoryName, categoryStringId)
            it.findNavController().navigate(action)
        }

        return binding.root
    }

    private fun setupRecyclerView() {
        recipeAdapter = RecipeAdapter()
        binding.recipesList.apply {
            adapter = recipeAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }


        val categoryName = arguments?.getString("categoryName") ?: "Default Category"
        recipeViewModel.getAllRecipesByCategoryName(categoryName).observe(viewLifecycleOwner) {recipes ->
            recipeAdapter.differ.submitList(recipes)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}