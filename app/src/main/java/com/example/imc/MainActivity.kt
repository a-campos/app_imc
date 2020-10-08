package com.example.imc

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_resultado.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //Executando o click do botão calcular
        btnMainCalcular.setOnClickListener {
            //Capturar os dados digitados
            val altura = edtMainAltura.text.toString().trim()
            val peso = edtMainPeso.text.toString().trim()
            val nome = edtMainNome.text.toString().trim()

            //Validação dos campos
            if(altura.isEmpty() || peso.isEmpty() || nome.isEmpty() ){
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_LONG).show()
            }else {
                //Todos os campos foram preenchidos


                //Criando ou acessando o arquivo de preferências compartilhadas
                val sharedPrefs = getSharedPreferences("main $nome", Context.MODE_PRIVATE)

                //Editar o arquivo de preferencias compartilhadas
                val editPrefs = sharedPrefs.edit()

                //Preparando os dados a serem salvos no arquivo
                editPrefs.putString("NOME", nome)
                editPrefs.putString("ALTURA", altura)
                editPrefs.putString("PESO", peso)


                //Salvando os dados no Shared Preferences
                editPrefs.apply()



                //Abrindo os Resultados
               val mIntent  = Intent(this, ResultadoActivity::class.java)

                //Passando informações através da Intent
                mIntent.putExtra("INTENT_NOME", nome)
                startActivity(mIntent)
                finish()

                //Tirando todas as telas do empilhamento
                finishAffinity()


            }
        }
    }
}