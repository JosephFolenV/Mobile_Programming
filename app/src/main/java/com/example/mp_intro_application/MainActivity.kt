package com.example.mp_intro_application

import android.media.tv.TvRecordingInfo
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
    private lateinit var result: String
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
        tvResult.text = currentTv
    }

    private fun numberButtonLogic(state: String){
        when(state){
            "s0" -> {
                calculatorViewModel.updateOperand1(tvResult.text.toString())
            }
            "s1" ->  {
                calculatorViewModel.updateOperand2(tvResult.text.toString())
            }
            "s3","s4" -> {
                tvResult.text = ""
                calculatorViewModel.state = "s0"
            }

        }
        Log.d(LOGTAG, "State: ")
        Log.d(LOGTAG, calculatorViewModel.state)
    }

    private fun opButtonLogic(state: String, operations: ArrayDeque<String>, newOperator: String, tvResult: TextView){
        when(state){
            "s0" -> {
                operations.addLast(newOperator)
                Log.d(LOGTAG, operations.toString())
                tvResult.text = ""
                calculatorViewModel.state = "s1"
            }
            "s1" ->  {
                tvResult.text = ""
                operations.addLast(newOperator)
                when(operations.removeFirst()){
                    "+" -> calculatorViewModel.doCalculation(CalcOperators.OP_PLUS, true)
                    "-" -> calculatorViewModel.doCalculation(CalcOperators.OP_MINUS, true)
                    "*" -> calculatorViewModel.doCalculation(CalcOperators.OP_TIMES, true)
                    "/" -> calculatorViewModel.doCalculation(CalcOperators.OP_DIVIDE, true)
                }
            }
            "s3","s4" -> {
                calculatorViewModel.updateOperand1(tvResult.toString())
                operations.addLast(newOperator)
                tvResult.text = ""
                calculatorViewModel.state = "s1"

            }

        }
        currentTv = ""
        Log.d(LOGTAG, "State: ")
        Log.d(LOGTAG, calculatorViewModel.state)
    }
    private fun equalsLogic(state: String, operations: ArrayDeque<String>, tvResult: TextView){
        when(state){
            "s0" -> {
                tvResult.text = ""
                tvResult.text = operand1
                calculatorViewModel.state = "s3"
            }
            "s1" -> {

                if(!(operations.isEmpty())){
                    Log.d(LOGTAG, "Operation found")
                    when (operations.removeFirst()) {
                        "+" -> {
                            calculatorViewModel.doCalculation(CalcOperators.OP_PLUS, false)
                            calculatorViewModel.state = "s4"
                        }
                        "-" -> calculatorViewModel.doCalculation(CalcOperators.OP_MINUS, false)
                        "*" -> calculatorViewModel.doCalculation(CalcOperators.OP_TIMES, false)
                        "/" -> calculatorViewModel.doCalculation(CalcOperators.OP_DIVIDE, false)
                    }
                }
            }
        }
        currentTv = ""
        Log.d(LOGTAG, "State: ")
        Log.d(LOGTAG, calculatorViewModel.state)
    }

    private fun printAll3(){
        Log.d(LOGTAG, "Op1:")
        Log.d(LOGTAG, operand1)
        Log.d(LOGTAG, "Op2:")
        Log.d(LOGTAG, operand2)
        Log.d(LOGTAG, "Res: ")
        Log.d(LOGTAG, result)
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
        tvResult = findViewById(R.id.tvResult)
        val operations: ArrayDeque<String> = ArrayDeque()

        findViewById<Button>(R.id.one).setOnClickListener{
            appendToInput("1")
            numberButtonLogic(calculatorViewModel.state)
            printAll3()
        }
        findViewById<Button>(R.id.two).setOnClickListener{
            appendToInput("2")
            numberButtonLogic(calculatorViewModel.state)
            printAll3()
        }
        findViewById<Button>(R.id.three).setOnClickListener{
            appendToInput("3")
            numberButtonLogic(calculatorViewModel.state)
            printAll3()
        }
        findViewById<Button>(R.id.four).setOnClickListener{
            appendToInput("4")
            numberButtonLogic(calculatorViewModel.state)
            printAll3()
        }
        findViewById<Button>(R.id.five).setOnClickListener{
            appendToInput("5")
            numberButtonLogic(calculatorViewModel.state)
            printAll3()
        }
        findViewById<Button>(R.id.six).setOnClickListener{
            appendToInput("6")
            numberButtonLogic(calculatorViewModel.state)
            printAll3()
        }
        findViewById<Button>(R.id.seven).setOnClickListener{
            appendToInput("7")
            numberButtonLogic(calculatorViewModel.state)
            printAll3()
        }
        findViewById<Button>(R.id.eight).setOnClickListener{
            appendToInput("8")
            numberButtonLogic(calculatorViewModel.state)
            printAll3()
        }
        findViewById<Button>(R.id.nine).setOnClickListener{
            appendToInput("9")
            numberButtonLogic(calculatorViewModel.state)
            printAll3()
        }
        findViewById<Button>(R.id.zero).setOnClickListener{
            appendToInput("0")
            numberButtonLogic(calculatorViewModel.state)
            printAll3()
        }
        findViewById<Button>(R.id.point).setOnClickListener{
            appendToInput(".")
            numberButtonLogic(calculatorViewModel.state)
            printAll3()
        }



        findViewById<Button>(R.id.btnPlus).setOnClickListener {
            opButtonLogic(calculatorViewModel.state, operations, "+", tvResult)


        }
        findViewById<Button>(R.id.btnMinus).setOnClickListener {
            opButtonLogic(calculatorViewModel.state, operations, "-", tvResult)

        }
        findViewById<Button>(R.id.btnTimes).setOnClickListener {
            opButtonLogic(calculatorViewModel.state, operations, "*", tvResult)
        }
        findViewById<Button>(R.id.btnDivide).setOnClickListener {
            opButtonLogic(calculatorViewModel.state, operations, "/", tvResult)
        }

        findViewById<Button>(R.id.equals).setOnClickListener {
            equalsLogic(calculatorViewModel.state, operations, tvResult)
            tvResult.text = result
        }

        findViewById<Button>(R.id.clear).setOnClickListener{
            tvResult.text = ""
            calculatorViewModel.updateOperand1("0")
            calculatorViewModel.updateOperand2("0")
            calculatorViewModel.state = "s0"
        }

        findViewById<Button>(R.id.backspace).setOnClickListener{
            if((tvResult.text.toString()).isNotEmpty()){
                tvResult.text = (tvResult.text.toString()).substring(0, (tvResult.text.toString()).length - 1)
            }
            numberButtonLogic(calculatorViewModel.state)
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                calculatorViewModel.uiState.collect { state ->
                    result = state.result.toString()
                    operand1 = state.operand1.toString()
                    operand2 = state.operand2.toString()
                }
            }
        }
    }
}
