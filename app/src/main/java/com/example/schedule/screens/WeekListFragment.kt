package com.example.schedule.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.schedule.R
import com.example.schedule.adapters.DaysAdapter
import com.example.schedule.databinding.FragmentWeekListBinding
import com.example.schedule.models.Week

class WeekListFragment : Fragment() {

    private var binding: FragmentWeekListBinding? = null
    private var adapter: DaysAdapter = DaysAdapter()
    private var week: Week? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            week = it.getParcelable("week")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding?.mainView) {
            this ?: return
            layoutManager = LinearLayoutManager(context)
            adapter = this@WeekListFragment.adapter
            addItemDecoration(createItemDecorator())
        }
        adapter.submitList(week!!.days)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWeekListBinding.inflate(inflater, container, false)
        return binding?.root
    }

    private fun createItemDecorator() =
        DividerItemDecoration(requireContext(), RecyclerView.VERTICAL).apply {
            ContextCompat.getDrawable(requireContext(), R.drawable.day_item_decorator)
                ?.let { this@apply.setDrawable(it) }
        }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance(week: Week): WeekListFragment {
            val args = Bundle()
            args.putParcelable("week", week);
            val fragment = WeekListFragment()
            fragment.arguments = args
            return fragment
        }
    }
}