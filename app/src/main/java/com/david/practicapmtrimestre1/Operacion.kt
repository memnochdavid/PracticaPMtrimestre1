package com.david.practicapmtrimestre1

data class Operacion(
    val operacion:String,
    val a:Int,
    val b:Int,
    var res:Int
){
    init{
        when(operacion){
            "+" ->{
                res=a+b
            }
            "-" -> {
                res=a-b
            }
            "*" -> {
                res=a*b
            }
        }
    }
}