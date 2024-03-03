package com.john.proyecto_pmdm_john_2023_2024.ui.view.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.john.proyecto_pmdm_john_2023_2024.ui.view.login.Login
import com.john.proyecto_pmdm_john_2023_2024.data.models.user.DaoUser
import com.john.proyecto_pmdm_john_2023_2024.data.models.user.Usuarios
import com.john.proyecto_pmdm_john_2023_2024.databinding.ActivityRegisterBinding
import com.john.proyecto_pmdm_john_2023_2024.domain.model.User
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Register : AppCompatActivity() {
    private lateinit var bindingRegister : ActivityRegisterBinding
    private val registerViewModel : RegisterViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingRegister = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(bindingRegister.root)

        initEvents()
        registerLiveData()
    }

    private fun initEvents() {
        bindingRegister.buttonSignUp.setOnClickListener{
            comprobarEstado()
        }
        bindingRegister.buttonRegresarLogin.setOnClickListener{
            regresarLogin()
        }

    }

    private fun registerLiveData() {
        registerViewModel.registerLiveData.observe(this){
            register ->
            if(register != null){

            }else{
            Toast.makeText(
                this, "Credenciales no validas",
                Toast.LENGTH_SHORT
            ).show()
        }
        }

    }

    private fun comprobarEstado() {
        val name = bindingRegister.editTextUsername.text.toString().trim()
        val email = bindingRegister.editTextEmail.text.toString().trim()
        val password = bindingRegister.editTextPassword.text.toString().trim()
        val confirmPassword = bindingRegister.editTextTextPassword2.text.toString().trim()
        // Verificar que ningún campo esté vacío
        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            // Mostrar un mensaje de error si algún campo está vacío
            val campo = "Campo no puede estar vacio"
            if(name.isEmpty())
                bindingRegister.editTextUsername.error = campo
            if(email.isEmpty())
                bindingRegister.editTextEmail.error = campo
            if (password.isEmpty())
                bindingRegister.editTextPassword.error = campo
            if (confirmPassword.isEmpty())
                bindingRegister.editTextTextPassword2.error = campo
            Toast.makeText(
                this, "Todos los campos deben ser completados",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        if (password == confirmPassword) {
            // Contraseña y confirmación coinciden, realiza el registro
            registerViewModel.isRegister(
                bindingRegister.editTextUsername.text.toString(),
                bindingRegister.editTextEmail.text.toString(),
                bindingRegister.editTextPassword.text.toString())
            Toast.makeText(
                this, "Usuario creado correctamente",
                Toast.LENGTH_SHORT
            ).show()
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        } else {
            // Contraseña y confirmación no coinciden, muestra un mensaje de error
            bindingRegister.editTextPassword.error = "Las contraseñas no coinciden"
            bindingRegister.editTextTextPassword2.error = "Las contraseñas no coinciden"
            Toast.makeText(
                this, "las contraseñas no coinciden",
                Toast.LENGTH_SHORT
            ).show()
            return
        }
    }

    private fun regresarLogin() {
        val intent = Intent(this, Login::class.java)
        startActivity(intent)
    }
}