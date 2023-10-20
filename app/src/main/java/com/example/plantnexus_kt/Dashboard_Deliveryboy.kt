package com.example.plantnexus_kt

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.ValueFormatter

class Dashboard_Deliveryboy : AppCompatActivity() {
    private lateinit var usernamedelivery:TextView
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard_deliveryboy)

        init()

        val pieChart = findViewById<PieChart>(R.id.pieChart)

        // Create a list of PieEntry objects to represent data points.
        val entries = ArrayList<PieEntry>()
        entries.add(PieEntry(2f, "Rejected"))
        entries.add(PieEntry(3f, "On Time"))
        entries.add(PieEntry(1f, "Late"))

        val labelColors = intArrayOf(
            resources.getColor(R.color.black), // Color for "Rejected"
            resources.getColor(R.color.black), // Color for "On Time"
            resources.getColor(R.color.black)  // Color for "Late"
        )

        val dataSet = PieDataSet(entries, "Deliveries")
        dataSet.colors = labelColors.toList()
        dataSet.colors = listOf(resources.getColor(R.color.c1), resources.getColor(R.color.c2), resources.getColor(R.color.c3))

        // Create a PieData object and set the dataSet to it.
        val pieData = PieData(dataSet)

        pieData.setValueTextSize(16f) // Set the desired font size (16dp in this example)
        pieData.setValueFormatter(object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                return value.toInt().toString() // Format values as integers
            }
        })

        // Set additional chart settings as needed.
        pieChart.data = pieData
        pieChart.description.isEnabled = false // Hide description
        pieChart.setUsePercentValues(false) // Display values as percentages
    }

    private fun init(){
        usernamedelivery = findViewById(R.id.deliveryname)
    }
}