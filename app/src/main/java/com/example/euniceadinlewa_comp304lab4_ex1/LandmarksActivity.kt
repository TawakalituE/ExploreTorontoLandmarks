package com.example.euniceadinlewa_comp304lab4_ex1

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class LandmarksActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landmarks)

        title = "euniceadinlewa_comp304lab4_ex1"

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val landmarkType = intent.getStringExtra("landmarkType")
        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewLandmarks)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val landmarks = getLandmarksByType(landmarkType)
        val adapter = LandmarkAdapter(landmarks) { landmark ->
            val intent = Intent(this, MapActivity::class.java)
            intent.putExtra("landmarkName", landmark.name)
            intent.putExtra("landmarkAddress", landmark.address)
            startActivity(intent)
        }
        recyclerView.adapter = adapter
    }

    private fun getLandmarksByType(type: String?): List<Landmark> {
        return when (type) {
            "Old Buildings" -> listOf(
                Landmark("Casa Loma", "1 Austin Terrace, Toronto, ON M5R 1X8"),
                Landmark("Fort York", "250 Fort York Blvd, Toronto, ON M5V 3K9"),
                Landmark("Spadina Museum", "285 Spadina Rd, Toronto, ON M5R 2V5"),
                Landmark("Campbell House Museum", "160 Queen St W, Toronto, ON M5H 3H3"),
                Landmark("Osgoode Hall", "130 Queen St W, Toronto, ON M5H 2N5")
            )
            "Museums" -> listOf(
                Landmark("Royal Ontario Museum", "100 Queens Park, Toronto, ON M5S 2C6"),
                Landmark("Art Gallery of Ontario", "317 Dundas St W, Toronto, ON M5T 1G4"),
                Landmark("Hockey Hall of Fame", "30 Yonge St, Toronto, ON M5E 1X8"),
                Landmark("Aga Khan Museum", "77 Wynford Dr, Toronto, ON M3C 1K1"),
                Landmark("Bata Shoe Museum", "327 Bloor St W, Toronto, ON M5S 1W7")
            )
            "Stadiums" -> listOf(
                Landmark("Rogers Centre", "1 Blue Jays Way, Toronto, ON M5V 1J1"),
                Landmark("Scotiabank Arena", "40 Bay St, Toronto, ON M5J 2X2"),
                Landmark("BMO Field", "170 Princes' Blvd, Toronto, ON M6K 3C3"),
                Landmark("Varsity Stadium", "299 Bloor St W, Toronto, ON M5S 1W2"),
                Landmark("Coca-Cola Coliseum", "45 Manitoba Dr, Toronto, ON M6K 3C3")
            )
            "Attractions" -> listOf(
                Landmark("CN Tower", "290 Bremner Blvd, Toronto, ON M5V 3L9"),
                Landmark("Ripley's Aquarium", "288 Bremner Blvd, Toronto, ON M5V 3L9"),
                Landmark("Toronto Islands", "Toronto Islands, Toronto, ON"),
                Landmark("Toronto Zoo", "2000 Meadowvale Rd, Toronto, ON M1B 5K7"),
                Landmark("Distillery District", "55 Mill St, Toronto, ON M5A 3C4")
            )
            else -> emptyList()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {

                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
