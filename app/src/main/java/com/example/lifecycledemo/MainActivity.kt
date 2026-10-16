package com.example.lifecycledemo

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private val TAG = "KucingKocak"

    private lateinit var tvMascot: TextView
    private lateinit var tvSpeech: TextView
    private lateinit var tvScore: TextView
    private lateinit var tvRage: TextView
    private lateinit var tvLifecycleStatus: TextView
    private lateinit var btnSeblak: Button
    private lateinit var btnPinjam: Button

    private lateinit var tvName: TextView
    private lateinit var tvRole: TextView
    private lateinit var etUsername: EditText
    private lateinit var etTech: EditText
    private lateinit var btnReset: Button
    private lateinit var btnSave: Button

    private var clickCount = 0
    private var rageLevel = 0

    private val funnyQuotes = listOf(
        "Aduh! Lara woiii, dikira tombol klakson apa kepimen?! 😿",
        "Pencet terus pencet terus sampe, sampe layar HP-mu jebol! 😹",
        "Ingat kata pepatah: Pinjam dulu seratus! 💸",
        "Cita-citaku jadi Kucing Bener, malah dadi bahan praktikum 😭",
        "Dosen killer lewat! Ngumpet ndisit 🙈",
        "Ngapain pencet-pencet? Kaya wis kenal sue bae!!! 😎"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvMascot = findViewById(R.id.tv_mascot)
        tvSpeech = findViewById(R.id.tv_speech)
        tvScore = findViewById(R.id.tv_score)
        tvRage = findViewById(R.id.tv_rage)
        tvLifecycleStatus = findViewById(R.id.tv_lifecycle_status)
        btnSeblak = findViewById(R.id.btn_seblak)
        btnPinjam = findViewById(R.id.btn_pinjam)

        tvName = findViewById(R.id.tv_name)
        tvRole = findViewById(R.id.tv_role)
        etUsername = findViewById(R.id.et_username)
        etTech = findViewById(R.id.et_tech)
        btnReset = findViewById(R.id.btn_reset)
        btnSave = findViewById(R.id.btn_save)

        // Event saat Maskot diketuk
        tvMascot.setOnClickListener {
            handleTapMascot()
        }

        // Event Tombol Seblak
        btnSeblak.setOnClickListener {
            feedSeblak()
        }

        // Event Tombol Pinjam 100
        btnPinjam.setOnClickListener {
            pinjamSeratus()
        }

        // Event Tombol Simpan
        btnSave.setOnClickListener {
            val name = etUsername.text.toString().trim()
            val tech = etTech.text.toString().trim()
            if (name.isNotEmpty()) {
                tvName.text = name
            }
            if (tech.isNotEmpty()) {
                tvRole.text = tech
            }
            Toast.makeText(this, "Profil Diperbarui!", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }

        // Event Tombol Reset
        btnReset.setOnClickListener {
            etUsername.text.clear()
            etTech.text.clear()
            Toast.makeText(this, "Input Direset!", Toast.LENGTH_SHORT).show()
        }

        Log.d(TAG, "=== onCreate: Kucing Kocak Siap Beraksi ===")
    }

    private fun handleTapMascot() {
        clickCount++
        rageLevel++

        vibratePhone(40)

        if (rageLevel >= 8) {
            // MODE NGAMUK (Rage Mode)
            tvMascot.text = "😾🔥"
            tvSpeech.text = "STOOOOOP WOIII! NYONG NGAMBEK KEH!! 🤬💥"
            tvRage.text = "🔥 Emosi: HYPER RAGE!"

            // Animasi Shake Parah
            tvMascot.animate().rotationBy(360f).scaleX(1.4f).scaleY(1.4f).setDuration(200).withEndAction {
                tvMascot.animate().scaleX(1.0f).scaleY(1.0f).setDuration(200).start()
            }.start()

            vibratePhone(250)
            rageLevel = 0
        } else {
            // Mode Biasa
            val randomQuote = funnyQuotes[Random.nextInt(funnyQuotes.size)]
            tvSpeech.text = randomQuote
            tvMascot.text = if (clickCount % 2 == 0) "😻" else "😹"
            tvRage.text = "🔥 Emosi: Level $rageLevel"

            // Animasi Membal (Bounce)
            tvMascot.animate().scaleX(1.2f).scaleY(1.2f).setDuration(100).withEndAction {
                tvMascot.animate().scaleX(1.0f).scaleY(1.0f).setDuration(100).start()
            }.start()
        }

        updateUI()
    }

    private fun feedSeblak() {
        clickCount += 5
        rageLevel = 0
        tvMascot.text = "🙀🌶️"
        tvSpeech.text = "HUAHH! PEDES NEMENNNN SEBLAKNYA!! 🍲🔥"
        tvRage.text = "🔥 Emosi: Kepedesan"

        vibratePhone(150)

        // Animasi Muter 360 Derajat
        tvMascot.animate().rotationBy(720f).setDuration(500).start()
        updateUI()
    }

    private fun pinjamSeratus() {
        tvMascot.text = "😿💸"
        tvSpeech.text = "langka duit cash cuy, transfer lewat QRIS bae pimen? 💸"
        vibratePhone(80)

        // Animasi Geser Kiri Kanan
        tvMascot.animate().translationX(30f).setDuration(50).withEndAction {
            tvMascot.animate().translationX(-30f).setDuration(50).withEndAction {
                tvMascot.animate().translationX(0f).setDuration(50).start()
            }.start()
        }.start()

        updateUI()
    }

    private fun updateUI() {
        tvScore.text = "❤️ Klik: $clickCount"
    }

    // Fungsi getar aman dari crash (Catch Exception)
    private fun vibratePhone(durationMs: Long) {
        try {
            val vibrator = getSystemService(VIBRATOR_SERVICE) as Vibrator?
            if (vibrator != null && vibrator.hasVibrator()) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    vibrator.vibrate(VibrationEffect.createOneShot(durationMs, VibrationEffect.DEFAULT_AMPLITUDE))
                } else {
                    @Suppress("DEPRECATION")
                    vibrator.vibrate(durationMs)
                }
            }
        } catch (e: Exception) {
            Log.e(TAG, "Getar diabaikan: ${e.message}")
        }
    }

    // --- INTEGRASI LIFECYCLE (Poin Praktikum) ---

    override fun onPause() {
        super.onPause()
        // Saat aplikasi ditinggal / ditutup sementara
        tvMascot.text = "😴💤"
        tvSpeech.text = "Malah ditinggal... Ya wis yong pan tuuru 💤"
        tvLifecycleStatus.text = "State: onPause (Kucing Pingsan)"
        Log.d(TAG, "onPause: Kucing tidur/pingsan")
    }

    override fun onResume() {
        super.onResume()
        // Saat aplikasi dibuka kembali
        if (clickCount > 0) {
            tvMascot.text = "😼"
            tvSpeech.text = "Akhirnya balik lagi! Jangan lupa pencet aku lagi ya 😸"
        }
        tvLifecycleStatus.text = "State: Active (onResume)"
        Log.d(TAG, "onResume: Kucing bangun kembali")
    }
}
