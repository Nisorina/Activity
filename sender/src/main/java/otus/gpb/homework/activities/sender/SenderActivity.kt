package otus.gpb.homework.activities.sender

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast

val payload = Payload(
    "Славные парни",
    "2016",
    "Что бывает, когда напарником брутального костолома становится субтильный лопух? Наемный охранник Джексон Хили и частный детектив Холланд Марч вынуждены работать в паре, чтобы распутать плевое дело о пропавшей девушке, которое оборачивается преступлением века. Смогут ли парни разгадать сложный ребус, если у каждого из них – свои, весьма индивидуальные методы."
)

class SenderActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sender)

        val mapsButton = findViewById<Button>(R.id.toMaps)
        val mailButton = findViewById<Button>(R.id.toMail)
        val receiverButton = findViewById<Button>(R.id.toReceiver)

        mapsButton.setOnClickListener { onMapsButtonClickListener() }
        mailButton.setOnClickListener { onMailButtonClickListener() }
        receiverButton.setOnClickListener { onReceiverButtonClickListener() }
    }

    private fun onMapsButtonClickListener() {
        val gmmIntentUri = Uri.parse("geo:0,0?q=рестораны")
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")
        try {
            startActivity(mapIntent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(this, "Не установлены Гугл карты", Toast.LENGTH_SHORT).show()
            Log.d("SENDER log", "toMaps: ActivityNotFoundException")
        }
    }

    private fun onMailButtonClickListener() {
        val uri = Uri.parse("mailto:android@otus.ru?subject=тема&body=Ну,%20привет")
        val intent = Intent(Intent.ACTION_SENDTO, uri)
        try {
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(this, "Не установлена почта", Toast.LENGTH_SHORT).show()
            Log.d("SENDER log", "toMail: ActivityNotFoundException")
        }
    }

    private fun onReceiverButtonClickListener() {
        val intent = Intent(Intent.ACTION_SEND).apply {
            putExtra("title", payload.title)
            putExtra("year", payload.year)
            putExtra("description", payload.description)
        }
        try {
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(this, "Не установлен ресивер", Toast.LENGTH_SHORT).show()
            Log.d("SENDER log", "toReceiver: ActivityNotFoundException")
        }
    }
}