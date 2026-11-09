import java.util.Random;
import java.util.Scanner;

// Codigo hecho con ia para basarse/saber un poco que es lo que tengo que hacer

public class Racher_ia {

    public static void main(String[] args) {

        // PRIMERA PARTE: VARIABLES Y TABLERO

        int filas = 10;
        int columnas = 10;

        char[][] tablero = new char[filas][columnas];

        char RATCHET = 'R';
        char LIBRE = 'L';
        char CAJA = 'B';
        char ROBOT = 'E';
        char NANOTEC = 'P';
        char PIEZA = 'T';
        char NAVE = 'F';

        Random aleatorio = new Random();
        Scanner teclado = new Scanner(System.in);

        int vidas = 10;
        int piezas = 0;

        // SEGUNDA PARTE: RELLENAR EL TABLERO

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                tablero[i][j] = LIBRE;
            }
        }

        // Colocar la nave en la posición 9,9

        tablero[9][9] = NAVE;

        int filaRatchet = -1;
        int columnaRatchet = -1;

        // Colocar a Ratchet

        do {
            filaRatchet = aleatorio.nextInt(filas);
            columnaRatchet = aleatorio.nextInt(columnas);

        } while (tablero[filaRatchet][columnaRatchet] != LIBRE);

        tablero[filaRatchet][columnaRatchet] = RATCHET;

        // TERCERA PARTE: COLOCAR LAS CAJAS

        for (int i = 0; i < 10; i++) {

            int fila;
            int columna;

            do {
                fila = aleatorio.nextInt(filas);
                columna = aleatorio.nextInt(columnas);

            } while (tablero[fila][columna] != LIBRE);

            tablero[fila][columna] = CAJA;
        }

        // CUARTA PARTE: COLOCAR LOS ROBOTS

        for (int i = 0; i < 7; i++) {

            int fila;
            int columna;

            do {
                fila = aleatorio.nextInt(filas);
                columna = aleatorio.nextInt(columnas);

            } while (tablero[fila][columna] != LIBRE);

            tablero[fila][columna] = ROBOT;
        }

        // QUINTA PARTE: COLOCAR LAS NANOTEC

        for (int i = 0; i < 5; i++) {

            int fila;
            int columna;

            do {
                fila = aleatorio.nextInt(filas);
                columna = aleatorio.nextInt(columnas);

            } while (tablero[fila][columna] != LIBRE);

            tablero[fila][columna] = NANOTEC;
        }

        // SEXTA PARTE: COLOCAR LAS PIEZAS

        for (int i = 0; i < 3; i++) {

            int fila;
            int columna;

            do {
                fila = aleatorio.nextInt(filas);
                columna = aleatorio.nextInt(columnas);

            } while (tablero[fila][columna] != LIBRE);

            tablero[fila][columna] = PIEZA;
        }

        // SÉPTIMA PARTE: EMPIEZA EL JUEGO

        boolean jugando = true;

        while (jugando && vidas > 0) {

            // Mostrar tablero

            System.out.println();

            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < columnas; j++) {

                    if (tablero[i][j] == RATCHET) {
                        System.out.print("\033[94mR\033[0m ");
                    } else {
                        System.out.print(tablero[i][j] + " ");
                    }

                }
                System.out.println();
            }

            System.out.println();
            System.out.println("Vidas: " + vidas);
            System.out.println("Piezas recogidas: " + piezas + "/3");

            System.out.println("Muévete con W A S D");
            System.out.print("Introduce un movimiento: ");

            char movimiento = teclado.next().toUpperCase().charAt(0);

            int filaAnterior = filaRatchet;
            int columnaAnterior = columnaRatchet;

            // OCTAVA PARTE: MOVIMIENTO

            switch (movimiento) {

                case 'W':
                    filaRatchet--;
                    break;

                case 'A':
                    columnaRatchet--;
                    break;

                case 'S':
                    filaRatchet++;
                    break;

                case 'D':
                    columnaRatchet++;
                    break;

                default:
                    System.out.println("Movimiento incorrecto");
                    filaRatchet = filaAnterior;
                    columnaRatchet = columnaAnterior;
                    break;
            }

            // COMPROBAR LOS LÍMITES DEL TABLERO

            if (filaRatchet < 0 || filaRatchet >= filas ||
                    columnaRatchet < 0 || columnaRatchet >= columnas) {

                System.out.println("No puedes salir del tablero");

                filaRatchet = filaAnterior;
                columnaRatchet = columnaAnterior;

            } else {

                // COMPROBAR SI HAY UNA CAJA

                if (tablero[filaRatchet][columnaRatchet] == CAJA) {

                    System.out.println("Hay una caja. No puedes avanzar");

                    filaRatchet = filaAnterior;
                    columnaRatchet = columnaAnterior;

                }

                // COMPROBAR SI HAY UN ROBOT

                else if (tablero[filaRatchet][columnaRatchet] == ROBOT) {

                    vidas--;

                    System.out.println("¡Te has encontrado con un robot! Pierdes una vida");

                    tablero[filaRatchet][columnaRatchet] = RATCHET;
                    tablero[filaAnterior][columnaAnterior] = LIBRE;

                }

                // COMPROBAR SI HAY NANOTEC

                else if (tablero[filaRatchet][columnaRatchet] == NANOTEC) {

                    vidas++;

                    System.out.println("¡Has conseguido nanotec! Ganas una vida");

                    tablero[filaRatchet][columnaRatchet] = RATCHET;
                    tablero[filaAnterior][columnaAnterior] = LIBRE;

                }

                // COMPROBAR SI HAY UNA PIEZA

                else if (tablero[filaRatchet][columnaRatchet] == PIEZA) {

                    piezas++;

                    System.out.println("¡Has recogido una pieza!");

                    tablero[filaRatchet][columnaRatchet] = RATCHET;
                    tablero[filaAnterior][columnaAnterior] = LIBRE;

                }

                // COMPROBAR SI HAY UNA NAVE

                else if (tablero[filaRatchet][columnaRatchet] == NAVE) {

                    if (piezas == 3) {

                        System.out.println("¡ENHORABUENA! Has recogido las tres piezas y has llegado a la nave.");
                        jugando = false;

                    } else {

                        System.out.println("Todavía necesitas recoger las tres piezas.");

                        filaRatchet = filaAnterior;
                        columnaRatchet = columnaAnterior;

                    }

                }

                // CASILLA LIBRE

                else if (tablero[filaRatchet][columnaRatchet] == LIBRE) {

                    tablero[filaRatchet][columnaRatchet] = RATCHET;
                    tablero[filaAnterior][columnaAnterior] = LIBRE;

                }

            }

        }

        // FINAL DEL JUEGO

        if (vidas <= 0) {
            System.out.println("GAME OVER. Te has quedado sin vidas.");
        }

        teclado.close();

    }
}
