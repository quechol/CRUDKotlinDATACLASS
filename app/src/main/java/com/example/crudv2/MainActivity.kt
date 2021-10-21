package com.example.crudv2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

import java.io.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //objetos de vista
        val btnAcpt:Button = findViewById(R.id.btnLogin)
        val edUser:EditText = findViewById(R.id.editUser)
        val edPswd:EditText = findViewById(R.id.editPaswd)

        //variables
        var errores:Int = 0
        var user: String = "root"
        var pswd: String = "root"

        //programacion
        btnAcpt.setOnClickListener{
            if (edUser.text.toString() == user && edPswd.text.toString() == pswd){
                Toast.makeText(this, "¡BIENVENIDO "+ user + "!", Toast.LENGTH_SHORT).show()
                val enviar = Intent(this, Menu::class.java)
                startActivity(enviar)
            }else{
                errores++
                Toast.makeText(this, "¡ERROR EN USUARIO/CONTRASEÑA!", Toast.LENGTH_SHORT).show()
            }
            if (errores == 3){
                this.finishAffinity()
            }
        }
    }
}