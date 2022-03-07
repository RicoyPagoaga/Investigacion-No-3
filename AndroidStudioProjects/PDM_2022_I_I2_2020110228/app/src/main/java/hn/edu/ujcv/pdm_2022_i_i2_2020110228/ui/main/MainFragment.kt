package hn.edu.ujcv.pdm_2022_i_i2_2020110228.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import hn.edu.ujcv.pdm_2022_i_i2_2020110228.R
import kotlinx.android.synthetic.main.main_fragment.*
import kotlin.Exception

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        btnCalcular.setOnClickListener {
            if (Ingresar() != null){
                Toast.makeText(requireActivity(),Ingresar().toString(),Toast.LENGTH_LONG).show()
            }else{
                viewModel.calcularDelta()
                txvX1R.text = viewModel.calcularX1().toString()
                txvX2R.text = viewModel.getX2().toString()
            }
        }
    }
    private fun Ingresar(): String?{
        try {
            if (txtAlumno.text.isNullOrEmpty()){
                throw IllegalArgumentException("Nombre de Alumno esta vacio")
            }
            if (txtA.text.isNullOrEmpty()){
                throw IllegalArgumentException("Campo A esta vacio")
            }
            if (txtB.text.isNullOrEmpty()){
                throw IllegalArgumentException("Campo B esta vacio")
            }
            if (txtC.text.isNullOrEmpty()){
                throw IllegalArgumentException("Campo C esta vacio")
            }
            viewModel.a = txtA.text.toString().trim().toFloat()
            viewModel.b = txtB.text.toString().trim().toFloat()
            viewModel.c = txtC.text.toString().trim().toFloat()
            return null
        }catch (e:Exception){
            return e.message.toString()
        }
    }
}