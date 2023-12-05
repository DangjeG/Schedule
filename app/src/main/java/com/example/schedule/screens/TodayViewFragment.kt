package com.example.schedule.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.schedule.DataStorage
import com.example.schedule.R
import com.example.schedule.adapters.LessonsAdapter
import com.example.schedule.databinding.FragmentTodayViewBinding
import com.example.schedule.enums.WeekNumber
import com.example.schedule.models.Day

class TodayViewFragment : Fragment() {

    private var binding: FragmentTodayViewBinding? = null
    private var adapter: LessonsAdapter = LessonsAdapter()
    private var storage: DataStorage = DataStorage()
    private var day :Day = storage.getDay(WeekNumber.First)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            this ?: return
            this.weekDayView.text = day.getDay()
            this.infoView.text = day.getInfo()
            this.groupView.text = "ПрИ-302"
            this.weekMenu.setOnMenuItemClickListener {
                changeWeak(it.itemId)
                true
            }
        }
        with(binding?.lessonsView) {
            this ?: return
            layoutManager = LinearLayoutManager(context)
            adapter = this@TodayViewFragment.adapter
        }
        adapter.submitList(day.lessons)
    }

    private fun changeWeak(@IdRes id: Int){
        when(id) {
            R.id.first_week ->
                day = storage.getDay(WeekNumber.First)
            R.id.second_week ->
                day = storage.getDay(WeekNumber.Second)
        }
        refresh()
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTodayViewBinding.inflate(inflater, container, false)
        return binding?.root
    }

    private fun refresh(){
        with(binding) {
            this ?: return
            this.weekDayView.text = day.getDay()
            this.infoView.text = day.getInfo()
            this.groupView.text = "ПрИ-302"
            adapter.submitList(day.lessons)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance(): TodayViewFragment = TodayViewFragment()
    }

}