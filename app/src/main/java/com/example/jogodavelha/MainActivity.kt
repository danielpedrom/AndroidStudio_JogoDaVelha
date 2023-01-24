package com.example.jogodavelha

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    var isPlayer1 = true;
    var fimJogo = false;

    private lateinit var start_top: ImageView
    private lateinit var center_top: ImageView
    private lateinit var end_top: ImageView

    private lateinit var start_center: ImageView
    private lateinit var center_center: ImageView
    private lateinit var end_center: ImageView

    private lateinit var start_bottom: ImageView
    private lateinit var center_bottom: ImageView
    private lateinit var end_bottom: ImageView

    private lateinit var botao: Button

    private lateinit var texto: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        start_top = findViewById(R.id.start_top)
        center_top = findViewById(R.id.center_top)
        end_top = findViewById(R.id.end_top)

        start_center = findViewById(R.id.left_center)
        center_center = findViewById(R.id.center_center)
        end_center = findViewById(R.id.end_center)

        start_bottom = findViewById(R.id.start_bottom)
        center_bottom = findViewById(R.id.center_bottom)
        end_bottom = findViewById(R.id.end_bottom)

        botao = findViewById(R.id.Botao)

        texto = findViewById(R.id.painelVencedor)

        botao.setOnClickListener{
            resetarJogo(start_top);
            resetarJogo(center_top);
            resetarJogo(end_top);
            resetarJogo(start_center);
            resetarJogo(center_center);
            resetarJogo(end_center);
            resetarJogo(start_bottom);
            resetarJogo(center_bottom);
            resetarJogo(end_bottom);

            texto.setText("")
        }

        configureBox(start_top);
        configureBox(center_top);
        configureBox(end_top);
        configureBox(start_center);
        configureBox(center_center);
        configureBox(end_center);
        configureBox(start_bottom);
        configureBox(center_bottom);
        configureBox(end_bottom);

    }

    private fun resetarJogo(box: ImageView){
        box.setImageDrawable(null)
        box.tag = null;
        fimJogo = false
        isPlayer1 = true
    }

    private fun configureBox(box: ImageView){
        box.setOnClickListener {
            if(box.tag == null && !fimJogo){
                if(isPlayer1){
                    box.setImageResource(R.drawable.baseline_close_24)
                    isPlayer1 = false
                    box.tag = 1
                }else{
                    box.setImageResource(R.drawable.baseline_remove_circle_outline_24)
                    isPlayer1 = true
                    box.tag = 2
                }
                if(playerWin(1)){
                    texto.setText("Jogador 1 Venceu")
                    fimJogo = true

                }else if(playerWin(2)){
                    texto.setText("Jogador 2 Venceu")
                    fimJogo = true
                }
            }
        }
    }

    private fun playerWin(value: Int): Boolean{
        if ((start_top.tag == value && center_top.tag == value && end_top.tag == value) ||
            (start_center.tag == value && center_center.tag == value && end_center.tag == value) ||
            (start_bottom.tag == value && center_bottom.tag == value && end_bottom.tag == value) ||

            (start_top.tag == value && start_center.tag == value && start_bottom.tag == value) ||
            (center_top.tag == value && center_center.tag == value && center_bottom.tag == value) ||
            (end_top.tag == value && end_center.tag == value && end_bottom.tag == value) ||

            (start_top.tag == value && center_center.tag == value && end_bottom.tag == value) ||
            (end_top.tag == value && center_center.tag == value && start_bottom.tag == value)
        ){
           return true
        }
        return false
    }
}