package com.john.proyecto_pmdm_john_2023_2024

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.john.proyecto_pmdm_john_2023_2024.databinding.ActivityMainBinding
import com.john.proyecto_pmdm_john_2023_2024.controller.Controller


class MainActivity : AppCompatActivity() {
    lateinit var controller : Controller
    lateinit var binding : ActivityMainBinding
    // lateinit var recyclerView: RecyclerView
    private var secondFragment= SecondFragment()
    private lateinit var fragmentManager : FragmentManager //gestor de Fragmentos d.
    private lateinit var btnExit: Button
    private lateinit var btnAdd: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityMainBinding.inflate( layoutInflater)
        setContentView( binding.root)

        init() //inicializo la clase
    }
    fun init(){
        initRecyclerView()
        controller = Controller( this) //Creamos el controler
        controller.setAdapter()
        //controller.loggOut() //muestro el log en pantalla
        fragmentManager = supportFragmentManager //gestor de transacciones

    }
    private fun initRecyclerView() {
        binding.myRecyclerView.layoutManager = LinearLayoutManager( this)


    }
}