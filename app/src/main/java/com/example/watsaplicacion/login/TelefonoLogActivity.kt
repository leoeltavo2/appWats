package com.example.watsaplicacion.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.watsaplicacion.MainActivity
import com.example.watsaplicacion.R
import kotlinx.android.synthetic.main.activity_telefono_log.*


class TelefonoLogActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_telefono_log)



        btnSiguienteLog.setOnClickListener{
            startActivity(Intent(this@TelefonoLogActivity, MainActivity::class.java))
        }

    }
}
