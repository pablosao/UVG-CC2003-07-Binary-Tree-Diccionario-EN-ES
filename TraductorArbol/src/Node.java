/***
 * Clase para la asociación de nodos
 *
 * Referencia para Implementación de árbol binario
 *        https://www.geeksforgeeks.org/inorder-tree-traversal-without-recursion/
 *        https://www.geeksforgeeks.org/tree-traversals-inorder-preorder-and-postorder/
 *
 * @author Pablo Sao
 * @version 19/03/2019
 */
class Node {
    Association data;
    Node left, right;

    /***
     * asociación de nodos
     * @param data objeto Association para asignar a nodo
     */
    Node(Association data)
    {
        this.data = data;
        left = right = null;
    }
}