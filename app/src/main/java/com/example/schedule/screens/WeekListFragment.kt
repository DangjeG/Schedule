package com.example.schedule.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.schedule.DataStorage
import com.example.schedule.R
import com.example.schedule.adapters.DaysAdapter
import com.example.schedule.databinding.FragmentWeekListBinding
import com.example.schedule.enums.WeekNumber
import com.example.schedule.models.Week

class WeekListFragment : Fragment() {

    private var binding: FragmentWeekListBinding? = null
    private var adapter: DaysAdapter = DaysAdapter()
    private var number: WeekNumber = WeekNumber.First
    private var storage: DataStorage = DataStorage()
    private var week: Week = storage.getWeek(number)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            this ?: return
            this.weekNumView.text = if (number == WeekNumber.First) "Первая неделя" else "Вторая неделя"
            this.groupView.text = "ПрИ-302"
            this.weekMenu.setOnMenuItemClickListener {
                changeWeak(it.itemId)
                true
            }
        }
        with(binding?.mainView) {
            this ?: return
            layoutManager = LinearLayoutManager(context)
            adapter = this@WeekListFragment.adapter
            addItemDecoration(createItemDecorator())
        }
        adapter.submitList(week.days)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWeekListBinding.inflate(inflater, container, false)
        return binding?.root
    }

    private fun changeWeak(@IdRes id: Int){
        when(id) {
            R.id.first_week ->
                number = WeekNumber.First
            R.id.second_week ->
                number = WeekNumber.Second
        }
        refresh()
    }
    private fun createItemDecorator() =
        DividerItemDecoration(requireContext(), RecyclerView.VERTICAL).apply {
            ContextCompat.getDrawable(requireContext(), R.drawable.day_item_decorator)
                ?.let { this@apply.setDrawable(it) }
        }

    private fun refresh(){
        week = storage.getWeek(number)
        with(binding) {
            this ?: return
            this.weekNumView.text = if (number == WeekNumber.First) "Первая неделя" else "Вторая неделя"
            adapter.submitList(week.days)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance(): WeekListFragment = WeekListFragment()
    }
}