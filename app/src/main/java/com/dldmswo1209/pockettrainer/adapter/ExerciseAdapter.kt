package com.dldmswo1209.pockettrainer.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dldmswo1209.pockettrainer.databinding.ExerciseItemBinding
import com.dldmswo1209.pockettrainer.datas.ExerciseItem

class ExerciseAdapter(val itemClicked: (ExerciseItem, Boolean) -> (Unit)): ListAdapter<ExerciseItem,ExerciseAdapter.ViewHolder>(diffUtil) {

    inner class ViewHolder(private val binding: ExerciseItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(exerciseItem: ExerciseItem){
            binding.exerciseNameTextView.text = exerciseItem.name
            binding.checkbox.isChecked = exerciseItem.isComplete
            binding.checkbox.setOnClickListener {
                itemClicked(exerciseItem, binding.checkbox.isChecked)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
    : ViewHolder = ViewHolder(ExerciseItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    companion object{
        val diffUtil = object: DiffUtil.ItemCallback<ExerciseItem>(){
            override fun areItemsTheSame(oldItem: ExerciseItem, newItem: ExerciseItem): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: ExerciseItem, newItem: ExerciseItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}