package com.dldmswo1209.pockettrainer.calendar

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.dldmswo1209.pockettrainer.MainActivity
import com.dldmswo1209.pockettrainer.R
import com.dldmswo1209.pockettrainer.adapter.CalendarAdapter
import com.dldmswo1209.pockettrainer.databinding.FragmentMonthBinding
import java.text.SimpleDateFormat
import java.util.*

class MonthFragment : Fragment(R.layout.fragment_month) {

    private lateinit var binding: FragmentMonthBinding

    private val TAG = javaClass.simpleName
    lateinit var mContext: Context
    var pageIndex = 0
    lateinit var currentDate: Date

    companion object {
        var instance: MonthFragment? = null
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MainActivity) {
            mContext = context
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        instance = this

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMonthBinding.bind(view)

        initView(view)
    }
    fun initView(view: View){
        pageIndex -= (Int.MAX_VALUE / 2)
        // 날짜 적용
        val date = Calendar.getInstance().run {
            add(Calendar.MONTH, pageIndex)
            time
        }
        currentDate = date
        Log.d("testt",currentDate.toString())
        // 포맷 적용
        var datetime: String = SimpleDateFormat(
            mContext.getString(R.string.calendar_year_month_format),
            Locale.KOREA
        ).format(date.time)
        binding.calendarYearMonthTextView.setText(datetime)
        val calendarLayout = view.findViewById<LinearLayoutCompat>(R.id.calendarLayout)
        val gridLayoutManager = GridLayoutManager(context, 7, LinearLayoutManager.VERTICAL, false)
        val calendarAdapter = CalendarAdapter(requireContext(), calendarLayout, currentDate) {
            Log.d("testt", it)
            (activity as MainActivity).calendarFragment.binding.dateTextView.text = it
        }

        binding.calendarView.adapter = calendarAdapter
        binding.calendarView.layoutManager = gridLayoutManager


    }

    override fun onDestroy() {
        super.onDestroy()
        instance = null
    }

}