package com.example.chatwisedemo.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.chatwisedemo.R

class HomeScreenFragment :Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.home_screen,container,false)
        val button:Button=view.findViewById(R.id.button2)
        button.setOnClickListener{
            val action=HomeScreenFragmentDirections.homeToProductlist()
            findNavController().navigate(action)
        }
        return view

    }
}