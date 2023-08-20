package com.example.workout

import android.graphics.Color
import android.renderscript.ScriptGroup.Binding
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.workout.databinding.SingleViewBinding

class ExerciseStatusAdapter(val list:ArrayList<model_exercise>):
    RecyclerView.Adapter<ExerciseStatusAdapter.ViewHolder>() {


    class ViewHolder(binding : SingleViewBinding) :
    RecyclerView.ViewHolder(binding.root)
    {
        val tv_item = binding.tvItem
        val Exercise_name = binding.ExerciseName
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

return ViewHolder(SingleViewBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
val model :model_exercise = list[position]
        holder.tv_item.text =( model.getid()).toString()
        holder.Exercise_name.text = (model.getexercise_name())

        when {
            model.getisSelected() -> {
                holder.tv_item.background =
                    ContextCompat.getDrawable(
                        holder.itemView.context,
                        R.drawable.item_circular_dark_green_accent_border
                    )
                holder.tv_item.setTextColor(Color.parseColor("#212121")) // Parse the color string, and return the corresponding color-int.
            }
            model.getiscompleted() -> {
                holder.tv_item.background =
                    ContextCompat.getDrawable(holder.itemView.context, R.drawable.item_circular_color_accent_border)
                holder.tv_item.setTextColor(Color.parseColor("#FFFFFF"))
            }
            else -> {
                holder.tv_item.background =
                    ContextCompat.getDrawable(holder.itemView.context, R.drawable.item_circular_color_gray_background)
                holder.tv_item.setTextColor(Color.parseColor("#212121"))
            }
        }

    }

    override fun getItemCount(): Int {
return list.size
    }
}


