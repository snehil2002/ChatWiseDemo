package com.example.chatwisedemo.screens

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.chatwisedemo.R
import com.example.chatwisedemo.model.Product

class ProductAdapter(private val productList: List<Product>,
    private val onItemClick:(Product)->Unit):RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

        inner class ProductViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
            val productName:TextView=itemView.findViewById(R.id.product_list_title)
            val productDescription:TextView=itemView.findViewById(R.id.product_list_description)
            val productImage:ImageView=itemView.findViewById(R.id.product_list_image)

            fun bind(product: Product){
                productName.text=product.title
                productDescription.text=product.description
                productImage.load(product.thumbnail)
                itemView.setOnClickListener { onItemClick(product) }

            }

        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.product_item_card,parent,false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(productList[position])
    }

    override fun getItemCount(): Int {
        return productList.size
    }

}
