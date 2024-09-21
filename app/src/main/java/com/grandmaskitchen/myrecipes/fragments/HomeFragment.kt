package com.grandmaskitchen.myrecipes.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.grandmaskitchen.myrecipes.R
import com.grandmaskitchen.myrecipes.adapters.CategoryAdapter
import com.grandmaskitchen.myrecipes.databinding.FragmentHomeBinding
import com.grandmaskitchen.myrecipes.model.CategoriesModel
// При viewBinding передавать layout в конструктор Fragment не надо
class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: CategoryAdapter
    private lateinit var recyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        recyclerView = binding.recyclerViewCategories

        val categoriesList = CategoryAdapter.createDefaultCategories()

        adapter = CategoryAdapter(categoriesList)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}