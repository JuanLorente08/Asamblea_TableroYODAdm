// 1 caballero (C)
// 10 rocas (B)
// 7 criaturas (E)
// 5 energias (P)
// 3 Sellos (T)
// Final

import java.util.Random;
import java.util.Scanner;
// Codigo añadido (no es el mio)

public class Hollow_night {
    public static void main(String[]args ){

        // Establezco los valores
        int fila = 10;
        int columna = 10;
        char Caballero = 'C';
        char libre = '*';

        //Defino la tablero y la relleno de *
        char[][] tablero = new char[fila][columna];
        for (int i = 0; i < fila; i++) {
            for (int j = 0; j < columna; j++) {
                tablero[i][j] = libre;
            }
        }
        char fin = 'F'; // Definir y poner la F del final (o de la capsula en este caso)
        tablero [9][9] = fin;


        // Definir valores random
        Random aleat = new Random();
        int filaAl = -1;
        int colAl = -1;
        int filaOficialCaballero = -1;
        int columnaOficialCaballero = -1;

        //DEFINO LAS VARIABLES
        // Caballero (1)
        for (int i = 0; i < 1; i++) {
            do{
                filaAl = aleat.nextInt(fila);
                colAl = aleat.nextInt(columna);
            }while(tablero[filaAl][colAl]!=libre);
            tablero[filaAl][colAl] = Caballero;
        }

        // OFICIALIZO LAS POSICIONES de mi Caballero
        filaOficialCaballero = filaAl;
        columnaOficialCaballero = colAl;
        // FIN

        // Rocas (10) Funcionan como muros (Aqui solo la defino)
        char rocas = 'B';
        for (int i = 0; i < 10; i++) {
            do{
                filaAl = aleat.nextInt(fila);
                colAl = aleat.nextInt(columna);
            }while(tablero[filaAl][colAl]!=libre);
            tablero[filaAl][colAl] = rocas;
        }

        // Criaturas (7) quitan 1 vida al jugador
        char criaturas = 'I';
        for (int i = 0; i < 7; i++) {
            do{
                filaAl = aleat.nextInt(fila);
                colAl = aleat.nextInt(columna);
            }while(tablero[filaAl][colAl]!=libre);
            tablero[filaAl][colAl] = criaturas;
        }

        // Piezas (3) Necesarias para poder llegar a la capsula
        char sellos = 'T';
        int tarjetascont = 0;
        for (int i = 0; i < 3; i++) {
            do{
                filaAl = aleat.nextInt(fila);
                colAl = aleat.nextInt(columna);
            }while(tablero[filaAl][colAl]!=libre);
            tablero[filaAl][colAl] = sellos;;
        }

        // Energias/Botiquines (5) Suman una vida (Aqui solo defino)
        char energias = 'P';
        for (int i = 0; i < 5; i++) {
            do{
                filaAl = aleat.nextInt(fila);
                colAl = aleat.nextInt(columna);
            }while(tablero[filaAl][colAl]!=libre);
            tablero[filaAl][colAl] = energias;
        }

        // Imprimo la tablero
        for (int i = 0; i < fila; i++) {
            for (int j = 0; j < columna; j++) {
                System.out.print(tablero[i][j] + " ");
            }
            System.out.println();
        }


        // Controles del juego
        // Definl las vidas y importo el teclado
        int vidas = 10;
        int fin1 = 0;
        Scanner lector = new Scanner(System.in);

        do {
            // Genero el movimiento
            System.out.println("Te puedes mover con WASD");
            System.out.print("Dime una letra para moverte por el tablero: ");
            char mov = lector.next().charAt(0);
            int filaAnteriorCaballero = filaOficialCaballero; // Hago esto para tener registri y poder guardarme la posición anterior del personaje
            int columnaAnteriorCaballero = columnaOficialCaballero; // lo mismo que en linea arriba pero para columnas
            switch (Character.toUpperCase(mov)){ // Defino para que con WASD el personaje A se mueva por el tablero // Tambien se puede hacer con else y else if
                case 'W':
                    filaOficialCaballero --;
                    break;
                case 'A':
                    columnaOficialCaballero --;
                    break;
                case 'S':
                    filaOficialCaballero ++;
                    break;
                case 'D':
                    columnaOficialCaballero ++;
                    break;
            }

            // Regas para que no se salga ler pesonaje A del tablero
            if(
                    filaOficialCaballero < 0 ||
                            filaOficialCaballero > 9 ||
                            columnaOficialCaballero < 0 ||
                            columnaOficialCaballero > 9
            ){
                System.out.println("Limite del tablero");
                filaOficialCaballero = filaAnteriorCaballero;
                columnaOficialCaballero = columnaAnteriorCaballero;
            }else{ // A partir de aqui lo que hacemos es añadir los demas valores (muros, criaturas, energias...)
                //Muro
                if(tablero[filaOficialCaballero][columnaOficialCaballero] == rocas){
                    System.out.println("Te has chocado con una caja");
                    filaOficialCaballero = filaAnteriorCaballero;
                    columnaOficialCaballero = columnaAnteriorCaballero;
                    //Botiquines (para que te sumen 1 vida)
                }else if (tablero[filaOficialCaballero][columnaOficialCaballero] == energias){
                    tablero[filaOficialCaballero][columnaOficialCaballero] = Caballero;
                    tablero[filaAnteriorCaballero][columnaAnteriorCaballero] = libre;
                    vidas++;
                    // Impostores (Para que te quiten vidas)
                }else if(tablero[filaOficialCaballero][columnaOficialCaballero] == criaturas){
                    tablero[filaOficialCaballero][columnaOficialCaballero] = Caballero;
                    tablero[filaAnteriorCaballero][columnaAnteriorCaballero] = libre;
                    vidas--;
                    // Movimiento normal
                }else if(tablero[filaOficialCaballero][columnaOficialCaballero] == libre){
                    tablero[filaOficialCaballero][columnaOficialCaballero] = Caballero;
                    tablero[filaAnteriorCaballero][columnaAnteriorCaballero] = libre;
                    // Una vez llegado al final (si tienes las 3 sellos)
                }else if(tablero[filaOficialCaballero][columnaOficialCaballero] == fin) {
                    if (tarjetascont==3){
                        tablero[filaOficialCaballero][columnaOficialCaballero] = Caballero;
                        tablero[filaAnteriorCaballero][columnaAnteriorCaballero] = libre;
                        System.out.println("Fin del juego");
                        fin1 = 1;
                    }else {
                        System.out.println("Necestas tener los sellos");
                        filaOficialCaballero = filaAnteriorCaballero;
                        columnaOficialCaballero = columnaAnteriorCaballero;
                    }
                    // Valor para que cojas una tarjeta una vez que estas encima de ella
                } else if (tablero[filaOficialCaballero][columnaOficialCaballero] == sellos){
                    tablero[filaOficialCaballero][columnaOficialCaballero] = Caballero;
                    tablero[filaAnteriorCaballero][columnaAnteriorCaballero] = libre;
                    tarjetascont++;
                }

                //Imprimir la tablero
            }
            for (int i = 0; i < fila; i++) {
                for (int j = 0; j < columna; j++) {
                    System.out.print(tablero[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println("Tienes " + vidas + " vidas");
            System.out.println("Tienes " + tarjetascont + " sellos de la nave");

        }while(vidas>0 && fin1 == 0) ;
    }
}
