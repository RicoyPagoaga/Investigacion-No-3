package hn.edu.ujcv.pdm_2022_i_i2_2020110228.ui.main

import androidx.lifecycle.ViewModel
import kotlin.math.*

class MainViewModel : ViewModel() {
    var a: Float = 0f
        set(value) {
            if (a.toString().isNullOrEmpty()) {
                throw IllegalArgumentException("Campo A esta vacio")
            }
            field = value
        }
    var b: Float = 0f
        set(value) {
            if (b.toString().isNullOrEmpty()) {
                throw IllegalArgumentException("Campo B esta vacio")
            }
            field = value
        }
    var c: Float = 0f
        set(value) {
            if (c.toString().isNullOrEmpty()) {
                throw IllegalArgumentException("Campo C esta vacio")
            }
            field = value
        }
    private var delta = 0f
    private var x1 = 0f
    private var x2: Float = 0f
    fun calcularDelta(): Float {
        delta = b.pow(2) - (4 * a * c)
        return delta
    }

    fun calcularX1(): Float {
        if (calcularDelta() == 0f) {
            x1 = -b / (2 * a)
        } else {
            x1 = (-b + sqrt(calcularDelta())) / (2 * a)
        }
        return x1
    }

    fun getX2(): Float {
        if (calcularDelta() > 0f) {
            x2 = (-b - sqrt(calcularDelta())) / (2 * a)
        }
        return x2
    }

}