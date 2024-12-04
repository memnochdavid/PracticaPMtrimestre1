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
    var operador= listaOperaciones[listaOperaciones.indices.random()]
    var a = getRandomIntInRange(minOperatorValue, maxOperatorValue)
    var b = getRandomIntInRange(minOperatorValue, maxOperatorValue)
    operacion=Operacion(operador,a,b)
    return operacion
}












fun getRandomInt(max: Int): Int {
    return Random.nextInt(max)
}
fun getRandomIntInRange(min: Int, max: Int): Int {
    require(min < max) { "min must be less than max" }
    return Random.nextInt(max - min + 1) + min
}