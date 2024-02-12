package com.john.proyecto_pmdm_john_2023_2024.ui.modelView

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.john.proyecto_pmdm_john_2023_2024.Login
import com.john.proyecto_pmdm_john_2023_2024.R
import com.john.proyecto_pmdm_john_2023_2024.databinding.FragmentLogoutBinding


class LogoutFragment : Fragment() {
    private lateinit var binding: FragmentLogoutBinding
    private lateinit var shared:SharedPreferences
    var login = Login()
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


        // Obtener el archivo de preferencias compartidas
        shared = requireActivity().getSharedPreferences(
            getString(R.string.preferencias_fichero_login),
            Context.MODE_PRIVATE)

        binding.logout.setOnClickListener {
            cerrarSesion()
            showToast("Sesión cerrada exitosamente")

        }
    }
    private fun cerrarSesion() {
        // Obtenemos un editor de SharedPreferences
        val editor = shared.edit()

        // Limpiamos el estado de inicio de sesión
        editor.putBoolean(getString(R.string.preferencia_login), false)

        // Aplicamos los cambios
        editor.apply()

        val intent = Intent(activity, Login::class.java)
        startActivity(intent)

        showToast("Sesión cerrada exitosamente")
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }



}