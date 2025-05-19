package com.example.uservip


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.uservip.UserVipApplication.Companion.prefs

class MainActivity : AppCompatActivity() {

    private lateinit var btnContinue: Button
    private lateinit var etName: EditText
    private lateinit var cbVip: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initUI()
        checkUserValues()
    }
    fun checkUserValues(){
        if(prefs.getName().isNotEmpty()){
            goToDetail()
        }

    }

    private fun initUI() {
        btnContinue = findViewById(R.id.btnContinue)
        etName = findViewById(R.id.etName)
        cbVip = findViewById(R.id.cbVip)

        btnContinue.setOnClickListener {
            accessToDetail()
        }
    }

    private fun accessToDetail() {
        if (etName.text.toString().isNotEmpty()) {
            prefs.saveName(etName.text.toString())
            prefs.saveVIP(cbVip.isChecked)
            goToDetail()
        } else {
            // Hacer alguna acción si el campo está vacío
        }
    }
    fun goToDetail(){
        startActivity(Intent(this, ResultActivity::class.java))
    }
}