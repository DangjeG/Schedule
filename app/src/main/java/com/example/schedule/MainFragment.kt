package com.example.schedule


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import com.example.schedule.databinding.FragmentMainBinding
import com.example.schedule.enums.WeekNumber
import com.example.schedule.screens.TodayViewFragment
import com.example.schedule.screens.WeekListFragment


class MainFragment : Fragment() {

    private var binding: FragmentMainBinding? = null
    private var storage: DataStorage = DataStorage()
    private var weekNumber: WeekNumber = WeekNumber.First

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        changeTab(R.id.nav_host)


        binding?.bottomAppBar?.setOnItemSelectedListener {
            changeTab(it.itemId)
            true
        }
        changeTab(R.id.navigation_day)
    }

    private fun changeTab(@IdRes id: Int) {
        val navHostId = binding?.navHost?.id
        navHostId ?: return
        val transaction = childFragmentManager.beginTransaction()
        when (id) {
            R.id.navigation_day ->
                transaction.replace(navHostId, TodayViewFragment.newInstance())

            R.id.navigation_week ->
                transaction.replace(navHostId, WeekListFragment.newInstance())
        }
        transaction.commit()
    }

    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }
}