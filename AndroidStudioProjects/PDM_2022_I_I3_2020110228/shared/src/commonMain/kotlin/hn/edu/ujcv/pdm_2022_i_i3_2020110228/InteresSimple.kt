package hn.edu.ujcv.pdm_2022_i_i3_2020110228

class InteresSimple {

    fun interessimple( capital: Int?, tasa: Float?,tiempo:Int?): String {
        var interes:Float? = 0F
        interes = capital!!.toFloat()*(tasa!! /100)* tiempo!!
        return (interes.toString())
    }
}