package com.john.proyecto_pmdm_john_2023_2024

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.john.proyecto_pmdm_john_2023_2024.dao.DaoUser
import com.john.proyecto_pmdm_john_2023_2024.databinding.ActivityLoginBinding
import com.john.proyecto_pmdm_john_2023_2024.fragments.RestaurantesFragment
import com.john.proyecto_pmdm_john_2023_2024.models.MainActivity
import com.john.proyecto_pmdm_john_2023_2024.models.Register


class Login : AppCompatActivity() {
    private lateinit var bindingLogin : ActivityLoginBinding
    private lateinit var shared : SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingLogin = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(bindingLogin.root)

        initEvents()
        loadLastUser()

    }

    private fun initEvents() {
        bindingLogin.buttonLogin.setOnClickListener {
            validarCredenciales()
        }

        bindingLogin.buttonRegistrar.setOnClickListener{
            registerUser()
        }

    }

    private fun validarCredenciales() {
        cargarPreferenciasCompartidas()
        val user = bindingLogin.editTextUsername.text.toString()
        val password = bindingLogin.editTextPassword.text.toString()

        val usuarioEncontrado =  DaoUser.myDao.getDataUser().find {
                    it.name==user && it.password ==password }
        val email = usuarioEncontrado?.email.toString()
        if (usuarioEncontrado != null) {
            // Guardar el último usuario ingresado
            guardarUltimoUsuario(user, password)
            // El usuario ha iniciado sesión con éxito
            // Credenciales válidas, iniciar Activity principal
            val intent = Intent(this, MainActivity::class.java)
            val intenFragment = Intent(this,
                    RestaurantesFragment::class.java).apply{
                // Pasa el usuario e imail como argumento al Activity principal
                intent.putExtra("name",user)
                intent.putExtra("email",email)
            }

            startActivity(intent)
        } else {
            // Las credenciales no son válidas
            Toast.makeText(this, "Credenciales no válidas",
                    Toast.LENGTH_SHORT).show()
        }

    }
    private fun registerUser() {
        val  intent = Intent(this, Register::class.java)
        startActivity(intent)

    }

    private fun getLastUsername(): Pair<String,String>  {
        val preferences = getPreferences(MODE_PRIVATE)
        val lastUsername = preferences.getString("lastUsername", "") ?: ""
        val lastPassword = preferences.getString("lastPassword", "") ?: ""
        return Pair(lastUsername, lastPassword)

    }

    private fun guardarUltimoUsuario(username: String, password: String) {
        val preferences = getPreferences(MODE_PRIVATE)
        val editor = preferences.edit()
        editor.putString("lastUsername", username)
        editor.putString("lastPassword", password)
        editor.apply()
    }
    private fun loadLastUser(){
        // Recuperar el último usuario almacenado y establecerlo en el campo de texto
        val (lastUserName,lastPassword) = getLastUsername()
        bindingLogin.editTextUsername.setText(lastUserName)
        bindingLogin.editTextPassword.setText(lastPassword)
    }
    private fun cargarPreferenciasCompartidas(){
       var fichePreferencias : String = "PreferenciasAppRestaurante"
        shared = this.getSharedPreferences(fichePreferencias, MODE_PRIVATE)
    }
    fun cerrarSesion(){
        val sharedPreferences:SharedPreferences = getSharedPreferences(getString(R.string.preferencias_fichero_login),
            MODE_PRIVATE)
        val editor : SharedPreferences.Editor = sharedPreferences.edit()
        editor.clear()
        editor.commit()
        finish()
    }
}
