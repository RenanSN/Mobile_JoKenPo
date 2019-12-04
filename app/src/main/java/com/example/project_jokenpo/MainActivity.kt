package com.example.project_jokenpo

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private var numeroAleatorio: Random? = null

    private var contDerrota = 0
    private var contEmpate = 0
    private var contVitoria = 0

    private val PEDRA = 1
    private val PAPEL = 2
    private val TESOURA = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        numeroAleatorio = Random()

        btPedra.setOnClickListener{
            ivJogadaPlayer!!.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.img_pedra))
            realizarJogada(PEDRA)
        }

        btPapel.setOnClickListener{
            ivJogadaPlayer!!.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.img_papel))
            realizarJogada(PAPEL)
        }

        btTesoura.setOnClickListener{
            ivJogadaPlayer!!.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.img_tesoura))
            realizarJogada(TESOURA)
        }
    }
    private fun realizarJogada(jogadaPlayer:Int){

        val player = MediaPlayer.create(this,R.raw.som_jokenpo)

        player.start()

        val jogadaPC = numeroAleatorio!!.nextInt(3) + 1

        when(jogadaPC){
            PEDRA ->{
                ivJogadaPC!!.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.img_pedra))
                when(jogadaPlayer){
                    PAPEL -> venceu()
                    PEDRA -> empatou()
                    TESOURA -> perdeu()
                }
            }

            PAPEL ->{
                ivJogadaPC!!.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.img_papel))
                when(jogadaPlayer){
                    PAPEL -> empatou()
                    PEDRA -> perdeu()
                    TESOURA -> venceu()
                }
            }

            TESOURA ->{
                ivJogadaPC!!.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.img_tesoura))
                when(jogadaPlayer){
                    PAPEL -> perdeu()
                    PEDRA -> venceu()
                    TESOURA -> empatou()
                }
            }
        }
    }

    private fun venceu(){
        tvResultado!!.text = getString(R.string.venceu)
        tvResultado!!.setTextColor(ContextCompat.getColor(this, R.color.vitoria))
        contVitoria++;
        tvVitoria!!.text = getString(R.string.vitoria) + contVitoria
    }

    private fun perdeu(){
        tvResultado!!.text = getString(R.string.perdeu)
        tvResultado!!.setTextColor(ContextCompat.getColor(this, R.color.derrota))
        contDerrota++;
        tvDerrota!!.text = getString(R.string.derrota) + contDerrota
    }

    private fun empatou(){
        tvResultado!!.text = getString(R.string.empatou)
        tvResultado!!.setTextColor(ContextCompat.getColor(this, R.color.empate))
        contEmpate++;
        tvEmpate!!.text = getString(R.string.empate) + contEmpate
    }

}
