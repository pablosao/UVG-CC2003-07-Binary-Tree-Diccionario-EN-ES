/**
 * Clase principal para la ejecucion del arbol binario
 * @author Pablo Sao
 * @version 18/03/2019
 */
public class TraductorArbol {

    final static String PRIMER_MENU =   "\n\t\tMenú"+
                                        "\n1). Cargar diccionario." +
                                        "\n2). Salir";

    final static String SEGUNDO_MENU =  "\n\t\tMenú"+
                                        "\n1). Traducir Documento." +
                                        "\n2). Cargar nuevo diccionario" +
                                        "\n3). Salir";

    /**
     *
     * @param args
     */
    public static void main(String[] args){

        int opcion = 0;


        while (true){
            System.out.println(PRIMER_MENU);
            System.out.print("\nIngrese número de opción: ");
            opcion = Keyboard.readInt();

            switch (opcion){
                case 1:
                    //Llamamos metodo para leer archivos y cargar al arbol.

                case 2:
                    System.exit(0);
            }
        }

    }
}