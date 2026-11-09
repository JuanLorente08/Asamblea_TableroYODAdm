import java.util.Random;
import java.util.Scanner;

public class AmongUs {
    public static void main(String[]args ){
        int fila = 10;
        int columna = 10;
        char Tripulante = 'A';
        char libre = '*';

        char[][] matriz = new char[fila][columna];

        for (int i = 0; i < fila; i++) {
            for (int j = 0; j < columna; j++) {
                matriz[i][j] = libre;
            }
        }
        char fin = 'F';
        matriz [9][9] = fin;


        // 1 Tripulante
        Random aleat = new Random();
        int filaAl = -1;
        int colAl = -1;
        int filaOficialTripulante = -1;
        int columnaOficialTripulante = -1;

        // Tripulante
        for (int i = 0; i < 1; i++) {
            do{
                filaAl = aleat.nextInt(fila);
                colAl = aleat.nextInt(columna);
            }while(matriz[filaAl][colAl]!=libre);
            matriz[filaAl][colAl] = Tripulante;
        }

        // OFICIALIZO LAS POSICIONES DE MI Tripulante
        filaOficialTripulante = filaAl;
        columnaOficialTripulante = colAl;
        // FIN

        // Cajas
        char cajas = 'B';
        for (int i = 0; i < 10; i++) {
            do{
                filaAl = aleat.nextInt(fila);
                colAl = aleat.nextInt(columna);
            }while(matriz[filaAl][colAl]!=libre);
            matriz[filaAl][colAl] = cajas;
        }

        // Robots
        char impostores = 'I';
        for (int i = 0; i < 7; i++) {
            do{
                filaAl = aleat.nextInt(fila);
                colAl = aleat.nextInt(columna);
            }while(matriz[filaAl][colAl]!=libre);
            matriz[filaAl][colAl] = impostores;
        }

        // Piezas
        char tarjetas = 'T';
        int tarjetascont = 0;
        for (int i = 0; i < 3; i++) {
            do{
                filaAl = aleat.nextInt(fila);
                colAl = aleat.nextInt(columna);
            }while(matriz[filaAl][colAl]!=libre);
            matriz[filaAl][colAl] = tarjetas;;
        }

        for (int i = 0; i < 20; i++) {
            System.out.println();
        }
        char botiquines = 'P';
        for (int i = 0; i < 5; i++) {
            do{
                filaAl = aleat.nextInt(fila);
                colAl = aleat.nextInt(columna);
            }while(matriz[filaAl][colAl]!=libre);
            matriz[filaAl][colAl] = botiquines;
        }
        for (int i = 0; i < fila; i++) {
            for (int j = 0; j < columna; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
        // EMPIEZA EL JUEGO !!!
        int vidas = 10;
        int fin1 = 0;
        Scanner lector = new Scanner(System.in);
        do {
            System.out.println("Te puedes mover con WASD");
            System.out.print("Dime una letra para moverte por el matriz: ");
            char mov = lector.next().charAt(0);
            int filaAnteriorTripulante = filaOficialTripulante;
            int columnaAnteriorTripulante = columnaOficialTripulante;
            switch (mov){ // W, A, S o D
                case 'W':
                    filaOficialTripulante --;
                    break;
                case 'A':
                    columnaOficialTripulante --;
                    break;
                case 'S':
                    filaOficialTripulante ++;
                    break;
                case 'D':
                    columnaOficialTripulante ++;
                    break;
            }
            // Para que no se salga del trablero
            if(
                    filaOficialTripulante < 0 ||
                            filaOficialTripulante > 9 ||
                            columnaOficialTripulante < 0 ||
                            columnaOficialTripulante > 9
            ){
                System.out.println("Limite del matriz");
                filaOficialTripulante = filaAnteriorTripulante;
                columnaOficialTripulante = columnaAnteriorTripulante;
            }else{
                if(matriz[filaOficialTripulante][columnaOficialTripulante] == cajas){
                    System.out.println("Te has chocado con una caja");
                    filaOficialTripulante = filaAnteriorTripulante;
                    columnaOficialTripulante = columnaAnteriorTripulante;
                }else if(matriz[filaOficialTripulante][columnaOficialTripulante] == botiquines){
                    matriz[filaOficialTripulante][columnaOficialTripulante] = Tripulante;
                    matriz[filaAnteriorTripulante][columnaAnteriorTripulante] = libre;
                    vidas++;
                }else if(matriz[filaOficialTripulante][columnaOficialTripulante] == impostores){
                    matriz[filaOficialTripulante][columnaOficialTripulante] = Tripulante;
                    matriz[filaAnteriorTripulante][columnaAnteriorTripulante] = libre;
                    vidas--;
                }else if(matriz[filaOficialTripulante][columnaOficialTripulante] == libre){
                    matriz[filaOficialTripulante][columnaOficialTripulante] = Tripulante;
                    matriz[filaAnteriorTripulante][columnaAnteriorTripulante] = libre;
                }else if(matriz[filaOficialTripulante][columnaOficialTripulante] == fin) {
                    if (tarjetascont==3){
                        matriz[filaOficialTripulante][columnaOficialTripulante] = Tripulante;
                        matriz[filaAnteriorTripulante][columnaAnteriorTripulante] = libre;
                        System.out.println("Fin del juego");
                        fin1 = 1;
                    }else {
                        System.out.println("Necestas tener las tarjetas de la nave");
                        filaOficialTripulante = filaAnteriorTripulante;
                        columnaOficialTripulante = columnaAnteriorTripulante;
                    }
                } else if (matriz[filaOficialTripulante][columnaOficialTripulante] == tarjetas){
                    matriz[filaOficialTripulante][columnaOficialTripulante] = Tripulante;
                    matriz[filaAnteriorTripulante][columnaAnteriorTripulante] = libre;
                    tarjetascont++;
                }

            }
            for (int i = 0; i < fila; i++) {
                for (int j = 0; j < columna; j++) {
                    System.out.print(matriz[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println("Tienes " + vidas + " vidas");
            System.out.println("Tienes " + tarjetascont + " tarjetas de la nave");

        }while(vidas>0 && fin1 == 0) ;
    }
}
