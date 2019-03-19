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
                    getDiccionario();
                    break;
                case 2:
                    System.exit(0);
            }
        }

    }

    private static void getDiccionario(){


        try{
            System.out.print("\nIngrese el PATH del archivo para el diccionaro: ");
            String path = Keyboard.readString();
            if(DataManager.getExists(path)){

                //Proceso para cargar diccionario al arbol binario

                //Nos trasladamos al segundo menú
                getTraduccion();
            }
            else{
                System.out.println(String.format("\n\t\tEl archivo no ha se encontro.\n\t\tRuta: %s",path));
            }
        }
        catch (Exception e){
            System.out.println("Existe un inconveniente al cargar el diccionario.\nError: " + e.toString());
        }
    }

    public static void  getTraduccion(){
        int opcion = 0;
        boolean control = true;

        while(control){
            System.out.println(SEGUNDO_MENU);
            System.out.print("\nIngrese el número de opción: ");
            opcion = Keyboard.readInt();

            switch (opcion){
                case 1:

                    break;
                case 2:
                    control = false;
                    break;
                case 3:
                    System.exit(0);
            }
        }

        getDiccionario();
    }

}