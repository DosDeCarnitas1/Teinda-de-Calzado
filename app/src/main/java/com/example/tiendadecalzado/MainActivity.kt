package com.example.tiendadecalzado

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Timer
import java.util.TimerTask

class MainActivity : AppCompatActivity() {

    private val correo = "viverosalejandro38@gmail.com"
    private val contraseña = "1234"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Verificar las credenciales una sola vez
        if (verificarCredenciales(correo, contraseña)) {
            // Redirigir a MenuActivity si las credenciales son correctas
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        } else {
            // Mostrar un mensaje si las credenciales no son válidas
            Toast.makeText(this, "Credenciales incorrectas. Por favor, intenta de nuevo.", Toast.LENGTH_LONG).show()
        }
    }

    // Método para verificar las credenciales
    private fun verificarCredenciales(correo: String, contraseña: String): Boolean {
        return correo == "viverosalejandro38@gmail.com" && contraseña == "1234"
    }
}