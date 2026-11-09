import java.util.Random;
import java.util.Scanner;
// Codigo añadido (no es el mio)

public class AmongUs {
    public static void main(String[]args ){
        // Establezco los valores
        int fila = 10;
        int columna = 10;
        char Tripulante = 'A';
        char libre = '*';

        //Defino la matriz y la relleno de *
        char[][] matriz = new char[fila][columna];
        for (int i = 0; i < fila; i++) {
            for (int j = 0; j < columna; j++) {
                matriz[i][j] = libre;
            }
        }
        char fin = 'F'; // Definir y poner la F del final (o de la capsula en este caso)
        matriz [9][9] = fin;


        // Definir valores random
        Random aleat = new Random();
        int filaAl = -1;
        int colAl = -1;
        int filaOficialTripulante = -1;
        int columnaOficialTripulante = -1;

        //DEFINO LAS VARIABLES
        // Tripulante (1)
        for (int i = 0; i < 1; i++) {
            do{
                filaAl = aleat.nextInt(fila);
                colAl = aleat.nextInt(columna);
            }while(matriz[filaAl][colAl]!=libre);
            matriz[filaAl][colAl] = Tripulante;
        }

        // OFICIALIZO LAS POSICIONES de mi Tripulante
        filaOficialTripulante = filaAl;
        columnaOficialTripulante = colAl;
        // FIN

        // Cajas (10) Funcionan como muros (Aqui solo la defino)
        char cajas = 'B';
        for (int i = 0; i < 10; i++) {
            do{
                filaAl = aleat.nextInt(fila);
                colAl = aleat.nextInt(columna);
            }while(matriz[filaAl][colAl]!=libre);
            matriz[filaAl][colAl] = cajas;
        }

        // Robots (7) quitan 1 vida al jugador
        char impostores = 'I';
        for (int i = 0; i < 7; i++) {
            do{
                filaAl = aleat.nextInt(fila);
                colAl = aleat.nextInt(columna);
            }while(matriz[filaAl][colAl]!=libre);
            matriz[filaAl][colAl] = impostores;
        }

        // Piezas (3) Necesarias para poder llegar a la capsula
        char tarjetas = 'T';
        int tarjetascont = 0;
        for (int i = 0; i < 3; i++) {
            do{
                filaAl = aleat.nextInt(fila);
                colAl = aleat.nextInt(columna);
            }while(matriz[filaAl][colAl]!=libre);
            matriz[filaAl][colAl] = tarjetas;;
        }

        // Botiquines (5) Suman una vida (Aqui solo defino)
        char botiquines = 'P';
        for (int i = 0; i < 5; i++) {
            do{
                filaAl = aleat.nextInt(fila);
                colAl = aleat.nextInt(columna);
            }while(matriz[filaAl][colAl]!=libre);
            matriz[filaAl][colAl] = botiquines;
        }

        // Imprimo la matriz
        for (int i = 0; i < fila; i++) {
            for (int j = 0; j < columna; j++) {
                System.out.print(matriz[i][j] + " ");
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
            System.out.print("Dime una letra para moverte por el matriz: ");
            char mov = lector.next().charAt(0);
            int filaAnteriorTripulante = filaOficialTripulante; // Hago esto para tener registri y poder guardarme la posición anterior del personaje
            int columnaAnteriorTripulante = columnaOficialTripulante; // lo mismo que en linea arriba pero para columnas
            switch (mov){ // Defino para que con WASD el personaje A se mueva por el tablero // Tambien se puede hacer con else y else if
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

            // Regas para que no se salga ler pesonaje A del tablero
            if(
                    filaOficialTripulante < 0 ||
                            filaOficialTripulante > 9 ||
                            columnaOficialTripulante < 0 ||
                            columnaOficialTripulante > 9
            ){
                System.out.println("Limite del matriz");
                filaOficialTripulante = filaAnteriorTripulante;
                columnaOficialTripulante = columnaAnteriorTripulante;
            }else{ // A partir de aqui lo que hacemos es añadir los demas valores (muros, impostores, botiquines...)
                //Muro
                if(matriz[filaOficialTripulante][columnaOficialTripulante] == cajas){
                    System.out.println("Te has chocado con una caja");
                    filaOficialTripulante = filaAnteriorTripulante;
                    columnaOficialTripulante = columnaAnteriorTripulante;
                //Botiquines (para que te sumen 1 vida)
                }else if (matriz[filaOficialTripulante][columnaOficialTripulante] == botiquines){
                    matriz[filaOficialTripulante][columnaOficialTripulante] = Tripulante;
                    matriz[filaAnteriorTripulante][columnaAnteriorTripulante] = libre;
                    vidas++;
                // Impostores (Para que te quiten vidas)
                }else if(matriz[filaOficialTripulante][columnaOficialTripulante] == impostores){
                    matriz[filaOficialTripulante][columnaOficialTripulante] = Tripulante;
                    matriz[filaAnteriorTripulante][columnaAnteriorTripulante] = libre;
                    vidas--;
                // Movimiento normal
                }else if(matriz[filaOficialTripulante][columnaOficialTripulante] == libre){
                    matriz[filaOficialTripulante][columnaOficialTripulante] = Tripulante;
                    matriz[filaAnteriorTripulante][columnaAnteriorTripulante] = libre;
                // Una vez llegado al final (si tienes las 3 tarjetas)
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
                // Valor para que cojas una tarjeta una vez que estas encima de ella
                } else if (matriz[filaOficialTripulante][columnaOficialTripulante] == tarjetas){
                    matriz[filaOficialTripulante][columnaOficialTripulante] = Tripulante;
                    matriz[filaAnteriorTripulante][columnaAnteriorTripulante] = libre;
                    tarjetascont++;
                }

            //Imprimir la matriz
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
