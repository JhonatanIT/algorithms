package com.example.algorithms;

//JAVA 17

import java.util.Arrays;

public class ExerciseCurlyBrackets {

    public static void main(String[] args) {
        int numeroParentesis = 4;
        parentesisBalanceados(numeroParentesis);
    }

    private static void parentesisBalanceados(int numeroParentesis) {

        if (numeroParentesis > 0) {
            String[] combinacionSimple = new String[numeroParentesis * 2];      //cada combinación tendrá (numeroParentesis * 2) caracteres
            imprimirParentesis(combinacionSimple, 0, numeroParentesis, 0, 0);
        } else {
            System.out.println("El número de paréntesis no puede ser menor o igual a 0");
        }

    }

    private static void imprimirParentesis(String[] combinacionSimple, int posicion, int numeroParentesis, int parentesisAbiertos, int parentesisCerrados) {

        if (parentesisAbiertos == parentesisCerrados && parentesisCerrados == numeroParentesis) {
            //condicion de salida
            Arrays.stream(combinacionSimple).forEach(System.out::print);
            System.out.println();
        } else {

            if (parentesisAbiertos > parentesisCerrados) {
                combinacionSimple[posicion] = "}";
                imprimirParentesis(combinacionSimple, posicion + 1, numeroParentesis, parentesisAbiertos, parentesisCerrados + 1);
            }
            if (parentesisAbiertos < numeroParentesis) {
                combinacionSimple[posicion] = "{";
                imprimirParentesis(combinacionSimple, posicion + 1, numeroParentesis, parentesisAbiertos + 1, parentesisCerrados);
            }
        }
    }

}
