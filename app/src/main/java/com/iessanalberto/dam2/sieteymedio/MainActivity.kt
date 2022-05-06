package com.iessanalberto.dam2.sieteymedio

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {


    private lateinit var btnCarta: Button
    private lateinit var btnPlantarse: Button
    private lateinit var btnReinicio: Button
    private lateinit var tvResulJug: TextView
    private lateinit var tvResulMaquina: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvResulJug = findViewById(R.id.tvResultado)
        tvResulMaquina = findViewById(R.id.tvMaquina)
        btnCarta = findViewById(R.id.btn1)
        btnPlantarse = findViewById(R.id.btn2)
        btnReinicio = findViewById(R.id.btn3)


        var totalJugador = 0.0
        var totalContrincante = 0.0

        val listaCartas: MutableList<Double> = mutableListOf()
        //Añadiendo las cartas normales en la baraja
        for (i: Int in 1..4)
            for (j: Int in 2..7)
                listaCartas.add(j.toDouble())

        //Añadiendo las Figuras a la baraja
        for (i: Int in 1..16)
            listaCartas.add(0.5)

        //Mezclar las cartas que hay en la lista
        listaCartas.shuffle()



        fun calcualarTotal() {
            //Cartas aleatorias de la lista
            var cartaSelJugador: Double = listaCartas.random()

            totalJugador += cartaSelJugador
            // totalContrincante += cartaSelMaquina

            tvResulJug.text = totalJugador.toString()

            val mensajeJugadorDerrota = "Has perdido\n Tu Resultado: $totalJugador"
            val mensajeMaquina = "Resultado de la Máquina: $totalContrincante"
            tvResulMaquina.text = mensajeMaquina

            //si el jugador se pasa del 7 y medio, la maquina gana
            if (totalJugador > 7.5) {
                tvResulMaquina.text = mensajeMaquina
                tvResulJug.text = mensajeJugadorDerrota
                btnCarta.isEnabled = false
                btnPlantarse.isEnabled = false
            }


            //se retiran la cartas que se han cogido de la baraja
            listaCartas.remove(cartaSelJugador)
        }

        //Solo se puede plantar una vez
        //Al plantarse, no sacas carta y la maquina saca carta en ese turno hasta ganar o pasarse
        fun plantarse() {
            btnPlantarse.isEnabled = false
            btnCarta.isEnabled = false


            //si la maquina aun no se ha pasado de 7 y medio seguira sacando cartas
            while (totalContrincante < 7.5) {
                //Cartas aleatorias de la lista
                var cartaSelMaquina: Double = listaCartas.random()

                totalContrincante += cartaSelMaquina

                val mensajeMaquina = "Resultado de la Máquina: $totalContrincante"
                val mensajeVictoria = "Has ganado!\n Tu Resultado: $totalJugador"
                val mensajeJugadorDerrota = "Has perdido\n Tu Resultado: $totalJugador"

                tvResulMaquina.text = mensajeMaquina

                //si la maquina se pasa, has ganado
                if (totalContrincante > 7.5) {
                    tvResulMaquina.text = mensajeMaquina
                    tvResulJug.text = mensajeVictoria
                    //si la maquina iguala el total maximo, la maquina gana
                } else if (totalContrincante == 7.5 && totalJugador == 7.5) {
                    tvResulMaquina.text = mensajeMaquina
                    tvResulJug.text = mensajeJugadorDerrota
                    //si la maquina supera el valor que has obtenido, mientras no sea 7 y medio, la maquina gana
                } else if (totalContrincante > totalJugador) {
                    tvResulMaquina.text = mensajeMaquina
                    tvResulJug.text = mensajeJugadorDerrota
                }

                //se retira la carta de la baraja
                listaCartas.remove(cartaSelMaquina)
            }
        }

        btnCarta.setOnClickListener {
            calcualarTotal()
        }

        btnPlantarse.setOnClickListener {
            plantarse()
        }

        //se reincia el juego
        btnReinicio.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            startActivity(intent)
        }
    }
}