package com.example.macropay.views.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.util.PatternsCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.macropay.R
import com.example.macropay.databinding.FragmentLoginBinding
import com.example.macropay.views.viewmodel.MacroPayViewModel

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding: FragmentLoginBinding get() = _binding!!
    private val viewModel: MacroPayViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        binding.btnLogin.setOnClickListener {
            val email = binding.inpEdtTxtEmail.text.toString()
            val password = binding.inpEdtTxtPassword.text.toString()
            if (checkInputs(email, password)) {
                viewModel.onCreate(email, password)
                findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
            } else {

            }
        }
        return binding.root
    }

    private fun checkInputs(email: String, password: String) =
        validateEmail(email) && validatePassword(password)

    private fun validateEmail(email: String): Boolean {
        return if (email.isEmpty()) {
            binding.inpLayEmail.error = "Campo requerido"
            false
        } else if (!PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.inpLayEmail.error = "Favor de introducir un correo valido"
            false
        } else {
            binding.inpLayEmail.error = null
            true
        }
    }

    private fun validatePassword(password: String): Boolean {
        return if (password.isEmpty()) {
            binding.inpLayPassword.error = "Campo requerido"
            false
        } else {
            binding.inpLayPassword.error = null
            true
        }
    }

}