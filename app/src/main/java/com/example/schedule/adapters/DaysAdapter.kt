package com.example.schedule.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.schedule.databinding.DayItemBinding
import com.example.schedule.models.Day


class DaysAdapter : RecyclerView.Adapter<DaysAdapter.DaysViewHolder>() {


    private val list: MutableList<Day> = mutableListOf()

    class DaysViewHolder(
        private val binding: DayItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        private var adapter: LessonsAdapter = LessonsAdapter()

        fun bind(item: Day) = with(binding) {
            weekDayView.text = item.getDay()
            infoView.text = item.getInfo()
            with(binding.lessonsView) {
                this ?: return
                layoutManager = LinearLayoutManager(context)
                adapter = this@DaysViewHolder.adapter
            }
            adapter.submitList(item.lessons)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DaysViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DayItemBinding.inflate(inflater, parent, false)
        return DaysViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: DaysViewHolder, position: Int) {
       holder.bind(list[position])
    }

    fun submitList(list: ArrayList<Day>) {
        this.list.clear()
        this.list.addAll(list)
    }
}