// MenuFragment.kt
package com.marisma.booklegacy.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.navigation.fragment.findNavController
import com.marisma.booklegacy.BookListFragment
import com.marisma.booklegacy.CreditFragment
import com.marisma.booklegacy.R
import com.marisma.booklegacy.databinding.FragmentMenuBinding

class MenuFragment : Fragment() {
    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userName = arguments?.getString("USER_NAME")

        binding.userNameTextView.text = userName

        binding.creditButton.setOnClickListener {
           findNavController().navigate(R.id.action_menuFragment_to_creditFragment)
        }

        binding.ListaLibros.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_bookListFragment)

        }

        binding.exitButton.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_mainFragment2)

        }
        binding.usuario.setOnClickListener{

            findNavController().navigate(R.id.action_menuFragment_to_usuFragment)
        }
        binding.Favs.setOnClickListener{

            findNavController().navigate((R.id.action_menuFragment_to_favBookList))

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
