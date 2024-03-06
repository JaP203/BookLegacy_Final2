package com.marisma.booklegacy
// BookListFragment.kt

import BookAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.marisma.booklegacy.databinding.FragmentBookListBinding
import com.marisma.booklegacy.databinding.FragmentFavBookListBinding
import com.marisma.booklegacy.sampledata.Datasource

class FavBookList : Fragment() {
    private var _binding: FragmentFavBookListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavBookListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Datasource.rellenaFAV()
        val adapter = BookAdapter(Datasource.favBookList, findNavController() )

        binding.favList.adapter = adapter
        binding.favList.layoutManager = LinearLayoutManager(requireContext())
        binding.favList.setHasFixedSize(true)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
