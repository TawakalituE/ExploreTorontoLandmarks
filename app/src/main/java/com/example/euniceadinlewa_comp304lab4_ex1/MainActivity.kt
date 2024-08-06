package com.example.euniceadinlewa_comp304lab4_ex1

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        title = "euniceadinlewa_comp304lab4_ex1"

        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewLandmarkTypes)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val landmarkTypes = listOf("Old Buildings", "Museums", "Stadiums", "Attractions")
        val adapter = LandmarkTypeAdapter(landmarkTypes) { landmarkType ->
            val intent = Intent(this, LandmarksActivity::class.java)
            intent.putExtra("landmarkType", landmarkType)
            startActivity(intent)
        }
        recyclerView.adapter = adapter
    }
}
