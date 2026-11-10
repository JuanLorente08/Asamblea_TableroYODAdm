import java.util.Random;
import java.util.Scanner;
// Codigo mio

public class Racher {
    public static void main(String[]args ){
        int MAX_FILA_RATCHET = 10;
        int MAX_COL_RETCHET = 10;
        char RATCHET = 'R';
        char LIBRE = '*';

        char[][] tablero = new char[MAX_FILA_RATCHET][MAX_COL_RETCHET];

        for (int i = 0; i < MAX_FILA_RATCHET; i++) {
            for (int j = 0; j < MAX_COL_RETCHET; j++) {
                tablero[i][j] = LIBRE;
            }
        }
        char fin = 'F';
        tablero [9][9] = fin;


        // 1 RATCHET
        Random aleat = new Random();
        int filaAl = -1;
        int colAl = -1;
        int filaOficialRacher = -1;
        int columnaOficioalRacher = -1;

        for (int i = 0; i < 1; i++) {
            do{
                filaAl = aleat.nextInt(MAX_FILA_RATCHET);
                colAl = aleat.nextInt(MAX_COL_RETCHET);
            }while(tablero[filaAl][colAl]!=LIBRE);
            tablero[filaAl][colAl] = RATCHET;
        }

        // OFICIALIZO LAS POSICIONES DE MI RATCHET
        filaOficialRacher = filaAl;
        columnaOficioalRacher = colAl;
        // FIN

        // Cajas
        char cajas = 'B';
        for (int i = 0; i < 10; i++) {
            do{
                filaAl = aleat.nextInt(MAX_FILA_RATCHET);
                colAl = aleat.nextInt(MAX_COL_RETCHET);
            }while(tablero[filaAl][colAl]!=LIBRE);
            tablero[filaAl][colAl] = cajas;
        }

        // Robots
        char robots = 'E';
        for (int i = 0; i < 7; i++) {
            do{
                filaAl = aleat.nextInt(MAX_FILA_RATCHET);
                colAl = aleat.nextInt(MAX_COL_RETCHET);
            }while(tablero[filaAl][colAl]!=LIBRE);
            tablero[filaAl][colAl] = robots;
        }

        // Piezas
        char piezas = 'T';
        int piezascont = 0;
        for (int i = 0; i < 3; i++) {
            do{
                filaAl = aleat.nextInt(MAX_FILA_RATCHET);
                colAl = aleat.nextInt(MAX_COL_RETCHET);
            }while(tablero[filaAl][colAl]!=LIBRE);
            tablero[filaAl][colAl] = piezas;;
        }

        for (int i = 0; i < 20; i++) {
            System.out.println();
        }
        char curas = 'P';
        for (int i = 0; i < 5; i++) {
            do{
                filaAl = aleat.nextInt(MAX_FILA_RATCHET);
                colAl = aleat.nextInt(MAX_COL_RETCHET);
            }while(tablero[filaAl][colAl]!=LIBRE);
            tablero[filaAl][colAl] = curas;
        }
        for (int i = 0; i < MAX_FILA_RATCHET; i++) {
            for (int j = 0; j < MAX_COL_RETCHET; j++) {
                System.out.print(tablero[i][j] + " ");
            }
            System.out.println();
        }
        // EMPIEZA EL JUEGO !!!
        int vidas = 10;
        int fin1 = 0;
        Scanner lector = new Scanner(System.in);
        do {
            System.out.println("Te puedes mover con WASD");
            System.out.print("Dime una letra para moverte por el tablero: ");
            char mov = lector.next().charAt(0);
            int filaAnteriorRacher = filaOficialRacher;
            int columnaAnteriorRacher = columnaOficioalRacher;
            switch (Character.toUpperCase(mov)){ // W, A, S o D
                case 'W':
                    filaOficialRacher --;
                    break;
                case 'A':
                    columnaOficioalRacher --;
                    break;
                case 'S':
                    filaOficialRacher ++;
                    break;
                case 'D':
                    columnaOficioalRacher ++;
                    break;
            }
            // Para que no se salga del trablero
            if(
                    filaOficialRacher < 0 ||
                            filaOficialRacher > 9 ||
                            columnaOficioalRacher < 0 ||
                            columnaOficioalRacher > 9
            ){
                System.out.println("Limite del tablero");
                filaOficialRacher = filaAnteriorRacher;
                columnaOficioalRacher = columnaAnteriorRacher;
            }else{
                if(tablero[filaOficialRacher][columnaOficioalRacher] == cajas){
                    System.out.println("Te has chocado con una caja");
                    filaOficialRacher = filaAnteriorRacher;
                    columnaOficioalRacher = columnaAnteriorRacher;
                }else if(tablero[filaOficialRacher][columnaOficioalRacher] == curas){
                    tablero[filaOficialRacher][columnaOficioalRacher] = RATCHET;
                    tablero[filaAnteriorRacher][columnaAnteriorRacher] = LIBRE;
                    vidas++;
                }else if(tablero[filaOficialRacher][columnaOficioalRacher] == robots){
                    tablero[filaOficialRacher][columnaOficioalRacher] = RATCHET;
                    tablero[filaAnteriorRacher][columnaAnteriorRacher] = LIBRE;
                    vidas--;
                }else if(tablero[filaOficialRacher][columnaOficioalRacher] == LIBRE){
                    tablero[filaOficialRacher][columnaOficioalRacher] = RATCHET;
                    tablero[filaAnteriorRacher][columnaAnteriorRacher] = LIBRE;
                }else if(tablero[filaOficialRacher][columnaOficioalRacher] == fin) {
                    if (piezascont==3){
                        tablero[filaOficialRacher][columnaOficioalRacher] = RATCHET;
                        tablero[filaAnteriorRacher][columnaAnteriorRacher] = LIBRE;
                        System.out.println("Fin del juego");
                        fin1 = 1;
                    }else {
                        System.out.println("Necestas tener las piezas de la nave");
                        filaOficialRacher = filaAnteriorRacher;
                        columnaOficioalRacher = columnaAnteriorRacher;
                    }
                } else if (tablero[filaOficialRacher][columnaOficioalRacher] == piezas){
                    tablero[filaOficialRacher][columnaOficioalRacher] = RATCHET;
                    tablero[filaAnteriorRacher][columnaAnteriorRacher] = LIBRE;
                    piezascont++;
                }

            }
            for (int i = 0; i < MAX_FILA_RATCHET; i++) {
                for (int j = 0; j < MAX_COL_RETCHET; j++) {
                    System.out.print(tablero[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println("Tienes " + vidas + " vidas");
            System.out.println("Tienes " + piezascont + " piezas de la nave");

        }while(vidas>0 && fin1 == 0) ;
    }
}
