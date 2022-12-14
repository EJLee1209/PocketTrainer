package com.dldmswo1209.pockettrainer.adapter

import android.content.Context
import android.graphics.Typeface
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.dldmswo1209.pockettrainer.MainActivity
import com.dldmswo1209.pockettrainer.R
import com.dldmswo1209.pockettrainer.calendar.FurangCalendar
import com.dldmswo1209.pockettrainer.datas.ExerciseItem
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

// 높이를 구하는데 필요한 LinearLayout과 FurangCalender를 사용할 때 필요한 date를 받는다.
class CalendarAdapter(val context: Context, val calendarLayout: LinearLayoutCompat, val date: Date, val itemClicked: (String) ->(Unit)) :
    RecyclerView.Adapter<CalendarAdapter.CalendarItemHolder>() {

    private val TAG = javaClass.simpleName
    var dataList: ArrayList<Int> = arrayListOf()

    // FurangCalendar을 이용하여 날짜 리스트 세팅
    var furangCalendar: FurangCalendar = FurangCalendar(date)
    init {
        furangCalendar.initBaseCalendar()
        dataList = furangCalendar.dateList
    }

    override fun onBindViewHolder(holder: CalendarItemHolder, position: Int) {

        // list_item_calendar 높이 지정
        val h = calendarLayout.height / 6
        holder.itemView.layoutParams.height = h

        holder?.bind(dataList[position], position, context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarItemHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.calendar_item, parent, false)
        return CalendarItemHolder(view)
    }

    override fun getItemCount(): Int = dataList.size

    inner class CalendarItemHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {

        var itemCalendarDateText: TextView = itemView!!.findViewById(R.id.dayTextView)
        val circle = itemView!!.findViewById<ImageView>(R.id.calendarItemCircle)

        fun bind(data: Int, position: Int, context: Context) {
            val firstDateIndex = furangCalendar.prevTail
            val lastDateIndex = dataList.size - furangCalendar.nextHead - 1
            val month = String.format("%02d",date.month+1)
            val year = date.year+1900
            val scheduleDB = Firebase.database.reference
                .child("Users")
                .child((context as MainActivity).user_uid)
                .child("schedules")


            var yearMonth: String = SimpleDateFormat(
                context.getString(R.string.year_month_format),
                Locale.KOREA
            ).format(date)

            if(position in firstDateIndex..lastDateIndex) {
                val yearMonthDay = "${yearMonth}-${data}"
                scheduleDB.child(yearMonthDay).addChildEventListener(object: ChildEventListener{
                    override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                        val exercise = snapshot.getValue(ExerciseItem::class.java)?:return
                        circle.isVisible = true
                    }

                    override fun onChildChanged(
                        snapshot: DataSnapshot,
                        previousChildName: String?
                    ) {}
                    override fun onChildRemoved(snapshot: DataSnapshot) {
                        circle.isVisible = false
                    }
                    override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {}
                    override fun onCancelled(error: DatabaseError) {}
                })

            }

            // 날짜 표시
            itemCalendarDateText.setText(data.toString())

            // 오늘 날짜 처리
            var dateString: String = SimpleDateFormat("dd", Locale.KOREA).format(date)
            var dateInt = dateString.toInt()
            if (dataList[position] == dateInt) {
                itemCalendarDateText.setTypeface(itemCalendarDateText.typeface, Typeface.BOLD)
            }

//             현재 월의 1일 이전, 현재 월의 마지막일 이후 값의 텍스트를 회색처리
            if (position < firstDateIndex || position > lastDateIndex) {
                itemCalendarDateText.setTextAppearance(R.style.LightColorTextViewStyle)
            }
            else if (position == 0 || position%7==0 || (position+1)%7==0){
                itemCalendarDateText.setTextAppearance(R.style.weekendColor)
            }


            itemView.setOnClickListener {
                if(position in firstDateIndex..lastDateIndex) itemClicked("$year-$month-$data")
            }
        }

    }
}