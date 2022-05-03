package com.iessanalberto.dam2.sieteymedio

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {


    private lateinit var btnCarta: Button
    private lateinit var tvResultado: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvResultado = findViewById(R.id.tvResultado)
        btnCarta = findViewById(R.id.btn1)


        var totalJugador: Double = 0.0
        var totalContrincante: Double = 0.0

        val listaCartas: MutableList<Double> = mutableListOf()
        //Añadiendo las cartas normales en la baraja
        for (i: Int in 1..4)
            for (j: Int in 2..7)
                listaCartas.add(j.toDouble())

        //Añadiendo las Figuras a la baraja
        for (i: Int in 1..16)
            listaCartas.add(0.5)

        fun calcualarTotal() {
            val mensaje = "Has perdido\n Tu Resultado: $totalJugador"

            val cartaSeleccionada = listaCartas.random()
            listaCartas.remove(cartaSeleccionada)
            totalJugador += cartaSeleccionada
            tvResultado.text = totalJugador.toString()
            if (totalJugador > 7.5) {
                tvResultado.text = mensaje
                btnCarta.isEnabled = false
            }
        }

        btnCarta.setOnClickListener {
            //Algunas comprobaciones de la lista
            //tvResultado.text = listaCartas.toString()
            //tvResultado.text = listaCartas.size.toString()

            calcualarTotal()
        }


    }
}