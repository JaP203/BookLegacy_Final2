package com.marisma.booklegacy


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import com.marisma.booklegacy.databinding.FragmentCreditBinding
import com.marisma.booklegacy.fragments.MainFragment

// TODO: Rename parameter arguments, choose names that match

class CreditFragment : Fragment() {


    private var _binding: FragmentCreditBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCreditBinding.inflate(inflater, container, false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val direccionesCorreo = arrayOf("correo1@example.com")

        val userName = MainFragment.usuario

        binding.creditText.text = "$userName, estás usando la versión 1 de BookLegacy\n\nDescripción de la aplicación"

        binding.contactButton.setOnClickListener {
            composeEmail(direccionesCorreo, "VA")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun composeEmail(addresses: Array<String>, subject: String?) {
        val intent = Intent(Intent.ACTION_SENDTO)
        intent.data = Uri.parse("mailto:") // solo las aplicaciones de correo electrónico deben manejar esto
        intent.putExtra(Intent.EXTRA_EMAIL, addresses)
        intent.putExtra(Intent.EXTRA_SUBJECT, subject)
        startActivity(intent)
    }
}