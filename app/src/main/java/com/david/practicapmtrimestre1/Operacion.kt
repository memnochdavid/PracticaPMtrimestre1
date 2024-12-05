package com.david.practicapmtrimestre1

import androidx.compose.runtime.LaunchedEffect
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import kotlin.random.Random
import kotlin.random.nextInt

data class Operacion(
    var operador:String,
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
            ""->{//por si acaso, en la preview cargaba un singno vacio ""
                operador="-"
            }
        }
    }
    override fun toString(): String {
        return "$a $operador $b"
    }

}

fun generaOperacion():Operacion{
    var operacion=Operacion("-",666,666)//si todo va bien, esta operación no aparecerá
    if(preferenciasCargadas){
        var operador= listaOperaciones[listaOperaciones.indices.random()]
        var a = getRandomIntInRange(minOperatorValue, maxOperatorValue)
        var b = getRandomIntInRange(minOperatorValue, maxOperatorValue)
        operacion=Operacion(operador,a,b)

    }
    return operacion
}




fun getRandomIntInRange(min: Int, max: Int): Int {
    require(min < max) { "" }
    return Random.nextInt(max - min + 1) + min
}
fun checkResultado(opracion:Operacion, intento:String):Boolean{
    if(intento.isNotEmpty()){
        val resInput=intento.toInt()
        return opracion.res==resInput
    }else return false
}