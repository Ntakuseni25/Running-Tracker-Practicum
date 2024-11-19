
// ST10462437  Ntakuseni Vilankulu
package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

lateinit var btnAdd : Button
lateinit var btnCalculate : Button
lateinit var btnDetails : Button
lateinit var btnClear : Button
lateinit var etDay : EditText
lateinit var etMorning : EditText
lateinit var etAfternoon : EditText
lateinit var tvResult : TextView

class MainActivity : AppCompatActivity() {
    private val runData = mutableListOf<Pair<String, Pair<Double, Double>>>() // Day, (Morning, Afternoon)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnAdd = findViewById<Button>(R.id.btnAdd)
        val btnCalculate = findViewById<Button>(R.id.btnCalculate)
        val btnDetails = findViewById<Button>(R.id.btnDetails)
        val btnClear = findViewById<Button>(R.id.btnClear)

        val etDay = findViewById<EditText>(R.id.etDay)
        val etMorning = findViewById<EditText>(R.id.etMorning)
        val etAfternoon = findViewById<EditText>(R.id.etAfternoon)
        val tvResult = findViewById<TextView>(R.id.tvResult)

        // Add data
        btnAdd.setOnClickListener {
            val day = etDay.text.toString().trim()
            val morning = etMorning.text.toString().toDoubleOrNull() ?: 0.0
            val afternoon = etAfternoon.text.toString().toDoubleOrNull() ?: 0.0

            if (day.isNotEmpty()) {
                runData.add(day to (morning to afternoon))
                Toast.makeText(this, "Data added for $day", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Please enter a valid day", Toast.LENGTH_SHORT).show()
            }
        }

        // Calculate weekly average
        btnCalculate.setOnClickListener {
            if (runData.isNotEmpty()) {
                val average = runData.sumOf { it.second.first + it.second.second } / runData.size
                tvResult.text = "Weekly Average: ${"%.2f".format(average)} km"
            } else {
                Toast.makeText(this, "No data to calculate", Toast.LENGTH_SHORT).show()
            }
        }

        // Navigate to details screen
        btnDetails.setOnClickListener {
            val intent = Intent(this, DetailsActivity::class.java)
            intent.putExtra("runData", ArrayList(runData)) // Convert to ArrayList
            startActivity(intent)
        }

        // Clear data
        btnClear.setOnClickListener {
            runData.clear()
            tvResult.text = ""
            Toast.makeText(this, "Data cleared", Toast.LENGTH_SHORT).show()
        }
    }
}