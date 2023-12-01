package com.example.schedule.adapters

import android.icu.text.SimpleDateFormat
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.schedule.databinding.LessonItemBinding
import com.example.schedule.models.Lesson

class LessonsAdapter : RecyclerView.Adapter<LessonsAdapter.LessonsViewHolder>() {

    private val list : MutableList<Lesson> = mutableListOf()

    class LessonsViewHolder(
        private val binding: LessonItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Lesson) = with(binding) {
            var formatter: SimpleDateFormat = SimpleDateFormat("HH:mm")
            startView.text = formatter.format(item.start)
            endView.text = formatter.format(item.end)
            titleView.text = item.title
            lectureHallView.text = item.lectureHall
            teacherView.text = item.teacher
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = LessonItemBinding.inflate(inflater, parent, false)
        return LessonsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LessonsViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun submitList(list: ArrayList<Lesson>) {
        this.list.clear()
        this.list.addAll(list)
    }
}