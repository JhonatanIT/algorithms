package com.example.algorithms;

//JAVA 17

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class ExerciseSortNSubstring {

    public static void main(String[] args) {
        imprimeNSubstring("dbac", 3);
    }

    private static void imprimeNSubstring(String cadena, int posicion) {

        if (cadena == null || cadena.isEmpty()) {
            System.out.println("Cadena vacía");
            return;
        }

        if (posicion <= 0) {
            System.out.println("posición no puede ser menor o igual a 0");
            return;
        }

        List<String> lista = new ArrayList<>();

        //Obtiene todas las combinaciones de subtrings
        IntStream.range(0, cadena.length())
                .forEach(i -> IntStream.range(0, cadena.length() - i)
                        .forEach(j -> lista.add(cadena.substring(j, cadena.length() - i))));

        lista.stream().sorted()                                                     //ordenar susbtrings
                .reduce((acumulador, substring) -> acumulador + substring)          //concatenar substrings
                .ifPresent(subtringsConcatenados -> {
                    if (posicion <= subtringsConcatenados.length()) {
                        System.out.println(subtringsConcatenados.charAt(posicion - 1)); //Imprime el caracter en la posicion deseada
                    } else {
                        System.out.printf("La cadena de subtrings concatenados solo tiene %s caracteres", subtringsConcatenados.length());
                    }
                });
    }

}
