public class Procedimientos_Practica {
    public static void main(String[] args) {
        char[] pasillo = {'M', '*', '*'};
        System.out.print(buscarLibre(pasillo));
    }

    static int buscarLibre(char[] pasillo) {
        for (int i = 0; i < pasillo.length; i++) {
            if (pasillo[i] == '*') {
                return i;
            }
        }
        return -1;
    }