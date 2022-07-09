package com.example.watsaplicacion.inicio

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.watsaplicacion.R
import com.example.watsaplicacion.login.TelefonoLogActivity
import kotlinx.android.synthetic.main.activity_inicio.*


class InicioActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio)

        btnInicioContinuar.setOnClickListener {
            startActivity(Intent(this@InicioActivity, TelefonoLogActivity::class.java)
            )
        }
    }
}
