import java.util.Random;
import java.util.Scanner;

public class Racher {
    public static void main(String[]args ){
        int MAX_FILA_RATCHET = 10;
        int MAX_COL_RETCHET = 10;
        char RATCHET = 'R';
        char LIBRE = '*';
        char DM = 'D';;

        char[][] tableroYoda = new char[MAX_FILA_RATCHET][MAX_COL_RETCHET];

        for (int i = 0; i < MAX_FILA_RATCHET; i++) {
            for (int j = 0; j < MAX_COL_RETCHET; j++) {
                tableroYoda[i][j] = LIBRE;
            }
        }

        // 1 RATCHET
        Random aleat = new Random();
        int filaAl = -1;
        int colAl = -1;
        int filaOficialYoda = -1;
        int columnaOficialYoda = -1;
        for (int i = 0; i < 1; i++) {
            do{
                filaAl = aleat.nextInt(MAX_FILA_RATCHET);
                colAl = aleat.nextInt(MAX_COL_RETCHET);
            }while(tableroYoda[filaAl][colAl]!=LIBRE);
            tableroYoda[filaAl][colAl] = RATCHET;
        }
        // OFICIALIZO LAS POSICIONES DE MI RATCHET
        filaOficialYoda = filaAl;
        columnaOficialYoda = colAl;
        // FIN
        char MURO = 'M';
        for (int i = 0; i < 10; i++) {
            do{
                filaAl = aleat.nextInt(MAX_FILA_RATCHET);
                colAl = aleat.nextInt(MAX_COL_RETCHET);
            }while(tableroYoda[filaAl][colAl]!=LIBRE);
            tableroYoda[filaAl][colAl] = MURO;
        }
        char VADER = 'V';
        for (int i = 0; i < 7; i++) {
            do{
                filaAl = aleat.nextInt(MAX_FILA_RATCHET);
                colAl = aleat.nextInt(MAX_COL_RETCHET);
            }while(tableroYoda[filaAl][colAl]!=LIBRE);
            tableroYoda[filaAl][colAl] = VADER;
        }
        for (int i = 0; i < 20; i++) {
            System.out.println();
        }
        char POCIMA = 'P';
        for (int i = 0; i < 5; i++) {
            do{
                filaAl = aleat.nextInt(MAX_FILA_RATCHET);
                colAl = aleat.nextInt(MAX_COL_RETCHET);
            }while(tableroYoda[filaAl][colAl]!=LIBRE);
            tableroYoda[filaAl][colAl] = POCIMA;
        }
        for (int i = 0; i < MAX_FILA_RATCHET; i++) {
            for (int j = 0; j < MAX_COL_RETCHET; j++) {
                System.out.print(tableroYoda[i][j] + " ");
            }
            System.out.println();
        }
        // EMPIEZA EL JUEGO !!!
        int vidas = 10;
        Scanner lector = new Scanner(System.in);
        do {
            System.out.println("Te puedes mover con WASD");
            System.out.print("Dime una letra para moverte por el tablero: ");
            char mov = lector.next().charAt(0);
            int filaAnteriorYoda = filaOficialYoda;
            int colAnteriorYoda = columnaOficialYoda;
            switch (mov){ // W, A, S o D
                case 'W':
                    filaOficialYoda --;
                    break;
                case 'A':
                    columnaOficialYoda --;
                    break;
                case 'S':
                    filaOficialYoda ++;
                    break;
                case 'D':
                    columnaOficialYoda ++;
                    break;
            }
            // Para que no se salga del trablero
            if(
                    filaOficialYoda < 0 ||
                            filaOficialYoda > 9 ||
                            columnaOficialYoda < 0 ||
                            columnaOficialYoda > 9
            ){
                System.out.println("Limite del tablero");
                filaOficialYoda = filaAnteriorYoda;
                columnaOficialYoda = colAnteriorYoda;
            }else{
                if(tableroYoda[filaOficialYoda][columnaOficialYoda] == MURO){
                    System.out.println("Te has chocado con un muro");
                    filaOficialYoda = filaAnteriorYoda;
                    columnaOficialYoda = colAnteriorYoda;
                }else if(tableroYoda[filaOficialYoda][columnaOficialYoda] == POCIMA){
                    tableroYoda[filaOficialYoda][columnaOficialYoda] = RATCHET;
                    tableroYoda[filaAnteriorYoda][colAnteriorYoda] = LIBRE;
                    vidas++;
                }else if(tableroYoda[filaOficialYoda][columnaOficialYoda] == VADER){
                    tableroYoda[filaOficialYoda][columnaOficialYoda] = RATCHET;
                    tableroYoda[filaAnteriorYoda][colAnteriorYoda] = LIBRE;
                    vidas--;
                }else if(tableroYoda[filaOficialYoda][columnaOficialYoda] == LIBRE){
                    tableroYoda[filaOficialYoda][columnaOficialYoda] = RATCHET;
                    tableroYoda[filaAnteriorYoda][colAnteriorYoda] = LIBRE;
                }

            }
            for (int i = 0; i < MAX_FILA_RATCHET; i++) {
                for (int j = 0; j < MAX_COL_RETCHET; j++) {
                    System.out.print(tableroYoda[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println("Tienes " + vidas + " vidas");

        }while(vidas>0);
    }
}
