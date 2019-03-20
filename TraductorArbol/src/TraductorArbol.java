import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Clase principal para la ejecucion del arbol binario
 *
 * Referencia para Implementación de árbol binario
 *      https://www.geeksforgeeks.org/inorder-tree-traversal-without-recursion/
 *      https://www.geeksforgeeks.org/tree-traversals-inorder-preorder-and-postorder/
 *
 * @author Pablo Sao
 * @version 18/03/2019
 */

public class TraductorArbol {

    final static String PRIMER_MENU =   "\n\n\t\tMenú"+
                                        "\n1). Cargar diccionario." +
                                        "\n2). Salir";

    final static String SEGUNDO_MENU =  "\n\n\t\tMenú"+
                                        "\n1). Traducir Documento." +
                                        "\n2). Cargar nuevo diccionario" +
                                        "\n3). Salir";

    final static String DELIMITADOR = "\t";
    final static String DELIMITADOR_DICCIONARIO = ",";
    final static String DELIMITADOR_DOCUMENTO = "\t\\s+";

    public static class TreeNode
    {
        Association data;
        TreeNode left;
        TreeNode right;
        TreeNode(Association data)
        {
            this.data=data;
        }
    }

    // Recursive Solution
    public void inOrder(TreeNode root) {
        if(root !=  null) {
            inOrder(root.left);
            //Visit the node by Printing the node data
            System.out.printf("%d ",root.data);
            inOrder(root.right);
        }
    }

    /**
     * Clase principal
     * @param args argumentos de clase principal
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
                    (new TraductorArbol()).getDiccionario();
                    break;
                case 2:
                    System.exit(0);
            }
        }
    }

    public static ArrayList<Association> getAssociation(String path){
        //Proceso para cargar diccionario al arbol binario
        List diccionario = DataManager.getFileTokens(DELIMITADOR,path);
        //array con los datos del diccionario
        ArrayList<Association> datos_diccionario = new ArrayList<>();
        //Variable donde se almacena la llave y el valor
        String[] tempInfo = null;

        //Ciclo para llenar la lista
        for(int control = 0;control<diccionario.size();control++){
            tempInfo = diccionario.get(control).toString().toLowerCase().replaceAll("\\(","").replaceAll("\\)","").split(DELIMITADOR_DICCIONARIO);
            //Se agrega a la lista el objeto conciliación que usa la clase comparable
            datos_diccionario.add( new Association(tempInfo[0].toString(),tempInfo[1].toString()) );
        }

        return datos_diccionario;
    }

    public void getDiccionario(){

        try{
            System.out.print("\nIngrese el PATH del archivo para el diccionaro: ");
            String path = Keyboard.readString();

            if(DataManager.getExists(path)){

                ArrayList<Association> db_diccionario = getAssociation(path);
                //Creamos arbol para armarlo
                TraductorArbol tree = new TraductorArbol();

                //Construimos el arbol binario
                Node root = tree.constructTree(db_diccionario, db_diccionario.size());

                //lista con ordenamiento del arbol inOrder
                List<Association> order_list = new ArrayList<Association>();

                //obtenemos lista inOrder
                tree.Inorder(root,order_list);

                //Nos trasladamos al segundo menú
                runTraduccion(order_list);

            }
            else{
                System.out.println(String.format("\n\t\tEl archivo no ha se encontro.\n\t\tRuta: %s",path));
            }
        }
        catch (Exception e){
            System.out.println("\n\tExiste un inconveniente al cargar el diccionario.\n\tError: " + e.toString());
        }
    }

    /***
     * Traducción del documento, en base al diccionario precargado
     * @param diccionario diccionario precargado para traducción
     */
    public static void  runTraduccion(List<Association> diccionario){
        int opcion = 0;
        boolean ciclo = true;

        System.out.println("\n\t\tSe ha cargado correctamente el diccionario.");

        while(ciclo){
            System.out.println(SEGUNDO_MENU);
            System.out.print("\nIngrese el número de opción: ");
            opcion = Keyboard.readInt();

            switch (opcion){
                case 1:
                    System.out.print("\nIngrese el PATH del archivo a traducir: ");
                    String path = Keyboard.readString();

                    //Verificamos si existe el archivo
                    if(DataManager.getExists(path)){
                        String doc = DataManager.getDataFile(path);

                        //Mostramos documento a traducir
                        System.out.println(String.format("\n\nOriginal:\n\t%s",doc));

                        //Mostramos documento traducido
                        System.out.println(String.format("\nTraducción:\n\t%s",getTraduccion(diccionario,doc)));
                    }

                    break;
                case 2:
                    //regresamos a la opción de cargar una nueva definición de diccionario
                    ciclo = false;
                    break;
                case 3:
                    System.exit(0);
            }
        }
        //redirigimos al usuario para actualizar diccionario
        (new TraductorArbol()).getDiccionario();
    }

    /***
     * Definición del nodo del arbol
     * @param post Lista en base al cual se construira el nodo
     * @param n tamaño del nodo
     * @return arbol construido, de tipo Node
     */
    public Node constructTree(List<Association> post, int n)
    {
        // Last node is root
        Node root = new Node(post.get(n - 1));
        Stack<Node> s = new Stack<>();
        s.push(root);

        // Traverse from second last node
        for (int i = n - 2; i >= 0; --i) {

            Node x = new Node(post.get(i));

            // Keep popping nodes while top() of stack
            // is greater.
            Node temp = null;
            while (!s.isEmpty() && post.get(i).equals(s.peek().data))
                temp = s.pop();

            // Make x as left child of temp
            if (temp != null)
                temp.left = x;

                // Else make x as right of top
            else
                s.peek().right = x;
            s.push(x);
        }
        return root;
    }

    /***
     * Asignación de valores a nodo
     * @param node nodo en el que se almacenara la información
     * @param data dato a almacenar en el nodo
     */
    public void Inorder(Node node,List<Association> data)
    {
        if (node == null){
            return;
        }
        Inorder(node.left,data);
        data.add(node.data);
        Inorder(node.right,data);
    }

    /***
     * Despliegue de la llave y el valor de la clase Association
     * @param list lista a imprimir
     */
    public void printList(List<Association> list) {
        for (Association item : list) {
            System.out.println(item.llave + " - " + item.valor);
        }
    }

    /***
     * Traducción del contenido del documento en base al diccionario precargado
     * @param diccionario dicionario precargado con la traducciones
     * @param doc texto a tratucir
     * @return Texto traducido
     */
    public static String getTraduccion(List<Association> diccionario, String doc){
        //Leemos el archivo que deseamos traducir
        String[] documento = doc.replaceAll("\t","").split(" ");

        //Variable que contendra la traduccion
        String traduccion = "";

        //Recorremos e
        for(String palabra : documento){
            //variable para identificar si esta dentro del diccionario
            boolean sin_coincidencia = true;
            //recorremos el diccionario para obtener su traduccion
            for(int control = 0; control<diccionario.size();control++){
                if(palabra.equals(diccionario.get(control).llave)){
                    sin_coincidencia = false;
                    traduccion += String.format("%s ",diccionario.get(control).valor);
                }
            }

            if(sin_coincidencia){
                traduccion += String.format("*%s* ",palabra);
            }
        }
        //Si no hay palabras que coincidan dentro del diccionario mostramos mensaje a usuario
        if(traduccion.equals("")){
            traduccion = "\n\t\tNo hay coincidencias en el diccionario. \n\t\tActualice su diccionario para traducir";
        }

        return traduccion;
    }

}