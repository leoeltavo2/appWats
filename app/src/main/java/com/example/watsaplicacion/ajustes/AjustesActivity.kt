package com.example.watsaplicacion.ajustes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.watsaplicacion.R
import com.example.watsaplicacion.perfil.PerfilActivity
import kotlinx.android.synthetic.main.activity_ajustes.*

class AjustesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ajustes)

        ajustesPerfil.setOnClickListener { perfil() }
    }
    private fun perfil(){
        startActivity(Intent(this@AjustesActivity, PerfilActivity::class.java))
    }
}