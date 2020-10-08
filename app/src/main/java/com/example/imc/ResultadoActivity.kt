package com.example.imc

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_resultado.*
import java.math.RoundingMode

class ResultadoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultado)


        //Recuperando o email passado por meio da Intent
        val nome = intent.getStringExtra("INTENT_NOME")

        //Acessar o arquivo de preferencias compartilhadas
        val sharedPrefs = getSharedPreferences("main $nome", Context.MODE_PRIVATE)

        //Recuperar dados no arquivo Shared Preferences
        val altura = sharedPrefs.getString("ALTURA", "")
        val peso = sharedPrefs.getString("PESO", "")

        //Cálculo IMC
        val altura1 = altura.toString().toDouble()
        val peso1 = peso.toString().toDouble()
        val imc = (peso1/(altura1*altura1))
        val resultado = imc.toBigDecimal().setScale(1,RoundingMode.UP).toString()


       //Exibir os dados recuperados na tela
        txvResultadoNome.text = "Olá $nome, o seu IMC é:"
        txvResultadoResultado.text = resultado

            //Ver a faixa correspondente de acordo com o IMC
        if (imc<18.5){
            txvResultadoFaixa.text = "Você está abaixo do peso"
        } else if (imc >= 18.5 && imc < 25){
            txvResultadoFaixa.text = "Você está com peso normal"
        } else if (imc >= 25 && imc < 30){
            txvResultadoFaixa.text = "Você está com sobrepeso"
        } else if (imc >= 30 && imc < 35){
            txvResultadoFaixa.text = "Você está com obesidade grau I"
        } else if (imc >= 35 && imc < 40){
            txvResultadoFaixa.text = "Você está com obesidade grau II"
        } else{
            txvResultadoFaixa.text = "Você está com obesidade grau III"
        }

        //Execute o botão Calcular novamente
        btnResultadoCalcular.setOnClickListener {
            val mIntent = Intent(this, MainActivity::class.java)
            startActivity(mIntent)
            finishAffinity()
        }


    }
}