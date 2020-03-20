package zamorano.miguel.newdigimind.ui.dashboard

import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlinx.android.synthetic.main.fragment_dashboard.view.*
import zamorano.miguel.newdigimind.R
import zamorano.miguel.newdigimind.Task
import zamorano.miguel.newdigimind.ui.home.HomeFragment
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
            ViewModelProviders.of(this).get(DashboardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
//        val textView: TextView = root.findViewById(R.id.text_dashboard)
//        dashboardViewModel.text.observe(this, Observer {
//            textView.text = it
//        })
        root.btn_time.setOnClickListener {
            val cal = Calendar.getInstance();
            val timeSetListener = TimePickerDialog.OnTimeSetListener{ timePicker, hour, minute ->
                cal.set(Calendar.HOUR_OF_DAY, hour)
                cal.set(Calendar.MINUTE, minute)

                btn_time.text = SimpleDateFormat("HH:mm").format(cal.time)

            }
            TimePickerDialog(root.context, timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE),true).show();
        }

        root.btn_save.setOnClickListener{
            var title = what_to_remember.text.toString()
            var time = btn_time.text.toString()

            var days = ArrayList<String>()

            if(cbMonday.isChecked)
                days.add("Monday")
            if(cbTuesday.isChecked)
                days.add("Tuesday")
            if(cbWednesday.isChecked)
                days.add("Wednesday")
            if(cbThursday.isChecked)
                days.add("Thursday")
            if(cbFriday.isChecked)
                days.add("Friday")
            if(cbSaturday.isChecked)
                days.add("Saturday")
            if(cbSunday.isChecked)
                days.add("Sunday")


            var task = Task(title,days,time)

            HomeFragment.tasks.add(task)

            Toast.makeText(root.context, "new task added", Toast.LENGTH_SHORT).show()

        }


        return root
    }
}