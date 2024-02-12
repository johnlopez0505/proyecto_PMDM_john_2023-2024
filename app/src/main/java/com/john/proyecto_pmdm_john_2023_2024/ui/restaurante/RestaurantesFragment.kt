package com.john.proyecto_pmdm_john_2023_2024.ui.restaurante

import android.content.Intent.getIntent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.john.proyecto_pmdm_john_2023_2024.R
import com.john.proyecto_pmdm_john_2023_2024.controller.Controller
import com.john.proyecto_pmdm_john_2023_2024.databinding.ActivityMainBinding
import com.john.proyecto_pmdm_john_2023_2024.databinding.FragmentRestaurantesBinding
import com.john.proyecto_pmdm_john_2023_2024.ui.adapter.AdapterRestaurant

class RestaurantesFragment : Fragment() {
    lateinit var binding: FragmentRestaurantesBinding
    lateinit var recyclerView : RecyclerView
    lateinit var controller: Controller
    lateinit var adapter: AdapterRestaurant
    val restaurantViewModel: RestaurantViewModel by viewModels() //tiene que ser constante.

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRestaurantesBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        registerLiveData()

    }

    fun init(){
        recyclerView = binding.myRecyclerView.findViewById(R.id.my_recycler_view)
        binding.myRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        controller = Controller(requireContext())
        controller.iniciar(recyclerView)
        binding.myRecyclerView

       binding.fab.setOnClickListener{
           controller.addRestaurant(recyclerView)
       }

    }
    private fun registerLiveData() {
        restaurantViewModel.restaurantListLiveData.observe(
            requireActivity()
        ) { myList ->
            //Aquí hacemos la actualización del adapter.
            adapter.restaurantRepository = myList!! //aseguro los datos.
            binding.myRecyclerView.adapter = adapter //le asigno el adapter.
            adapter.notifyDataSetChanged()
        }
    }

}





