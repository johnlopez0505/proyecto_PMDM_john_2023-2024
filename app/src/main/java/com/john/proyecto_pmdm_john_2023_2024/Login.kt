package com.john.proyecto_pmdm_john_2023_2024

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.john.proyecto_pmdm_john_2023_2024.data.models.user.DaoUser
import com.john.proyecto_pmdm_john_2023_2024.databinding.ActivityLoginBinding
import com.john.proyecto_pmdm_john_2023_2024.domain.model.User
import com.john.proyecto_pmdm_john_2023_2024.ui.view.MainActivity
import com.john.proyecto_pmdm_john_2023_2024.ui.view.Register
import com.john.proyecto_pmdm_john_2023_2024.ui.view.login.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Login : AppCompatActivity() {
    private lateinit var bindingLogin : ActivityLoginBinding
    private val loginViewModel : LoginViewModel by viewModels()
    private lateinit var shared : SharedPreferences
    private lateinit var user: String
    private lateinit var email: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingLogin = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(bindingLogin.root)

        cargarPreferenciasCompartidas()
        verificarLogueo()
        registerLiveData()


    }

    private fun registerLiveData() {
        loginViewModel.loginLiveData.observe(this){
                login->
            if(login != null){
                validarCredenciales(login)
            }else{
                Toast.makeText(
                    this, "Error en el servidor $login",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }
    }

    private fun initEvents() {
        bindingLogin.buttonLogin.setOnClickListener {
            loginViewModel.isLogin("srodher115@g.educaand.es",
                "santi")
            Log.i(TAG, "initEvents: ${bindingLogin.email}")
        }


        bindingLogin.buttonRegistrar.setOnClickListener{
            registerUser()
        }

    }

    private fun verificarLogueo(){
        if(isLogeo()){
            // Si ya está logueado, iniciamos la actividad principal directamente
            startMainActivity()
        }
        else{
            // Si no está logueado, mostramos la interfaz de inicio de sesión
            initEvents()
            loadLastUser()
        }
    }

    private fun validarCredenciales(login: User) {
        val user = bindingLogin.email.text.toString()
        val password = bindingLogin.password.text.toString()
        val email = login.email
        // Guardar el último usuario ingresado
        guardarUltimoUsuario(user, password, email)
        // El usuario ha iniciado sesión con éxito
        //guardamos las preferencias
        saveLoginState(email)
        // Credenciales válidas, iniciar Activity principal
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("name", user)
        intent.putExtra("email", email)
        startActivity(intent)
    }
    private fun registerUser() {
        val  intent = Intent(this, Register::class.java)
        startActivity(intent)

    }

    private fun getLastUsername(): Triple<String,String,String>  {
        val preferences = getPreferences(MODE_PRIVATE)
        val lastUsername = preferences.getString("lastUsername", "") ?: ""
        val lastPassword = preferences.getString("lastPassword", "") ?: ""
        val lastEmail = preferences.getString("lastEmail","") ?: ""
        return Triple(lastUsername, lastPassword,lastEmail)

    }

    private fun guardarUltimoUsuario(username: String, password: String,email: String) {
        val preferences = getPreferences(MODE_PRIVATE)
        val editor = preferences.edit()
        editor.putString("lastUsername", username)
        editor.putString("lastPassword", password)
        editor.putString("email",email)
        editor.apply()
    }
    private fun loadLastUser(){
        // Recuperar el último usuario almacenado y establecerlo en el campo de texto
        val (lastUserName,lastPassword,lastEmail) = getLastUsername()
        bindingLogin.email.setText(lastUserName)
        bindingLogin.password.setText(lastPassword)
        user = lastUserName
        email=lastEmail
    }
    private fun cargarPreferenciasCompartidas(){

       val fichePreferencias : String = getString(R.string.preferencias_fichero_login)
        shared = this.getSharedPreferences(fichePreferencias, MODE_PRIVATE)
    }

    private fun isLogeo(): Boolean {
        // Obtener el estado de inicio de sesión almacenado en SharedPreferences
        return shared.getBoolean(getString(R.string.preferencia_login),false)
    }

    @SuppressLint("SuspiciousIndentation")
    private fun startMainActivity() {
        loadLastUser()
        val email = shared.getString(getString(R.string.preferencias_email), "")
        // Iniciar la actividad principal
        val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("name",user)
            intent.putExtra("email",email)
        startActivity(intent)

        // Finalizar esta actividad para que no vuelva atrás con el botón de retroceso
        finish()
    }

    private fun saveLoginState(userEmail: String) {
        // Obtener un editor de SharedPreferences
        val editor = shared.edit()
        // Guardar el estado de inicio de sesión como verdadero
        editor.putBoolean(getString(R.string.preferencia_login), true)
        // Guardar el correo electrónico del usuario
        editor.putString(getString(R.string.preferencias_email), userEmail)
        // Aplicar los cambios
        editor.apply()
    }

}
