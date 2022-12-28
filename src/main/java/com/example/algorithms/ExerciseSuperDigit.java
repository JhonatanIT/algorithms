package com.example.algorithms;

//JAVA 17

import java.util.Arrays;

public class ExerciseSuperDigit {

    public static void main(String[] args) {

        try {
            superDigit(9875L);
            superDigit(148L, 3);
        } catch (NumberFormatException ex) {
            System.out.printf("Error: %s\n", ex.getMessage());
        }
    }

    private static void superDigit(long numero) {

        if (numero < 0) {
            System.out.println("Numero negativo");
            return;
        }

        String numeroString = String.valueOf(numero);

        if (numeroString.length() > 1) {
            String[] numeroSplit = numeroString.split("");
            Arrays.stream(numeroSplit).map(Long::parseLong)         //separar numero por digitos
                    .reduce(Long::sum)                              //sumar digitos
                    .ifPresent(ExerciseSuperDigit::superDigit);     //llamada recursivamente
        } else {
            System.out.println(numeroString);                       //condicion de salida
        }
    }

    private static void superDigit(long numero, int repeticion) {

        if (repeticion <= 0) {
            System.out.println("repeticion debe ser mayor a 0");
            return;
        }

        String numeroString = String.valueOf(numero).repeat(repeticion);
        superDigit(Long.parseLong(numeroString));
    }

}
