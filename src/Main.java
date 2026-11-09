import java.util.Random;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        int MAX_FIL_YODA = 10;
        int MAX_COL_YODA = 10;
        int MAX_FIL_DM = 10;
        int MAX_COL_DM = 10;
        char YODA = 'Y';
        char LIBRE = 'L';
        char DM = 'D';
        boolean yodaActive = true;
        boolean dmActive = false;
        // NO VOY A UTILIZAR FUNCIONES/PROCEDIMIENTOS
        char[][] tableroYoda = new char[MAX_FIL_YODA][MAX_COL_YODA];
        char[][] tableroDM = new char[MAX_FIL_DM][MAX_COL_DM];
        // tableroYoda
        char[][] tableroACTIVO =null;
        /*if((yodaActive==true) && (dmActive==false)){
            tableroACTIVO = tableroYoda;
        }else{
            tableroACTIVO = tableroDM;
        }*/
        if((yodaActive==true) && (dmActive==false)){

            for (int i = 0; i < MAX_FIL_YODA; i++) {
                for (int j = 0; j < MAX_COL_YODA; j++) {
                    tableroYoda[i][j] = LIBRE;
                }
            }

            // 1 YODA
            Random aleat = new Random();
            int filaAl = -1;
            int colAl = -1;
            int filaOficialYoda = -1;
            int columnaOficialYoda = -1;
            for (int i = 0; i < 1; i++) {
                do{
                    filaAl = aleat.nextInt(MAX_FIL_YODA);
                    colAl = aleat.nextInt(MAX_COL_YODA);
                }while(tableroYoda[filaAl][colAl]!=LIBRE);
                tableroYoda[filaAl][colAl] = YODA;
            }
            // OFICIALIZO LAS POSICIONES DE MI YODA
            filaOficialYoda = filaAl;
            columnaOficialYoda = colAl;
            // FIN
            char MURO = 'M';
            for (int i = 0; i < 10; i++) {
                do{
                    filaAl = aleat.nextInt(MAX_FIL_YODA);
                    colAl = aleat.nextInt(MAX_COL_YODA);
                }while(tableroYoda[filaAl][colAl]!=LIBRE);
                tableroYoda[filaAl][colAl] = MURO;
            }
            char VADER = 'V';
            for (int i = 0; i < 7; i++) {
                do{
                    filaAl = aleat.nextInt(MAX_FIL_YODA);
                    colAl = aleat.nextInt(MAX_COL_YODA);
                }while(tableroYoda[filaAl][colAl]!=LIBRE);
                tableroYoda[filaAl][colAl] = VADER;
            }
            for (int i = 0; i < 20; i++) {
                System.out.println();
            }
            char POCIMA = 'P';
            for (int i = 0; i < 5; i++) {
                do{
                    filaAl = aleat.nextInt(MAX_FIL_YODA);
                    colAl = aleat.nextInt(MAX_COL_YODA);
                }while(tableroYoda[filaAl][colAl]!=LIBRE);
                tableroYoda[filaAl][colAl] = POCIMA;
            }
            for (int i = 0; i < MAX_FIL_YODA; i++) {
                for (int j = 0; j < MAX_COL_YODA; j++) {
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
                        tableroYoda[filaOficialYoda][columnaOficialYoda] = YODA;
                        tableroYoda[filaAnteriorYoda][colAnteriorYoda] = LIBRE;
                        vidas++;
                    }else if(tableroYoda[filaOficialYoda][columnaOficialYoda] == VADER){
                        tableroYoda[filaOficialYoda][columnaOficialYoda] = YODA;
                        tableroYoda[filaAnteriorYoda][colAnteriorYoda] = LIBRE;
                        vidas--;
                    }else if(tableroYoda[filaOficialYoda][columnaOficialYoda] == LIBRE){
                        tableroYoda[filaOficialYoda][columnaOficialYoda] = YODA;
                        tableroYoda[filaAnteriorYoda][colAnteriorYoda] = LIBRE;
                    }

                }
                for (int i = 0; i < MAX_FIL_YODA; i++) {
                    for (int j = 0; j < MAX_COL_YODA; j++) {
                        System.out.print(tableroYoda[i][j] + " ");
                    }
                    System.out.println();
                }
                System.out.println("Tienes " + vidas + " vidas");

            }while(vidas>0);

            yodaActive = false;
            dmActive = true;
        }else{

            for (int i = 0; i < MAX_FIL_DM; i++) {
                for (int j = 0; j < MAX_COL_DM; j++) {
                    tableroDM[i][j] = LIBRE;
                }
            }

            // 1 YODA
            Random aleat = new Random();
            int filaAl = -1;
            int colAl = -1;
            int filaOficialDM = -1;
            int columnaOficialDM = -1;
            for (int i = 0; i < 1; i++) {
                do{
                    filaAl = aleat.nextInt(MAX_FIL_DM);
                    colAl = aleat.nextInt(MAX_COL_DM);
                }while(tableroDM[filaAl][colAl]!=LIBRE);
                tableroDM[filaAl][colAl] = YODA;
            }
            // OFICIALIZO LAS POSICIONES DE MI YODA
            filaOficialDM = filaAl;
            columnaOficialDM = colAl;
            // FIN
            char MURO = 'M';
            for (int i = 0; i < 10; i++) {
                do{
                    filaAl = aleat.nextInt(MAX_FIL_DM);
                    colAl = aleat.nextInt(MAX_COL_DM);
                }while(tableroDM[filaAl][colAl]!=LIBRE);
                tableroDM[filaAl][colAl] = MURO;
            }
            char VADER = 'V';
            for (int i = 0; i < 7; i++) {
                do{
                    filaAl = aleat.nextInt(MAX_FIL_DM);
                    colAl = aleat.nextInt(MAX_COL_DM);
                }while(tableroDM[filaAl][colAl]!=LIBRE);
                tableroDM[filaAl][colAl] = VADER;
            }
            for (int i = 0; i < 20; i++) {
                System.out.println();
            }
            char POCIMA = 'P';
            for (int i = 0; i < 5; i++) {
                do{
                    filaAl = aleat.nextInt(MAX_FIL_DM);
                    colAl = aleat.nextInt(MAX_COL_DM);
                }while(tableroDM[filaAl][colAl]!=LIBRE);
                tableroDM[filaAl][colAl] = POCIMA;
            }
            for (int i = 0; i < MAX_FIL_DM; i++) {
                for (int j = 0; j < MAX_COL_DM; j++) {
                    System.out.print(tableroDM[i][j] + " ");
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
                int filaAnteriorDM = filaOficialDM;
                int columnaAnteriorDM = columnaOficialDM;
                switch (mov){ // W, A, S o D
                    case 'W':
                        filaOficialDM --;
                        break;
                    case 'A':
                        columnaOficialDM --;
                        break;
                    case 'S':
                        filaOficialDM ++;
                        break;
                    case 'D':
                        columnaOficialDM ++;
                        break;
                }
                // Para que no se salga del trablero
                if(
                        filaOficialDM < 0 ||
                                filaOficialDM > 9 ||
                                columnaOficialDM < 0 ||
                                columnaOficialDM > 9
                ){
                    System.out.println("Limite del tablero");
                    filaOficialDM = filaAnteriorDM;
                    columnaOficialDM = columnaAnteriorDM;
                }else{
                    if(tableroDM[filaOficialDM][columnaOficialDM] == MURO){
                        System.out.println("Te has chocado con un muro");
                        filaOficialDM = filaAnteriorDM;
                        columnaOficialDM = columnaAnteriorDM;
                    }else if(tableroDM[filaOficialDM][columnaOficialDM] == POCIMA){
                        tableroDM[filaOficialDM][columnaOficialDM] = YODA;
                        tableroDM[filaAnteriorDM][columnaAnteriorDM] = LIBRE;
                        vidas++;
                    }else if(tableroDM[filaOficialDM][columnaOficialDM] == VADER){
                        tableroDM[filaOficialDM][columnaOficialDM] = YODA;
                        tableroDM[filaAnteriorDM][columnaAnteriorDM] = LIBRE;
                        vidas--;
                    }else if(tableroDM[filaOficialDM][columnaOficialDM] == LIBRE){
                        tableroDM[filaOficialDM][columnaOficialDM] = YODA;
                        tableroDM[filaAnteriorDM][columnaAnteriorDM] = LIBRE;
                    }

                }
                for (int i = 0; i < MAX_FIL_DM; i++) {
                    for (int j = 0; j < MAX_COL_DM; j++) {
                        System.out.print(tableroDM[i][j] + " ");
                    }
                    System.out.println();
                }
                System.out.println("Tienes " + vidas + " vidas");

            }while(vidas>0);

            yodaActive = true;
            dmActive = false;
        }












    }
}