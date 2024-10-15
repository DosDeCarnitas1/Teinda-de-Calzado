package com.example.tiendadecalzado

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat

class FormularioActivity : AppCompatActivity() {

    private lateinit var nombre: EditText
    private lateinit var domicilio: EditText
    private lateinit var producto: Spinner
    private lateinit var prodSel: String
    private lateinit var telefono: EditText
    private var articulo: Articulo = Articulo()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario)

        nombre = findViewById(R.id.edtNombre)
        domicilio = findViewById(R.id.edtDomicilio)
        producto = findViewById(R.id.spnArticulo)
        telefono = findViewById(R.id.edtTelefono)

        val opciones = resources.getStringArray(R.array.articulo)
        val adaptador = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, opciones)
        producto.adapter = adaptador
        prodSel = opciones[0]
        producto.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                prodSel = opciones[p2]
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                // No hace nada
            }
        }
    }

    fun onClick(view: View?) {
        when (view?.id) {
            R.id.btnRegistrar -> pedido()
            R.id.btnCancelar -> cancelar()
        }
    }

    private fun pedido() {
        if (nombre.text.isNotEmpty() && domicilio.text.isNotEmpty() && telefono.text.isNotEmpty()) {
            // Configura los detalles del pedido
            articulo.nombre = nombre.text.toString()
            articulo.domicilio = domicilio.text.toString()
            articulo.telefono = telefono.text.toString()
            articulo.producto = prodSel

            // Muestra la notificación
            mostrarNotificacion(articulo.producto)
        } else {
            Toast.makeText(this, "Por favor, completa todos los campos.", Toast.LENGTH_LONG).show()
        }
    }

    private fun cancelar() {
        val intent = Intent(this, NosotrosActivity::class.java)
        startActivity(intent)
    }

    private fun mostrarNotificacion(ropaSeleccionada: String) {
        // Variables para imagen y descripción del producto seleccionado
        val imageResId: Int
        val productDescription: String

        // Asignar imagen y descripción basadas en el producto seleccionado
        when (ropaSeleccionada) {
            "Calcetines clásicos" -> {
                imageResId = R.drawable.img_1
                productDescription = "Calcetines clásicos.\nComposición: 85% algodón, 10% nylon, 5% elastano.\nPrecio: \$169.00"
            }
            "Gorra Wild Cowboys" -> {
                imageResId = R.drawable.img_2
                productDescription = "Gorra Wild Cowboys.\nGorra sin estructura rígida, diseño bordado.\nPrecio: \$799.00"
            }
            "Paliacate rojo" -> {
                imageResId = R.drawable.img_3
                productDescription = "Paliacate rojo.\nTamaño: 60 cm x 60 cm, material charmeuse satinado.\nPrecio: \$399.00"
            }
            "Chamarra biker" -> {
                imageResId = R.drawable.img_4
                productDescription = "Chamarra biker color negro.\nFabricada en piel de borrego pelibuey forro textil.\nPrecio: \$3,999.00"
            }
            "Pantalón Bestial Club" -> {
                imageResId = R.drawable.img_5
                productDescription = "Pantalón Bestial Club.\nFabricado 100% en mezclilla color azul claro.\nPrecio: \$899.00"
            }
            "Jersey Champagne" -> {
                imageResId = R.drawable.img_6
                productDescription = "Jersey Champagne.\nEn color beige/café de la nueva colección Bestial Club.\nPrecio: \$949.00"
            }
            else -> {
                imageResId = R.drawable.img // Imagen por defecto
                productDescription = "Descripción no disponible"
            }
        }

        // Crear un Intent para abrir DetallesActivity al aceptar
        val intentAceptar = Intent(this, DetallesActivity::class.java).apply {
            putExtra("imageResId", imageResId)
            putExtra("productInfo", productDescription)
        }

        // Crear un PendingIntent para aceptar
        val pendingIntentAceptar = PendingIntent.getActivity(
            this, 0, intentAceptar, PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        // Crear un Intent para abrir NosotrosActivity al rechazar
        val intentRechazar = Intent(this, NosotrosActivity::class.java)

        // Crear un PendingIntent para rechazar
        val pendingIntentRechazar = PendingIntent.getActivity(
            this, 1, intentRechazar, PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        // Crear el canal de notificación
        val channelId = "tu_id_de_canal"
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId, "Canal de Notificación", NotificationManager.IMPORTANCE_DEFAULT)
            notificationManager.createNotificationChannel(channel)
        }

        // Crear la notificación
        val notification = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.ic_notification) // Cambia esto por tu icono
            .setContentTitle("Formulario Enviado")
            .setContentText("Haz llenado el formulario correctamente, ¿Deseas realizar el pedido?")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .addAction(R.drawable.ic_accept, "Aceptar", pendingIntentAceptar) // Acción para aceptar
            .addAction(R.drawable.ic_cancel, "Cancelar", pendingIntentRechazar) // Acción para rechazar
            .setAutoCancel(true) // Cancelar la notificación al tocarla
            .build()

        // Enviar la notificación
        notificationManager.notify(1, notification)
    }
}