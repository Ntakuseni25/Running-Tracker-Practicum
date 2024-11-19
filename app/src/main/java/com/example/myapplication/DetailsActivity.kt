
// ST10462437  Ntakuseni Vilankulu

package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

   lateinit var tvDetails : TextView
   lateinit var btnBack : Button
class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_details)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }

        // Initialize Views
        tvDetails = findViewById(R.id.tvDetails)
        btnBack = findViewById(R.id.btnBack)

        // Handle window insets (Edge-to-Edge UI)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Retrieve run data from intent
        val runData = intent.getSerializableExtra("runData") as? ArrayList<Pair<String, Pair<Double, Double>>>

        // Format the run data into a readable format
        val detailsText = runData?.joinToString(separator = "\n\n") { (day, distances) ->
            """
            Day: $day
            Morning: ${distances.first} km
            Afternoon: ${distances.second} km
            """.trimIndent()
        } ?: "No data available"

        // Set the formatted details into the TextView
        tvDetails.text = detailsText

        // Set up Back button click listener
        btnBack.setOnClickListener {
            finish() // Closes the current activity and returns to the previous one
        }
    }
}

