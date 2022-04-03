package hn.edu.ujcv.pdm_2022_i_i3_2020110228.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import hn.edu.ujcv.pdm_2022_i_i3_2020110228.Greeting
import hn.edu.ujcv.pdm_2022_i_i3_2020110228.InteresSimple
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

fun greet(): String {
    return Greeting().greeting()
}
fun Interes(capital:Int,tasa:Float,tiempo:Int):String {
    return InteresSimple().interessimple(capital, tasa, tiempo)
}
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tv: TextView = findViewById(R.id.text_view)
        //tv.text = greet()

        btnCalular.setOnClickListener {
            tv.text = Interes(txtCapital.text.toString().toInt(),txtInteres.text.toString().toFloat()
                ,txtTiempo.text.toString().toInt())
        }

    }
}
