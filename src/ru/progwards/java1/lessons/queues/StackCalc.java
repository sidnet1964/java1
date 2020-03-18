package ru.progwards.java1.lessons.queues;

import java.util.ArrayDeque;
import java.util.Deque;

public class StackCalc {
    Deque<Double> stack;
//  конструктор
    StackCalc(){
        stack = new ArrayDeque<>();
    }
//  положить value на вершину стека
    public void push(double value){
        stack.push(value);
    }
//  взять (убрать) значение с вершины стека
    public double pop(){
        if (!stack.isEmpty())
            return stack.pop();
        else
            return Double.POSITIVE_INFINITY;
    }
//   сложить 2 верхних значения на стеке, результат положить на стек.
//   В итогу в стеке должно быть на один элемент меньше
    public void add(){
        double rez = 0;
        if (stack.size() > 1 ) {
            rez += stack.pop();
            rez += stack.pop();
            stack.push(rez);
        }
    }
//   - вычесть верхнее значение на стеке, из следующего по глубине,
//   результат положить на стек. В итоге в стеке должно быть на один элемент меньше
    public void sub(){
        double rez = 0;
        if (stack.size() > 1 ) {
            rez -= stack.pop();
            rez += stack.pop();
            stack.push(rez);
        }
    }
//   умножить 2 верхних значения на стеке, результат положить на стек.
//   В итоге в стеке должно быть на один элемент меньше
    public void mul(){
        double rez = 1;
        if (stack.size() > 1 ) {
            rez *= stack.pop();
            rez *= stack.pop();
            stack.push(rez);
        }
    }
 //  поделить на верхнее значение на стеке, следующее по глубине,
 //  результат положить на стек. В итоге в стеке должно быть на один элемент меньше
    public void div(){
        double rez = 1;
        if (stack.size() > 1 ) {
            rez /= stack.pop();
            rez *= stack.pop();
            stack.push(rez);
        }
    }
}
