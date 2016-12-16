package com.derekcsm.wave_example.projectlist.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.derekcsm.wave_example._model.Product
import java.util.*

class ProductsAdapter(private val listener: ProductsAdapter.ClickListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

  private var ProductsAdapterItems = ArrayList<ProductsAdapterItem<*>>()

  init {
  }

  fun addItems(ProductsAdapterItems: ArrayList<ProductsAdapterItem<*>>) {
    this.ProductsAdapterItems = ProductsAdapterItems
  }

  override fun getItemCount(): Int {
    return ProductsAdapterItems.size
  }

  override fun getItemViewType(position: Int): Int {
    return ProductsAdapterItems[position].viewType
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
    when (viewType.toLong()) {
      ProductsAdapterItem.PRODUCT -> return ProductViewHolder.create(parent.context, parent)
      else -> throw IllegalStateException("Incorrect view type: " + viewType)
    }
  }

  @Suppress("UNCHECKED_CAST")
  override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    val currentItem = ProductsAdapterItems[position]

    when (holder.itemViewType.toLong()) {
      ProductsAdapterItem.PRODUCT -> (holder as ProductViewHolder).onBind(
          currentItem as ProductsAdapterItem<Product>, listener)
      else -> throw IllegalStateException("Incorrect view type: " + holder.itemViewType)
    }
  }

  override fun onAttachedToRecyclerView(recyclerView: RecyclerView?) {
    super.onAttachedToRecyclerView(recyclerView)
  }

  interface ClickListener {
    fun onProductClicked(item: ProductsAdapterItem<Product>)
  }
}