import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Clase principal para la ejecucion del arbol binario
 *
 * https://www.geeksforgeeks.org/inorder-tree-traversal-without-recursion/
 * https://www.geeksforgeeks.org/tree-traversals-inorder-preorder-and-postorder/
 *
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

    final static String DELIMITADOR = "\t";
    final static String DELIMITADOR_DICCIONARIO = ",";
    final static String DELIMITADOR_DOCUMENTO = "\t\\s+";

    public static class TreeNode
    {
        Conciliacion data;
        TreeNode left;
        TreeNode right;
        TreeNode(Conciliacion data)
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
                List diccionario = DataManager.getFileTokens(DELIMITADOR,path);
                //array con los datos del diccionario
                ArrayList<Conciliacion> datos_diccionario = new ArrayList<>();
                //Variable donde se almacena la llave y el valor
                String tempInfo[] = null;

                //Ciclo para llenar la lista
                for(int control = 0;control<diccionario.size();control++){
                    tempInfo = diccionario.get(control).toString().toLowerCase().replaceAll("\\(","").replaceAll("\\)","").split(DELIMITADOR_DICCIONARIO);
                    //Se agrega a la lista el objeto conciliación que usa la clase comparable
                    datos_diccionario.add( new Conciliacion(tempInfo[0].toString(),tempInfo[1].toString()) );
                }

                //Creamos arbol para armarlo
                TraductorArbol tree = new TraductorArbol();

                //Construimos el arbol binario
                Node root = tree.constructTree(datos_diccionario, datos_diccionario.size());

                //lista con ordenamiento del arbol inOrder
                List<Conciliacion> order_list = new ArrayList<Conciliacion>();

                //obtenemos lista inOrder
                tree.Inorder(root,order_list);
                //tree.printList(order_list);

                //Nos trasladamos al segundo menú
                getTraduccion(order_list);

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
     *
     * @param diccionario
     */
    public static void  getTraduccion(List<Conciliacion> diccionario){
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
                        System.out.println(String.format("Original:\n\t%s",doc));

                        //Mostramos documento traducido
                        System.out.println(String.format("Traducción:\n\t%s",getTraduccion(diccionario,doc)));
                    }

                    break;
                case 2:
                    ciclo = false;
                    break;
                case 3:
                    System.exit(0);
            }
        }

        getDiccionario();
    }

    Node constructTree(ArrayList<Conciliacion> post, int n)
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


    // A utility function to print inorder traversal
    // of a Binary Tree
    public void Inorder(Node node,List<Conciliacion> data)
        {
        if (node == null)
            return;
        Inorder(node.left,data);
        data.add(node.data);
        Inorder(node.right,data);
    }

    public void printList(List<Conciliacion> list) {
        for (Conciliacion item : list) {
            System.out.println(item.llave + " - " + item.valor);
        }
    }

    public static String getTraduccion(List<Conciliacion> diccionario, String doc){
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

        if(traduccion.equals("")){
            traduccion = "\n\t\tNo hay coincidencias en el diccionario. \n\t\tActualice su diccionario para traducir";
        }

        return traduccion;
    }

}