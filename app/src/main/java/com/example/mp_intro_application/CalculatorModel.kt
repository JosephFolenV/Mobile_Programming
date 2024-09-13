package com.example.mp_intro_application

class CalculatorModel {

    private var operand1: Double = 0.0
    private var operand2: Double = 0.0

    fun setOperand1(op1:Double){
        operand1 = op1
    }
    fun setOperand2(op2:Double){
        operand2 = op2
    }

    fun addOperands():Double{
        return operand1+operand2
    }

    fun subOperands():Double{
        return operand1-operand2
    }

    fun divideOperands():Double{
        //TODO: Handle divide by 0 error
        return operand1/operand2
    }

    fun multiplyOperands():Double{
        return operand1*operand2
    }
}