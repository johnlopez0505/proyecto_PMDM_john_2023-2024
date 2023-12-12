package com.john.proyecto_pmdm_john_2023_2024.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.john.proyecto_pmdm_john_2023_2024.MainActivity2
import com.john.proyecto_pmdm_john_2023_2024.R
import com.john.proyecto_pmdm_john_2023_2024.controller.Controller
import com.john.proyecto_pmdm_john_2023_2024.databinding.FragmentRestaurantesBinding

class RestaurantesFragment : Fragment() {
    lateinit var binding: FragmentRestaurantesBinding
    lateinit var recyclerView : RecyclerView
    lateinit var controller: Controller
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

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
    }

    fun init(){
        recyclerView = binding.myRecyclerView.findViewById(R.id.my_recycler_view)
        controller = Controller(requireContext())
        controller.iniciar(recyclerView)

       binding.fab.setOnClickListener{
           controller.addRestaurant(recyclerView)
       }

    }


}