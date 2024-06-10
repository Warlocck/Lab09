package Ejercicios;
public class Main {
    public static void main(String[] args) {
        BTree<Integer> tree = new BTree<>(4); // Orden 4

        tree.insert(31);
        tree.insert(12);
        tree.insert(41);
        tree.insert(3);
        tree.insert(19);
        tree.insert(25);
        tree.insert(63);
        tree.insert(10);
        tree.insert(49);
        tree.insert(16);
        tree.insert(13);
        tree.insert(22);
        tree.insert(52);
        tree.insert(55);
        tree.insert(60);
        tree.insert(28);
        tree.insert(67);
        tree.insert(57);
        tree.insert(33);
        tree.insert(62);
        tree.insert(70);
        tree.insert(38);
        tree.insert(72);
        tree.insert(40);

        System.out.println(tree.search(52)); 
        System.out.println(tree.search(67)); 
        System.out.println(tree.search(31)); 
        System.out.println(tree.search(55)); 
        System.out.println(tree.toString());
        
                // Eliminar un valor del árbol
        tree.delete(31);

        // Imprimir el árbol después de eliminar el valor
        System.out.println("Arbol despues de eliminar:");
        System.out.println(tree);
    }
}
