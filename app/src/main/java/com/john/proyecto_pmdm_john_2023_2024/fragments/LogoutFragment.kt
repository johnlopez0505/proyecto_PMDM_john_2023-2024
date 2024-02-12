package com.john.proyecto_pmdm_john_2023_2024.fragments

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.john.proyecto_pmdm_john_2023_2024.Login
import com.john.proyecto_pmdm_john_2023_2024.databinding.FragmentLogoutBinding
import com.john.proyecto_pmdm_john_2023_2024.models.MainActivity

class LogoutFragment : Fragment() {
    private lateinit var binding: FragmentLogoutBinding
    lateinit var login: Login
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLogoutBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textLogout.text = "Estamos en el fragment logout"

        binding.logout.setOnClickListener {
            login = Login()
            login.cerrarSesion()

        }

    }
}