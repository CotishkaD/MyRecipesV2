package com.grandmaskitchen.myrecipes.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.grandmaskitchen.myrecipes.MainActivity
import com.grandmaskitchen.myrecipes.R
import com.grandmaskitchen.myrecipes.adapters.RecipeAdapter
import com.grandmaskitchen.myrecipes.databinding.FragmentHomeBinding
import com.grandmaskitchen.myrecipes.databinding.FragmentRecipesBinding
import com.grandmaskitchen.myrecipes.viewmodel.AllRecipesViewModel
import com.grandmaskitchen.myrecipes.viewmodel.RecipeViewModel
import com.grandmaskitchen.myrecipes.viewmodel.RecipeViewModelFactory


class AllRecipesFragment : Fragment(R.layout.fragment_recipes) {

    private var _binding : FragmentRecipesBinding? = null
    private val binding get() = _binding!!

    private lateinit var recipeAdapter: RecipeAdapter
    private lateinit var allRecipesViewModel: AllRecipesViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRecipesBinding.inflate(inflater, container, false)

        val app = requireActivity().application
        val repository = (activity as MainActivity).getRecipesRepository()
        val factory = RecipeViewModelFactory(app, repository)
        allRecipesViewModel = ViewModelProvider(this, factory)[AllRecipesViewModel::class.java]

        setupRecyclerView()

        val categoryName = arguments?.getString("categoryName") ?: "Default Category"
        val categoryStringId = arguments?.getInt("categoryStringId") ?: R.string.default_string

        binding.recipesTopBarTextView.text = getString(categoryStringId)

        binding.backButton.setOnClickListener{
            val action = AllRecipesFragmentDirections.actionRecipesFragmentToHomeFragment()
            it.findNavController().navigate(action)
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
        allRecipesViewModel.getAllRecipesByCategoryName(categoryName).observe(viewLifecycleOwner) {recipes ->
            recipeAdapter.differ.submitList(recipes)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}