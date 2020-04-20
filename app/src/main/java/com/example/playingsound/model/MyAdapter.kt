package com.example.playingsound.model

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.playingsound.databinding.TextRowItemBinding

const val TAG = "MyAdapter"

class MyAdapter(val listFiles : List<String>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    class MyViewHolder(val binding: TextRowItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(TextRowItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun getItemCount(): Int = listFiles.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.textView.apply {
            text = listFiles[position]
            setOnClickListener {
                Toast.makeText(holder.itemView.context, listFiles[position], Toast.LENGTH_SHORT).show()
            }
        }
    }
}
