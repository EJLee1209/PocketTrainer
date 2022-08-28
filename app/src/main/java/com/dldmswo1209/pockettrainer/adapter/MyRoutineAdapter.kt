package com.dldmswo1209.pockettrainer.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dldmswo1209.pockettrainer.R
import com.dldmswo1209.pockettrainer.databinding.MyRoutineItemBinding
import com.dldmswo1209.pockettrainer.datas.ExerciseItem
import com.dldmswo1209.pockettrainer.datas.RoutineItem

class MyRoutineAdapter(val itemClicked: (RoutineItem) -> (Unit)): ListAdapter<RoutineItem,MyRoutineAdapter.ViewHolder>(diffUtil) {

    inner class ViewHolder(private val binding: MyRoutineItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(routineItem: RoutineItem){
            binding.routineNameTextView.text = routineItem.routineName
            if(routineItem.isSelected){
                binding.routineNameTextView.setBackgroundResource(R.drawable.select_item)
            }else{
                binding.routineNameTextView.setBackgroundResource(R.drawable.exercise_item)
            }
            binding.root.setOnClickListener {
                routineItem.isSelected = !routineItem.isSelected
                if(routineItem.isSelected){
                    binding.routineNameTextView.setBackgroundResource(R.drawable.select_item)
                }else{
                    binding.routineNameTextView.setBackgroundResource(R.drawable.exercise_item)
                }
                itemClicked(routineItem)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
    : ViewHolder = ViewHolder(MyRoutineItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    companion object{
        val diffUtil = object: DiffUtil.ItemCallback<RoutineItem>(){
            override fun areItemsTheSame(oldItem: RoutineItem, newItem: RoutineItem): Boolean {
                return oldItem.routineName == newItem.routineName
            }

            override fun areContentsTheSame(oldItem: RoutineItem, newItem: RoutineItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}