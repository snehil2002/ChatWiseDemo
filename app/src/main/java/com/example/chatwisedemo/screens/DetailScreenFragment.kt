package com.example.chatwisedemo.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import coil.load
import com.example.chatwisedemo.R
import com.example.chatwisedemo.model.Product
import com.example.chatwisedemo.viewmodel.ProductViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailScreenFragment :Fragment(){

    private val productViewModel by viewModels<ProductViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.product_details_screen,container,false)

        val navController=findNavController()

        val progressBar = view.findViewById<ProgressBar>(R.id.desc_progress_bar)
        val imageView = view.findViewById<ImageView>(R.id.desc_image)
        val titleTextView = view.findViewById<TextView>(R.id.desc_title)
        val categoryTextView = view.findViewById<TextView>(R.id.desc_category)
        val brandTextView = view.findViewById<TextView>(R.id.desc_brand)
        val descriptionTextView = view.findViewById<TextView>(R.id.desc_description)
        val linearLayout=view.findViewById<LinearLayout>(R.id.desc_linear_layout)

        val productId=arguments?.getString("productId")
        val topBar=view.findViewById<Toolbar>(R.id.desc_topbar)

        val topbarTitle=topBar.findViewById<TextView>(R.id.topbar_title)
        topbarTitle.text="Product Details"

        topBar.findViewById<ImageView>(R.id.topbar_back).setOnClickListener{
            navController.popBackStack()
        }

        linearLayout.visibility=View.GONE



        viewLifecycleOwner.lifecycleScope.launch {
            productViewModel.isLoading.collect{isLoading->
                progressBar.visibility=if(isLoading) View.VISIBLE else View.GONE

            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            productViewModel.allProducts.collect{productList->
               val product=productList.find{product ->
                    product.id.toString()==productId
                }
                if (product!=null) {
                    imageView.load(product.thumbnail)
                    titleTextView.text = product.title
                    categoryTextView.text = "Category : ${product.category}"
                    brandTextView.text = "Brand : ${product.brand}"
                    descriptionTextView.text = product.description

                    linearLayout.visibility=View.VISIBLE


                }



            }
        }

    return view
    }

}