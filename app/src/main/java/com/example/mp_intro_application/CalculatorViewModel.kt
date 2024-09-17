package com.example.mp_intro_application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class CalculatorUiState(
    val operand1:Double = 0.0,
    val operand2:Double = 0.0,
    val result:Double = 0.0,
)

enum class CalcOperators{
    OP_PLUS,OP_MINUS,OP_TIMES,OP_DIVIDE
}

class CalculatorViewModel(private val calculatorModel: CalculatorModel): ViewModel() {
    private val _uiState = MutableStateFlow(CalculatorUiState())
    val uiState: StateFlow<CalculatorUiState> =_uiState.asStateFlow()
    var state: String = "s0"

    fun updateOperand1(op1: String) {
        try {
            _uiState.update { currentState ->
                currentState.copy(
                    operand1 = op1.toDouble()
                )
            }
        }catch(e: NumberFormatException){
            _uiState.update{currentState -> currentState}
        }
    }

    fun updateOperand2(op2: String) {
        try{
            _uiState.update { currentState ->
                currentState.copy(
                    operand2 = op2.toDouble()
                )
            }
        }catch(e: NumberFormatException){
            _uiState.update{currentState -> currentState}
        }
    }

    fun doCalculation(operation: CalcOperators, cycle: Boolean) {
        calculatorModel.setOperand1(uiState.value.operand1)
        calculatorModel.setOperand2(uiState.value.operand2)
        val calcResult = when (operation) {
            CalcOperators.OP_PLUS -> calculatorModel.addOperands()
            CalcOperators.OP_MINUS -> calculatorModel.subOperands()
            CalcOperators.OP_TIMES -> calculatorModel.multiplyOperands()
            CalcOperators.OP_DIVIDE -> calculatorModel.divideOperands()
        }
        if(cycle) {
            _uiState.update { currentState ->
                currentState.copy(
                    operand1 = calcResult,
                )
            }
        } else {
            _uiState.update { currentState ->
                currentState.copy(
                    result = calcResult,
                )
            }
        }
    }


    class CalculatorViewModelFactory(private val model: CalculatorModel) : ViewModelProvider.Factory{
            override fun <T: ViewModel> create(modelClass: Class<T>): T{
                if(modelClass.isAssignableFrom(CalculatorViewModel::class.java)){
                    @Suppress("UNCHECKED_CAST")
                    return CalculatorViewModel(model) as T
                }
                throw IllegalArgumentException("Unknown ViewModel Class")
            }
    }

}