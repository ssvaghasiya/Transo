package com.example.transoapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.transoapp.R
import com.example.transoapp.databinding.LayoutDataBinding
import com.example.transoapp.databinding.LayoutDetailDataBinding
import com.example.transoapp.extension.loadImage
import com.example.transoapp.listener.EventListener
import com.example.transoapp.pojo.ExampleData

class DetailItemAdapter(
    var data: ArrayList<ExampleData>,
    var mEventListener: EventListener<ExampleData>
) :
    RecyclerView.Adapter<DetailItemAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemBinding = DataBindingUtil.inflate<LayoutDetailDataBinding>(
            inflater,
            R.layout.layout_detail_data, parent, false
        )
        return MyViewHolder(itemBinding)
    }


    override fun getItemCount(): Int {
        return data.size
    }

    private fun getItem(p: Int): ExampleData {
        return data[p]

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) = with(holder.itemBinding) {
        val item = getItem(position)
        holder.itemBinding.exampleData = item
        item.apply {
            imageView.loadImage(url)
        }

        root.setOnClickListener {
            mEventListener.onItemClick(position, item, it)
        }
    }

    inner class MyViewHolder(internal var itemBinding: LayoutDetailDataBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

}