package com.example.crudv2

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class Menu  : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        //objetos de vista
        val rgCRUD: RadioGroup = findViewById(R.id.radioCRUD)
        val rbC: RadioButton = findViewById(R.id.rbtnCreate)
        val rbR: RadioButton = findViewById(R.id.rbtnRead)
        val rbU: RadioButton = findViewById(R.id.rbtnUpdate)
        val rbD: RadioButton = findViewById(R.id.rbtnDelete)
        val btnAceptar: Button = findViewById(R.id.buttonSel)
        val txtNom: EditText = findViewById(R.id.editNombre)
        val txtDes: EditText = findViewById(R.id.editDescripcion)
        val txtPrueba: TextView = findViewById(R.id.salida)
        val txtNombre: TextView = findViewById(R.id.nombreSalida)
        val spinner = findViewById<Spinner>(R.id.spinnerFotos)
        val img: ImageView =findViewById(R.id.imgFoto)

        //listas
        var zoo: MutableList<Animal> = mutableListOf()
        val listaAnimales = listOf("aguilareal","cangurio","caracal","condor","hiena","hipopotamo","jaguar","jirafa","lemur","leon","oso","panda","pinguino","tigre")
        var fotos= arrayOf(R.drawable.aguilareal,R.drawable.canguro,R.drawable.caracal,R.drawable.condor,R.drawable.hiena,R.drawable.hipopotamo,R.drawable.jaguar,R.drawable.jirafa,R.drawable.lemur,R.drawable.leon,R.drawable.oso,R.drawable.panda,R.drawable.pinguino,R.drawable.tigre)

        //animales
        var oso = Animal("Oso \n", "Habitad: \n Dieta: \n Promedio de vida: \n",R.drawable.oso)
        var aguila = Animal("Aguila \n", "Habitad: \n Dieta: \n Promedio de vida: \n",R.drawable.aguilareal)

        //spinner
        val adaptador = ArrayAdapter(this, android.R.layout.simple_spinner_item, listaAnimales)
        spinner.adapter = adaptador

        //agregar a la lista
        zoo.add(aguila)
        zoo.add(oso)

        //variables
        var animales:Int = 1
        var imagen: Int = 0
        var pos: Int = 0
        var encontrado: Boolean = false
        var consulta: Boolean = false
        var nombreAux: String = ""

        //programacion
        spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                p0: AdapterView<*>?,
                p1: View?,
                p2: Int,
                p3: Long
            ) {
                imagen = p2
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }

        btnAceptar.setOnClickListener {
            val opc: Int = rgCRUD.checkedRadioButtonId
            var i: Int = 0
            if(opc == -1){
                //no seleccion
                txtPrueba.text = "Selecciona una opcion"
            }else{
                //seleccion
                when(opc){
                    rbC.id -> {
                        //create
                        txtPrueba.text = ""
                        txtNombre.text = ""
                        if(animales < 10){
                            val nombreR: String =txtNom.text.toString()
                            val descripcionR: String =txtDes.text.toString()
                            if (nombreR != "" && descripcionR != ""){
                                var animal = Animal(nombreR, descripcionR, fotos[imagen])
                                zoo.add(animal)
                                animales++
                                txtPrueba.text = "ANIMAL CREADO"
                                txtNombre.text = nombreR
                                txtNom.text.clear()
                                txtDes.text.clear()
                            }else{
                                txtPrueba.text ="DEBES LLENAR TODOS LOS CAMPOS"
                            }
                        }else{
                            txtPrueba.text = "NUMERO MAXIMO DE ANIMALES ALCANZADO"
                        }
                        txtNom.text.clear()
                        txtDes.text.clear()
                        consulta = false
                    }
                    rbR.id -> {
                        //read
                        txtPrueba.text = ""
                        txtNombre.text = ""
                        val nombreR: String = txtNom.text.toString()
                        val descripcionR: String = txtDes.text.toString()
                        /*if (nombreR == "master"){
                            var supperT: String = ""
                            zoo.forEach {
                                supperT = supperT + " " +it.nombre
                            }
                            txtPrueba.text = supperT
                        }*/
                        if (nombreR != "") {
                            //var pos: Int = 1
                            zoo.forEach {
                                if (it.nombre.contains(nombreR)) {
                                    encontrado = true
                                    txtNombre.text = it.nombre
                                    txtPrueba.text = it.descripcion
                                    img.setImageResource(it.img)
                                    txtNom.text.clear()
                                    txtDes.text.clear()
                                    consulta = true
                                    nombreAux = it.nombre
                                    //var pos = it.
                                }else{
                                    pos++
                                }
                            }
                        } else if (descripcionR != "") {
                            zoo.forEach {
                                if (it.descripcion.contains(descripcionR)) {
                                    encontrado = true
                                    txtNombre.text = it.nombre
                                    txtPrueba.text = it.descripcion
                                    txtNom.text.clear()
                                    txtDes.text.clear()
                                    consulta = true
                                    nombreAux = it.nombre
                                }else{
                                    pos++
                                }
                            }
                        }else{
                            txtPrueba.text = "DEBES LLENAR TODOS LOS CAMPOS"
                            consulta = false
                        }
                        if(!encontrado){
                            txtPrueba.text = "ANIMAL NO ENCONTRADO"
                            consulta = false
                        }

                        txtNom.text.clear()
                        txtDes.text.clear()
                        encontrado = false
                    }
                    rbU.id -> {
                        //update
                        if (consulta){
                            txtPrueba.text = ""
                            txtNombre.text = ""
                            if(nombreAux != ""){
                                val nombreR: String =txtNom.text.toString()
                                val descripcionR: String =txtDes.text.toString()
                                if (nombreR!= "" && descripcionR !=""){
                                    var animalAux:Animal = Animal(nombreR, descripcionR, fotos[imagen])
                                    zoo[pos] = animalAux
                                    txtPrueba.text = "ANIMAL MODIFICADO"
                                }else{
                                    txtPrueba.text = "DEBES DE LLENAR TODOS LOS CAMPOS"
                                }
                            }else{
                                txtPrueba.text = "ERROR"
                            }
                        }else{
                            txtPrueba.text = "DEBES HACER LA LECTURA PRIMERO"
                        }
                        txtNom.text.clear()
                        txtDes.text.clear()
                        encontrado = false
                        consulta = false
                        pos = 0
                    }
                    rbD.id -> {
                        //delete
                        txtPrueba.text = ""
                        txtNombre.text = ""
                        var i: Int = 0
                        var nombreR: String = txtNom.text.toString()
                        if(nombreR!= ""){
                            val iterator = zoo.iterator()
                            while(iterator.hasNext()){
                                val item = iterator.next()
                                if(item.nombre.contains(nombreR)){
                                    encontrado = true
                                    iterator.remove()
                                    txtPrueba.text = "ANIMAL ELIMINADO"
                                }
                            }
                        }else{
                            txtPrueba.text = "NO PUEDES DEJAR LOS CAMPOS VACIOS"
                        }
                        if(!encontrado){
                            txtPrueba.text = "ANIMAL NO ENCONTRADO"
                        }
                        txtNom.text.clear()
                        txtDes.text.clear()
                        encontrado = false
                        consulta = false
                    }
                }
            }
        }
    }
}
data class Animal(
    val nombre: String,
    val descripcion: String,
    val img:Int
)