package com.example.tiendadecalzado

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class ArticulosActivity : AppCompatActivity() {

    private lateinit var img1: ImageButton
    private lateinit var img2: ImageButton
    private lateinit var img3: ImageButton
    private lateinit var img4: ImageButton
    private lateinit var img5: ImageButton
    private lateinit var img6: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_articulo)

        // Inicializa los ImageButton
        img1 = findViewById(R.id.img1)
        img2 = findViewById(R.id.img2)
        img3 = findViewById(R.id.img3)
        img4 = findViewById(R.id.img4)
        img5 = findViewById(R.id.img5)
        img6 = findViewById(R.id.img6)

        setupImageButtonListeners()
    }

    private fun setupImageButtonListeners() {
        img1.setOnClickListener {
            navigateToProductDetail(R.drawable.img_1, "Calcetines clásicos.\n" +
                    "Composición: 85% algodón, 10% nylon, 5% elastano.\n" +
                    "\n" +
                    "Precio: \$169.00")
        }
        img2.setOnClickListener {
            navigateToProductDetail(R.drawable.img_2, "Gorra Wild Cowboys.\n" +
                    "Gorra sin estructura rígida, diseño bordado.\n" +
                    "\n" +
                    "Precio: \$799.00")
        }
        img3.setOnClickListener {
            navigateToProductDetail(R.drawable.img_3, "Paliacate rojo.\n" +
                    "Tamaño: 60 cm x 60 cm, material charmeuse satinado.\n" +
                    "\n" +
                    "Precio: \$399.00")

        }
        img4.setOnClickListener {
            navigateToProductDetail(R.drawable.img_4, "Chamarra biker color negro\n" +
                    "Fabricada en piel de borrego pelibuey forro textil. \n" +
                    "Cinto ajustable de piel, botones y remaches de metal.\n" +
                    "\n" +
                    "Precio: \$3,999.00")
        }
        img5.setOnClickListener {
            navigateToProductDetail(R.drawable.img_5, "Pantalón Bestial Club.\n" +
                    "Fabricado 100% en mezclilla color azul claro.\n" +
                    "\n" +
                    "Precio: \$899.00")
        }
        img6.setOnClickListener {
            navigateToProductDetail(R.drawable.img_6, "Jersey Champagne \n" +
                    "En color beige/café de la nueva colección Bestial Club\n" +
                    "Tela de micropunto, lo que la convierte en un material transpirable, de secado rápido y suave.\n" +
                    "\n" +
                    "Precio: \$ 949.00")
        }
    }

    private fun navigateToProductDetail(imageResId: Int, productInfo: String) {
        val intent = Intent(this, DetallesActivity::class.java)
        intent.putExtra("imageResId", imageResId)
        intent.putExtra("productInfo", productInfo)
        startActivity(intent)
    }
}