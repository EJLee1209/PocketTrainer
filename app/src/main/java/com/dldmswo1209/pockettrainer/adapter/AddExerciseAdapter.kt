package com.dldmswo1209.pockettrainer.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dldmswo1209.pockettrainer.R
import com.dldmswo1209.pockettrainer.databinding.ExerciseItemAddBinding
import com.dldmswo1209.pockettrainer.datas.ExerciseItem

class AddExerciseAdapter(val itemClicked: (ExerciseItem)->(Unit)): ListAdapter<ExerciseItem,AddExerciseAdapter.ViewHolder>(diffUtil) {

    inner class ViewHolder(private val binding: ExerciseItemAddBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(exerciseItem: ExerciseItem){
            binding.exerciseNameTextView.text = exerciseItem.name
            if(exerciseItem.isSelected){
                binding.itemLayout.setBackgroundResource(R.drawable.select_item)
            }else{
                binding.itemLayout.setBackgroundResource(R.drawable.exercise_item)
            }
            binding.root.setOnClickListener {
                exerciseItem.isSelected = !exerciseItem.isSelected
                if(exerciseItem.isSelected){
                    binding.itemLayout.setBackgroundResource(R.drawable.select_item)
                }else{
                    binding.itemLayout.setBackgroundResource(R.drawable.exercise_item)
                }
                itemClicked(exerciseItem)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
    : ViewHolder = ViewHolder(ExerciseItemAddBinding.inflate(LayoutInflater.from(parent.context), parent, false))

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