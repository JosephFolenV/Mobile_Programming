package com.example.mp_intro_application

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var operand1: String
    private lateinit var operand2: String
    private lateinit var tvResult: TextView
    private var currentTv: String = ""
    private val LOGTAG: String = "MainActivity"
    private val calculatorViewModel: CalculatorViewModel by viewModels {
        CalculatorViewModel.CalculatorViewModelFactory((application as CalculatorApplication).calculatorModel)
    }

    override fun onResume() {
        super.onResume()
        Log.d(LOGTAG, "onResume Called")
    }

    override fun onStart() {
        super.onStart()
        Log.d(LOGTAG, "OnStart Called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(LOGTAG, "OnPause Called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(LOGTAG, "OnStop Called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(LOGTAG, "onDestroy Called")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(LOGTAG, "onRestart Called")
    }

    private fun appendToInput(s: String){
        currentTv += s
        tvResult.setText(currentTv)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
//        etOperand1 = findViewById<EditText>(R.id.operand1)
//        etOperand1.addTextChangedListener { charSequence ->
//            calculatorViewModel.updateOperand1(charSequence.toString())
//        }
//        etOperand2 = findViewById<EditText>(R.id.operand2)
//        etOperand2.addTextChangedListener { charSequence ->
//            calculatorViewModel.updateOperand2(charSequence.toString())
//        }
        tvResult = findViewById(R.id.tvResult)

        findViewById<Button>(R.id.one).setOnClickListener{ appendToInput("1") }
        findViewById<Button>(R.id.two).setOnClickListener{ appendToInput("2") }
        findViewById<Button>(R.id.three).setOnClickListener{ appendToInput("3") }
        findViewById<Button>(R.id.four).setOnClickListener{ appendToInput("4") }
        findViewById<Button>(R.id.five).setOnClickListener{ appendToInput("5") }
        findViewById<Button>(R.id.six).setOnClickListener{ appendToInput("6") }
        findViewById<Button>(R.id.seven).setOnClickListener{ appendToInput("7") }
        findViewById<Button>(R.id.eight).setOnClickListener{ appendToInput("8") }
        findViewById<Button>(R.id.nine).setOnClickListener{ appendToInput("9") }









        findViewById<Button>(R.id.btnPlus).setOnClickListener {
            operand1 = tvResult.toString()
            Log.d(LOGTAG, "operand 1 =$tvResult")
            calculatorViewModel.doCalculation(CalcOperators.OP_PLUS)
        }
        findViewById<Button>(R.id.btnMinus).setOnClickListener {
            calculatorViewModel.doCalculation(CalcOperators.OP_MINUS)
        }
        findViewById<Button>(R.id.btnTimes).setOnClickListener {
            calculatorViewModel.doCalculation(CalcOperators.OP_TIMES)
        }
        findViewById<Button>(R.id.btnDivide).setOnClickListener {
            calculatorViewModel.doCalculation(CalcOperators.OP_DIVIDE)
        }


        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                calculatorViewModel.uiState.collect { state ->
                    // Update UI elements
//                    etOperand1.setText(state.operand1.toString())
//                    etOperand2.setText(state.operand2.toString())
                    tvResult.setText(state.result.toString())
                }
            }
        }
    }
}
