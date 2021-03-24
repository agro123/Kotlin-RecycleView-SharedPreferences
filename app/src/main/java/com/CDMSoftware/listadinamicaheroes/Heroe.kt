package com.CDMSoftware.listadinamicaheroes

data class Heroe(var name:String, var alterEgo:String, var url: String) { //data representa datos persistentes
  fun getFullName(): String = "$alterEgo -> $name"
}