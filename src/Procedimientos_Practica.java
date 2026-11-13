public class Procedimientos_Practica {
    static char[][] tablero = new char[10][10];
    static
    static void imprimirTablero(){
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero.length; j++) {
                System.out.print(tablero[i][j] + " ");
            }
            System.out.println();
        }
    }
    public static void main(String[]args){

        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero.length; j++) {
                tablero[i][j] = 'L';
            }
        }
        imprimirTablero();

    }
}
