package com.example.schedule.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.schedule.adapters.LessonsAdapter
import com.example.schedule.databinding.FragmentTodayViewBinding
import com.example.schedule.models.Day

class TodayViewFragment : Fragment() {

    private var binding: FragmentTodayViewBinding? = null
    private var adapter: LessonsAdapter = LessonsAdapter()
    private var day :Day? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            day = it.getParcelable("day")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){
            this?.dayView?.text = day?.getDay()
            this?.infoView?.text = day?.getInfo()

        }
        with(binding?.lessonsView) {
            this ?: return
            layoutManager = LinearLayoutManager(context)
            adapter = this@TodayViewFragment.adapter
        }
        adapter.submitList(day!!.lessons)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTodayViewBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance(day: Day): TodayViewFragment {
            val args = Bundle()
            args.putParcelable("day", day);
            val fragment = TodayViewFragment()
            fragment.arguments = args
            return fragment
        }
    }

}