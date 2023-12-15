package com.john.proyecto_pmdm_john_2023_2024.models

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import com.john.proyecto_pmdm_john_2023_2024.R
import com.john.proyecto_pmdm_john_2023_2024.controller.Controller
import com.john.proyecto_pmdm_john_2023_2024.databinding.ActivityMainBinding
import com.john.proyecto_pmdm_john_2023_2024.databinding.NavHeaderMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    lateinit var binding: ActivityMainBinding
    private lateinit var controller : Controller
    private lateinit var name : TextView
    private lateinit var email : TextView
    private lateinit var imagen : ImageView
    private lateinit var navView : NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init() //inicializo la clase
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main_activity, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main2)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private fun init(){
        setSupportActionBar(binding.appBarMain.toolbar)
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView


        login()
        val navController = findNavController(R.id.nav_host_fragment_content_main2)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_principal, R.id.nav_restaurantes
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        initRecyclerView()
        controller = Controller( this) //Creamos el controler
        //controller.setAdapter(recicle)
        //controller.loggOut() //muestro el log en pantalla

    }
    private fun initRecyclerView() {
        //binding.myRecyclerView.layoutManager = LinearLayoutManager( this)
    }
    @SuppressLint("ResourceType")
    private fun login() {
        // Uso de ViewBinding para acceder a las vistas en el encabezado de navegación
        navView = binding.navView
        val navHeaderBinding = NavHeaderMainBinding.bind(navView.getHeaderView(0))
        name = navHeaderBinding.textUserNavHeader
        email = navHeaderBinding.textViewEmailNavHeader
        imagen = navHeaderBinding.imageViewHeader

        val name1 = intent.getStringExtra("name")
        val email1 = intent.getStringExtra("email")

        if (name1 != null || email1 != null || imagen != null) {
            name.text = name1
            email.text = email1
            imagen.setImageResource(R.mipmap.p1_round)
        }

    }

}