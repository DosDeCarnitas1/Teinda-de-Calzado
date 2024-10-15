package com.example.tiendadecalzado

import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetallesActivity : AppCompatActivity() {

    private lateinit var imgProducto: ImageView
    private lateinit var txtInfo: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalles)

        imgProducto = findViewById(R.id.imgProducto)
        txtInfo = findViewById(R.id.txtDetalles)

        val imageResId = intent.getIntExtra("imageResId", -1)
        val productInfo = intent.getStringExtra("productInfo")

        if (imageResId != -1) {
            imgProducto.setImageResource(imageResId)
        } else {
            imgProducto.setImageResource(R.drawable.img)
        }

        txtInfo.text = productInfo ?: "Descripción no disponible."
    }
}