package com.example.uservip

import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import com.example.uservip.R
import com.example.uservip.UserVipApplication.Companion.prefs

class ResultActivity : AppCompatActivity() {
    // …

    private lateinit var container: LinearLayout
    private lateinit var btnBack: Button
    private lateinit var tvName: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContentView(R.layout.activity_result)

        // Inicialización de vistas
        container = findViewById(R.id.container)
        btnBack = findViewById(R.id.btnBack)  // Asegúrate que btnBack existe en el XML
        tvName = findViewById(R.id.tvName)

        initUI()

        // Aplicando márgenes para las barras del sistema
        ViewCompat.setOnApplyWindowInsetsListener(container) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun initUI() {
        val userName = prefs.getName()  // Asegúrate de que 'prefs' tiene un valor válido
        if (userName.isNullOrEmpty()) {
            tvName.text = "Bienvenido invitado"
        } else {
            tvName.text = "Bienvenido $userName"
        }

        if (prefs.getVIP()) {
            setVIPColorBackground()
        }

        // Acción del botón para cerrar sesión
        btnBack.setOnClickListener {
            prefs.wipe()  // Limpia preferencias
            finish()  // Finaliza la actividad
        }
    }

    private fun setVIPColorBackground() {
        // Asegúrate que el color Vip_yellow esté definido en 'colors.xml'
        container.setBackgroundColor(ContextCompat.getColor(this, R.color.Vip_yellow))
    }
}
