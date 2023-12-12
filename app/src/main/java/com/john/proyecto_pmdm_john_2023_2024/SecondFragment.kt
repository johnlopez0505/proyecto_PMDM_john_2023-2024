package com.john.proyecto_pmdm_john_2023_2024


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.john.proyecto_pmdm_john_2023_2024.controller.Controller

import com.john.proyecto_pmdm_john_2023_2024.databinding.FragmentSecondBinding


class SecondFragment : Fragment() {
   private lateinit var binding: FragmentSecondBinding
    lateinit var controller : Controller
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSecondBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




    }



}