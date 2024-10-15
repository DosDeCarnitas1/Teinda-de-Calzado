package com.example.tiendadecalzado

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val toolbar: Toolbar = findViewById(R.id.barra)
        setSupportActionBar(toolbar)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_overflow, menu)
        return true
        //return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val intent: Intent?
        when (item.itemId){
            R.id.itmNosotros -> {
                intent = Intent(applicationContext, NosotrosActivity::class.java)
                startActivity(intent)
            }
            R.id.itmArticulos -> {
                intent = Intent(applicationContext, ArticulosActivity::class.java)
                startActivity(intent)
            }
            R.id.itmFormulario -> {
                intent = Intent(applicationContext, FormularioActivity::class.java)
                startActivity(intent)
            }
            R.id.itmCerrar -> {
                cerrarSesion()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun cerrarSesion() {
        val preferences: SharedPreferences = getSharedPreferences("preferenciasusuario", MODE_PRIVATE)
        val editor: SharedPreferences.Editor = preferences.edit()

        editor.clear()
        editor.apply()

        val intent = Intent(applicationContext, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP; Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }
}