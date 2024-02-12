package com.john.proyecto_pmdm_john_2023_2024.ui.view

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import android.view.Window
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.john.proyecto_pmdm_john_2023_2024.R
import com.john.proyecto_pmdm_john_2023_2024.controller.Controller
import com.john.proyecto_pmdm_john_2023_2024.databinding.ActivityMainBinding
import com.john.proyecto_pmdm_john_2023_2024.databinding.NavHeaderMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    lateinit var binding: ActivityMainBinding
    private lateinit var controller : Controller
    private lateinit var navController: NavController
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var name : TextView
    private lateinit var email : TextView
    private lateinit var imagen : ImageView
    private lateinit var navView : NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init() //inicializo la clase
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main_activity, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private fun init(){
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_main) as
                NavHostFragment
        navController = navHostFragment.navController

        setSupportActionBar(binding.appBarMain.toolbar)
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView



        login()
        initFab()
        navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        binding.appBarMain.appBottomBar.bottomNavigationView.background = null
        binding.appBarMain.appBottomBar.bottomNavigationView.setupWithNavController( navController )

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_principal, R.id.nav_restaurantes,R.id.shorts,
                R.id.library, R.id.subscription
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
            imagen.setImageResource(R.mipmap.logo1_round)
        }

    }
    private fun initFab(){
        binding.appBarMain.appBottomBar.fab.setOnClickListener{
            showBottomDialog()
        }
    }
    private fun showBottomDialog() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.bottomsheetlayout)
        val videoLayout = dialog.findViewById<LinearLayout>(R.id.layoutVideo)
        val shortsLayout = dialog.findViewById<LinearLayout>(R.id.layoutShorts)
        val liveLayout = dialog.findViewById<LinearLayout>(R.id.layoutLive)
        val cancelButton = dialog.findViewById<ImageView>(R.id.cancelButton)
        videoLayout.setOnClickListener {
            dialog.dismiss()
            Toast.makeText(this, "Upload a Video is clicked", Toast.LENGTH_SHORT)
                .show()
        }
        shortsLayout.setOnClickListener {
            dialog.dismiss()
            Toast.makeText(this, "Create a short is Clicked", Toast.LENGTH_SHORT)
                .show()
        }
        liveLayout.setOnClickListener {
            dialog.dismiss()
            Toast.makeText(this, "Go live is Clicked", Toast.LENGTH_SHORT).show()
        }
        cancelButton.setOnClickListener { dialog.dismiss() }
        dialog.show()
        dialog.window!!.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window!!.attributes.windowAnimations = R.style.DialogAnimation
        dialog.window!!.setGravity(Gravity.BOTTOM)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.buscar -> {
                navController.navigate(R.id.buscar)
                true
            }
            R.id.settings -> {
                navController.navigate(R.id.settings)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}