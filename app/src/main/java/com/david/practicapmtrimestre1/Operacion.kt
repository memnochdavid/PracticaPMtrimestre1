package com.david.practicapmtrimestre1

import kotlin.random.Random
import kotlin.random.nextInt

data class Operacion(
    val operador:String,
    val a:Int,
    val b:Int,
    var res:Int=0
){
    init{
        when(operador){
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
    override fun toString(): String {
        return "$a $operador $b"
    }

}

fun generaOperacion():Operacion{
    var operacion:Operacion
    //var operador= listaOperaciones[getRandomInt(listaOperaciones.size)-1]
    var operador= "*"
    var a = getRandomIntInRange(minOperatorValue, maxOperatorValue)
    var b = getRandomIntInRange(minOperatorValue, maxOperatorValue)
    operacion=Operacion(operador,a,b)
    return operacion
}




fun getRandomIntInRange(min: Int, max: Int): Int {
    require(min < max) { "min must be less than max" }
    return Random.nextInt(max - min + 1) + min
}
fun checkResultado(opracion:Operacion, intento:String):Boolean{
    if(intento.isNotEmpty()){
        val resInput=intento.toInt()
        return opracion.res==resInput
    }else return false
}