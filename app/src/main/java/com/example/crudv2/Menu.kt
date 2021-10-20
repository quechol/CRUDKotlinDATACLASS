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
        val spinner = findViewById<Spinner>(R.id.spinnerFotos)
        val img: ImageView =findViewById(R.id.imgFoto)
        //listas
        var zoo: MutableList<Animal> = mutableListOf()
        val listaAnimales = listOf("aguilareal","cangurio","caracal","condor","hiena","hipopotamo","jaguar","jirafa","lemur","leon","oso","panda","pinguino","tigre")
        var fotos= arrayOf(R.drawable.aguilareal,R.drawable.canguro,R.drawable.caracal,R.drawable.condor,R.drawable.hiena,R.drawable.hipopotamo,R.drawable.jaguar,R.drawable.jirafa,R.drawable.lemur,R.drawable.leon,R.drawable.oso,R.drawable.panda,R.drawable.pinguino,R.drawable.tigre)
        //animales
        val aguila = Animal("Aguila \n", "Habitad: \n Dieta: \n Promedio de vida: \n",R.drawable.aguilareal)
        //spinner
        val adaptador = ArrayAdapter(this, android.R.layout.simple_spinner_item, listaAnimales)
        spinner.adapter = adaptador
        //agregar a la lista
        zoo.add(aguila)
        //variables
        var animales:Int = 10
        var imagen: Int = 0
        //programacion

        spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                p0: AdapterView<*>?,
                p1: View?,
                p2: Int,
                p3: Long
            ) {
                //Toast.makeText(this@Menu,fotos[p2],Toast.LENGTH_LONG).show()
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
                when(opc){
                    rbC.id -> {
                        //create
                        txtPrueba.text = ""
                        if(animales <= 10){
                            var nombreR: String =txtNom.text.toString()
                            var descripcionR: String =txtDes.text.toString()
                            if (nombreR != "" && descripcionR != ""){
                                //crear
                                var animal = Animal(nombreR, descripcionR, fotos[imagen])
                                zoo.add(animal)
                                animales++
                                txtPrueba.text = "ANIMAL CREADO"
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
                    }
                    rbR.id -> {
                        //read
                        txtPrueba.text = ""
                        var encontrado: Boolean = false
                        var nombreR: String =txtNom.text.toString()
                        var descripcionR: String =txtDes.text.toString()
                        if (nombreR != ""){
                            zoo.forEach{
                                if(it.nombre.contains(nombreR)){
                                    var encontrado: Boolean = true
                                    txtPrueba.text = it.nombre + "\n" + it.descripcion
                                    img.setImageResource(it.img)
                                    txtNom.text.clear()
                                    txtDes.text.clear()
                                }
                            }
                        }else if (descripcionR != ""){
                            zoo.forEach{
                                if(it.descripcion.contains(descripcionR)){
                                    var encontrado: Boolean = true
                                    txtPrueba.text = it.nombre + "\n" + it.descripcion
                                    txtNom.text.clear()
                                    txtDes.text.clear()
                                }
                            }
                        }else{
                            txtPrueba.text = "DEBES LLENAR TODOS LOS CAMPOS"
                        }
                        /*if(!encontrado){
                            txtPrueba.text = "ANIMAL NO ENCONTRADO"
                        }*/
                        txtNom.text.clear()
                        txtDes.text.clear()
                    }
                    rbU.id -> {
                        //update

                    }
                    rbD.id -> {
                        //delete
                        txtPrueba.text = ""
                        txtNom.text.clear()
                        txtDes.text.clear()
                        var nombreR: String =txtNom.text.toString()
                        var descripcionR: String =txtDes.text.toString()
                        var encontrado: Boolean = false
                        val iterator = zoo.iterator()
                        while(iterator.hasNext()){
                            val item = iterator.next()
                            if(item.nombre.contains(nombreR)){
                                encontrado = true
                                txtPrueba.text = "Animal eliminado: " + nombreR
                                iterator.remove()
                                animales--
                            }
                        }
                        if(!encontrado){
                            txtPrueba.text = "ANIMAL NO ENCONTRADO"
                        }
                        txtNom.text.clear()
                        txtDes.text.clear()
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