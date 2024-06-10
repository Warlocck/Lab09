package Ejercicios;
public class Main3 {
    public static void main(String[] args) {
        BTree<Integer> tree = new BTree<>(3); // Orden 4

       	int[] keys = {412,312,320,480,520};


        for (int key : keys) {
            tree.insert(key);
        }
        // Buscar elementos en el árbol
        System.out.println(tree.search(312)); // true, mensaje esperado: "52 se encuentra en el nodo X en la posición Y"
        System.out.println(tree.search(520)); // false
        System.out.println(tree.toString());
        
                // Eliminar un valor del árbol
        tree.delete(480);

        // Imprimir el árbol después de eliminar el valor
        System.out.println("Arbol despues de eliminar:");
        System.out.println(tree);
    }
}
