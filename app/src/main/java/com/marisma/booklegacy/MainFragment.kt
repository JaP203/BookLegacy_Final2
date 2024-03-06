// MainFragment.kt
package com.marisma.booklegacy.fragments

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.marisma.booklegacy.MainActivity
import com.marisma.booklegacy.R
import com.marisma.booklegacy.databinding.FragmentMainBinding
import com.marisma.booklegacy.sampledata.DataStoreUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.Locale




class MainFragment : Fragment() {

    companion object {

        var usuario = ""
    }

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding?.let { binding ->
            binding.nextButton.setOnClickListener {
                val userName = binding.userEditText.text.toString()

                // Guardar el último usuario
                GlobalScope.launch(Dispatchers.IO) {
                    DataStoreUtil.saveLastUser(requireContext(), userName)
                }

                val bundle = Bundle().apply {
                    putString("USER_NAME", userName)
                }

                val mA = requireActivity() as MainActivity
                mA.activarNav()
                findNavController().navigate(R.id.action_mainFragment2_to_menuFragment, bundle)
            }

            GlobalScope.launch(Dispatchers.Main) {
                DataStoreUtil.getLastUser(requireContext()).collect { lastUser ->
                    lastUser?.let {
                        binding.userEditText.setText(it)
                    }
                }
            }
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
