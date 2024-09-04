package com.example.mp_intro_application

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var operand1:EditText
    private lateinit var operand2:EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        var result: Double
        val tvresult:TextView = findViewById<TextView>(R.id.result)
        operand1 = findViewById<EditText>(R.id.operand1)
        operand2 = findViewById<EditText>(R.id.operand2)

        findViewById<Button>(R.id.btnPlus).setOnClickListener{
            val op1 = getOperand1()
            val op2 = getOperand2()
            result = setResult(op1+op2)
            tvresult.text = result.toString()

        }

        findViewById<Button>(R.id.btnMinus).setOnClickListener{
            val op1 = getOperand1()
            val op2 = getOperand2()
            result = setResult(op1-op2)
            tvresult.text = result.toString()

        }
    }

    private fun getOperand1():Double{
        val op1:String = operand1.text.toString()
        val op1dec:Double = op1.toDouble()
        return op1dec
    }

    private fun getOperand2():Double{
        val op2:String = operand2.text.toString()
        val op2dec:Double = op2.toDouble()
        return op2dec
    }

    private fun setResult(p:Double):Double{
        return p
    }
}