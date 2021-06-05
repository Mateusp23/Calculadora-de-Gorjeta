package com.example.calculargorjeta

import android.os.Bundle
import android.widget.EditText
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {

    private var editValor: EditText? = null
    private var textPorcentagem: TextView? = null
    private var textGorjeta:TextView? = null
    private var textTotal:TextView? = null
    private var seekBarGorjeta: SeekBar? = null
    private var porcentagem = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editValor = findViewById(R.id.editValor)
        textPorcentagem = findViewById(R.id.textPorcentagem)
        textGorjeta = findViewById(R.id.textGorjeta)
        textTotal = findViewById(R.id.textTotal)
        seekBarGorjeta = findViewById(R.id.seekBarGorjeta)


        // adicionar listenar capturar o progresso
        seekBarGorjeta?.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                porcentagem = progress.toDouble()
                textPorcentagem?.text = porcentagem.roundToInt().toString() + " %"
                calcular()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })
    }

    fun calcular() {
        val valorRecuperado = editValor!!.text.toString()
        if (valorRecuperado == null || valorRecuperado == "") {
            Toast.makeText( // exibe mensagem na tela
                applicationContext,
                "Digite um valor para efetuar o calculo!!",
                Toast.LENGTH_LONG
            ).show()
        } else {
            // converter string para double
            val valorDigitado = valorRecuperado.toDouble()

            // calcular gorjeta total
            val gorjeta = valorDigitado * (porcentagem / 100)
            val total = gorjeta + valorDigitado

            //exibir gorjeta e total
            textGorjeta?.text = "R$ " + Math.round(gorjeta)
            //textGorjeta.setText( "R$ " + gorjeta);
            textTotal?.text = "R$ $total"
        }
    }
}