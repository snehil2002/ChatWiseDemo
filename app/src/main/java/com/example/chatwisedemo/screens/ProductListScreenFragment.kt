package com.example.chatwisedemo.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chatwisedemo.R
import com.example.chatwisedemo.viewmodel.ProductViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProductListScreenFragment :Fragment(){

    private val productViewModel by viewModels<ProductViewModel>()

    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.product_list_screen,container,false)
        val navController=findNavController()

        val topBar=view.findViewById<Toolbar>(R.id.list_topbar)
        val recyclerView:RecyclerView=view.findViewById(R.id.recycler_view1)
        recyclerView.layoutManager=LinearLayoutManager(context)

        topBar.findViewById<ImageView>(R.id.topbar_back).setOnClickListener{
            navController.popBackStack()
        }

        val topbarTitle=topBar.findViewById<TextView>(R.id.topbar_title)
        topbarTitle.text="Product List"

        viewLifecycleOwner.lifecycleScope.launch {
            productViewModel.isLoading.collect{isLoading->
                view.findViewById<ProgressBar>(R.id.list_progress_bar).visibility=if(isLoading) View.VISIBLE else View.GONE

            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            productViewModel.allProducts.collect{productList->
                recyclerView.adapter=ProductAdapter(productList){product->
                    val action=ProductListScreenFragmentDirections.productlistToDetail(product.id.toString())
                    navController.navigate(action)
                }
            }
        }
        return view

    }
}